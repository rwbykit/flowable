package rwbykit.flowableTemp.core.customized.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.model.enumeration.ExecuteMode;
import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.core.customized.ObjectResult;

//import org.mvel2.MVEL;

/**
 * 表达式执行
 * @author Cytus_
 *
 */
public class ExpressionRunner implements Runner<Object, Result<?>> {

    private final static Logger logger = LoggerFactory.getLogger(ExpressionRunner.class);
    
    @Override
    public Result<?> run(String value, Object parameter)  {
        logger.info("当前为自定义Expression模式执行!");
        Object object = null;//MVEL.eval(value, parameter);
        return ObjectResult.createSuccess(object);
    }
    
}
