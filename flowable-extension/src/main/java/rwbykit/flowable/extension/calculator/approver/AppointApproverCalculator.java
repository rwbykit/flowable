package rwbykit.flowable.extension.calculator.approver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.engine.runtime.calculator.approver.GenericApproverCalculator;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.model.parser.ArtifactNode;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 
 * 指定的审批人信息
 * 
 * @author Cytus_
 * @since 2018年7月4日 上午8:45:16
 * @version 1.0
 *
 */
@Type(category = Constants.TYPE_CALCULATOR_APPROVER, type = "Appoint")
public class AppointApproverCalculator extends GenericApproverCalculator {
    
    private static Logger logger = LoggerFactory.getLogger(AppointApproverCalculator.class);

    @Override
    public List<Approver> calculate(Context context) throws FlowableException {
        
        logger.info("节点实例[{}], 节点[{}]指定审批人计算当前处理人开始", context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId());
        
        List<Approver> approvers = context.getParam("NextApprover");
        if (Collections.isEmpty(approvers)) {
            handleNonApprover(context.getCurrentInstance().getNodeId(), context.getCurrentInstance().getNodeInstanceId());
        }

        ArtifactNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        long assignQuantity = node.getAssignment().getAssignQuantity();
        List<Approver> approverList = approvers.stream().limit(assignQuantity).collect(Collectors.toList());
        logger.info("节点实例[{}], 节点[{}]指定审批人计算当前处理人结束, 处理人信息为:{}",
                context.getCurrentInstance().getNodeInstanceId(), context.getCurrentInstance().getNodeId(), approverList);
        return approverList;
    }
    
}
