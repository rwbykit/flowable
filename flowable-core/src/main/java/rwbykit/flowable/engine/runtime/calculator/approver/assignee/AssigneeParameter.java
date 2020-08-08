package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import rwbykit.flowable.engine.runtime.current.Initiator;

import java.io.Serializable;

/**
 * 自定义审批人计算向下传递对象
 * 
 * @author Cytus_
 * @since 2018年12月28日 上午10:53:16
 * @version 1.0
 */
public class AssigneeParameter implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String processId;

    private String processInstanceId;
    
    private String nodeId;

    private String nodeInstanceId;
    
    private Initiator initiator;

    AssigneeParameter(String processId, String processInstanceId, String nodeId, String nodeInstanceId, Initiator initiator) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.initiator = initiator;
    }

    public static AssigneeParameterBuilder builder() {
        return new AssigneeParameterBuilder();
    }


    public static class AssigneeParameterBuilder {
        private String processId;
        private String processInstanceId;
        private String nodeId;
        private String nodeInstanceId;
        private Initiator initiator;

        AssigneeParameterBuilder() {
        }

        public AssigneeParameterBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public AssigneeParameterBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public AssigneeParameterBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public AssigneeParameterBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public AssigneeParameterBuilder initiator(Initiator initiator) {
            this.initiator = initiator;
            return this;
        }

        public AssigneeParameter build() {
            return new AssigneeParameter(processId, processInstanceId, nodeId, nodeInstanceId, initiator);
        }

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

    public Initiator getInitiator() {
        return initiator;
    }
}
