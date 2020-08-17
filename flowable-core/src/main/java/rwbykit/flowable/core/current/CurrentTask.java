package rwbykit.flowable.core.current;

public class CurrentTask {

    private String taskId;

    private String taskInstanceId;

    private String taskStatus;

    private CurrentTask(String taskId, String taskInstanceId, String taskStatus) {
        this.taskId = taskId;
        this.taskInstanceId = taskInstanceId;
        this.taskStatus = taskStatus;
    }

    public final static CurrentTask of(String taskId, String taskInstanceId, String taskStatus) {
        return new CurrentTask(taskId, taskInstanceId, taskStatus);
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
