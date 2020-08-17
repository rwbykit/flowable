package rwbykit.flowable.engine.runtime.parameter;


import rwbykit.flowable.core.model.runtime.Approval;

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

    private List<Approval> approvals;

    private String nodeId;

    private String processId;

    LinkParameter(String bizNo, Map<String, Object> params, List<Approval> approvals, String nodeId, String processId) {
        this.bizNo = bizNo;
        this.params = params;
        this.approvals = approvals;
        this.nodeId = nodeId;
        this.processId = processId;
    }

    public static LinkParameterBuilder builder() {
        return new LinkParameterBuilder();
    }

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

    public List<Approval> getApprovals() {
        return approvals;
    }

    public void setApprovals(List<Approval> approvals) {
        this.approvals = approvals;
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

    public static class LinkParameterBuilder {
        private String bizNo;
        private Map<String, Object> params;
        private List<Approval> approvals;
        private String nodeId;
        private String processId;

        LinkParameterBuilder() {
        }

        public LinkParameterBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public LinkParameterBuilder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public LinkParameterBuilder approvals(List<Approval> approvals) {
            this.approvals = approvals;
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
            return new LinkParameter(bizNo, params, approvals, nodeId, processId);
        }

        public String toString() {
            return "LinkParameter.LinkParameterBuilder(bizNo=" + this.bizNo + ", params=" + this.params + ", approvals=" + this.approvals + ", nodeId=" + this.nodeId + ", processId=" + this.processId + ")";
        }
    }
}

