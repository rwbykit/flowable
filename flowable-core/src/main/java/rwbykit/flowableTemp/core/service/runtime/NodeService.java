package rwbykit.flowableTemp.core.service.runtime;

import com.war3.nova.beans.NvInsNode;
import rwbykit.flowableTemp.core.runtime.model.NodeInstance;
import rwbykit.flowable.model.Node;

/**
 * 实例节点操作服务
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:57:19
 * @version 1.0
 */
public interface NodeService {
    
    /**
     * 获取当前代执行节点
     * @param processInstanceId
     * @return
     */
    NodeInstance getInstanceByProcessInstanceId(String processInstanceId);
    
    /**
     * 获取最后一个执行节点
     * @param processInstanceId
     * @return
     */
    NvInsNode getLastByProcessInstId(String processInstanceId);

    /**
     * 设置实例状态
     * @param nodeInstanceId
     * @param status
     * @param errorMessage
     */
    void setInstanceStatus(String nodeInstanceId, String status, String errorMessage);
    
    /**
     * 更新节点状态
     * @param nodeInstanceId
     * @param nodeStatus
     * @return
     */
    int update(NvInsNode insNode);
    
    /**
     * 数据插入
     * @param insNode
     * @return
     */
    int insert(NvInsNode insNode);
    
    /**
     * 根据节点实例号查询实例信息
     * @param instanceId
     * @return
     */
    NvInsNode getByInstanceId(String instanceId);

    /**
     * 初始化节点信息
     * @param processId
     * @param processInstanceId
     * @param node
     * @return 节点实例号
     */
    String initialize(String processId, String processInstanceId, Node node);

    /**
     * 查询节点状态
     * @param nodeInstanceId
     * @return
     */
    String getNodeStatus(String nodeInstanceId);
}
