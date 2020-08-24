package rwbykit.flowable.engine.model;

import rwbykit.flowable.core.model.runtime.NodeInstance;

public class NodeInstanceImpl extends RuntimeInfo implements NodeInstance {
    
    private String nodeId;

    private String nodeName;
    
    private String nodeInstanceId;
    
    private String nodeStatus;

    private String nodeType;

    private String processInstanceId;

    @Override
    public String getNodeId() {
        return null;
    }

    @Override
    public String getNodeInstanceId() {
        return null;
    }

    @Override
    public String getNodeStatus() {
        return null;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public void setNodeInstanceId(String nodeInstanceId) {
        this.nodeInstanceId = nodeInstanceId;
    }

    public void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public static NodeInstanceImplBuilder builder() {
        return new NodeInstanceImplBuilder();
    }

    public static final class NodeInstanceImplBuilder extends RuntimeInfo.RuntimeInfoBuilder {
        private String nodeId;
        private String nodeName;
        private String nodeInstanceId;
        private String nodeStatus;
        private String nodeType;
        private String processInstanceId;

        private NodeInstanceImplBuilder() {
        }
        
        public NodeInstanceImplBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public NodeInstanceImplBuilder nodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public NodeInstanceImplBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public NodeInstanceImplBuilder nodeStatus(String nodeStatus) {
            this.nodeStatus = nodeStatus;
            return this;
        }

        public NodeInstanceImplBuilder nodeType(String nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        public NodeInstanceImplBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public NodeInstanceImplBuilder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public NodeInstanceImplBuilder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public NodeInstanceImplBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public NodeInstanceImplBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public NodeInstanceImpl build() {
            NodeInstanceImpl nodeInstanceImpl = super.build(new NodeInstanceImpl());
            nodeInstanceImpl.setNodeId(nodeId);
            nodeInstanceImpl.setNodeName(nodeName);
            nodeInstanceImpl.setNodeInstanceId(nodeInstanceId);
            nodeInstanceImpl.setNodeStatus(nodeStatus);
            nodeInstanceImpl.setNodeType(nodeType);
            nodeInstanceImpl.setProcessInstanceId(processInstanceId);
            return nodeInstanceImpl;
        }
    }
}
