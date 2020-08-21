package rwbykit.flowable.engine.notice;

/**
 * process notice object
 *
 */
public class ProcessNotice {

    private String processId;

    private String processInstanceId;

    private String processStatus;

    private String processName;

    private String bizNo;

    public ProcessNotice(){}

    public ProcessNotice(String processId, String processInstanceId, String processStatus, String processName, String bizNo) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.processStatus = processStatus;
        this.processName = processName;
        this.bizNo = bizNo;
    }

    public String getProcessId() {
        return processId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public String getProcessStatus() {
        return processStatus;
    }

    public String getProcessName() {
        return processName;
    }

    public String getBizNo() {
        return bizNo;
    }

    public static ProcessNoticeBuilder builder() {
        return new ProcessNoticeBuilder();
    }

    public static class ProcessNoticeBuilder {
        private String processId;
        private String processInstanceId;
        private String processStatus;
        private String processName;
        private String bizNo;

        ProcessNoticeBuilder() {
        }

        public ProcessNoticeBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public ProcessNoticeBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public ProcessNoticeBuilder processStatus(String processStatus) {
            this.processStatus = processStatus;
            return this;
        }

        public ProcessNoticeBuilder processName(String processName) {
            this.processName = processName;
            return this;
        }

        public ProcessNoticeBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public ProcessNotice build() {
            return new ProcessNotice(processId, processInstanceId, processStatus, processName, bizNo);
        }
    }
}
