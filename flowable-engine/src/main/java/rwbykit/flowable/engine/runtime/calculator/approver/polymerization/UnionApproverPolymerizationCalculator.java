package rwbykit.flowable.engine.runtime.calculator.approver.polymerization;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 并集
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午3:48:03
 * @version 1.0
 */
@Type(category = Constants.TYPE_CALCULATOR_APPROVER_POLYMERIZATION, type = "Union")
public class UnionApproverPolymerizationCalculator extends ApproverPolymerizationCalculator {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        return Collections.nonEmpty(approvers) ?
                approvers.stream().flatMap(List::stream).distinct().collect(Collectors.toList()) :
                Lists.emptyList();
    }

}
