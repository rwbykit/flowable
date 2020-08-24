package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.TaskInstance;
import rwbykit.flowable.core.model.parser.Task;

import java.util.List;

public interface TaskService<T extends TaskInstance> {

    /**
     * 初始化
     * @param nodeInstanceId
     * @param task
     * @return
     */
    T initialize(String nodeInstanceId, Task task);


    boolean modifyInstanceStatus(String taskInstanceId, String status, String errorCode, String errorMessage);


    List<T> getAllTaskInstances(String nodeInstanceId);
}
