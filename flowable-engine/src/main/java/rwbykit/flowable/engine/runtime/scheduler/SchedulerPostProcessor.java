package rwbykit.flowable.engine.runtime.scheduler;

import rwbykit.flowable.core.Context;

public interface SchedulerPostProcessor {

    Context postProcessorBeforeActuator(Context context);

    Context postProcessorAfterActuator(Context context);
}
