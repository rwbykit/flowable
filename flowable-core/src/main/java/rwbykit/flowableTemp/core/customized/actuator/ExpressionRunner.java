package rwbykit.flowableTemp.core.customized.actuator;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowableTemp.annotation.NovaMapper;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowableTemp.core.customized.ObjectResult;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;
//import org.mvel2.MVEL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 表达式执行
 * @author Cytus_
 *
 */
@NovaMapper(enumClass = ExecuteMode.class, enumValue = "EXPRESSION", mapperName = Constants.EXECUTION_RUNNER)
public class ExpressionRunner implements Runner<Object, Result<?>> {

    private final static Logger logger = LoggerFactory.getLogger(ExpressionRunner.class);
    
    @Override
    public Result<?> run(String value, Object parameter) throws FlowableException {
        logger.info("当前为自定义Expression模式执行!");
        Object object = null;//MVEL.eval(value, parameter);
        return ObjectResult.createSuccess(object);
    }
    
}
