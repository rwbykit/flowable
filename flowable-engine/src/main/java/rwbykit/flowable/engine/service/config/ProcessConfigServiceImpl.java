package rwbykit.flowable.engine.service.config;

import rwbykit.flowable.core.model.parser.AutoNode;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.service.ProcessConfigService;

public class ProcessConfigServiceImpl implements ProcessConfigService {

    private final Process process;

    private ProcessConfigServiceImpl(Process process) {
        this.process = process;
    }

    public static <T> ProcessConfigService of(T process) {
        return new ProcessConfigServiceImpl((Process) process);
    }

    @Override
    public <T extends Process> T getProcess() {
        return (T) process;
    }

    @Override
    public <T extends Node> T getNode(String nodeId) {
        return (T) process.getNodes().stream().filter(node -> node.getId().equals(nodeId)).findFirst().orElse(null);
    }

    @Override
    public <T extends Task> T getTask(String nodeId, String taskId) {
        Node node = this.getNode(nodeId);
        return node instanceof AutoNode ? getTask((AutoNode) node, taskId) : null;
    }

    private <T extends Task> T getTask(AutoNode node, String taskId) {
        return (T) node.getTasks().stream().filter(task -> task.getId().equals(taskId)).findFirst().orElse(null);
    }
}
