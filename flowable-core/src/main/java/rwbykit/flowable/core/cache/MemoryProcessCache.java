package rwbykit.flowable.core.cache;

import rwbykit.flowable.core.model.parser.Process;

public class MemoryProcessCache implements ProcessCache {

    @Override
    public void addProcess(String id, String version, Process process) {

    }

    @Override
    public Process getProcess(String id, String version) {
        return null;
    }

    @Override
    public Process getProcess(String id) {
        return null;
    }
}
