package rwbykit.flowable.core.model;

import java.util.List;

/**
 * 分配人实体对象
 *
 * @author Cytus_
 */
public interface Assignment {

    public long getMaxNumber();


    public String getAssignType();


    public String getPolymerizationType();


    public List<Assignee> getAssignees();


    public String getAssigneeType();


    public AssignmentMode getAfterAssignmentMode();


    public long getAssignQuantity();

}
