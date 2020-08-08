/*
package rwbykit.flowableTemp.core;

import rwbykit.flowable.engine.exception.UnsupportedOperateException;
import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import com.war3.nova.beans.Approver;
import rwbykit.flowableTemp.core.runtime.Context;
import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import com.war3.nova.beans.NvInsProcess;
import com.war3.nova.beans.NvNode;
import com.war3.nova.beans.NvProcess;
import com.war3.nova.cache.CacheManager;
import rwbykit.flowableTemp.core.service.util.Processes;
import rwbykit.flowable.engine.util.Asserts;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

*/
/**
 * 默认的流程引擎处理
 * 
 * @author Cytus_
 * @since 2018年12月13日 下午7:37:43
 * @version 1.0
 *//*

public class DefaultNovaProcessEngine implements NovaProcessEngine {
    
    private final static Logger logger = LoggerFactory.getLogger(DefaultNovaProcessEngine.class);
    
    @Autowired
    private CoreProcessor processor;

    @Override
    public ProcessResult initProcessEngine(String processId, String bizSerno, String startUser, String orgId, String instOrgId, Map<String, Object> extParams)
            throws FlowableException {
        Context context = new Context();
        context.setBizSerno(bizSerno);
        context.setProcessId(processId);
        context.setStartUser(startUser);
        context.setOrgId(orgId);
        context.setInstOrgId(instOrgId);
        context.setExtParams(extParams);
        ProcessResult result = null;
        try {
            context = processor.initProcess(context);
            result = ProcessResult.success();
            result.setProcessInstId(context.getProcessInstId());
        } catch (FlowableException e) {
            logger.error(e.getErrorMessage(), e);
            result = ProcessResult.failure(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            logger.error("初始化流程出现异常!", e);
            result = ProcessResult.failure(Constants.SYSTEM_ERROR_CODE, e.getMessage());
        }
        
        return result;
    }

    @Override
    public ProcessResult submit(String processId, String bizSerno, String startUser, String orgId, String instOrgId,
                                Map<String, Object> extParams) throws FlowableException {
        Context context = new Context();
        context.setBizSerno(bizSerno);
        context.setProcessId(processId);
        context.setStartUser(startUser);
        context.setOrgId(orgId);
        context.setInstOrgId(instOrgId);
        context.setExtParams(extParams);
        ProcessResult result = null;
        try {
            context = processor.submit(context);
            result = ProcessResult.success();
            result.setProcessInstId(context.getProcessInstId());
        } catch (FlowableException e) {
            logger.error(e.getErrorMessage(), e);
            result = ProcessResult.failure(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            logger.error("初始化流程出现异常!", e);
            result = ProcessResult.failure(Constants.SYSTEM_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    @Override
    public ProcessResult submit(String processInstId, String nodeInstId, String nextNodeId, List<Approver> nextApprovers,
                                ApprovalInstance insApproval) throws FlowableException {
        Context context = new Context();
        context.setProcessInstId(processInstId);
        context.setNodeInstId(nodeInstId);
        context.setNextNodeId(nextNodeId);
        context.setNextApprovers(nextApprovers);
        context.setInstApproval(insApproval);
        ProcessResult result = null;
        try {
            context = processor.submit(context);
            result = ProcessResult.success();
            result.setProcessInstId(context.getProcessInstId());
            result.setNodeInstId(context.getNodeInstId());
            result.setNextNodeId(context.getNextNodeId());
            result.setNextApprovers(context.getNextApprovers());
        } catch (FlowableException e) {
            logger.error(e.getErrorMessage(), e);
            result = ProcessResult.failure(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            logger.error("初始化流程出现异常!", e);
            result = ProcessResult.failure(Constants.SYSTEM_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    @Override
    public ProcessResult continueProcess(String processInstId, String nodeInstId, String nodeId) throws FlowableException {
        Context context = new Context();
        context.setProcessInstId(processInstId);
        context.setNodeId(nodeId);
        context.setNodeInstId(nodeInstId);
        ProcessResult result = null;
        try {
            context = processor.submit(context);
            result = ProcessResult.success();
            result.setProcessInstId(context.getProcessInstId());
            result.setNodeInstId(context.getNodeInstId());
            result.setNextNodeId(context.getNextNodeId());
            result.setNextApprovers(context.getNextApprovers());
        } catch (FlowableException e) {
            logger.error(e.getErrorMessage(), e);
            result = ProcessResult.failure(e.getErrorCode(), e.getErrorMessage());
        } catch (Exception e) {
            logger.error("初始化流程出现异常!", e);
            result = ProcessResult.failure(Constants.SYSTEM_ERROR_CODE, e.getMessage());
        }
        return result;
    }

    @Override
    public ProcessResult submitSubProcess(String processInstId, String subProcessId, String startUser, String orgId,
                                          String instOrgId, Map<String, Object> extParams) throws FlowableException {
        // TODO Auto-generated method stub
        return null;
    }

    // 重办
    @Override
    public ProcessResult reApproval(String processInstId, String nodeId, Approver approver) throws FlowableException {
        Asserts.assertNullOrEmpty(processInstId, "传入的重办流程实例为空");
        Asserts.assertNullOrEmpty(nodeId, "流程实例[{}], 传入的重办流程节点实例为空", processInstId);
        Asserts.assertNullOrEmpty(approver, "流程实例[{}], 节点[{}], 传入的重办人信息为空", processInstId, nodeId);
        
        NvInsProcess insProcess = Processes.getInitProcessInfo(processInstId);
        Asserts.assertNull(insProcess, "流程实例[{}]不存在对应的流程实例信息或该流程实例已办结", insProcess);
        Asserts.assertNullOrEmpty(insProcess.getInstanceId(), "流程实例[{}]不存在对应的流程实例信息或该流程实例已办结", insProcess);
        if (ProcessConstants.STATUS_APPROVAL.equals(insProcess.getStatus())) {
            
            NvProcess nvProcess = CacheManager.getProcessByIdAndVersion(insProcess.getProcessId(), insProcess.getVersion());
            Asserts.assertEquals(nvProcess.getProperty().getReApproval(), Constants.COMMON_YESNO_NO, 
                    "流程实例[{}], 流程[{}], 版本[{}]配置不允许重办", processInstId, nvProcess.getId(), nvProcess.getVersion());
            
            NvNode nvNode = nvProcess.getNode(nodeId);
            Asserts.assertEquals(nvNode.getProperty().getReApproval(), Constants.COMMON_YESNO_NO, 
                    "流程实例[{}], 流程[{}], 版本[{}], 节点[{}]配置不允许重办", processInstId, nvProcess.getId(), nvProcess.getVersion(), nodeId);
            
            
            
            
        } else {
            String errorMsg = FlowableHelper.formatMessage("流程实例[{}], 当前状态为[{}], 未在人工审批中, 当前节点[{}]不可进行操作!", processInstId, insProcess, nodeId);
            logger.error(errorMsg);
            throw new UnsupportedOperateException(errorMsg);
            
        }
        
        
        return null;
    }

    @Override
    public ProcessResult revocation(String processInstId, String nodeInstId, Approver approver) throws FlowableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProcessResult transferTransact(String processInstId, String nodeInstId, Approver srcApprover,
                                          Approver operateApprover, Approver targetApprover) throws FlowableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProcessResult takeBack(String processInstId, String takeBackNodeInstId, Approver takeBackApprover)
            throws FlowableException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ProcessResult returnBack(String processInstId, String returnBackNodeInstId, Approver returnBackApprover)
            throws FlowableException {
        // TODO Auto-generated method stub
        return null;
    }

}
*/
