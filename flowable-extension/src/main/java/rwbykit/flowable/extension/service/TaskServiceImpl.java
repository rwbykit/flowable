package rwbykit.flowable.extension.service;

import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.service.TaskService;
import rwbykit.flowable.extension.runtime.model.TaskInstanceImpl;

import java.util.List;

public class TaskServiceImpl implements TaskService<TaskInstanceImpl> {
    @Override
    public TaskInstanceImpl initialize(String nodeInstanceId, Task task) {
        return null;
    }

    @Override
    public boolean modifyInstanceStatus(String nodeInstanceId, String status, String errorCode, String errorMessage) {
        return false;
    }

    @Override
    public List<TaskInstanceImpl> getAllTaskInstances(String nodeInstanceId) {
        return null;
    }
}
