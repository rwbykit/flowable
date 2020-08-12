package rwbykit.flowable.extension.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.runner.Runner;

/**
 * 
 * 自定义bean运行
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午9:44:18
 * @version 1.0
 */
public class SpringBeanRunner implements Runner<Object, Result<?>> {
    
    private final static Logger logger = LoggerFactory.getLogger(SpringBeanRunner.class);

    @Override
    public Result<?> run(String value, Object parameter)  {
        /*CustomizedActuator<Object> customizedActuator = ObjectFactory.factory().create(ExecuteMode.BEAN, value);
        logger.info("当前为自定义Spring Bean模式执行!");
        return customizedActuator.execute(parameter);*/
        return null;
    }

}
