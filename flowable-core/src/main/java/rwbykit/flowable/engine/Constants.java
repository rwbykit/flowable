package rwbykit.flowable.engine;

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

    public final static String TYPE_CALCULATOR = "Calculator";

    public final static String SELECTOR_TASK = "selectorTask";

    public final static String TYPE_ACTUATOR_NODE = "ActuatorNode";
    public final static String TYPE_ACTUATOR_PROCESS = "ActuatorProcess";
    public final static String TYPE_ACTUATOR_TASK = "ActuatorTask";


    // 默认的任务池用户编号
    public static String POOL_USER = "PoolUser";

    // 默认的任务池用户名
    public static String POOL_USER_NAME = "任务池";

}
