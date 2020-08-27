package rwbykit.flowable.core.model.runtime;

public interface ApprovalInstance {

    String getNodeInstanceId();

    Approver getApprover();

    String getOpinion();

    String getConclusion();

    String getCompleted();

    String getOptime();

    String getApprovalInstanceId();

    public static class Approval implements ApprovalInstance {

        private String approvalInstanceId;

        /**
         * 审批人信息
         */
        private Approver approver;

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
        public Approver getApprover() {
            return approver;
        }

        public void setApprover(Approver approver) {
            this.approver = approver;
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

        public static ApprovalBuilder builder() {
            return new ApprovalBuilder();
        }

        public static final class ApprovalBuilder {
            private String approvalInstanceId;
            private Approver approver;
            private String processInstanceId;
            private String nodeInstanceId;
            private String opinion;
            private String conclusion;
            private String completed;
            private String optime;

            private ApprovalBuilder() {
            }

            public ApprovalBuilder approvalInstanceId(String approvalInstanceId) {
                this.approvalInstanceId = approvalInstanceId;
                return this;
            }

            public ApprovalBuilder approver(Approver approver) {
                this.approver = approver;
                return this;
            }

            public ApprovalBuilder processInstanceId(String processInstanceId) {
                this.processInstanceId = processInstanceId;
                return this;
            }

            public ApprovalBuilder nodeInstanceId(String nodeInstanceId) {
                this.nodeInstanceId = nodeInstanceId;
                return this;
            }

            public ApprovalBuilder opinion(String opinion) {
                this.opinion = opinion;
                return this;
            }

            public ApprovalBuilder conclusion(String conclusion) {
                this.conclusion = conclusion;
                return this;
            }

            public ApprovalBuilder completed(String completed) {
                this.completed = completed;
                return this;
            }

            public ApprovalBuilder optime(String optime) {
                this.optime = optime;
                return this;
            }

            public Approval build() {
                Approval approval = new Approval();
                approval.setApprovalInstanceId(approvalInstanceId);
                approval.setApprover(approver);
                approval.setProcessInstanceId(processInstanceId);
                approval.setNodeInstanceId(nodeInstanceId);
                approval.setOpinion(opinion);
                approval.setConclusion(conclusion);
                approval.setCompleted(completed);
                approval.setOptime(optime);
                return approval;
            }
        }

    }

}
