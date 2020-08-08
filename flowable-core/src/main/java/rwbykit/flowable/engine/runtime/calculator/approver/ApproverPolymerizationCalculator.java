package rwbykit.flowable.engine.runtime.calculator.approver;

import rwbykit.flowable.engine.Calculator;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.runtime.model.Approver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 审批集合计算器
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午4:12:52
 * @version 1.0
 */
public class ApproverPolymerizationCalculator implements Calculator<List<List<Approver>>, List<Approver>> {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        return approvers.stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
