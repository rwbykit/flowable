package rwbykit.flowable.engine.runtime.actuator.task;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.Notification;
import rwbykit.flowable.core.Result;
import rwbykit.flowable.core.current.CurrentInstance;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.core.factory.ThreadPoolFactory;
import rwbykit.flowable.core.model.enumeration.ResultStorageType;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.model.runtime.TaskInstance;
import rwbykit.flowable.core.util.Utils;
import rwbykit.flowable.engine.enumeration.TaskScheduleType;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.notice.NotificationHelper;
import rwbykit.flowable.engine.notice.TaskNotice;
import rwbykit.flowable.engine.runtime.InitializeService;
import rwbykit.flowable.engine.runtime.actuator.AbstractActuator;
import rwbykit.flowable.engine.runtime.parameter.ParameterHelper;
import rwbykit.flowable.engine.runtime.parameter.TaskParameter;
import rwbykit.flowable.engine.runtime.runner.Runner;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * 任务执行
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 上午10:37:37
 */
public abstract class AbstractTaskActuator extends AbstractActuator<TaskNotice> implements InitializeService {

    /**
     * 执行任务
     *
     * @param context
     */
    protected Context taskExecute(Context context) throws FlowableException {
        Task task = context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), context.getCurrentInstance().getTaskId());
        TaskParameter taskParameter = ParameterHelper.taskParameter(context, task.getFields());
        boolean isAsync = isAsyncTask(task);

        final Context ctx = isAsync ? context.cloneContext() : context;

        Observable<Result<?>> observable = Observable.fromCallable((Callable<Result<?>>) () -> {
            Runner<TaskParameter, Result<?>> runner = GenericObjectFactory.factory().getRunner(task.getRunMode());
            Result<?> runnerResult = runner.run(task.getRunValue(), taskParameter);
            if (!runnerResult.isSuccess()) {
                throw new FlowableException(runnerResult.errorCode(), runnerResult.errorMessage());
            }
            return runnerResult;
        }).doAfterNext(result -> {
            ctx.getCurrentInstance().setTaskStatus(Constants.STATUS_END);
            if (ResultStorageType.TEMPORARY.toString().equals(task.getResultStorageType())) {
                ctx.addParam(task.getResultKey(), result.getResult());
            } else if (ResultStorageType.FOREVER.toString().equals(task.getResultStorageType())) {
                ctx.addContextParam(task.getResultKey(), result.getResult());
            }
        }).doOnError((e) -> {
            String errorMessage = Utils.formatMessage("任务实例[{}]执行当前任务出现异常!, case by:{}", ctx.getCurrentInstance().getTaskInstanceId(), e.getMessage());
            this.handleException(ctx, Phase.TASK, e, errorMessage);
        }).doFinally(() -> afterSet(ctx));

        if (isAsync) {
            observable.subscribeOn(Schedulers.from(ThreadPoolFactory.factory().getExecutor()));
        }
        observable.publish().connect();

        if (ctx.getCurrentInstance().isError()) {
            throw new FlowableException("任务实例[{}]执行当前任务出现异常!", ctx.getCurrentInstance().getTaskId());
        }

        return context;
    }

    /**
     * 判断当前任务是否为异步执行
     *
     * @param task
     * @return
     */
    private boolean isAsyncTask(Task task) {
        return TaskScheduleType.compare(TaskScheduleType.ASYNC, task.getScheduleType());
    }

    @Override
    public List<Notification<TaskNotice>> getNotifications(Context context) {
        Task task = context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), context.getParam(Constants.TASK_ID));
        return NotificationHelper.getNotificationsByType(task.getListeners());
    }

    @Override
    protected TaskNotice assembleNotify(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        Task task = context.getProcessConfigService().getTask(instance.getNodeId(), context.getParam(Constants.TASK_ID));
        return TaskNotice.builder()
                .nodeId(instance.getNodeId())
                .nodeInstanceId(instance.getNodeInstanceId())
                .processId(instance.getProcessId())
                .processInstanceId(instance.getProcessInstanceId())
                .taskId(task.getId())
                .taskName(task.getName())
                .taskStatus(instance.getTaskStatus())
                .build();
    }

    @Override
    public void initialize(Context context) {
        Task task = context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), context.getParam(Constants.TASK_ID));
        TaskInstance taskInstance = context.getRuntimeService().getTaskService().initialize(context.getCurrentInstance().getNodeInstanceId(), task);
        context.getCurrentInstance().setCurrentTask(task.getId(), taskInstance.getTaskInstanceId(), taskInstance.getTaskStatus());
    }

    public void afterSet(Context context) {
        context.getRuntimeService().getTaskService().modifyInstanceStatus(context.getCurrentInstance().getTaskInstanceId(),
                context.getCurrentInstance().getTaskStatus(), context.getCurrentInstance().errorCode(), context.getCurrentInstance().errorMessage());
    }

    @Override
    public final Phase getSupportedType() {
        return Phase.TASK;
    }

}
