package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowableTemp.annotation.NovaMapper;
import rwbykit.flowableTemp.core.runtime.model.ApprovalInstance;
import com.war3.nova.beans.NvMulti;
import com.war3.nova.beans.NvNode;
import rwbykit.flowableTemp.core.ProcessConfigContext;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowableTemp.core.enumeration.ApproverType;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;
import rwbykit.flowableTemp.core.enumeration.MultiRuleType;
import rwbykit.flowable.engine.factory.RunnerFactory;
import rwbykit.flowableTemp.core.service.util.Approvals;
import rwbykit.flowableTemp.core.util.SpringContexts;
import rwbykit.flowableTemp.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 会签审批提交
 * 
 * @author Cytus_
 * @since 2018年12月28日 下午3:17:16
 * @version 1.0
 */
@NovaMapper(enumClass = ApproverType.class, enumValue = "MULTI", mapperName = Constants.ARTIFICIAL_APPROVAL_SUBMIT_SERVICE)
public class MultiJoinSignArtiAprvSubmitService implements ArtificialApprovalSubmitService {
    
    private final static Logger logger = LoggerFactory.getLogger(MultiJoinSignArtiAprvSubmitService.class);

    @Override
    @SuppressWarnings("unchecked")
    public ArtificialApprovalSubmitResult doSubmit(String processInstId, String nodeInstId) throws FlowableException {
        
        Result<?> runnerResult ;
        NvNode nvNode = ProcessConfigContext.getContext().getCurrentNode();
        NvMulti nvMulti = nvNode.getApproval().getMulit();
        List<ApprovalInstance> insApprovals = Approvals.getNodeAllApproval(nodeInstId);
        MultiJoinSignParameter parameter = new MultiJoinSignParameter(
                ProcessConfigContext.getContext().getProcess().getId(), processInstId, nvNode.getId(), nodeInstId, insApprovals);
        
        if (MultiRuleType.compare(MultiRuleType.SYSTEM, nvMulti.getRuleType())) {
            String beanName = SystemMultiRuleType.valueOf(nvMulti.getValue()).getValue();
            CustomizedMultiJoinSignRule rule = SpringContexts.getBean(beanName, CustomizedMultiJoinSignRule.class);
            logger.debug("节点实例[{}], 节点[{}]使用系统预定义会签规则执行开始", nodeInstId, nvNode.getId());
            runnerResult = rule.execute(parameter);
            
        } else {
            logger.debug("节点实例[{}], 节点[{}]自定义会签规则执行开始", nodeInstId, nvNode.getId());
            Runner<MultiJoinSignParameter, Result<?>> runner = (Runner<MultiJoinSignParameter, Result<?>>)
                    RunnerFactory.factory().getObjectActuator(ExecuteMode.get(nvMulti.getExecutionMode()));
            runnerResult = runner.run(nvMulti.getValue(), parameter);
        }
        
        if (!runnerResult.isSuccess()) {
            throw new FlowableException(runnerResult.errorCode(), runnerResult.errorMessage());
        } else {
            boolean result = Boolean.valueOf(Strings.replaceNullByObj(runnerResult.getResult()));
            logger.info("节点实例[{}], 节点[{}], 为审批提交，审批结果为：{}", nodeInstId, nvNode.getId(), result ? "拒绝" : "通过");
            return result ? ArtificialApprovalSubmitResult.PASS : ArtificialApprovalSubmitResult.REFUSE;
        }
        
        
    }

}
