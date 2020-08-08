package rwbykit.flowableTemp.core.runtime.instance;

public class Node {

    private String nodeId;

    private String nodeInstanceId;

    private String nodeStatus;

    private Node(String nodeId, String nodeInstanceId, String nodeStatus) {
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.nodeStatus = nodeStatus;
    }

    public final static Node of(String nodeId, String nodeInstanceId, String nodeStatus) {
        return new Node(nodeId, nodeInstanceId, nodeStatus);
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
