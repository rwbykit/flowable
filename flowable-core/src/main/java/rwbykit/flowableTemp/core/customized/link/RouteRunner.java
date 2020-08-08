package rwbykit.flowableTemp.core.customized.link;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.runtime.parameter.LinkParameter;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.FlowableRuntimeException;
import rwbykit.flowable.engine.Result;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.engine.factory.RunnerFactory;
import rwbykit.flowableTemp.core.util.Strings;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;

/**
 * 自定义路由执行者
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月13日 上午8:37:42
 */
public class RouteRunner {

    private final static Logger logger = LoggerFactory.getLogger(RouteRunner.class);

    public static final boolean executeRoute(ExecuteMode executeMode, String value, LinkParameter linkParameter) {
        try {
            Runner<LinkParameter, Result<?>> runner = RunnerFactory.factory().getRunner(executeMode);
            Result<?> result = runner.run(value, linkParameter);
            if (result.isSuccess()) {
                return Boolean.parseBoolean(Strings.replaceNullByObj(result.getResult()));
            }
        } catch (FlowableException e) {
            logger.error("当前路由条件执行异常!", e);
            throw new FlowableRuntimeException(e.getErrorCode(), e.getErrorMessage(), e);
        }
        return false;
    }

}
