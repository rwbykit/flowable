package rwbykit.flowable.engine;

import rwbykit.flowable.core.FlowableException;

/**
 * 执行器
 * @param <In> 入参
 * @param <Out> 出参
 * @author Cytus_
 * @since 2018年12月27日 下午2:23:33
 * @version 1.0
 */
public interface Actuator<In, Out> {

    /**
     * 执行
     * @param in
     * @return
     * @throws FlowableException
     */
    Out execute(In in) throws FlowableException;
    
}
