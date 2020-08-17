package rwbykit.flowable.engine.runtime.calculator.approver.polymerization;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.Calculator;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.model.runtime.Approver;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 审批集合计算器
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午4:12:52
 * @version 1.0
 */
@Type(category = Constants.TYPE_CALCULATOR_APPROVER_POLYMERIZATION, type = "Default")
public class ApproverPolymerizationCalculator implements Calculator<List<List<Approver>>, List<Approver>> {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        return approvers.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
