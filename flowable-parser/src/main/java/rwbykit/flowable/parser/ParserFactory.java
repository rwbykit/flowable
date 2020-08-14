package rwbykit.flowable.parser;

import rwbykit.flowable.core.parser.Parser;

public class ParserFactory {

    private static class ParserFactoryHolder {
        private static final ParserFactory FACTORY = new ParserFactory();
    }

    private ParserFactory() {
    }

    public final static ParserFactory factory() {
        return ParserFactory.ParserFactoryHolder.FACTORY;
    }

    public <T, R, P extends Parser<T, R>> P getParser(String type) {
        return null;
    }

}
