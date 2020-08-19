package rwbykit.flowable.core.cache;

import rwbykit.flowable.core.model.parser.Process;

public interface ProcessCache {

    void addProcess(String id, String version, Process process);

    Process getProcess(String id, String version);

    Process getProcess(String id);

}
