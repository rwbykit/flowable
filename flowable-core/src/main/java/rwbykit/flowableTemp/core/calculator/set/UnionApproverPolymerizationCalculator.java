package rwbykit.flowableTemp.core.calculator.set;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.annotation.NovaMapper;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowable.engine.runtime.calculator.approver.ApproverPolymerizationCalculator;
import rwbykit.flowableTemp.core.enumeration.ApproverSetType;
import rwbykit.flowableTemp.core.util.FlowableHelper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 并集
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午3:48:03
 * @version 1.0
 */
@NovaMapper(enumClass = ApproverSetType.class, enumValue = "U", mapperName = Constants.APRV_SET_CALCULATOR)
public class UnionApproverPolymerizationCalculator implements ApproverPolymerizationCalculator {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        return FlowableHelper.nonNullOrEmpty(approvers) ?
                approvers.stream().flatMap(List::stream).distinct().collect(Collectors.toList()) :
                Collections.emptyList();
    }

}
