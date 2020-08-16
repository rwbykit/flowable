package rwbykit.flowable.parser;

import rwbykit.flowable.core.factory.support.GenericFactoryAware;
import rwbykit.flowable.core.parser.Parser;
import rwbykit.flowable.core.util.Asserts;

public class ParserFactory extends GenericFactoryAware {

    private static class ParserFactoryHolder {
        private static final ParserFactory FACTORY = new ParserFactory();
    }

    private ParserFactory() {
    }

    public final static ParserFactory factory() {
        return ParserFactory.ParserFactoryHolder.FACTORY;
    }

    public <T, R, P extends Parser<T, R>> P getParser(String type) {
        Asserts.nonEmpty(type, "Type must not null!");
        return getObjectFactory().getObject(NodeConstants.CATEGORY_PARSER, type);
    }

}
