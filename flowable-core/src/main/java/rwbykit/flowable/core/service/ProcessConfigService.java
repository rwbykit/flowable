package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.parser.Task;

public interface ProcessConfigService {

    <T extends Process> T getProcess();

    <T extends Node> T getNode(String nodeId);

    <T extends Task> T getTask(String nodeId, String taskId);

}
