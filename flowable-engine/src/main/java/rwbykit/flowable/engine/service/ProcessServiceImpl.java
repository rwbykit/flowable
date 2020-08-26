package rwbykit.flowable.engine.service;

import rwbykit.flowable.core.current.Initiator;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.service.ProcessService;
import rwbykit.flowable.engine.model.ProcessInstanceImpl;
import rwbykit.flowable.engine.repository.ProcessRepository;

public class ProcessServiceImpl implements ProcessService<ProcessInstanceImpl> {

    private final ProcessRepository processRepository;

    public ProcessServiceImpl(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @Override
    public ProcessInstanceImpl initialize(Process process, Initiator initiator, String bizNo) {
        ProcessInstanceImpl processInstance = null;
        processRepository.insert(processInstance);
        return processInstance;
    }

    @Override
    public ProcessInstanceImpl getByProcessInstanceId(String processInstanceId) {
        return null;
    }
}
