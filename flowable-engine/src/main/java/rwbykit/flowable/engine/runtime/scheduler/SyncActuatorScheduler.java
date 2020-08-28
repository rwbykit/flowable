package rwbykit.flowable.engine.runtime.scheduler;

import rwbykit.flowable.core.Actuator;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.engine.runtime.processor.ActuatorPostProcessor;

import java.util.List;

/**
 * 流程调度器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月18日 上午8:37:00
 */
@Type(category = Constants.CATEGORY_SCHEDULER, type = Constants.TYPE_SCHEDULER_SYNC)
public class SyncActuatorScheduler extends AbstractActuatorScheduler {

    public SyncActuatorScheduler(List<ActuatorPostProcessor> actuatorPostProcessors) {
        super(actuatorPostProcessors);
    }

    @Override
    public Context schedule(Actuator<Context, Context> actuator, Context context, Phase phase) throws FlowableException {
        return super.doSchedule(actuator, context, phase);
    }

}
