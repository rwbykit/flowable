package rwbykit.flowable.engine.runtime;

import rwbykit.flowable.engine.Context;

public interface InstanceService {

    void initialize(Context context);

    void afterSet(Context context);

}
