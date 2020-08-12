package rwbykit.flowable.parser;

public class ParserFactory {

    private static class ParserFactoryHolder {
        private static final ParserFactory FACTORY = new ParserFactory();
    }

    private ParserFactory() {
    }

    public final static ParserFactory factory() {
        return ParserFactory.ParserFactoryHolder.FACTORY;
    }

    public <T, R extends Parser<T>> R getParser(String type) {
        return null;
    }

}
