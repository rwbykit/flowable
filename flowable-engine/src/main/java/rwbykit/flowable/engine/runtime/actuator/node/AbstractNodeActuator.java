package rwbykit.flowable.engine.runtime.actuator.node;

import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Notification;
import rwbykit.flowable.engine.enumeration.Phase;
import rwbykit.flowable.engine.notice.NodeNotice;
import rwbykit.flowable.engine.notice.NotificationHelper;
import rwbykit.flowable.engine.runtime.actuator.AbstractActuator;
import rwbykit.flowable.engine.runtime.current.CurrentInstance;
import rwbykit.flowable.engine.runtime.model.NodeInstance;
import rwbykit.flowable.engine.runtime.InstanceService;
import rwbykit.flowable.core.model.Node;

import java.util.List;

/**
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月15日 下午3:22:51
 */
public abstract class AbstractNodeActuator extends AbstractActuator<NodeNotice> implements InstanceService {

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
    public void afterSet(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        context.getRuntimeService().getNodeService().modifyInstanceStatus(instance.getNodeInstanceId(),
                instance.getNodeStatus(), instance.errorCode(), instance.errorMessage());
    }

    @Override
    public Context doExecute(Context context) throws FlowableException {
        //TODO 可能需要做一些操作
        this.initialize(context);
        try {
            return nodeExecute(context);
        } catch (Exception e) {
            throw handleException(context, Phase.NODE, e);
        } finally {
            afterSet(context);
        }
    }

    /**
     * 节点执行
     *
     * @param context
     * @return
     * @throws FlowableException
     */
    abstract Context nodeExecute(Context context) throws FlowableException;


}
