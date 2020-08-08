package rwbykit.flowable.engine.notice;

public class NodeNotice {

    private String processId;

    private String processInstanceId;

    private String nodeId;

    private String nodeInstanceId;

    private String nodeName;

    private String nodeType;

    private String nodeStatus;

    private String bizNo;

    public NodeNotice(){}

    public NodeNotice(String processId, String processInstanceId, String nodeId, String nodeInstanceId, String nodeName, String nodeType, String nodeStatus, String bizNo) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.nodeName = nodeName;
        this.nodeType = nodeType;
        this.nodeStatus = nodeStatus;
        this.bizNo = bizNo;
    }

    public static NodeNoticeBuilder builder() {
        return new NodeNoticeBuilder();
    }


    public static class NodeNoticeBuilder {
        private String processId;
        private String processInstanceId;
        private String nodeId;
        private String nodeInstanceId;
        private String nodeName;
        private String nodeType;
        private String nodeStatus;
        private String bizNo;

        NodeNoticeBuilder() {
        }

        public NodeNoticeBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public NodeNoticeBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public NodeNoticeBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public NodeNoticeBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public NodeNoticeBuilder nodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public NodeNoticeBuilder nodeType(String nodeType) {
            this.nodeType = nodeType;
            return this;
        }

        public NodeNoticeBuilder nodeStatus(String nodeStatus) {
            this.nodeStatus = nodeStatus;
            return this;
        }

        public NodeNoticeBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public NodeNotice build() {
            return new NodeNotice(processId, processInstanceId, nodeId, nodeInstanceId, nodeName, nodeType, nodeStatus, bizNo);
        }

    }
}
