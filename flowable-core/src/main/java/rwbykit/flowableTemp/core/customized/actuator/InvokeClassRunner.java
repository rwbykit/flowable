package rwbykit.flowableTemp.core.customized.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.runner.Runner;

/**
 * 
 * 自定义class运行
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午9:44:18
 * @version 1.0
 */
public class InvokeClassRunner implements Runner<Object, Result<?>> {

    private final static Logger logger = LoggerFactory.getLogger(InvokeClassRunner.class);
    
    @Override
    public Result<?> run(String value, Object parameter)  {
        /*CustomizedActuator<Object> customizedActuator = ObjectFactory.factory().create(ExecuteMode.INVOKE, value);
        logger.info("当前为自定义Class模式执行!");
        return customizedActuator.execute(parameter);*/
        return null;
        
    }

}