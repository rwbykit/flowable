package rwbykit.flowable.engine.repository;

import rwbykit.flowable.engine.model.ProcessInstanceImpl;

public interface ProcessRepository {

    int insert(ProcessInstanceImpl processInstance);

}
