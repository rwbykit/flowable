package rwbykit.flowable.core;

import rwbykit.flowable.core.parser.Parser;

import java.util.List;

public class Flowable {


    private static class GenericBootstrapImpl implements GenericBootstrap {


        @Override
        public <T, R> GenericBootstrap registerParser(Parser<T, R> parser) {
            return null;
        }

        @Override
        public <T, R> GenericBootstrap registerParsers(List<? extends Parser<T, R>> parsers) {
            return null;
        }

        @Override
        public <T, R> GenericBootstrap registerParser(String parserName, Parser<T, R> parser) {
            return null;
        }

        @Override
        public Configuration<?> configure() {
            return null;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(Object object) {
            return null;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(Class<?> classType) {
            return null;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(String category, String type, Object object) {
            return null;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(String category, String type, Class<?> classType) {
            return null;
        }
    }


}
