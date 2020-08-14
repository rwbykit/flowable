package rwbykit.flowable.engine;


import rwbykit.flowable.core.FlowableException;

/**
 * 选择器
 * @param <In> 入参
 * @param <Out> 出参
 * @author Cytus_
 * @since 2018年12月27日 下午2:27:07
 * @version 1.0
 */
public interface Selector<In, Out> {

    /**
     * 选择
     * @param in
     * @return
     * @throws FlowableException
     */
    Out select(In in) throws FlowableException;
    
}
