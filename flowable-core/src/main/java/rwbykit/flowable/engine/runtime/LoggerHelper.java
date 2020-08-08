package rwbykit.flowable.engine.runtime;

import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.runtime.current.CurrentInstance;
import rwbykit.flowableTemp.core.util.FlowableHelper;

public class LoggerHelper {

    public final static String actuator_node_startMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("流程实例[{}], 节点实例[{}], 执行任务[{}]开始",
                instance.getProcessInstanceId(), instance.getNodeInstanceId(), instance.getTaskStatus());
    }

    public final static String actuator_node_endMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("流程实例[{}], 节点实例[{}], 执行任务[{}]结束, 任务执行结果状态为[{}]",
                instance.getProcessInstanceId(), instance.getNodeInstanceId(), instance.getTaskStatus(), instance.getTaskStatus());
    }

    public final static String select_task_selectStartMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("节点实例[{}], 节点[{}], 当前执行任务[{}]查询下一执行任务开始",
                instance.getNodeInstanceId(), instance.getNodeId(), instance.getTaskId());
    }

    public final static String select_task_nextTaskMessage(Context context, String nextTaskId) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("节点实例[{}], 节点[{}], 当前执行任务[{}], 下一待执行任务为[{}]",
                instance.getNodeInstanceId(), instance.getNodeId(), instance.getTaskId(), nextTaskId);
    }

    public final static String select_node_returnNonCompleteMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("当前流程实例[{}]已实例化, 并且当前节点[{}]未执行完成, 状态为[{}], 返回当前节点",
                instance.getProcessInstanceId(), instance.getNodeId(), instance.getTaskStatus());
    }

    public final static String select_node_nextNodeMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("当前流程实例[{}]已实例化, 并且当前节点[{}]已执行完成, 寻找下一节点",
                instance.getProcessInstanceId(), instance.getNodeId());
    }

    public final static String select_node_contextNextNodeMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("流程实例[{}], 当前节点[{}]已执行完成, 传入的下一待执行节点为[{}]",
                instance.getProcessInstanceId(), instance.getNodeId(), context.getParam("nextNodeId"));
    }

    public final static String select_node_ctxNonNextStartMessage(Context context) {
        CurrentInstance instance = context.getCurrentInstance();
        return FlowableHelper.formatMessage("流程实例[{}], 当前节点[{}]已执行完成, 无传入的下一待执行节点, 当前进行查找下一节点",
                instance.getProcessInstanceId(), instance.getNodeId());
    }


}
