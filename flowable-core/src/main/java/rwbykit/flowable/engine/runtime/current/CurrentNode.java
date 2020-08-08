package rwbykit.flowable.engine.runtime.current;

public class CurrentNode {

    private String nodeId;

    private String nodeInstanceId;

    private String nodeStatus;

    private CurrentNode(String nodeId, String nodeInstanceId, String nodeStatus) {
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.nodeStatus = nodeStatus;
    }

    public final static CurrentNode of(String nodeId, String nodeInstanceId, String nodeStatus) {
        return new CurrentNode(nodeId, nodeInstanceId, nodeStatus);
    }

    String getNodeId() {
        return nodeId;
    }

    String getNodeInstanceId() {
        return nodeInstanceId;
    }

    String getNodeStatus() {
        return nodeStatus;
    }

    void setNodeStatus(String nodeStatus) {
        this.nodeStatus = nodeStatus;
    }
}
