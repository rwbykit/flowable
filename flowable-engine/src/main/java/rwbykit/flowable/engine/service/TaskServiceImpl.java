package rwbykit.flowable.engine.service;

import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.service.TaskService;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Datetimes;
import rwbykit.flowable.core.util.Ids;
import rwbykit.flowable.engine.model.TaskInstanceImpl;
import rwbykit.flowable.engine.repository.TaskRepository;

import java.util.List;

public class TaskServiceImpl implements TaskService<TaskInstanceImpl> {

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskInstanceImpl initialize(String nodeInstanceId, Task task) {

        TaskInstanceImpl taskInstance = TaskInstanceImpl.builder()
                .taskId(task.getId())
                .taskInstanceId(Ids.getId())
                .nodeInstanceId(nodeInstanceId)
                .taskName(task.getName())
                .taskStatus(Constants.STATUS_RUNNING)
                .scheduleType(task.getScheduleType())
                .taskInfo(task.getRunValue())
                .runMode(task.getRunMode())
                .order(task.getOrder())
                .startTime(Datetimes.formatByDefault())
                .build();
        int record = taskRepository.insert(taskInstance);
        Asserts.isEquals(record, 1, "Node instance id[{}], task id[{}], initialize task, " +
                "The number of affected items is not 1, initialize failure!", nodeInstanceId, task.getId());
        return taskInstance;
    }

    @Override
    public boolean modifyInstanceStatus(String taskInstanceId, String status, String errorCode, String errorMessage) {
        TaskInstanceImpl taskInstance = taskRepository.getByTaskInstanceId(taskInstanceId);
        Asserts.nonNull(taskInstance, "Current task instance[{}] not found record!", taskInstanceId);
        taskInstance.setTaskStatus(status);
        taskInstance.setErrorCode(errorCode);
        taskInstance.setErrorMessage(errorMessage);
        taskInstance.setEndTime(Datetimes.formatByDefault());
        int record = taskRepository.update(taskInstance);
        Asserts.isEquals(record, 1, "Task instance id[{}], The number of affected items is not 1 , modify status failure!", taskInstanceId);
        return record == 1;
    }

    @Override
    public List<TaskInstanceImpl> getAllTaskInstances(String nodeInstanceId) {
        List<TaskInstanceImpl> taskInstances = taskRepository.getByNodeInstanceId(nodeInstanceId);
        Asserts.nonNull(taskInstances, "Current node instance[{}] not found all task instance record!", nodeInstanceId);
        return taskInstances;
    }
}
