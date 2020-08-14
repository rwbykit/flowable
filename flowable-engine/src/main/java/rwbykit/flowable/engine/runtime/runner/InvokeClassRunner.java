package rwbykit.flowable.engine.runtime.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.factory.RuntimeObjectFactory;
import rwbykit.flowable.core.model.enumeration.ExecuteMode;

/**
 * 
 * 自定义class运行
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午9:44:18
 * @version 1.0
 */
@Type(category = Constants.TYPE_RUNNER, type = "Invoke")
public class InvokeClassRunner<R, P> implements Runner<P, Result<R>> {

    private final static Logger logger = LoggerFactory.getLogger(InvokeClassRunner.class);
    
    @Override
    public Result<R> run(String value, P parameter) throws FlowableException {
        RunnerActuator<R, P> runnerActuator = RuntimeObjectFactory.factory().getObject(ExecuteMode.INVOKE.name(), value);
        logger.info("当前为自定义Class模式执行!");
        return runnerActuator.execute(parameter);
    }

}