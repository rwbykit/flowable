package rwbykit.flowable.engine.runtime.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Actuator;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.Scheduler;
import rwbykit.flowable.core.enumeration.Phase;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.engine.runtime.processor.ActuatorPostProcessor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 抽象类流程调度器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月18日 上午8:39:58
 */
public abstract class AbstractActuatorScheduler implements Scheduler<Context, Actuator<Context, Context>, Context> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractActuatorScheduler.class);
    private List<ActuatorPostProcessor> actuatorPostProcessors;
    private Map<String, List<ActuatorPostProcessor>> actuatorPostProcessorMap;

    public AbstractActuatorScheduler(List<ActuatorPostProcessor> actuatorPostProcessors) {
        this.actuatorPostProcessors = Lists.immutable(actuatorPostProcessors);
        this.actuatorPostProcessorMap = new ConcurrentHashMap<>();
    }

    protected Context doSchedule(Actuator<Context, Context> actuator, Context context, Phase phase) throws FlowableException {
        beforeActuator(context, phase);
        context = actuator.execute(context);
        afterActuator(context, phase);
        return context;
    }

    protected Context beforeActuator(Context context, Phase phase) {
        List<ActuatorPostProcessor> supportedActuatorPostProcessors = getSupportedActuatorPostProcessor(context, phase);
        if (Collections.nonEmpty(supportedActuatorPostProcessors)) {
            for (ActuatorPostProcessor postProcessor : supportedActuatorPostProcessors) {
                context = postProcessor.beforePostProcessor(context);
            }
        }
        return context;
    }

    protected Context afterActuator(Context context, Phase phase) {
        List<ActuatorPostProcessor> supportedActuatorPostProcessors = getSupportedActuatorPostProcessor(context, phase);
        if (Collections.nonEmpty(supportedActuatorPostProcessors)) {
            for (ActuatorPostProcessor postProcessor : supportedActuatorPostProcessors) {
                context = postProcessor.afterPostProcessor(context);
            }
        }
        return context;
    }

    private List<ActuatorPostProcessor> getSupportedActuatorPostProcessor(Context context, Phase phase) {
        String phaseId = getPhaseId(context, phase);
        String supportedId = Strings.builder(phase.name(), "-", phaseId);
        if (!actuatorPostProcessorMap.containsKey(supportedId)) {
            List<ActuatorPostProcessor> actuatorPostProcessors = this.actuatorPostProcessors.stream()
                    .filter(actuatorPostProcessor -> phase.equals(actuatorPostProcessor.getSupportedType()) && actuatorPostProcessor.isSupported(phaseId))
                    .collect(Collectors.toList());
            actuatorPostProcessorMap.put(supportedId, Lists.immutable(actuatorPostProcessors));
        }
        return actuatorPostProcessorMap.get(supportedId);
    }

    protected String getPhaseId(Context context, Phase phase) {
        String phaseId;
        switch (phase) {
            case PROCESS:
                phaseId = context.getCurrentInstance().getProcessId();
                break;
            case NODE:
                phaseId = context.getCurrentInstance().getNodeId();
                break;
            case TASK:
                phaseId = context.getCurrentInstance().getTaskId();
                break;
            default:
                phaseId = Strings.EMPTY;
        }
        return phaseId;
    }

}
