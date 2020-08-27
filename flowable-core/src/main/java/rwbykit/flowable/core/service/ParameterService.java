package rwbykit.flowable.core.service;

import rwbykit.flowable.core.model.runtime.ParameterInstance;

import java.util.Map;

public interface ParameterService<T extends ParameterInstance> {

    T initialize(String processInstanceId, Map<String, Object> params);

    T getByProcessInstanceId(String processInstanceId);

}
