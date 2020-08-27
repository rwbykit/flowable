package rwbykit.flowable.engine.model;

import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.model.runtime.Approver;

public class ApprovalInstanceImpl implements ApprovalInstance {

    private String approvalInstanceId;

    /**
     * 审批人信息
     */
    private Approver approvers;

    /**
     * 流程实例
     */
    private String processInstanceId;

    /**
     * 节点示例
     */
    private String nodeInstanceId;

    /**
     * 审批意见
     */
    private String opinion;

    /**
     * 审批结论
     */
    private String conclusion;

    /**
     * 是否已操作完成
     */
    private String completed;

    /**
     * 操作时间 格式yyyy-MM-dd HH:mm:ss
     */
    private String optime;


    @Override
    public Approver getApprovers() {
        return approvers;
    }

    public void setApprovers(Approver approvers) {
        this.approvers = approvers;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    @Override
    public String getNodeInstanceId() {
        return nodeInstanceId;
    }

    public void setNodeInstanceId(String nodeInstanceId) {
        this.nodeInstanceId = nodeInstanceId;
    }

    @Override
    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    @Override
    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    @Override
    public String getOptime() {
        return optime;
    }

    public void setOptime(String optime) {
        this.optime = optime;
    }

    @Override
    public String getApprovalInstanceId() {
        return this.approvalInstanceId;
    }

    public void setApprovalInstanceId(String approvalInstanceId) {
        this.approvalInstanceId = approvalInstanceId;
    }


}
