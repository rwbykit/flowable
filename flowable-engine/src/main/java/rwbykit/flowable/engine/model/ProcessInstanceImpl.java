package rwbykit.flowable.engine.model;

import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.runtime.ProcessInstance;

public class ProcessInstanceImpl extends RuntimeInfo implements ProcessInstance {

    private String processId;

    private String processInstanceId;

    private String mainProcessInstanceId;

    private String bizNo;

    private String processStatus;

    private String currentNodeInstanceId;

    private String version;

    private Initiator initiator;

    @Override
    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getMainProcessInstanceId() {
        return mainProcessInstanceId;
    }

    public void setMainProcessInstanceId(String mainProcessInstanceId) {
        this.mainProcessInstanceId = mainProcessInstanceId;
    }

    @Override
    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    @Override
    public String getProcessStatus() {
        return processStatus;
    }

    public void setProcessStatus(String processStatus) {
        this.processStatus = processStatus;
    }

    @Override
    public String getCurrentNodeInstanceId() {
        return currentNodeInstanceId;
    }

    public void setCurrentNodeInstanceId(String currentNodeInstanceId) {
        this.currentNodeInstanceId = currentNodeInstanceId;
    }

    @Override
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public Initiator getInitiator() {
        return initiator;
    }

    public void setInitiator(Initiator initiator) {
        this.initiator = initiator;
    }

    public static ProcessInstanceImplBuilder builder() {
        return new ProcessInstanceImplBuilder();
    }

    public static final class ProcessInstanceImplBuilder extends RuntimeInfo.RuntimeInfoBuilder {
        private String processId;
        private String processInstanceId;
        private String mainProcessInstanceId;
        private String bizNo;
        private String processStatus;
        private String currentNodeInstanceId;
        private String version;
        private Initiator initiator;
        private String startTime;
        private String endTime;
        private String errorCode;
        private String errorMessage;

        private ProcessInstanceImplBuilder() {
        }

        public ProcessInstanceImplBuilder processId(String processId) {
            this.processId = processId;
            return this;
        }

        public ProcessInstanceImplBuilder processInstanceId(String processInstanceId) {
            this.processInstanceId = processInstanceId;
            return this;
        }

        public ProcessInstanceImplBuilder mainProcessInstanceId(String mainProcessInstanceId) {
            this.mainProcessInstanceId = mainProcessInstanceId;
            return this;
        }

        public ProcessInstanceImplBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public ProcessInstanceImplBuilder processStatus(String processStatus) {
            this.processStatus = processStatus;
            return this;
        }

        public ProcessInstanceImplBuilder currentNodeInstanceId(String currentNodeInstanceId) {
            this.currentNodeInstanceId = currentNodeInstanceId;
            return this;
        }

        public ProcessInstanceImplBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ProcessInstanceImplBuilder initiator(Initiator initiator) {
            this.initiator = initiator;
            return this;
        }

        public ProcessInstanceImplBuilder startTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public ProcessInstanceImplBuilder endTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public ProcessInstanceImplBuilder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public ProcessInstanceImplBuilder errorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
            return this;
        }

        public ProcessInstanceImpl build() {
            ProcessInstanceImpl processInstanceImpl = super.build(new ProcessInstanceImpl());
            processInstanceImpl.setProcessId(processId);
            processInstanceImpl.setProcessInstanceId(processInstanceId);
            processInstanceImpl.setMainProcessInstanceId(mainProcessInstanceId);
            processInstanceImpl.setBizNo(bizNo);
            processInstanceImpl.setProcessStatus(processStatus);
            processInstanceImpl.setCurrentNodeInstanceId(currentNodeInstanceId);
            processInstanceImpl.setVersion(version);
            processInstanceImpl.setInitiator(initiator);
            return processInstanceImpl;
        }
    }
}
