package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.TaskInstance;
import rwbykit.flowable.core.model.parser.Task;

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
