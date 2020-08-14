package rwbykit.flowable.engine;

import rwbykit.flowable.core.FlowableException;

/**
 * 计算器
 * @param <In> 入参
 * @param <Out> 出参
 * @author Cytus_
 * @since 2018年12月27日 下午2:23:58
 * @version 1.0
 */
public interface Calculator<In, Out> {

    /**
     * 计算
     * @param in
     * @return
     * @throws
     */
    Out calculate(In in) throws FlowableException;
    
}
