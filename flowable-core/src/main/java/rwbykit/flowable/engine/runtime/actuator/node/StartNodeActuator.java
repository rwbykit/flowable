package rwbykit.flowable.engine.runtime.actuator.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;

/**
 * 开始节点
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 上午8:53:31
 */
public class StartNodeActuator extends AbstractNodeActuator {

    private static final Logger logger = LoggerFactory.getLogger(StartNodeActuator.class);

    @Override
    public Context nodeExecute(Context context) {
        logger.info("流程实例[{}], 开始节点执行开始!", context.getCurrentInstance().getProcessInstanceId());
        context.getCurrentInstance().setNodeStatus(Constants.STATUS_END);
        logger.info("流程实例[{}], 开始节点执行结束!", context.getCurrentInstance().getProcessInstanceId());
        return context;
    }


}