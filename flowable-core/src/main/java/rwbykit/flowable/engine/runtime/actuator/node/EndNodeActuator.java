package rwbykit.flowable.engine.runtime.actuator.node;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;

/**
 * 结束节点
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 上午8:51:25
 */
public class EndNodeActuator extends AbstractNodeActuator {

    private final static Logger logger = LoggerFactory.getLogger(EndNodeActuator.class);

    @Override
    public Context nodeExecute(Context context) {
        logger.info("流程实例[{}], 结束节点执行开始!", context.getCurrentInstance().getProcessInstanceId());
        context.getCurrentInstance().setNodeStatus(Constants.STATUS_END);
        logger.info("流程实例[{}], 结束节点执行结束!", context.getCurrentInstance().getProcessInstanceId());
        return context;
    }

}
