package rwbykit.flowable.extension.actuator.artificial.approval;

import rwbykit.flowable.engine.runtime.model.ApprovalInstance;

import java.io.Serializable;
import java.util.List;

/**
 * 多人会签参数对象
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午5:50:36
 * @version 1.0
 */
public class MultiJoinSignParameter implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String processId;
    
    private String processInstanceId;
    
    private String nodeId;
    
    private String nodeInstanceId;
    
    private List<ApprovalInstance> approvalInstances;

    MultiJoinSignParameter(String processId, String processInstanceId, String nodeId, String nodeInstanceId, List<ApprovalInstance> approvalInstances) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.approvalInstances = approvalInstances;
    }

    public static MultiJoinSignParameterBuilder builder() {
        return new MultiJoinSignParameterBuilder();
    }

    public String getProcessId() {
        return processId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public String getNodeInstanceId() {
        return nodeInstanceId;
    }

    public List<ApprovalInstance> getApprovalInstances() {
        return approvalInstances;
    }

    public static class MultiJoinSignParameterBuilder {
        private String processId;
        private String processInstanceId;
        private String nodeId;
        private String nodeInstanceId;
        private List<ApprovalInstance> approvalInstances;

        MultiJoinSignParameterBuilder() {
        }

        public MultiJoinSignParameterBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public MultiJoinSignParameterBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public MultiJoinSignParameterBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public MultiJoinSignParameterBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public MultiJoinSignParameterBuilder approvalInstances(List<ApprovalInstance> approvalInstances) {
            this.approvalInstances = approvalInstances;
            return this;
        }

        public MultiJoinSignParameter build() {
            return new MultiJoinSignParameter(processId, processInstanceId, nodeId, nodeInstanceId, approvalInstances);
        }

        public String toString() {
            return "MultiJoinSignParameter.MultiJoinSignParameterBuilder(processId=" + this.processId + ", processInstanceId=" + this.processInstanceId + ", nodeId=" + this.nodeId + ", nodeInstanceId=" + this.nodeInstanceId + ", approvalInstances=" + this.approvalInstances + ")";
        }
    }
}
