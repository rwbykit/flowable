package rwbykit.flowableTemp.core.runtime.instance;

class Process {

    private String processId;

    private String processInstanceId;

    private String mainProcessInstanceId;

    private String processStatus;

    private Process(String processId, String processInstanceId, String mainProcessInstanceId, String processStatus) {
        this.processId = processId;
        this.processInstanceId = processInstanceId;
        this.mainProcessInstanceId = mainProcessInstanceId;
        this.processStatus = processStatus;
    }

    public final static Process of(String processId, String processInstanceId, String mainProcessInstanceId, String processStatus) {
        return new Process(processId, processInstanceId, mainProcessInstanceId, processStatus);
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
