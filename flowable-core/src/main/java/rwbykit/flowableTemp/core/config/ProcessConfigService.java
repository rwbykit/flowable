package rwbykit.flowableTemp.core.config;

import rwbykit.flowable.model.Process;
import rwbykit.flowable.model.Node;
import rwbykit.flowable.model.Task;

public interface ProcessConfigService {

    <T extends Node> T getNode(String nodeId);

    Process getProcess();

    Task getTask(String nodeId, String taskId);

}
