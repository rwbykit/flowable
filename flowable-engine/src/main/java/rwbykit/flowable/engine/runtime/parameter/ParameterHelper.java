package rwbykit.flowable.engine.runtime.parameter;

import rwbykit.flowable.core.model.parser.ValueField;
import rwbykit.flowable.core.model.runtime.ApprovalInstance;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.engine.enumeration.FieldType;
import rwbykit.flowable.core.model.runtime.Approval;
import rwbykit.flowableTemp.core.util.FlowableHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月26日 上午11:03:21
 */
public final class ParameterHelper {


    public final static TaskParameter taskParameter(Context context, List<ValueField> valueFields) {
        return TaskParameter.builder()
                .bizNo(context.getCurrentInstance().getBizNo())
                .params(paramExchange(context, valueFields))
                .build();
    }

    public final static LinkParameter createLinkParameter(Context context, List<ApprovalInstance> approvalInstances, List<ValueField> valueFields) {
        return LinkParameter.builder()
                .bizNo(context.getCurrentInstance().getBizNo())
                .approvalInstances(approvalInstances)
                .processId(context.getCurrentInstance().getProcessId())
                .nodeId(context.getCurrentInstance().getNodeId())
                .params(paramExchange(context, valueFields))
                .build();
    }

    public final static Map<String, Object> paramExchange(Context context, List<ValueField> valueFields) {
        Map<String, Object> dataMap = new ConcurrentHashMap<>(8);
        if (Collections.nonEmpty(valueFields)) {
            Map<String, Object> customParams = toCustomizedParameter(context, valueFields);
            if (FlowableHelper.nonNull(customParams)) {
                dataMap.putAll(customParams);
            }
        }
        return dataMap;

    }

    /**
     * 通过自定义配置转化成需要的配置值
     *
     * @param context
     * @param valueFields
     * @return
     */
    public final static Map<String, Object> toCustomizedParameter(Context context, List<ValueField> valueFields) {
        Map<String, Object> dataMap = new HashMap<>(8);
        if (FlowableHelper.nonNullOrEmpty(valueFields)) {
            valueFields.forEach(valueField -> dataMap.put(valueField.getName(), getValue4ExtParams(context, valueField)));
        }
        return dataMap;
    }


    /**
     * 单个配置转化
     *
     * @param valueField
     * @param context
     * @return
     */
    public final static Object getValue4ExtParams(Context context, ValueField valueField) {
        Object value;
        if (FieldType.compare(FieldType.CONSTANT, valueField.getValueType())) {
            value = valueField.getValue();
        } else {
            value = context.getParam(valueField.getValue());
            if (Objects.isNull(value)) {
                value = context.getContextParam(valueField.getValue());
            }
        }
        return value;
    }

}
