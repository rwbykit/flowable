package rwbykit.flowable.engine.factory;

import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.beans.factory.support.GenericFactoryAware;
import rwbykit.flowable.engine.runtime.actuator.node.AbstractNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.task.AbstractTaskActuator;
import rwbykit.flowable.engine.util.Asserts;


/**
 * 
 * 执行器工厂
 * 
 * @author Cytus_
 * @since 2018年6月26日 下午2:14:20
 * @version 1.0
 *
 */
public class ActuatorFactory extends GenericFactoryAware {
    
    private static class ActuatorFactoryHolder {
        private static final ActuatorFactory FACTORY = new ActuatorFactory();
    }

    private ActuatorFactory() {}

    public final static ActuatorFactory factory() {
        return ActuatorFactoryHolder.FACTORY;
    }
    
    /**
     * 获取节点执行器
     * @param nodeType 节点类型
     * @return
     */
    public AbstractNodeActuator getNodeActuator(String nodeType) {
        Asserts.nonEmpty(nodeType, "Unsupported Node Actuator type[{}]!", nodeType);
        return getBeanFactory().getBean(Constants.TYPE_ACTUATOR_NODE, nodeType);
    }
    
    /**
     * 获取任务执行器
     * @param taskType 任务类型
     * @return
     */
    public AbstractTaskActuator getTaskActuator(String taskType) {
        Asserts.nonEmpty(taskType, "Unsupported Task Actuator type[{}]!", taskType);
        return getBeanFactory().getBean(Constants.TYPE_ACTUATOR_TASK, taskType);
    }
    
    /**
     * 流程执行器
     * @param actuatorType 流程执行器类型
     * @return
     */
    /*public AbstractProcessActuator getProcessActuator(ActuatorType actuatorType) {
        if (Objects.isNull(actuatorType)) {
            String errorMsg = FlowableHelper.formatMessage("Unsupported Process Actuator type[{}]!", actuatorType);
            logger.error(errorMsg);
            throw new UnsupportedArgumentException(errorMsg);
        }
        return getObjectFactory().getObject(Constants.TYPE_NODE, "");
    }*/
    
}
