package rwbykit.flowable.core.service;

import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.runtime.ProcessInstance;

public interface ProcessService<T extends ProcessInstance> {

    T initialize(Process process, Initiator initiator, String bizNo);

    T getByProcessInstanceId(String processInstanceId);

}
