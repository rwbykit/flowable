package rwbykit.flowable.engine.runtime.runner;

import rwbykit.flowable.core.FlowableException;

/**
 * 自定义运行者
 * @param <P>
 * @param <O>
 * @author Cytus_
 * @since 2018年12月27日 下午2:43:21
 * @version 1.0
 */
public interface Runner<P, O> {

    O run(String value, P parameter) throws FlowableException;
    
}
