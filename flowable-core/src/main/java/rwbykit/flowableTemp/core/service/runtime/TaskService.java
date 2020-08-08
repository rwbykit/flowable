package rwbykit.flowableTemp.core.service.runtime;

import com.war3.nova.beans.NvInsTask;
import rwbykit.flowableTemp.core.runtime.model.TaskInstance;
import rwbykit.flowable.model.Task;

import java.util.List;

/**
 * 实例任务操作服务
 * 
 * @author Cytus_
 * @since 2018年12月15日 上午11:32:54
 * @version 1.0
 */
public interface TaskService {
	
    /**
     * 插入
     * @param insTask
     * @return
     */
    int insert(NvInsTask insTask);
    
    /**
     * 根据主键更新
     * @param insTask
     * @return
     */
    int update(NvInsTask insTask);
    
    /**
     * 根据节点实例号查询所有实例任务
     * @param nodeInstanceId
     * @return
     */
    List<NvInsTask> queryAllByNodeInstId(String nodeInstanceId);
    
    /**
     * 根据节点实例号任务编号查询实例任务信息
     * @param nodeInstId
     * @param taskId
     * @return
     */
    NvInsTask queryByNodeInstIdAndTaskId(String nodeInstId, String taskId);


    TaskInstance initialize(String nodeId, String nodeInstanceId, Task task);

    void setInstanceStatus(String taskInstanceId, String status, String errorMessage);

    String getStatus(String taskInstanceId);

    List<TaskInstance> getAllTaskInstances(String nodeInstanceId);
}
