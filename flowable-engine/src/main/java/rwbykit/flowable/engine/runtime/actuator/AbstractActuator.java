package rwbykit.flowable.engine.runtime.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Actuator;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Notification;
import rwbykit.flowable.engine.enumeration.Phase;
import rwbykit.flowable.engine.factory.support.Factory;
import rwbykit.flowable.engine.runtime.scheduler.AbstractProcessScheduler;

import java.util.List;

/**
 * @param <Notify>
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月18日 上午8:28:13
 */
public abstract class AbstractActuator<Notify> implements Actuator<Context, Context> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractActuator.class);

    @Override
    public Context execute(Context context) throws FlowableException {
        beforeNotify(context);
        try {
            context = doExecute(context);
        } catch (Exception e) {
            exceptionNotify(context, e);
            throw handleException(e);
        }
        afterNotify(context);
        return context;
    }

    /**
     * 异常处理
     *
     * @param e
     * @return
     */
    protected FlowableException handleException(Throwable e) {
        return handleException(e, e.getMessage());
    }

    protected FlowableException handleException(Throwable e, String errorMessage) {
        logger.error(errorMessage, e);
        return e instanceof FlowableException ? (FlowableException) e : new FlowableException(errorMessage, e);
    }

    protected FlowableException handleException(Context context, Phase phase, Throwable e) {
        return handleException(context, phase, e, e.getMessage());
    }

    protected FlowableException handleException(Context context, Phase phase, Throwable e, String errorMessage) {
        String errorCode = FlowableException.ERROR_CODE;
        if (e instanceof FlowableException) {
            errorCode = ((FlowableException) e).getErrorCode();
        }
        context.getCurrentInstance().error(phase, errorCode, errorMessage);
        return handleException(e);
    }

    /**
     * 实际每个执行器需要做的执行动作
     *
     * @param context
     * @return
     * @throws FlowableException
     */
    public abstract Context doExecute(Context context) throws FlowableException;

    /**
     * 获得配置的监听器
     *
     * @param context
     * @return
     * @throws FlowableException
     */
    public abstract List<? extends Notification<Notify>> getNotifications(Context context);

    /**
     * 前通知
     *
     * @param context
     */
    protected void beforeNotify(Context context) {
        Notify notify = assembleNotify(context);
        getNotifications(context).forEach(notification -> notification.beforeNotify(notify));
    }

    /**
     * 后通知
     *
     * @param context
     */
    protected void afterNotify(Context context) {
        Notify notify = assembleNotify(context);
        getNotifications(context).forEach(notification -> notification.afterNotify(notify));
    }

    /**
     * 异常通知
     *
     * @param context
     * @param e
     */
    protected void exceptionNotify(Context context, Exception e) {
        Notify notify = assembleNotify(context);
        getNotifications(context).forEach(notification -> notification.exceptionNotify(notify, e));
    }

    /**
     * ss
     *
     * @param context
     * @return
     */
    protected abstract Notify assembleNotify(Context context);

    protected Context schedule(Actuator<Context, Context> actuator, Context context, String schedulerType) throws FlowableException {
        AbstractProcessScheduler scheduler = Factory.factory().getScheduler(schedulerType);
        return scheduler.schedule(actuator, context);
    }

}