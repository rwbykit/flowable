package rwbykit.flowable.extension.service;

import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.service.NodeService;
import rwbykit.flowable.extension.repository.NodeRepository;
import rwbykit.flowable.extension.runtime.model.NodeInstanceImpl;

public class NodeServiceImpl implements NodeService<NodeInstanceImpl> {

    private NodeRepository nodeRepository;

    public NodeServiceImpl(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public NodeInstanceImpl initialize(String processId, String processInstanceId, Node node) {
        return null;
    }

    @Override
    public boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage) {
        return false;
    }
}
