package rwbykit.flowable.engine.runtime.actuator.node;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.enumeration.Phase;
import rwbykit.flowable.engine.enumeration.TaskType;
import rwbykit.flowable.engine.factory.ActuatorFactory;
import rwbykit.flowable.engine.factory.SelectorFactory;
import rwbykit.flowable.engine.runtime.LoggerHelper;
import rwbykit.flowable.engine.runtime.actuator.task.AbstractTaskActuator;
import rwbykit.flowable.engine.runtime.selector.TaskSelector;
import rwbykit.flowable.model.Task;

import java.util.Objects;

/**
 * 自动节点执行器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 下午2:35:43
 */
public class AutoNodeActuator extends AbstractNodeActuator {

    private final static Logger logger = LoggerFactory.getLogger(AutoNodeActuator.class);

    @Override
    public Context nodeExecute(Context context) throws FlowableException {
        AbstractTaskActuator actuator = ActuatorFactory.factory().getTaskActuator(TaskType.DEFAULT.toString());
        Observable.create((ObservableOnSubscribe<Task>) emitter -> {
            TaskSelector taskSelector = SelectorFactory.factory().getSelector(Constants.SELECTOR_TASK);
            Task task = taskSelector.select(context);
            if (Objects.nonNull(task) && !emitter.isDisposed()) {
                context.addParam("taskId", task.getId());
                emitter.onNext(task);
            } else if (Objects.isNull(task)) {
                emitter.onComplete();
            }
        }).doOnNext(task -> {
            logger.info(LoggerHelper.actuator_node_startMessage(context));
            try {
                context.addParam(Constants.TASK_ID, task.getId());
                actuator.execute(context);
            } catch (Exception e) {
                this.handleException(context, Phase.TASK, e);
            }
            logger.info(LoggerHelper.actuator_node_endMessage(context));
        }).doOnSubscribe(disposable -> {
            if (context.getCurrentInstance().isError()) {
                disposable.dispose();
            }
        }).doOnError((e) -> {
            this.handleException(context, Phase.TASK, e);
        }).doOnComplete(() -> {
            context.getRuntimeService().getApprovalService().initSystemAutoApproval(
                    context.getCurrentInstance().getProcessInstanceId(),
                    context.getCurrentInstance().getNodeInstanceId(), "1", "系统默认通过!");
        }).publish().connect();

        if (context.getCurrentInstance().isError()) {
            throw new FlowableException(context.getCurrentInstance().errorCode(), context.getCurrentInstance().errorMessage());
        }
        return context;
    }

}
