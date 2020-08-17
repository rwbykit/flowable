package rwbykit.flowable.core.current;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.enumeration.Phase;

import java.util.Objects;

public class CurrentInstance {

    private final static Logger logger = LoggerFactory.getLogger(CurrentInstance.class);

    private Initiator initiator;

    private CurrentProcess process;

    private CurrentNode node;

    private CurrentTask task;

    private String bizNo;

    private String errorCode;

    private String errorMessage;

    private Phase phase;

    private boolean isError = false;

    private CurrentInstance(Initiator initiator, String bizNo, CurrentProcess process) {
        this.process = process;
        this.bizNo = bizNo;
        this.initiator = initiator;
    }

    public final static CurrentInstance of(Initiator initiator, String bizNo, CurrentProcess process) {
        return new CurrentInstance(initiator, bizNo, process);
    }

    public void setCurrentNode(String nodeId, String nodeInstanceId, String nodeStatus) {
        if (isError) {
            logger.warn("Current process[{}] has occur exception, don`t update instance information!", this.process.getProcessInstanceId());
        } else {
            this.node = CurrentNode.of(nodeId, nodeInstanceId, nodeStatus);
        }
    }

    public void setCurrentNode(String nodeId, String nodeInstanceId) {
        setCurrentNode(nodeId, nodeInstanceId, null);
    }

    public void setCurrentTask(String taskId, String taskInstanceId, String taskStatus) {
        if (isError) {
            logger.warn("Current process[{}] has occur exception, don`t update instance information!", this.process.getProcessInstanceId());
        } else {
            this.task = CurrentTask.of(taskId, taskInstanceId, taskStatus);
        }
    }

    public void setCurrentTask(String taskId, String taskInstanceId) {
        setCurrentTask(taskId, taskInstanceId, null);
    }

    public void error(Phase phase, String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.phase = phase;
        this.errorCode = errorCode;
        switch (phase) {
            case PROCESS:
                this.setProcessStatus(Constants.STATUS_EXCEPTION);
                break;
            case NODE:
                this.setNodeStatus(Constants.STATUS_EXCEPTION);
                break;
            case TASK:
                this.setTaskStatus(Constants.STATUS_EXCEPTION);
                break;
            default:
                break;
        }
        this.isError = true;
    }

    public void clearError() {
        this.isError = false;
        this.phase = null;
        this.errorMessage = null;
        this.errorCode = null;
    }

    public CurrentInstance cloneCurrentInstance() {
        CurrentInstance instance = of(this.initiator, this.bizNo,
                CurrentProcess.of(this.getProcessId(), this.getProcessInstanceId(), this.getMainProcessInstanceId(), this.getProcessStatus()));
        if (Objects.nonNull(this.node)) {
            instance.setCurrentNode(this.node.getNodeId(), this.node.getNodeInstanceId(), this.node.getNodeStatus());
        }
        if (Objects.nonNull(this.task)) {
            instance.setCurrentTask(this.task.getTaskId(), this.task.getTaskInstanceId(), this.task.getTaskStatus());
        }
        return instance;
    }

    public Initiator getInitiator() {
        return initiator;
    }

    public String errorMessage() {
        return errorMessage;
    }

    public Phase phase() {
        return phase;
    }

    public boolean isError() {
        return isError;
    }


    public String getBizNo() {
        return this.bizNo;
    }

    public void setProcessStatus(String status) {
        this.process.setProcessStatus(status);
    }

    public String getProcessId() {
        return this.process.getProcessId();
    }

    public String getProcessInstanceId() {
        return this.process.getProcessInstanceId();
    }

    public String getProcessStatus() {
        return this.process.getProcessStatus();
    }

    public String getMainProcessInstanceId() {
        return this.process.getMainProcessInstanceId();
    }

    public String getNodeId() {
        return Objects.nonNull(this.node) ? this.node.getNodeId() : null;
    }

    public String getNodeInstanceId() {
        return Objects.nonNull(this.node) ? this.node.getNodeInstanceId() : null;
    }

    public String getNodeStatus() {
        return Objects.nonNull(this.node) ? this.node.getNodeStatus() : null;
    }

    public void setNodeStatus(String nodeStatus) {
        if (Objects.nonNull(this.node)) {
            this.node.setNodeStatus(nodeStatus);
        }
    }

    public String getTaskId() {
        return Objects.nonNull(this.task) ? this.task.getTaskId() : null;
    }

    public String getTaskInstanceId() {
        return Objects.nonNull(this.task) ? this.task.getTaskStatus() : null;
    }

    public String getTaskStatus() {
        return Objects.nonNull(this.task) ? this.task.getTaskStatus() : null;
    }

    public void setTaskStatus(String taskStatus) {
        if (Objects.nonNull(this.task)) {
            this.task.setTaskStatus(taskStatus);
        }
    }

    public String errorCode() {
        return errorCode;
    }
}
