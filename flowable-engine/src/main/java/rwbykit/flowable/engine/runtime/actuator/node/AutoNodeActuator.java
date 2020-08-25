package rwbykit.flowable.engine.runtime.actuator.node;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.engine.enumeration.TaskType;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.runtime.LoggerHelper;
import rwbykit.flowable.engine.runtime.actuator.task.AbstractTaskActuator;
import rwbykit.flowable.engine.runtime.selector.TaskSelector;
import rwbykit.flowable.core.model.parser.Task;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自动节点执行器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 下午2:35:43
 */
@Type(category = Constants.TYPE_ACTUATOR_NODE, type = "Auto")
public class AutoNodeActuator extends AbstractNodeActuator {

    private final static Logger logger = LoggerFactory.getLogger(AutoNodeActuator.class);

    @Override
    public Context nodeExecute(Context context) throws FlowableException {
        AbstractTaskActuator actuator = GenericObjectFactory.factory().getTaskActuator(TaskType.DEFAULT.toString());
        AtomicReference<Context> ctx = new AtomicReference<>(context);
        Observable.create((ObservableOnSubscribe<Task>) emitter -> {
            TaskSelector taskSelector = GenericObjectFactory.factory().getSelector(Constants.SELECTOR_TASK);
            Task task = taskSelector.select(ctx.get());
            if (Objects.nonNull(task) && !emitter.isDisposed()) {
                ctx.get().addParam("taskId", task.getId());
                emitter.onNext(task);
            } else if (Objects.isNull(task)) {
                emitter.onComplete();
            }
        }).doOnNext(task -> {
            logger.info(LoggerHelper.actuator_node_startMessage(ctx.get()));
            try {
                ctx.get().addParam(Constants.TASK_ID, task.getId());
                ctx.set(super.schedule(actuator, ctx.get(), "sync"));
            } catch (Exception e) {
                this.handleException(ctx.get(), Phase.TASK, e);
            }
            logger.info(LoggerHelper.actuator_node_endMessage(ctx.get()));
        }).doOnSubscribe(disposable -> {
            if (ctx.get().getCurrentInstance().isError()) {
                disposable.dispose();
            }
        }).doOnError((e) -> {
            this.handleException(ctx.get(), Phase.TASK, e);
        }).doOnComplete(() -> {
            ctx.get().getRuntimeService().getApprovalService().initSystemAutoApproval(
                    ctx.get().getCurrentInstance().getProcessInstanceId(),
                    ctx.get().getCurrentInstance().getNodeInstanceId(), "1", "系统默认通过!");
        }).publish().connect();
        context = ctx.get();
        if (context.getCurrentInstance().isError()) {
            throw new FlowableException(context.getCurrentInstance().errorCode(), context.getCurrentInstance().errorMessage());
        }
        return context;
    }

}
