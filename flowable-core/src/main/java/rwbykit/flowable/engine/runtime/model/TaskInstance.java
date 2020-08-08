package rwbykit.flowable.engine.runtime.model;

public interface TaskInstance {

    String getTaskId();

    String getTaskInstanceId();

    String getTaskStatus();

    String getNodeInstanceId();

    int getOrder();

}
