package rwbykit.flowable.engine.runtime.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Actuator;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.core.factory.ThreadPoolFactory;
import rwbykit.flowable.engine.runtime.processor.ActuatorPostProcessor;

import java.util.List;

/**
 * 异步流程度调度
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月18日 上午8:45:25
 */
@Type(category = Constants.CATEGORY_SCHEDULER, type = Constants.TYPE_SCHEDULER_ASYNC)
public class AsyncActuatorScheduler extends AbstractActuatorScheduler {

    private final static Logger logger = LoggerFactory.getLogger(AsyncActuatorScheduler.class);

    public AsyncActuatorScheduler(List<ActuatorPostProcessor> actuatorPostProcessors) {
        super(actuatorPostProcessors);
    }

    @Override
    public Context schedule(Actuator<Context, Context> actuator, Context context, Phase phase) {
        ThreadPoolFactory.factory().addRunnable(AsyncRunner.of(actuator, context, phase));
        return context;
    }

    private static class AsyncRunner implements Runnable {

        private Actuator<Context, Context> actuator;
        private Context context;
        private Phase phase;

        private AsyncRunner(Actuator<Context, Context> actuator, Context context, Phase phase) {
            this.actuator = actuator;
            this.context = context;
            this.phase = phase;
        }

        public static AsyncRunner of(Actuator<Context, Context> actuator, Context context, Phase phase) {
            return new AsyncRunner(actuator, context, phase);
        }

        @Override
        public void run() {
            try {
                actuator.execute(context);
            } catch (Exception e) {
                logger.error("Async execute occur exception!", e);
            }
        }
    }

}
