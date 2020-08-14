package rwbykit.flowable.core;

import rwbykit.flowable.core.parser.Parser;

import java.util.List;

public interface GenericBootstrap {

    <T, R> GenericBootstrap registerParser(Parser<T, R> parser);

    <T, R> GenericBootstrap registerParsers(List<? extends Parser<T, R>> parsers);

    <T, R> GenericBootstrap registerParser(String parserName, Parser<T, R> parser);

    Configuration<?> configure();

    GenericBootstrap registerRuntimeObject(Object object);

    GenericBootstrap registerRuntimeObject(Class<?> classType);

    GenericBootstrap registerRuntimeObject(String category, String type, Object object);

    GenericBootstrap registerRuntimeObject(String category, String type, Class<?> classType);

}
