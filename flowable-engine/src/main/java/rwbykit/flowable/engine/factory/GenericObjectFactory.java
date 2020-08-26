package rwbykit.flowable.engine.factory;

import rwbykit.flowable.core.factory.support.GenericFactoryAware;
import rwbykit.flowable.core.Actuator;
import rwbykit.flowable.core.Calculator;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Constructor;
import rwbykit.flowable.core.Scheduler;
import rwbykit.flowable.core.Selector;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.core.util.Asserts;

public class GenericObjectFactory extends GenericFactoryAware {

    private static class FactoryHolder {
        private static final GenericObjectFactory RUNTIME_OBJECT_FACTORY = new GenericObjectFactory();
    }

    private GenericObjectFactory() {
    }

    public final static GenericObjectFactory factory() {
        return GenericObjectFactory.FactoryHolder.RUNTIME_OBJECT_FACTORY;
    }

    public <I, O, R extends Actuator<I, O>> R getProcessActuator(String processType) {
        return getActuator(Constants.TYPE_ACTUATOR_PROCESS, processType);
    }

    /**
     * 获取节点执行器
     *
     * @param nodeType 节点类型
     * @return
     */
    public <I, O, R extends Actuator<I, O>> R getNodeActuator(String nodeType) {
        return getActuator(Constants.TYPE_ACTUATOR_NODE, nodeType);
    }

    /**
     * 获取任务执行器
     *
     * @param taskType 任务类型
     * @return
     */
    public <I, O, R extends Actuator<I, O>> R getTaskActuator(String taskType) {
        return getActuator(Constants.TYPE_ACTUATOR_TASK, taskType);
    }


    private <I, O, R extends Actuator<I, O>> R getActuator(String category, String type) {
        Asserts.nonEmpty(type, "Unsupported Actuator category[{}]!", category);
        Asserts.nonEmpty(type, "Unsupported Actuator type[{}]!", type);
        return getObjectFactory().getObject(category, type);
    }


    /**
     * 获得审批范围计算
     *
     * @param approverRangeType
     * @return
     */
    public <I, O, R extends Calculator<I, O>> R getApproverRangeCalculator(String approverRangeType) {
        return getCalculator(Constants.TYPE_CALCULATOR_ASSIGNEE, approverRangeType);
    }

    /**
     * 获得审批人集合计算
     *
     * @param polymerizationType
     * @return
     */
    public <I, O, R extends Calculator<I, O>> R getApproverPolymerizationCalculator(String polymerizationType) {
        return getCalculator(Constants.TYPE_CALCULATOR_APPROVER_POLYMERIZATION, polymerizationType);
    }

    /**
     * 审批人员计算类型集合计算
     *
     * @param approverType
     * @return
     */
    public <I, O, R extends Calculator<I, O>> R getApproverCalculator(String approverType) {
        return getCalculator(Constants.TYPE_CALCULATOR_APPROVER, approverType);
    }

    private <I, O, R extends Calculator<I, O>> R getCalculator(String category, String type) {
        Asserts.nonEmpty(type, "Unsupported Calculator category[{}]!", category);
        Asserts.nonEmpty(type, "Unsupported Calculator type[{}]!", type);
        return getObjectFactory().getObject(category, type);
    }


    public <V, P> Runner<V, P> getRunner(String executeMode) {
        Asserts.nonEmpty(executeMode, "Unsupported Execution Mode[{}]!", executeMode);
        return this.getObjectFactory().getObject(Constants.TYPE_RUNNER, executeMode);
    }

    public <In, O, Out, R extends Scheduler<In, O, Out>> R getScheduler(String schedulerType) {
        Asserts.nonEmpty(schedulerType, "Unsupported scheduler type[{}]!", schedulerType);
        return this.getObjectFactory().getObject(Constants.TYPE_SCHEDULER, schedulerType);
    }

    public <I, O, R extends Selector<I, O>> R getSelector(String selectorType) {
        Asserts.nonEmpty(selectorType, "Unsupported RouteSelector type[{}]!", selectorType);
        return this.getObjectFactory().getObject(Constants.TYPE_SELECTOR, selectorType);
    }

    /**
     * 构造器进行对象构造
     *
     * @param constructionMode
     * @param value
     * @param <T>
     * @return
     */
    public <T> T getObject(String constructionMode, String value) {
        Constructor<T> constructor = this.getObjectFactory().getObject(Constants.TYPE_CONSTRUCTOR, constructionMode);
        return constructor.build(value);
    }

}
