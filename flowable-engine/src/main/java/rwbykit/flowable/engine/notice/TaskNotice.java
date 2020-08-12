package rwbykit.flowable.engine.notice;

public class TaskNotice {

    private String processId;

    private String processInstanceId;

    private String nodeId;

    private String nodeInstanceId;

    private String taskId;

    private String taskName;

    private String taskStatus;

    public TaskNotice() {
    }

    public TaskNotice(String processId, String processInstanceId, String nodeId, String nodeInstanceId, String taskId, String taskName, String taskStatus) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.nodeId = nodeId;
        this.nodeInstanceId = nodeInstanceId;
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskStatus = taskStatus;
    }

    public static TaskNoticeBuilder builder() {
        return new TaskNoticeBuilder();
    }


    public static class TaskNoticeBuilder {
        private String processId;
        private String processInstanceId;
        private String nodeId;
        private String nodeInstanceId;
        private String taskId;
        private String taskName;
        private String taskStatus;

        TaskNoticeBuilder() {
        }

        public TaskNoticeBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public TaskNoticeBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public TaskNoticeBuilder nodeId(String nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public TaskNoticeBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public TaskNoticeBuilder taskId(String taskId) {
            this.taskId = taskId;
            return this;
        }

        public TaskNoticeBuilder taskName(String taskName) {
            this.taskName = taskName;
            return this;
        }

        public TaskNoticeBuilder taskStatus(String taskStatus) {
            this.taskStatus = taskStatus;
            return this;
        }

        public TaskNotice build() {
            return new TaskNotice(processId, processInstanceId, nodeId, nodeInstanceId, taskId, taskName, taskStatus);
        }

    }
}
