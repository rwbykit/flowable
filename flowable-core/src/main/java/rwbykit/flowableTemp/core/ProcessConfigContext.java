package rwbykit.flowableTemp.core;

/**
 * 流程配置的上线文线程对象
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:32:03
 * @version 1.0
 */
public class ProcessConfigContext {
    
    /**
     * 流程配置对象信息
     */
    private NvProcess process;
    
    /**
     * 当前节点id
     */
    private String nodeId;
    
    /**
     * 当前任务id
     */
    private String taskId;
    
    private final static ThreadLocal<ProcessConfigContext> threadLocal = new ThreadLocal<>();
    
    private ProcessConfigContext(NvProcess process) {
        this.process = process;
        threadLocal.set(this);
    }
    
    public final static ProcessConfigContext createContext(NvProcess process) {
        return new ProcessConfigContext(process);
    }
    
    public static ProcessConfigContext getContext() {
        return threadLocal.get();
    }
    
    public NvProcess getProcess() {
        return this.process;
    }
    

    
}
