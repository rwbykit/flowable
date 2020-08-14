package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.GenericBootstrap;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.parser.Parser;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.tag.ArtifactNodeParser;
import rwbykit.flowable.parser.tag.AssigneeParser;
import rwbykit.flowable.parser.tag.AssignmentParser;
import rwbykit.flowable.parser.tag.AutoNodeParser;
import rwbykit.flowable.parser.tag.LinkParser;
import rwbykit.flowable.parser.tag.ListenerParser;
import rwbykit.flowable.parser.tag.NodeParser;
import rwbykit.flowable.parser.tag.ProcessParser;
import rwbykit.flowable.parser.tag.PropertyParser;
import rwbykit.flowable.parser.tag.TaskParser;
import rwbykit.flowable.parser.tag.ViewPageParser;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Flowable {


    public static GenericBootstrap byDefaultRegister() {
        return new GenericBootstrapImpl();
    }


    private static class GenericBootstrapImpl implements GenericBootstrap {

        private static Map<String, Map<String, Object>> registers = new ConcurrentHashMap<>();

        static {

            Map<String, Map<String, Object>> registerObject = new ConcurrentHashMap<>();

            registerParser(registerObject, Lists.newArrayList(ArtifactNodeParser.class,
                    AssigneeParser.class,
                    AssignmentParser.class,
                    AutoNodeParser.class,
                    LinkParser.class,
                    ListenerParser.class,
                    NodeParser.class,
                    ProcessParser.class,
                    PropertyParser.class,
                    TaskParser.class,
                    ViewPageParser.class));

        }


        private static <T, R> void registerParser(Map<String, Map<String, Object>> registerMap, List<Class<? extends Parser>> classTypes) {
            Map<String, Object> parserMap = registerMap.get(NodeConstants.CATEGORY_PARSER);
            if (Objects.isNull(parserMap)) {
                parserMap = new ConcurrentHashMap<>(classTypes.size());
                registerMap.put(NodeConstants.CATEGORY_PARSER, parserMap);
            }
            for (Class<? extends Parser<T, R>> classType : classTypes) {
                Object parser = Objects.newInstance(classType);
                if (Objects.nonNull(parser)) {
                    if (classType.isAnnotationPresent(NodeName.class)) {
                        NodeName nodeName = parser.getClass().getAnnotation(NodeName.class);
                        parserMap.put(nodeName.value(), parser);
                    } else if (classType.isAnnotationPresent(NodeName.NodeNames.class)) {
                        NodeName.NodeNames nodeNames = parser.getClass().getAnnotation(NodeName.NodeNames.class);
                        for (NodeName nodeName : nodeNames.value()) {
                            parserMap.put(nodeName.value(), parser);
                        }
                    }
                }
            }
            ;
        }


        @Override
        public <T, R> GenericBootstrap registerParser(Parser<T, R> parser) {
            return this.registerParsers(Lists.immutable(parser));
        }

        @Override
        public <T, R> GenericBootstrap registerParsers(List<? extends Parser<T, R>> parsers) {
            parsers.forEach(parser -> {
                Class classType = parser.getClass();
                if (classType.isAnnotationPresent(NodeName.class)) {
                    NodeName nodeName = parser.getClass().getAnnotation(NodeName.class);
                    registerParser(nodeName.value(), parser);
                } else if (classType.isAnnotationPresent(NodeName.NodeNames.class)) {
                    NodeName.NodeNames nodeNames = parser.getClass().getAnnotation(NodeName.NodeNames.class);
                    for (NodeName nodeName : nodeNames.value()) {
                        registerParser(nodeName.value(), parser);
                    }
                }
            });
            return this;
        }

        @Override
        public <T, R> GenericBootstrap registerParser(String parserName, Parser<T, R> parser) {
            return registerRuntimeObject(NodeConstants.CATEGORY_PARSER, parserName, parser);
        }

        @Override
        public Configuration<?> configure() {
            return null;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(Object object) {
            if (Objects.nonNull(object) && object.getClass().isAnnotationPresent(Type.class)) {
                Type type = object.getClass().getAnnotation(Type.class);
                registerRuntimeObject(type.category(), type.type(), object);
            }
            return this;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(Class<?> classType) {
            return registerRuntimeObject(Objects.newInstance(classType));
        }


        @Override
        public GenericBootstrap registerRuntimeObject(String category, String type, Object object) {
            Asserts.nonEmpty(category, "Category must not empty!");
            Asserts.nonEmpty(type, "Type must not empty!");
            Asserts.nonNull(object, "Register object must not null!");
            Map<String, Object> categoryMap = registers.get(category);
            if (Collections.isEmpty(categoryMap)) {
                categoryMap = new ConcurrentHashMap();
                registers.put(category, categoryMap);
            }
            categoryMap.put(type, object);
            return this;
        }

        @Override
        public GenericBootstrap registerRuntimeObject(String category, String type, Class<?> classType) {
            return registerRuntimeObject(category, type, Objects.newInstance(classType));
        }

    }


}
