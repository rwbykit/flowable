package rwbykit.flowableTemp.core.runtime.instance;

public class Task {

    private String taskId;

    private String taskInstanceId;

    private String taskStatus;

    private Task(String taskId, String taskInstanceId, String taskStatus) {
        this.taskId = taskId;
        this.taskInstanceId = taskInstanceId;
        this.taskStatus = taskStatus;
    }

    public final static Task of(String taskId, String taskInstanceId, String taskStatus) {
        return new Task(taskId, taskInstanceId, taskStatus);
    }

    String getTaskId() {
        return taskId;
    }

    String getTaskInstanceId() {
        return taskInstanceId;
    }

    String getTaskStatus() {
        return taskStatus;
    }

    void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
