package rwbykit.flowable.engine;


/**
 * 选择器
 * @param <I> 入参
 * @param <O> 出参
 * @author Cytus_
 * @since 2018年12月27日 下午2:27:07
 * @version 1.0
 */
public interface Selector<I, O> {

    /**
     * 选择
     * @param arg
     * @return
     * @throws FlowableException
     */
    O select(I arg) throws FlowableException;
    
}
