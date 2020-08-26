package rwbykit.flowable.core.model.runtime;

import rwbykit.flowable.core.current.Initiator;

public interface ProcessInstance {

    String getProcessId();

    String getProcessInstanceId();

    String getMainProcessInstanceId();

    String getProcessStatus();

    String getBizNo();

    String getCurrentNodeInstanceId();

    Initiator getInitiator();

    String getVersion();

}
