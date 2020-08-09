package rwbykit.flowable.engine.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.beans.factory.support.GenericFactoryAware;
import rwbykit.flowable.engine.runtime.scheduler.AbstractProcessScheduler;
import rwbykit.flowable.engine.util.Asserts;

public class SchedulerFactory extends GenericFactoryAware {

    private final static Logger logger = LoggerFactory.getLogger(SelectorFactory.class);

    private static class SchedulerFactoryHandler {
        private static final SchedulerFactory FACTORY = new SchedulerFactory();
    }

    private SchedulerFactory() {
    }

    public final static SchedulerFactory factory() {
        return SchedulerFactoryHandler.FACTORY;
    }

    public AbstractProcessScheduler getScheduler(String schedulerType) {
        Asserts.nonEmpty(schedulerType, "Unsupported scheduler type[{}]!", schedulerType);
        return this.getBeanFactory().getBean(Constants.TYPE_SCHEDULER, schedulerType);
    }

}
