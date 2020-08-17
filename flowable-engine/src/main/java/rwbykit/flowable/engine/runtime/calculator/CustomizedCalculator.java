package rwbykit.flowable.engine.runtime.calculator;

import rwbykit.flowable.core.Calculator;
import rwbykit.flowable.core.Result;

public interface CustomizedCalculator<I, O> extends Calculator<I, Result<O>> {
}
