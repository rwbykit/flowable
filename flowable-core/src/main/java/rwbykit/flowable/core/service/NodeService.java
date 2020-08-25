package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.core.model.parser.Node;

public interface NodeService<T extends NodeInstance> {

    /**
     * 初始化节点信息
     * @param processId
     * @param processInstanceId
     * @param node
     * @return 节点实例号
     */
    T initialize(String processId, String processInstanceId, Node node);


    boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage);

    T getByProcessInstanceId(String processInstanceId);

}
