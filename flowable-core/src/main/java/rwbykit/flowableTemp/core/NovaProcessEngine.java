/*
package rwbykit.flowableTemp.core;

import rwbykit.flowable.war3.beans.Approver;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.runtime.model.ApprovalInstance;

import java.util.List;
import java.util.Map;

*/
/**
 * 
 * 
 * @author Cytus_
 * @since 2018年12月13日 下午7:24:47
 * @version 1.0
 *//*

public interface NovaProcessEngine {
	
	*/
/**
	 * 初始化
	 * @param processId 流程id
	 * @param bizSerno 业务流水号
	 * @param startUser 流程启动者
	 * @param orgId 流程启动者机构号
	 * @param instOrgId 流程启动者金融机构号
	 * @return
	 *//*

	ProcessResult initProcessEngine(String processId, String bizSerno, String startUser, String orgId, String instOrgId, Map<String, Object> extParams) throws FlowableException;
	
	*/
/**
	 * 流程提交
	 * @param processId 流程id
	 * @param bizSerno 业务流水号
	 * @param startUser 流程启动者
	 * @param orgId 流程启动者机构号
	 * @param instOrgId 流程启动者金融机构号
	 * @param extParams 扩展参数
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult submit(String processId, String bizSerno, String startUser, String orgId, String instOrgId, Map<String, Object> extParams) throws FlowableException;
	
	*/
/**
	 * 流程提交（适用人工节点提交）
	 * @param processInstId 流程实例id
	 * @param nodeInstId 节点实例id
	 * @param nextId 下一节点编号
	 * @param nextApprovers 下一及节点审批人
	 * @param insApproval 当前审批结果
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult submit(String processInstId, String nodeInstId, String nextNodeId, List<Approver> nextApprovers, ApprovalInstance insApproval) throws FlowableException;
	
	*/
/**
	 * 继续执行流程，适用于自动节点异常等
	 * @param processInstId 流程实例id
	 * @param nodeInstId 节点实例Id
	 * @param nodeId 节点id
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult continueProcess(String processInstId, String nodeInstId, String nodeId) throws FlowableException;
	
	*/
/**
	 * 发起子流程
	 * @param processInstId 当前流程实例
	 * @param subProcessId 子流程ID
	 * @param startUser 启动者
	 * @param orgId 发起人所在机构
	 * @param instOrgId 发起人所在金融机构
	 * @param extParams 扩展参数
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult submitSubProcess(String processInstId, String subProcessId, String startUser, String orgId, String instOrgId, Map<String, Object> extParams) throws FlowableException;
	
	*/
/**
	 * 重办
	 * @param processInstId 流程实例编号
	 * @param nodeInstId 节点实例编号
	 * @param approver 重办人信息
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult reApproval(String processInstId, String nodeInstId, Approver approver) throws FlowableException;
	
	*/
/**
	 * 撤办
	 * @param processInstId 流程实例编号
	 * @param nodeInstId 节点实例编号
	 * @param approver 撤办人信息
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult revocation(String processInstId, String nodeInstId, Approver approver) throws FlowableException;
	
	*/
/**
	 * 转办
	 * @param processInstId 流程实例编号
	 * @param nodeInstId 节点实例编号
	 * @param srcApprover 原办理人
	 * @param operateApprover 转办操作人
	 * @param targetApprover 转办人
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult transferTransact(String processInstId, String nodeInstId, Approver srcApprover, Approver operateApprover, Approver targetApprover) throws FlowableException;
	
	*/
/**
	 * 拿回
	 * @param processInstId 流程实例编号
	 * @param takeBackNodeInstId 拿回节点实例号
	 * @param takeBackApprover 拿回人
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult takeBack(String processInstId, String takeBackNodeInstId, Approver takeBackApprover) throws FlowableException;
	
	*/
/**
	 * 退回
	 * @param processInstId 流程实例号
	 * @param returnBackNodeInstId 退回节点实例号
	 * @param returnBackApprover 退回人
	 * @return
	 * @throws FlowableException
	 *//*

	ProcessResult returnBack(String processInstId, String returnBackNodeInstId, Approver returnBackApprover) throws FlowableException;
}
*/
