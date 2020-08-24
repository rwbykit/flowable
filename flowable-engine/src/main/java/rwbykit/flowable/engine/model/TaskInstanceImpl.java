package rwbykit.flowable.engine.model;

import rwbykit.flowable.core.model.runtime.TaskInstance;

public class TaskInstanceImpl extends RuntimeInfo implements TaskInstance {

    private String taskId;

    private String taskName;

    private String scheduleType;

    private String runMode;

    private String taskInfo;

    private String taskInstanceId;

    private String taskStatus;

    private String nodeInstanceId;

    private int order;

    @Override
    public String getTaskId() {
        return null;
    }

    @Override
    public String getTaskInstanceId() {
        return null;
    }

    @Override
    public String getTaskStatus() {
        return null;
    }

    @Override
    public String getNodeInstanceId() {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public void setTaskInfo(String taskInfo) {
        this.taskInfo = taskInfo;
    }

    public void setTaskInstanceId(String taskInstanceId) {
        this.taskInstanceId = taskInstanceId;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public void setNodeInstanceId(String nodeInstanceId) {
        this.nodeInstanceId = nodeInstanceId;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public static TaskInstanceImplBuilder builder() {
        return new TaskInstanceImplBuilder();
    }

    public static final class TaskInstanceImplBuilder extends RuntimeInfo.RuntimeInfoBuilder {
        private String taskId;
        private String taskName;
        private String scheduleType;
        private String runMode;
        private String taskInfo;
        private String taskInstanceId;
        private String taskStatus;
        private String nodeInstanceId;
        private int order;

        private TaskInstanceImplBuilder() {
            super();
        }

        public TaskInstanceImplBuilder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskInstanceImplBuilder taskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public TaskInstanceImplBuilder scheduleType(String scheduleType) {
            this.scheduleType = scheduleType;
            return this;
        }

        public TaskInstanceImplBuilder runMode(String runMode) {
            this.runMode = runMode;
            return this;
        }

        public TaskInstanceImplBuilder taskInfo(String taskInfo) {
            this.taskInfo = taskInfo;
            return this;
        }

        public TaskInstanceImplBuilder taskInstanceId(String taskInstanceId) {
            this.taskInstanceId = taskInstanceId;
            return this;
        }

        public TaskInstanceImplBuilder taskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public TaskInstanceImplBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public TaskInstanceImplBuilder order(int order) {
            this.order = order;
            return this;
        }

        public TaskInstanceImplBuilder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public TaskInstanceImplBuilder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public TaskInstanceImplBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public TaskInstanceImplBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public TaskInstanceImpl build() {
            TaskInstanceImpl taskInstanceImpl = super.build(new TaskInstanceImpl());
            taskInstanceImpl.setTaskId(taskId);
            taskInstanceImpl.setTaskName(taskName);
            taskInstanceImpl.setScheduleType(scheduleType);
            taskInstanceImpl.setRunMode(runMode);
            taskInstanceImpl.setTaskInfo(taskInfo);
            taskInstanceImpl.setTaskInstanceId(taskInstanceId);
            taskInstanceImpl.setTaskStatus(taskStatus);
            taskInstanceImpl.setNodeInstanceId(nodeInstanceId);
            taskInstanceImpl.setOrder(order);
            return taskInstanceImpl;
        }
    }
}
