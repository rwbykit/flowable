package rwbykit.flowable.engine.repository;

import rwbykit.flowable.engine.model.NodeInstanceImpl;

public interface NodeRepository {

    int insert(NodeInstanceImpl nodeInstance);

    int update(NodeInstanceImpl nodeInstance);

    NodeInstanceImpl getByNodeInstanceId(String nodeInstanceId);

    NodeInstanceImpl getByProcessInstanceId(String processInstanceId);

}
