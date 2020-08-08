package rwbykit.flowable.engine.runtime.actuator.porcess;

import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Notification;
import rwbykit.flowable.engine.notice.NotificationHelper;
import rwbykit.flowable.engine.notice.ProcessNotice;
import rwbykit.flowable.engine.runtime.actuator.AbstractActuator;
import rwbykit.flowable.engine.runtime.scheduler.AbstractProcessScheduler;
import rwbykit.flowable.model.Process;

import java.util.List;

/**
 * 流程核心处理抽象类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:36:49
 * @version 1.0
 */
public abstract class AbstractProcessActuator extends AbstractActuator<ProcessNotice> {

    @Override
    public List<? extends Notification<ProcessNotice>> getNotifications(Context context) {
        Process process = context.getProcessConfigService().getProcess();
        return NotificationHelper.getNotificationsByType(process.getListeners());
    }

    @Override
    protected ProcessNotice assembleNotify(Context context) {
        return ProcessNotice.builder()
                .bizNo(context.getCurrentInstance().getBizNo())
                .processId(context.getCurrentInstance().getProcessId())
                .processInstanceId(context.getCurrentInstance().getProcessInstanceId())
                .processName(context.getProcessConfigService().getProcess().getName())
                .processStatus(context.getCurrentInstance().getProcessStatus())
                .build();
    }

    protected Context schedule(Context context) throws FlowableException {
        AbstractProcessScheduler scheduler = null;
        scheduler.schedule(this, context);
        return context;
    }

    /**
     * 获得调度者类型
     * @param context
     * @return
     */
    /*protected abstract SchedulerType getSchedulerType(Context context);*/

}
