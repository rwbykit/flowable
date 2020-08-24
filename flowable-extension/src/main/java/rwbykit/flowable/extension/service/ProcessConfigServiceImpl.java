package rwbykit.flowable.extension.service;

import rwbykit.flowable.core.model.parser.AutoNode;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.parser.QuickSearch;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.service.ProcessConfigService;
import rwbykit.flowable.core.util.Asserts;

import java.util.Optional;

public class ProcessConfigServiceImpl implements ProcessConfigService {

    private Process process;

    public ProcessConfigServiceImpl(Process process) {
        this.process = process;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Process> T getProcess() {
        return (T) this.process;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Node> T getNode(String nodeId) {
        return isQuickSearch(this.process) ? ((QuickSearch) this.process).search("nodes", nodeId) :
                (T) this.process.getNodes().stream().filter(node -> node.getId().equals(nodeId)).findFirst().orElse(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Task> T getTask(String nodeId, String taskId) {
        Node node = this.getNode(nodeId);
        Asserts.nonNull(node, "Not found current node id[{}]!", nodeId);
        return isQuickSearch(node) ? ((QuickSearch) node).search("tasks", taskId) :
                isAutoNode(node) ?
                        (T) Optional.of(node)
                                .map(AutoNode.class::cast)
                                .get()
                                .getTasks()
                                .stream()
                                .filter(task -> task.getId().equals(taskId))
                                .findFirst()
                                .orElse(null)
                        : null;
    }

    private static boolean isQuickSearch(Object object) {
        return QuickSearch.class.isInstance(object);
    }

    private static boolean isAutoNode(Node node) {
        return AutoNode.class.isInstance(node);
    }

}
