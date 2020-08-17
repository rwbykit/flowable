package rwbykit.flowable.extension.calculator.approver.assignee;

import rwbykit.flowable.core.TypesSupported;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.AssigneeInformation;
import rwbykit.flowable.core.current.Initiator;

import java.util.List;

/**
 * 范围层级接口
 *
 * @author Cytus_
 * @since 2018年12月27日 下午4:59:13
 * @version 1.0
 */
public interface InitiatorRelationshipService extends TypesSupported<String> {

    /**
     * 获得发起人相关的相关信息
     * @param initiator
     * @return
     */
    List<AssigneeInformation> getAssigneeInformations(Initiator initiator);

}
