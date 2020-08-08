package rwbykit.flowable.engine;

/**
 * 调度器
 * @param <In> 入参
 * @param <Out> 出参
 * @param <O> 调度对象
 * @author Cytus_
 * @since 2018年12月27日 下午2:26:34
 * @version 1.0
 */
public interface Scheduler<In, O, Out> {

    /**
     * 调度
     * @param object
     * @param inArgs
     * @return
     * @throws FlowableException
     */
    Out schedule(O object, In inArgs) throws FlowableException;
    
}
