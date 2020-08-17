package rwbykit.flowable.core.model.runtime;

public interface TaskInstance {

    String getTaskId();

    String getTaskInstanceId();

    String getTaskStatus();

    String getNodeInstanceId();

    int getOrder();

}
