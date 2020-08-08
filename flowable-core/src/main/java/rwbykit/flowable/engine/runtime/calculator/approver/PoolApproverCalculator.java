package rwbykit.flowable.engine.runtime.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;

/**
 * 任务池方式审批人员计算器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年6月21日 下午3:09:21
 */
public class PoolApproverCalculator extends GenericApproverCalculator {

    private static Logger logger = LoggerFactory.getLogger(PoolApproverCalculator.class);

    ApproverTaskPoolCalculator poolCalculator;

    @Override
    public List<Approver> calculate(Context context) throws FlowableException {

        logger.info("节点实例[{}], 节点[{}]任务池计算当前处理人开始", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());

        ApprovalProcess pool = ApprovalProcess.builder()
                .bizNo(context.getCurrentInstance().getBizNo())
                .processId(context.getCurrentInstance().getProcessId())
                .nodeId(context.getCurrentInstance().getNodeId())
                .build();
        List<Approver> apprvoers = poolCalculator.calculate(pool);

        logger.info("节点实例[{}], 节点[{}]任务池计算当前处理人结束, 当前节点配置为任务池模式!", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());

        return apprvoers;
    }

}
