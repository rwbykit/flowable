package rwbykit.flowable.engine.runtime.actuator.porcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.Notification;
import rwbykit.flowable.core.Selector;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.core.factory.ThreadPoolFactory;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.notice.NotificationHelper;
import rwbykit.flowable.engine.notice.ProcessNotice;
import rwbykit.flowable.engine.runtime.actuator.AbstractActuator;

import java.util.List;

/**
 * 流程核心处理抽象类
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:36:49
 * @version 1.0
 */
public abstract class AbstractProcessActuator extends AbstractActuator<ProcessNotice> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractProcessActuator.class);

    @Override
    protected List<? extends Notification<ProcessNotice>> getNotifications(Context context) {
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

    /**
     * 流程信息预执行
     * @param context
     */
    @Override
    protected void prepare(Context context) {
        Node node = deduceNodeTobeExecuted(context);
        context.addParam(Constants.NODE_ID, node.getId());
        super.prepare(context);
    }

    protected Node deduceNodeTobeExecuted(Context context) {
        Selector<Context, Node> nodeSelector = GenericObjectFactory.factory().getSelector(Constants.TYPE_SELECTOR_NODE);
        Asserts.nonNull(nodeSelector, "Not found register node selector!!!");
        return nodeSelector.select(context);
    }

    @Override
    protected void completed(Context context) {
        super.completed(context);
        Node node = deduceNodeTobeExecuted(context);
        if (Objects.nonNull(node) && Strings.ignoreCaseCompare(node.getType(), Constants.TYPE_NODE_AUTO)) {
            ThreadPoolFactory.factory().addRunnable(() -> {
                try {
                    super.schedule(this, context.cloneContext(), Phase.PROCESS);
                } catch (FlowableException e) {
                    logger.error(e.getMessage(), e);
                }
            });
        }
    }

    @Override
    protected String getSchedulerType(Context context) {
        Node node = context.getProcessConfigService().getNode(context.getParam(Constants.NODE_ID));
        return Objects.nonNull(node) && Strings.ignoreCaseCompare(node.getType(), Constants.TYPE_NODE_AUTO) ?
                Constants.TYPE_SCHEDULER_ASYNC : Constants.TYPE_SCHEDULER_SYNC;
    }

    @Override
    public final Phase getSupportedType() {
        return Phase.PROCESS;
    }

}
