package rwbykit.flowableTemp.core.runtime.instance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowableTemp.annotation.ErrorSegment;
import rwbykit.flowableTemp.core.ProcessConstants;

import java.util.Objects;

/**
 * 运行时实例信息
 *
 * @author rwbykits
 */
public class Instance {

    private final static Logger logger = LoggerFactory.getLogger(Instance.class);

    private Process process;

    private Node node;

    private Task task;

    private String bizNo;

    private String errorMessage;

    private ErrorSegment errorSegment;

    private boolean isError = false;

    private Instance(String bizNo, Process process) {
        this.process = process;
        this.bizNo = bizNo;
    }

    public final static Instance of(String bizNo, String processId, String processInstanceId) {
        return of(bizNo, processId, processInstanceId, null);
    }

    public final static Instance of(String bizNo, String processId, String processInstanceId, String mainProcessInstanceId) {
        return of(bizNo, processId, processInstanceId, mainProcessInstanceId, null);
    }

    public final static Instance of(String bizNo, String processId, String processInstanceId, String mainProcessInstanceId, String processStatus) {
        return new Instance(bizNo, Process.of(processId, processInstanceId, mainProcessInstanceId, processStatus));
    }

    public void setCurrentNode(String nodeId, String nodeInstanceId, String nodeStatus) {
        if (isError) {
            logger.warn("Current process[{}] has occur exception, don`t update instance information!", this.process.getProcessInstanceId());
        } else {
            this.node = Node.of(nodeId, nodeInstanceId, nodeStatus);
        }
    }

    public void setCurrentNode(String nodeId, String nodeInstanceId) {
        setCurrentNode(nodeId, nodeInstanceId, null);
    }

    public void setCurrentTask(String taskId, String taskInstanceId, String taskStatus) {
        if (isError) {
            logger.warn("Current process[{}] has occur exception, don`t update instance information!", this.process.getProcessInstanceId());
        } else {
            this.task = Task.of(taskId, taskInstanceId, taskStatus);
        }
    }

    public void setCurrentTask(String taskId, String taskInstanceId) {
        setCurrentTask(taskId, taskInstanceId, null);
    }

    public void setError(ErrorSegment errorSegment, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorSegment = errorSegment;
        switch (errorSegment) {
            case PROCESS:
                this.setProcessStatus(ProcessConstants.STATUS_EXCEPTION);
                break;
            case NODE:
                this.setNodeStatus(ProcessConstants.STATUS_EXCEPTION);
                break;
            case TASK:
                this.setTaskStatus(ProcessConstants.STATUS_EXCEPTION);
                break;
            default:
                break;
        }
        this.isError = true;
    }

    public void clearError() {
        this.isError = false;
        this.errorSegment = null;
        this.errorMessage = null;
    }

    public Instance copyCurrentInstance() {
        Instance instance = of(this.getBizNo(), this.getProcessId(), this.getProcessInstanceId(), this.getMainProcessInstanceId(), this.getProcessStatus());
        if (Objects.nonNull(this.node)) {
            instance.setCurrentNode(this.node.getNodeId(), this.node.getNodeInstanceId(), this.node.getNodeStatus());
        }
        if (Objects.nonNull(this.task)) {
            instance.setCurrentTask(this.task.getTaskId(), this.task.getTaskInstanceId(), this.task.getTaskStatus());
        }
        return instance;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ErrorSegment getErrorSegment() {
        return errorSegment;
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

}
