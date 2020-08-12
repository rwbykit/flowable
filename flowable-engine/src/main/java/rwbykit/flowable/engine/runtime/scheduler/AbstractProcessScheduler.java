package rwbykit.flowable.engine.runtime.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Actuator;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.Scheduler;
import rwbykit.flowable.core.util.Collections;

import java.util.List;

/**
 * 抽象类流程调度器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月18日 上午8:39:58
 */
public abstract class AbstractProcessScheduler implements Scheduler<Context, Actuator<Context, Context>, Context> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractProcessScheduler.class);

    private List<SchedulerPostProcessor> schedulerPostProcessors;

    public AbstractProcessScheduler(List<SchedulerPostProcessor> schedulerPostProcessors) {
        this.schedulerPostProcessors = schedulerPostProcessors;
    }

    protected Context doSchedule(Actuator<Context, Context> actuator, Context context) throws FlowableException {
        beforeActuator(context);
        context = actuator.execute(context);
        afterActuator(context);
        return context;
    }

    protected Context beforeActuator(Context context) {
        if (Collections.nonEmpty(schedulerPostProcessors)) {
            for (SchedulerPostProcessor postProcessor : schedulerPostProcessors) {
                context = postProcessor.postProcessorBeforeActuator(context);
            }
        }
        return context;
    }

    protected Context afterActuator(Context context) {
        if (Collections.nonEmpty(schedulerPostProcessors)) {
            for (SchedulerPostProcessor postProcessor : schedulerPostProcessors) {
                context = postProcessor.postProcessorAfterActuator(context);
            }
        }
        return context;
    }

}
