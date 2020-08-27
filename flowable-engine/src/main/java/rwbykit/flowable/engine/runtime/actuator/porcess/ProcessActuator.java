package rwbykit.flowable.engine.runtime.actuator.porcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.runtime.actuator.node.AbstractNodeActuator;
import rwbykit.flowable.engine.runtime.selector.NodeSelector;

/**
 * 流程处理器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午1:30:36
 */
@Type(category = Constants.TYPE_ACTUATOR_PROCESS, type = Constants.DEFAULT)
public class ProcessActuator extends AbstractProcessActuator {

    private final static Logger logger = LoggerFactory.getLogger(ProcessActuator.class);

    @Override
    public Context doExecute(Context context) throws FlowableException {

        try {
            AbstractNodeActuator nodeActuator = GenericObjectFactory.factory().getNodeActuator(null);
            logger.info("流程[{}], 流程实例[{}]调用开始", context.getCurrentInstance().getProcessId(), context.getCurrentInstance().getProcessInstanceId());
            context = super.schedule(nodeActuator::execute, context);
            logger.info("流程[{}], 流程实例[{}]调用结束", context.getCurrentInstance().getProcessId(), context.getCurrentInstance().getProcessInstanceId());

        } catch (Exception e) {
            throw e;
        }

        return context;
    }


}
