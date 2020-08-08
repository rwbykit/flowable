package rwbykit.flowable.engine.factory;

import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.beans.factory.support.GenericFactoryAware;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.AssigneeCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverPolymerizationCalculator;
import rwbykit.flowable.engine.util.Asserts;

/**
 * 计算器工厂类
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午3:24:10
 */
public class CalculatorFactory extends GenericFactoryAware {

    private static class CalculatorFactoryHolder {
        private static final CalculatorFactory FACTORY = new CalculatorFactory();
    }

    private CalculatorFactory() {
    }

    public final static CalculatorFactory factory() {
        return CalculatorFactoryHolder.FACTORY;
    }

    /**
     * 获得审批范围计算
     *
     * @param approverRangeType
     * @return
     */
    public AssigneeCalculator getApproverRangeCalculator(String approverRangeType) {
        Asserts.nonEmpty(approverRangeType, "Unsupported Approver Range type[{}]!", approverRangeType);
        return getBeanFactory().getBean(Constants.TYPE_CALCULATOR, approverRangeType);
    }

    /**
     * 获得审批人集合计算
     *
     * @param polymerizationType
     * @return
     */
    public ApproverPolymerizationCalculator getApproverPolymerizationCalculator(String polymerizationType) {
        Asserts.nonEmpty(polymerizationType, "Unsupported Approver Polymerization type[{}]!", polymerizationType);
        return getBeanFactory().getBean(Constants.TYPE_CALCULATOR, polymerizationType);
    }

    /**
     * 审批人员计算类型集合计算
     *
     * @param approverType
     * @return
     */
    public ApproverCalculator getApproverCalculator(String approverType) {
        Asserts.nonEmpty(approverType, "Unsupported Approver type[{}]!", approverType);
        return getBeanFactory().getBean(Constants.TYPE_CALCULATOR, approverType);
    }

}
