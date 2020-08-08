package rwbykit.flowable.engine.runtime.calculator.approver.polymerization;

import rwbykit.flowable.engine.runtime.calculator.approver.ApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.util.Collections;
import rwbykit.flowable.engine.util.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 并集
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午3:48:03
 * @version 1.0
 */
public class UnionApproverPolymerizationCalculator extends ApproverPolymerizationCalculator {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        return Collections.nonEmpty(approvers) ?
                approvers.stream().flatMap(List::stream).distinct().collect(Collectors.toList()) :
                Lists.emptyList();
    }

}
