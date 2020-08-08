package rwbykit.flowable.engine.factory;

import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.beans.factory.support.GenericFactoryAware;
import rwbykit.flowable.engine.runtime.runner.Runner;
import rwbykit.flowable.engine.util.Asserts;


/**
 * 执行器工厂
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年6月26日 下午2:14:20
 */
public class RunnerFactory extends GenericFactoryAware {

    private static class RunnerFactoryHolder {
        private static final RunnerFactory FACTORY = new RunnerFactory();
    }

    private RunnerFactory() {
    }

    public final static RunnerFactory factory() {
        return RunnerFactoryHolder.FACTORY;
    }

    public <V, P> Runner<V, P> getRunner(String executeMode) {
        Asserts.nonEmpty(executeMode, "Unsupported Execution Mode[{}]!", executeMode);
        return this.getBeanFactory().getBean(Constants.TYPE_RUNNER, executeMode);
    }

}
