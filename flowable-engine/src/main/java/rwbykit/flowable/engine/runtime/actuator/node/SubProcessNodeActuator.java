package rwbykit.flowable.engine.runtime.actuator.node;

import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.annotation.Type;

/**
 * 子流程
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月28日 上午11:28:56
 */
@Type(category = Constants.CATEGORY_ACTUATOR_NODE, type = Constants.TYPE_NODE_SUB_PROCESS)
public class SubProcessNodeActuator extends AbstractNodeActuator {

    @Override
    public Context doExecute(Context context) {
        throw new UnsupportedOperationException("当前不支持节点类型!");
    }

}
