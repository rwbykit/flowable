package rwbykit.flowable.core.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.util.Asserts;

public class CacheManager {

    private final static Logger logger = LoggerFactory.getLogger(CacheManager.class);

    private static ProcessCache cache = null;
    private static Boolean enableInit = true;

    public synchronized static void setProcessCache(ProcessCache processCache) {
        if (!enableInit) {
            Asserts.nonNull(processCache, "Process Cache must not null!");
            cache = processCache;
            enableInit = true;
        } else {
            logger.warn("Process Cache has init!");
        }
    }

    public static void addProcess(Process process) {
        Asserts.nonNull(process, "Process Information must not null!");
        addProcess(process.getId(), process.getVersion(), process);
    }

    public static void addProcess(String id, String version, Process process) {
        cache.addProcess(id, version, process);
    }

    public static Process getProcess(String id, String version) {
        return cache.getProcess(id, version);
    }

    public static Process getProcess(String id) {
        return cache.getProcess(id);
    }

}
