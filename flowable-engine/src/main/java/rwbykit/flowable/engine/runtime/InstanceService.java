package rwbykit.flowable.engine.runtime;

import rwbykit.flowable.core.Context;

public interface InstanceService {

    void initialize(Context context);

    void afterSet(Context context);

}
