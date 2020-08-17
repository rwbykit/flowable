package rwbykit.flowable.engine.runtime.actuator.task;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;

/**
 * 自动任务执行
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月15日 上午11:42:52
 */
@Type(category = Constants.TYPE_ACTUATOR_TASK, type = "Default")
@Type(category = Constants.TYPE_ACTUATOR_TASK, type = "Auto")
public class AutoTaskActuator extends AbstractTaskActuator {

    @Override
    public Context taskExecute(Context context) throws FlowableException {
        return super.taskExecute(context);
    }

}
