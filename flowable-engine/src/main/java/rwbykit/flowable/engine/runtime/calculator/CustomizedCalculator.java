package rwbykit.flowable.engine.runtime.calculator;

import rwbykit.flowable.engine.Calculator;
import rwbykit.flowable.engine.Result;

public interface CustomizedCalculator<I, O> extends Calculator<I, Result<O>> {
}
