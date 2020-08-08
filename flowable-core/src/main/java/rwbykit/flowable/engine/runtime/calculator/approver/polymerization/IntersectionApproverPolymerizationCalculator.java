package rwbykit.flowable.engine.runtime.calculator.approver.polymerization;

import rwbykit.flowable.engine.runtime.calculator.approver.ApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.model.Approver;
import rwbykit.flowableTemp.core.util.FlowableHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 交集
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月17日 下午3:47:47
 */
public class IntersectionApproverPolymerizationCalculator extends ApproverPolymerizationCalculator {

    @Override
    public List<Approver> calculate(List<List<Approver>> approvers) {
        if (FlowableHelper.nonNullOrEmpty(approvers)) {
            Map<Approver, Integer> numberMap = new ConcurrentHashMap(8);
            approvers.stream().flatMap(List::stream).forEach(approver -> numberMap.put(approver, numberMap.getOrDefault(approver, 0) + 1));
            return numberMap.entrySet().stream().filter(entry -> entry.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

}
