package rwbykit.flowable.engine.runtime.calculator.approver;

import java.io.Serializable;

/**
 * 自定义审批流程用的相关扩展对象
 * 
 * @author Cytus_
 * @since 2018年12月20日 下午5:22:38
 * @version 1.0
 */
public class ApprovalProcess implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String processId;
    
    private String nodeId;
    
    private String bizNo;

    public String getProcessId() {
        return processId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getBizNo() {
        return bizNo;
    }

    ApprovalProcess(String processId, String nodeId, String bizNo) {
        this.processId = processId;
        this.nodeId = nodeId;
        this.bizNo = bizNo;
    }

    public static ApprovalProcessBuilder builder() {
        return new ApprovalProcessBuilder();
    }


    public static class ApprovalProcessBuilder {
        private String processId;
        private String nodeId;
        private String bizNo;

        ApprovalProcessBuilder() {
        }

        public ApprovalProcessBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public ApprovalProcessBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public ApprovalProcessBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public ApprovalProcess build() {
            return new ApprovalProcess(processId, nodeId, bizNo);
        }

    }
}
