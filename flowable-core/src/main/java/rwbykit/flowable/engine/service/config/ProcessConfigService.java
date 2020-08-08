package rwbykit.flowable.engine.service.config;

import rwbykit.flowable.model.Node;
import rwbykit.flowable.model.Process;
import rwbykit.flowable.model.Task;

public interface ProcessConfigService {

    <T extends Process> T getProcess();

    <T extends Node> T getNode(String nodeId);

    <T extends Task> T getTask(String nodeId, String taskId);

}
