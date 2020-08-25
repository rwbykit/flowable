package rwbykit.flowable.engine.runtime.parameter;

import rwbykit.flowable.core.model.runtime.ApprovalInstance;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 路由参数
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 下午2:43:58
 */
public class LinkParameter implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String bizNo;

    private Map<String, Object> params;

    private List<ApprovalInstance> approvalInstances;

    private String nodeId;

    private String processId;

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public List<ApprovalInstance> getApprovalInstances() {
        return approvalInstances;
    }

    public void setApprovalInstances(List<ApprovalInstance> approvalInstances) {
        this.approvalInstances = approvalInstances;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    public static LinkParameterBuilder builder() {
        return new LinkParameterBuilder();
    }

    public static final class LinkParameterBuilder {
        private String bizNo;
        private Map<String, Object> params;
        private List<ApprovalInstance> approvalInstances;
        private String nodeId;
        private String processId;

        private LinkParameterBuilder() {
        }

        public LinkParameterBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public LinkParameterBuilder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public LinkParameterBuilder approvalInstances(List<ApprovalInstance> approvalInstances) {
            this.approvalInstances = approvalInstances;
            return this;
        }

        public LinkParameterBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public LinkParameterBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public LinkParameter build() {
            LinkParameter linkParameter = new LinkParameter();
            linkParameter.setBizNo(bizNo);
            linkParameter.setParams(params);
            linkParameter.setApprovalInstances(approvalInstances);
            linkParameter.setNodeId(nodeId);
            linkParameter.setProcessId(processId);
            return linkParameter;
        }
    }
}

