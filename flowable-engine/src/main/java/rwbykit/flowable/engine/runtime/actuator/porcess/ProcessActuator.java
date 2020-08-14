package rwbykit.flowable.engine.runtime.actuator.porcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.engine.factory.RuntimeObjectFactory;
import rwbykit.flowable.engine.runtime.actuator.node.AbstractNodeActuator;

/**
 * 流程处理器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午1:30:36
 */
public class ProcessActuator extends AbstractProcessActuator {

    private final static Logger logger = LoggerFactory.getLogger(ProcessActuator.class);

    @Override
    public Context doExecute(Context context) throws FlowableException {

        try {
            AbstractNodeActuator nodeActuator = RuntimeObjectFactory.factory().getNodeActuator(null);
            logger.info("流程[{}], 流程实例[{}]调用开始", context.getCurrentInstance().getProcessId(), context.getCurrentInstance().getProcessInstanceId());
            context = super.schedule(nodeActuator::execute, context, "sync");
            logger.info("流程[{}], 流程实例[{}]调用结束", context.getCurrentInstance().getProcessId(), context.getCurrentInstance().getProcessInstanceId());

        } catch (Exception e) {
            throw e;
        }

        return context;
    }

}
