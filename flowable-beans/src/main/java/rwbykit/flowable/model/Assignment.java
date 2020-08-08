package rwbykit.flowable.model;

import java.util.List;

/**
 * 分配人实体对象
 * @author Cytus_
 */
public class Assignment {

    /**
     * 每个人员最大处理数量, -1表示无上限
     */
    private long maxNumber  = -1;

    /**
     * 分配数量
     */
    private long assignQuantity = 1;

    /**
     * 分配类型
     * @see rwbykit.flowableTemp.model.enumeration.AssignType
     */
    private String assignType;


    /**
     * 审批人员聚合类型
     * @see rwbykit.flowableTemp.model.enumeration.PolymerizationType
     */
    private String polymerizationType;

    /**
     * 审批人
     */
    private List<Assignee> assignees;

    /**
     * 审批人类型
     * @see rwbykit.flowableTemp.model.enumeration.AssigneeType
     */
    private String assigneeType;

    private AssignmentMode afterAssignmentMode;

    public long getMaxNumber() {
        return maxNumber;
    }

    public void setMaxNumber(long maxNumber) {
        this.maxNumber = maxNumber;
    }

    public String getAssignType() {
        return assignType;
    }

    public void setAssignType(String assignType) {
        this.assignType = assignType;
    }

    public String getPolymerizationType() {
        return polymerizationType;
    }

    public void setPolymerizationType(String polymerizationType) {
        this.polymerizationType = polymerizationType;
    }

    public List<Assignee> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Assignee> assignees) {
        this.assignees = assignees;
    }

    public String getAssigneeType() {
        return assigneeType;
    }

    public void setAssigneeType(String assigneeType) {
        this.assigneeType = assigneeType;
    }

    public AssignmentMode getAfterAssignmentMode() {
        return afterAssignmentMode;
    }

    public void setAfterAssignmentMode(AssignmentMode afterAssignmentMode) {
        this.afterAssignmentMode = afterAssignmentMode;
    }

    public long getAssignQuantity() {
        return assignQuantity;
    }

    public void setAssignQuantity(long assignQuantity) {
        this.assignQuantity = assignQuantity;
    }
}
