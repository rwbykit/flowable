package rwbykit.flowable.engine.repository;

import rwbykit.flowable.engine.model.TaskInstanceImpl;

import java.util.List;

public interface TaskRepository {

    int insert(TaskInstanceImpl taskInstance);


    int update(TaskInstanceImpl taskInstance);


    TaskInstanceImpl getByTaskInstanceId(String taskInstanceId);

    List<TaskInstanceImpl> getByNodeInstanceId(String nodeIntanceId);

}





