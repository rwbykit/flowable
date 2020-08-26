package rwbykit.flowable.core.service;

import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.Approver;

import java.util.List;
import java.util.Map;

public interface ProcessEngineService {


    /**
     * 初始化
     *
     * @param processId 流程id
     * @param bizNo  业务流水号
     * @param initiator 流程启动者信息
     * @param params  参数信息
     * @return
     */
    ProcessResult initialize(String processId, String bizNo, Initiator initiator, Map<String, Object> params) throws FlowableException;


    /**
     * 流程提交
     *
     * @param processId 流程id
     * @param bizNo  业务流水号
     * @param initiator 流程启动者信息
     * @param params  参数信息
     * @return
     * @throws FlowableException
     */
    ProcessResult initializeAndSubmit(String processId, String bizNo, Initiator initiator, Map<String, Object> params) throws FlowableException;


    /**
     * 流程提交（适用人工节点提交）
     *
     * @param processInstanceId 流程实例id
     * @param nodeInstanceId    节点实例id
     * @param nextNodeId        下一节点编号
     * @param nextApprovers 下一及节点审批人
     * @param currentApprovalInstance   当前审批结果
     * @param params 参数信息
     * @return
     * @throws FlowableException
     */

    ProcessResult submit(String processInstanceId, String nodeInstanceId, String nextNodeId, List<Approver> nextApprovers,
                         ApprovalInstance currentApprovalInstance, Map<String, Object> params) throws FlowableException;


    /**
     * 继续执行流程，适用于自动节点异常等
     *
     * @param processInstanceId 流程实例id
     * @param nodeInstanceId    节点实例Id
     * @throws FlowableException
     */

    ProcessResult continueProcess(String processInstanceId, String nodeInstanceId) throws FlowableException;


    /**
     * 发起子流程
     *
     * @param processInstanceId 当前流程实例
     * @param subProcessId  子流程ID
     * @param initiator     启动者信息
     * @param params     扩展参数
     * @return
     * @throws FlowableException
     */

    ProcessResult submitSubProcess(String processInstanceId, String subProcessId, Initiator initiator, Map<String, Object> params) throws FlowableException;


    /**
     * 重办
     *
     * @param processInstanceId 流程实例编号
     * @param nodeInstanceId    节点实例编号
     * @param approver      重办人信息
     * @return
     * @throws FlowableException
     */

    ProcessResult reApproval(String processInstanceId, String nodeInstanceId, Approver approver) throws FlowableException;


    /**
     * 撤办
     *
     * @param processInstanceId 流程实例编号
     * @param nodeInstanceId    节点实例编号
     * @param approver      撤办人信息
     * @return
     * @throws FlowableException
     */

    ProcessResult revocation(String processInstanceId, String nodeInstanceId, Approver approver) throws FlowableException;


    /**
     * 转办
     *
     * @param processInstanceId   流程实例编号
     * @param nodeInstanceId      节点实例编号
     * @param srcApprover     原办理人
     * @param operateApprover 转办操作人
     * @param targetApprover  转办人
     * @return
     * @throws FlowableException
     */

    ProcessResult transferTransact(String processInstanceId, String nodeInstanceId, Approver srcApprover, Approver operateApprover, Approver targetApprover) throws FlowableException;


    /**
     * 拿回
     *
     * @param processInstanceId      流程实例编号
     * @param takeBackNodeInstanceId 拿回节点实例号
     * @param takeBackApprover   拿回人
     * @return
     * @throws FlowableException
     */

    ProcessResult takeBack(String processInstanceId, String takeBackNodeInstanceId, Approver takeBackApprover) throws FlowableException;


    /**
     * 退回
     *
     * @param processInstanceId        流程实例号
     * @param returnBackNodeInstanceId 退回节点实例号
     * @param returnBackApprover   退回人
     * @return
     * @throws FlowableException
     */

    ProcessResult returnBack(String processInstanceId, String returnBackNodeInstanceId, Approver returnBackApprover) throws FlowableException;
}

