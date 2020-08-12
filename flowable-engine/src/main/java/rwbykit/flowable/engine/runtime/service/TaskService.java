package rwbykit.flowable.engine.runtime.service;

import rwbykit.flowable.engine.runtime.model.TaskInstance;
import rwbykit.flowable.core.model.Task;

import java.util.List;

public interface TaskService {

    /**
     * 初始化
     * @param nodeInstanceId
     * @param task
     * @return
     */
    TaskInstance initialize(String nodeInstanceId, Task task);


    boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage);


    List<TaskInstance> getAllTaskInstances(String nodeInstanceId);
}
