package rwbykit.flowable.engine.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Selector;
import rwbykit.flowable.engine.util.Asserts;
import rwbykit.flowableTemp.object.factory.GenericFactory;

/**
 * 选择器工厂
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月14日 上午9:56:23
 */
public class SelectorFactory extends GenericFactory {

    private final static Logger logger = LoggerFactory.getLogger(SelectorFactory.class);

    private static class SelectorFactoryHolder {
        private static final SelectorFactory FACTORY = new SelectorFactory();
    }

    private SelectorFactory() {
    }

    public final static SelectorFactory factory() {
        return SelectorFactoryHolder.FACTORY;
    }

    public <I, O, R extends Selector<I, O>> R getSelector(String selectorType) {
        Asserts.nonEmpty(selectorType, "Unsupported RouteSelector type[{}]!", selectorType);
        return this.getObjectFactory().getObject(Constants.TYPE_SELECTOR, selectorType);
    }

}
