package rwbykit.flowable.core;

public class Constants {

    // 状态-运行中
    public static String STATUS_RUNNING = "01";
    // 状态-审批中
    public static String STATUS_APPROVAL = "03";
    // 状态-拒绝
    public static String STATUS_REFUSE = "02";
    // 状态-异常
    public static String STATUS_EXCEPTION = "04";
    // 状态-结束
    public static String STATUS_END = "99";

    /**
     * 节点ID
     */
    public final static String NODE_ID = "nodeId";

    public final static String TASK_ID = "taskId";


    public final static String TYPE_RUNNER = "Runner";

    public final static String TYPE_SELECTOR = "Selector";

    public final static String TYPE_SCHEDULER = "Scheduler";

    public final static String TYPE_CALCULATOR = "Calculator";

    public final static String SELECTOR_TASK = "selectorTask";

    public final static String TYPE_ACTUATOR_NODE = "ActuatorNode";
    public final static String TYPE_ACTUATOR_PROCESS = "ActuatorProcess";
    public final static String TYPE_ACTUATOR_TASK = "ActuatorTask";

    public final static String TYPE_CALCULATOR_ASSIGNEE = "CalculatorAssignee";
    public final static String TYPE_CALCULATOR_APPROVER_POLYMERIZATION = "CalculatorApproverPolymerization";
    public final static String TYPE_CALCULATOR_APPROVER = "CalculatorApprover";

    public final static String TYPE_CONSTRUCTOR = "Constructor";

    public final static String DEFAULT = "Default";



    public final static String TYPE_SELECTOR_NODE = "NodeSelector";

    public final static String SCHEDULER_TYPE_SYNC = "Sync";
    public final static String SCHEDULER_TYPE_ASYNC = "Async";

    public final static String TYPE_NODE_AUTO = "Auto";



    // 默认的任务池用户编号
    public static String POOL_USER = "PoolUser";

    // 默认的任务池用户名
    public static String POOL_USER_NAME = "任务池";

    /**
     * 通用是否 - 是
     */
    public final static String COMMMON_YESNO_YES = "1";

    /**
     * 通用是否 - 否
     */
    public final static String COMMON_YESNO_NO = "0";


    // 审批状态-通过
    public static String ARRV_RESULT_PASS = "01";

    // 审批状态-拒绝
    public static String ARRV_RESULT_REFUSE = STATUS_REFUSE;

    /**
     * 系统默认错误码
     */
    public final static String SYSTEM_ERROR_CODE = "999999";

    /**
     * 系统默认错误信息
     */
    public final static String SYSTEM_ERROR_MESSAGE = "System Exception!";

    public final static String NEXT_NODE_ID = "NextNodeId";

    public final static String NEXT_APPROVERS = "NextApprovers";

    public final static String CURRENT_APPROVAL_INSTANCE = "CurrentApprovalInstance";

}
