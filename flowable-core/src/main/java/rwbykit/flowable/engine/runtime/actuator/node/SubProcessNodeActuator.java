package rwbykit.flowable.engine.runtime.actuator.node;

import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.exception.UnsupportedOperateException;

/**
 * 子流程
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 上午11:28:56
 */
public class SubProcessNodeActuator extends AbstractNodeActuator {

    @Override
    public Context nodeExecute(Context context) throws FlowableException {
        throw new UnsupportedOperateException("当前不支持节点类型!");
    }

}
