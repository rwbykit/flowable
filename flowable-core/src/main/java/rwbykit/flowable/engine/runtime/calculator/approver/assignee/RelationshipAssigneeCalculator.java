/*
package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.annotation.NovaMapper;
import com.war3.nova.beans.Approver;
import rwbykit.flowableTemp.core.runtime.Context;
import com.war3.nova.beans.NvApprover;
import rwbykit.flowableTemp.core.enumeration.AprvRangeType;
import rwbykit.flowable.engine.util.Asserts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * 关联关系审批人配置
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午5:36:26
 * @version 1.0                                                                                                 
 *//*

@NovaMapper(enumClass = AprvRangeType.class, enumValue = "N", mapperName = Constants.APRV_RANGE_CALCULATOR)
public class RelationshipAssigneeCalculator extends GenericAssigneeCalculator {

    private final static Logger logger = LoggerFactory.getLogger(RelationshipAssigneeCalculator.class);
    
    @Autowired
    RelationshipHierarchyService relationshipHierarchyService;
    
    @Override
    public List<Approver> calculate(Context context) throws FlowableException {
        InitiatingStaff startUser = new InitiatingStaff(context.getStartUser(), context.getOrgId(), context.getInstOrgId());
        NvApprover nvApprover = approver.get();
        
        RelationshipHierarchyType type = RelationshipHierarchyType.get(nvApprover.getRange());
        Asserts.assertNull(type, "流程[{}]当前配置的关联关系审批人信息未配置范围", context.getProcessId());
        logger.info("流程实例[{}], 节点[{}], 当前配置的关联关系类型为:", context.getProcessInstId(), context.getNodeId(), type);
        
        List<AssigneeInformation> approvers = null;
        switch (type) {
            case _01 : approvers = relationshipHierarchyService.getCurrentOrg(startUser); break;
            case _02 : approvers = relationshipHierarchyService.getSuperOrg(startUser); break;
            case _03 : approvers = relationshipHierarchyService.getSuperSubOrg(startUser); break;
            case _04 : approvers = relationshipHierarchyService.getCurrAndSuperOrg(startUser); break;
            case _05 : approvers = relationshipHierarchyService.getDoubleSuperOrg(startUser); break;
            case _06 : approvers = relationshipHierarchyService.getSuperAndDoubleSuperOrg(startUser); break;
            case _07 : approvers = relationshipHierarchyService.getDoubleSuperSubOrg(startUser); break;
            case _08 : approvers = relationshipHierarchyService.getSubOrg(startUser); break;
            case _09 : approvers = relationshipHierarchyService.getProcessStartUser(startUser); break;
            case _10 : approvers = relationshipHierarchyService.getApprovedUser(context.getProcessInstId()); break;
            case _11 : approvers = relationshipHierarchyService.getOrgLeader(startUser); break;
            case _12 : approvers = relationshipHierarchyService.getCurrDept(startUser); break;
        }
        
        return approvers.parallelStream().map(s -> userToApprover(s)).collect(Collectors.toList());
        
    }

    @Override
    public AssigneeService getRangeService() {
        return null;
    }
    
    
}
*/
