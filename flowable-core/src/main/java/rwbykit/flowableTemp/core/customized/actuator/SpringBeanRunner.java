package rwbykit.flowableTemp.core.customized.actuator;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowableTemp.annotation.NovaMapper;
import rwbykit.flowableTemp.core.customized.CustomizedActuator;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;
import rwbykit.flowable.engine.factory.ObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 自定义bean运行
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午9:44:18
 * @version 1.0
 */
@NovaMapper(enumClass = ExecuteMode.class, enumValue = "BEAN", mapperName = Constants.EXECUTION_RUNNER)
public class SpringBeanRunner implements Runner<Object, Result<?>> {
    
    private final static Logger logger = LoggerFactory.getLogger(SpringBeanRunner.class);

    @Override
    public Result<?> run(String value, Object parameter) throws FlowableException {
        CustomizedActuator<Object> customizedActuator = ObjectFactory.factory().create(ExecuteMode.BEAN, value);
        logger.info("当前为自定义Spring Bean模式执行!");
        return customizedActuator.execute(parameter);
    }

}
