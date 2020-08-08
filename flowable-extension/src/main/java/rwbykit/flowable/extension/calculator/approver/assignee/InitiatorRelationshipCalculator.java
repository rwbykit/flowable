package rwbykit.flowable.extension.calculator.approver.assignee;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.AssigneeInformation;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.GenericAssigneeCalculator;
import rwbykit.flowable.engine.runtime.current.CurrentInstance;
import rwbykit.flowable.engine.runtime.current.Initiator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.util.Collections;
import rwbykit.flowable.engine.util.Lists;
import rwbykit.flowable.engine.util.Maps;
import rwbykit.flowable.model.Assignee;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 关联关系审批人配置
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午5:36:26
 * @version 1.0                                                                                                 
 */
public class InitiatorRelationshipCalculator extends GenericAssigneeCalculator {

    private final static Logger logger = LoggerFactory.getLogger(InitiatorRelationshipCalculator.class);

    private static Map<String, InitiatorRelationshipService> INITIATOR_RELATIONSHIP_SERVICE_MAP;

    public InitiatorRelationshipCalculator(List<InitiatorRelationshipService> initiatorRelationshipServices) {
        if (Collections.nonEmpty(initiatorRelationshipServices)) {
            Map<String, InitiatorRelationshipService> initiatorRelationshipServiceMap = Maps.newConcurrentHashMap(initiatorRelationshipServices.size());
            initiatorRelationshipServices.stream().forEach(service -> {
                initiatorRelationshipServiceMap.putIfAbsent(service.getSupportedType(), service);
            });
            INITIATOR_RELATIONSHIP_SERVICE_MAP = Maps.unmodifiableMap(initiatorRelationshipServiceMap);
        } else {
            INITIATOR_RELATIONSHIP_SERVICE_MAP = Maps.emptyMap();
        }
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
