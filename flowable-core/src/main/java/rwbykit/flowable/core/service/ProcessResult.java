package rwbykit.flowable.core.service;


import java.io.Serializable;

/**
 * 流程操作结果集
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月21日 上午11:36:20
 */
public class ProcessResult implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String processInstanceId;

    private String nodeInstanceId;

    private String nextNodeId;

    private String nextScheduleMode;

    protected boolean isSuccess;

    protected String errorCode;

    protected String errorMessage;

    public ProcessResult() {
        super();
    }

    public final static ProcessResultBuilder success() {
        return new ProcessResultBuilder(true, null, null);
    }

    public final static ProcessResultBuilder failure(String errorCode, String errorMsg) {
        return new ProcessResultBuilder(false, errorCode, errorMsg);
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getNodeInstanceId() {
        return nodeInstanceId;
    }

    public void setNodeInstanceId(String nodeInstanceId) {
        this.nodeInstanceId = nodeInstanceId;
    }

    public String getNextNodeId() {
        return nextNodeId;
    }

    public void setNextNodeId(String nextNodeId) {
        this.nextNodeId = nextNodeId;
    }

    public String getNextScheduleMode() {
        return nextScheduleMode;
    }

    public void setNextScheduleMode(String nextScheduleMode) {
        this.nextScheduleMode = nextScheduleMode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static final class ProcessResultBuilder {
        protected boolean isSuccess;
        protected String errorCode;
        protected String errorMessage;
        private String processInstanceId;
        private String nodeInstanceId;
        private String nextNodeId;
        private String nextScheduleMode;

        ProcessResultBuilder(boolean isSuccess, String errorCode, String errorMessage) {
            this.isSuccess = isSuccess;
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public ProcessResultBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public ProcessResultBuilder nodeInstanceId(String nodeInstanceId) {
            this.nodeInstanceId = nodeInstanceId;
            return this;
        }

        public ProcessResultBuilder nextNodeId(String nextNodeId) {
            this.nextNodeId = nextNodeId;
            return this;
        }

        public ProcessResultBuilder nextScheduleMode(String nextScheduleMode) {
            this.nextScheduleMode = nextScheduleMode;
            return this;
        }

        public ProcessResultBuilder isSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
            return this;
        }

        public ProcessResultBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ProcessResultBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ProcessResult build() {
            ProcessResult processResult = new ProcessResult();
            processResult.setProcessInstanceId(processInstanceId);
            processResult.setNodeInstanceId(nodeInstanceId);
            processResult.setNextNodeId(nextNodeId);
            processResult.setNextScheduleMode(nextScheduleMode);
            processResult.setErrorCode(errorCode);
            processResult.setErrorMessage(errorMessage);
            processResult.isSuccess = this.isSuccess;
            return processResult;
        }
    }
}
