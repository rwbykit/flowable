package rwbykit.flowable.engine.runtime.service;

import rwbykit.flowable.engine.runtime.model.NodeInstance;
import rwbykit.flowable.model.Node;

public interface NodeService {

    /**
     * 初始化节点信息
     * @param processId
     * @param processInstanceId
     * @param node
     * @return 节点实例号
     */
    NodeInstance initialize(String processId, String processInstanceId, Node node);


    boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage);

}
