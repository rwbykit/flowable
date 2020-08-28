package rwbykit.flowable.engine.runtime.actuator.node;

import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.Notification;
import rwbykit.flowable.core.Selector;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.notice.NodeNotice;
import rwbykit.flowable.engine.notice.NotificationHelper;
import rwbykit.flowable.engine.runtime.actuator.AbstractActuator;
import rwbykit.flowable.core.current.CurrentInstance;
import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.engine.runtime.InitializeService;
import rwbykit.flowable.core.model.parser.Node;

import java.util.List;

/**
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月15日 下午3:22:51
 */
public abstract class AbstractNodeActuator extends AbstractActuator<NodeNotice> implements InitializeService {

    /**
     * 初始化
     *
     * @param context
     * @return
     */
    @Override
    public void initialize(Context context) {
        NodeInstance nodeInstance = context.getRuntimeService()
                .getNodeService()
                .initialize(context.getCurrentInstance().getProcessId(),
                        context.getCurrentInstance().getProcessInstanceId(),
                        context.getProcessConfigService().getNode(context.getParam(Constants.NODE_ID)));
        context.getCurrentInstance().setCurrentNode(nodeInstance.getNodeId(), nodeInstance.getNodeInstanceId(), nodeInstance.getNodeStatus());
    }

    /**
     * 获取节点监听对象
     */
    @Override
    public List<Notification<NodeNotice>> getNotifications(Context context) {
        Node node = context.getProcessConfigService().getNode(context.getParam(Constants.NODE_ID));
        return NotificationHelper.getNotificationsByType(node.getListeners());
    }

    @Override
    protected NodeNotice assembleNotify(Context context) {
        Node node = context.getProcessConfigService().getNode(context.getParam(Constants.NODE_ID));
        CurrentInstance instance = context.getCurrentInstance();
        return NodeNotice.builder().bizNo(instance.getBizNo())
                .nodeId(node.getId())
                .nodeInstanceId(instance.getNodeInstanceId())
                .nodeType(node.getType())
                .nodeName(node.getName())
                .nodeStatus(instance.getNodeStatus())
                .processId(instance.getProcessId())
                .processInstanceId(instance.getProcessInstanceId())
                .build();
    }

    @Override
    protected void prepare(Context context) {
        nodeExecutePrepare(context);
        super.prepare(context);
    }

    protected void nodeExecutePrepare(Context context) {
        Node node = getCurrentExecuteNode(context);
        Asserts.nonNull(node, "Not found current node to be executed!");
        initialize(context);
    }

    protected Node getCurrentExecuteNode(Context context) {
        if (Strings.nonEmpty(context.getParam(Constants.NODE_ID))) {
            return context.getProcessConfigService().getNode(context.getParam(Constants.NODE_ID));
        }
        Selector<Context, Node> nodeSelector = GenericObjectFactory.factory().getSelector(Constants.TYPE_SELECTOR_NODE);
        return nodeSelector.select(context);
    }

    @Override
    protected void exception(Context context, Exception exception) throws FlowableException {
        super.exception(context, exception);
        throw handleException(context, Phase.NODE, exception);
    }

    @Override
    protected void terminate(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        context.getRuntimeService().getNodeService().modifyInstanceStatus(instance.getNodeInstanceId(),
                instance.getNodeStatus(), instance.errorCode(), instance.errorMessage());
    }

    @Override
    public final Phase getSupportedType() {
        return Phase.NODE;
    }

}
