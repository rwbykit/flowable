package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import java.util.List;

/**
 * 范围查询用户服务
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月27日 下午5:22:37
 */
public interface AssigneeService {

    /**
     * 通过ID查询
     *
     * @param ids
     * @return
     */
    List<AssigneeInformation> getAssigneeInformation(String... ids);

}
