package rwbykit.flowable.engine.service;

import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.service.NodeService;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Datetimes;
import rwbykit.flowable.core.util.Ids;
import rwbykit.flowable.engine.model.NodeInstanceImpl;
import rwbykit.flowable.engine.repository.NodeRepository;

public class NodeServiceImpl implements NodeService<NodeInstanceImpl> {

    private final NodeRepository nodeRepository;

    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public NodeInstanceImpl initialize(String processId, String processInstanceId, Node node) {

        NodeInstanceImpl nodeInstance = NodeInstanceImpl.builder()
                .nodeId(node.getId())
                .nodeInstanceId(Ids.getId())
                .nodeStatus(Constants.STATUS_RUNNING)
                .nodeName(node.getName())
                .nodeType(node.getType())
                .startTime(Datetimes.formatByDefault())
                .processInstanceId(processInstanceId)
                .build();
        int record = nodeRepository.insert(nodeInstance);
        Asserts.isEquals(record, 1, "Process id[{}], process instance id[{}], node id[{}], initialize node instance, " +
                "The number of affected items is not 1, initialize failure!", processId, processInstanceId, node.getId());
        return nodeInstance;
    }

    @Override
    public boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage) {
        NodeInstanceImpl nodeInstance = nodeRepository.getByNodeInstanceId(nodeInstanceId);
        Asserts.nonNull(nodeInstance, "Current node instance[{}] not found record!", nodeInstanceId);
        nodeInstance.setNodeStatus(status);
        nodeInstance.setErrorCode(errorCode);
        nodeInstance.setErrorMessage(errorMessage);
        int record = nodeRepository.update(nodeInstance);
        Asserts.isEquals(record, 1, "Node instance id[{}], The number of affected items is not 1 , modify status failure!", nodeInstanceId);
        return record == 1;
    }

    @Override
    public NodeInstanceImpl getByProcessInstanceId(String processInstanceId) {
        return nodeRepository.getByProcessInstanceId(processInstanceId);
    }

    @Override
    public NodeInstanceImpl getByNodeInstanceId(String nodeInstanceId) {
        return null;
    }
}
