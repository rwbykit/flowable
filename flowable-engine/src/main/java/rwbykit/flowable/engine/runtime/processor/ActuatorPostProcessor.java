package rwbykit.flowable.engine.runtime.processor;

import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.Supported;
import rwbykit.flowable.core.TypesSupported;
import rwbykit.flowable.core.enumeration.Phase;

public interface ActuatorPostProcessor extends PostProcessor<Context, Context>, Supported<String>, TypesSupported<Phase> {
}
