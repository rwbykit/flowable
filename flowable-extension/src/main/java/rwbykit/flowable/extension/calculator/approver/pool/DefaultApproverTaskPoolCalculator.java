package rwbykit.flowable.extension.calculator.approver.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.runtime.calculator.approver.ApprovalProcess;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverTaskPoolCalculator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.core.util.Lists;

import java.util.List;

/**
 * 系统默认任务池
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 上午10:07:59
 */
@Type(category = "ApproverTaskPool", type = "Default")
public class DefaultApproverTaskPoolCalculator implements ApproverTaskPoolCalculator {

    private final static Logger logger = LoggerFactory.getLogger(DefaultApproverTaskPoolCalculator.class);

    @Override
    public List<Approver> calculate(ApprovalProcess pool) {
        logger.info("流程[{}], 节点[{}], 业务流水号[{}]采用系统默认审批池人员计算方式", pool.getProcessId(), pool.getNodeId(), pool.getBizNo());
        return Lists.newArrayList(Approver.of(Constants.POOL_USER, Constants.POOL_USER_NAME));
    }

}
