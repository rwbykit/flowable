package rwbykit.flowable.engine.runtime.current;

class CurrentProcess {

    private String processId;

    private String processInstanceId;

    private String mainProcessInstanceId;

    private String processStatus;

    private CurrentProcess(String processId, String processInstanceId, String mainProcessInstanceId, String processStatus) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.mainProcessInstanceId = mainProcessInstanceId;
        this.processStatus = processStatus;
    }

    public final static CurrentProcess of(String processId, String processInstanceId, String mainProcessInstanceId, String processStatus) {
        return new CurrentProcess(processId, processInstanceId, mainProcessInstanceId, processStatus);
    }

    void setProcessStatus(String status) {
        this.processStatus = status;
    }

    String getProcessId() {
        return processId;
    }

    String getProcessInstanceId() {
        return processInstanceId;
    }

    String getProcessStatus() {
        return processStatus;
    }

    String getMainProcessInstanceId() {
        return mainProcessInstanceId;
    }

}
