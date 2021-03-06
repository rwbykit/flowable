package rwbykit.flowable.extension.calculator.approver.assignee;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.current.CurrentInstance;
import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.parser.Assignee;
import rwbykit.flowable.core.model.runtime.Approver;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.AssigneeInformation;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.GenericAssigneeCalculator;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 关联关系审批人配置
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 下午5:36:26
 */
@Type(category = Constants.TYPE_CALCULATOR_ASSIGNEE, type = "Relationship")
public class InitiatorRelationshipCalculator extends GenericAssigneeCalculator {

    private final static Logger logger = LoggerFactory.getLogger(InitiatorRelationshipCalculator.class);

    private static Map<String, InitiatorRelationshipService> INITIATOR_RELATIONSHIP_SERVICE_MAP;

    public InitiatorRelationshipCalculator(List<InitiatorRelationshipService> initiatorRelationshipServices) {
        Map<String, InitiatorRelationshipService> initiatorRelationshipServiceMap = null;
        if (Collections.nonEmpty(initiatorRelationshipServices)) {
            initiatorRelationshipServiceMap = Maps.newConcurrentHashMap(initiatorRelationshipServices.size());
            for (InitiatorRelationshipService service : initiatorRelationshipServices) {
                initiatorRelationshipServiceMap.putIfAbsent(service.getSupportedType(), service);
            };
        }
        INITIATOR_RELATIONSHIP_SERVICE_MAP = Maps.immutable(initiatorRelationshipServiceMap);
    }

    @Override
    public List<Approver> doCalculate(Assignee assignee, Initiator initiator, CurrentInstance currentInstance) {
        logger.debug("Current process instance id[{}], node instance id[{}] config releationship value is[{}]",
                currentInstance.getProcessInstanceId(), currentInstance.getNodeInstanceId(), assignee.getValue());
        InitiatorRelationshipService service = INITIATOR_RELATIONSHIP_SERVICE_MAP.get(assignee.getValue());
        if (Objects.nonNull(service)) {
            List<AssigneeInformation> assigneeInformations = service.getAssigneeInformations(initiator);
            return assigneeInformations.stream().map(this::toApprover).collect(Collectors.toList());
        }
        return Lists.emptyList();
    }

}
