package rwbykit.flowable.engine.model;

public class RuntimeInfo {

    private String startTime;

    private String endTime;

    private String errorCode;

    private String errorMessage;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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


    static class RuntimeInfoBuilder {
        protected String startTime;
        protected String endTime;
        protected String errorCode;
        protected String errorMessage;

        protected <T extends RuntimeInfo> T build(T t) {
            t.setStartTime(startTime);
            t.setEndTime(endTime);
            t.setErrorCode(errorCode);
            t.setErrorMessage(errorMessage);
            return t;
        }


    }

}
