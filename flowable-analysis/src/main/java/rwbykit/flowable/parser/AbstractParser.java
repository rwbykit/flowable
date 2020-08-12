package rwbykit.flowable.parser;

import org.jdom2.Attribute;
import org.jdom2.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractParser<T> implements Parser<T> {


    protected <R> List<R> parseChildrens(List<Element> elements) {
        if (Objects.isNull(elements) || elements.isEmpty()) {
            return Collections.emptyList();
        }

        Parser<R> parser = getParser(elements.get(0));
        if (Objects.isNull(parser)) {
            throw new RuntimeException("");
        }

        return elements.stream().map(parser::parse).collect(Collectors.toList());
    }


    protected <R> R parseChildren(Element element) {
        Parser<R> parser = getParser(element);
        return parser.parse(element);
    }

    protected void fillByAttribute(T t, Element element) {
        List<Attribute> attributes = element.getAttributes();
        attributes.forEach(attribute -> fillByAttribute(t, attribute));
    }

    protected void fillByAttribute(T t, Attribute attribute) {
        fillByAttribute(t, attribute, null);
    }

    protected <C> void fillByAttribute(T t, Attribute attribute, Converter<C> converter) {
        String name = attribute.getName();
        Method method = getSetterMethod(t.getClass(), name);
        if (Objects.nonNull(method)) {
            try {
                Object value = Objects.nonNull(converter) ? converter.convert(attribute.getValue()) : attribute.getValue();
                method.invoke(t, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void fillByAttribute(T t, Element element, String name) {
        fillByAttribute(t, element, name);
    }

    protected <C> void fillByAttribute(T t, Element element, String name, Converter<C> converter) {
        Attribute attribute = element.getAttribute(name);
        if (Objects.nonNull(attribute)) {
            fillByAttribute(t, attribute, converter);
        }
    }

    private <R> Parser<R> getParser(Element element) {
        String type = element.getAttributeValue("type");
        Parser<R> parser = ParserFactory.factory().getParser(type);
        if (Objects.isNull(parser)) {
            parser = ParserFactory.factory().getParser(element.getName());
        }
        return parser;
    }

    private Method getSetterMethod(Class<?> clazz, String name) {

        try {
            Field field = clazz.getDeclaredField(name);
            String upperIndexName = upperIndexChar(name, 0);
            Method method = clazz.getMethod("set" + upperIndexName, field.getType());
            if (Objects.isNull(method)) {
                return clazz.getMethod("add" + upperIndexName, field.getType());
            }
        } catch (Exception e) {

        }
        return null;

    }

    private String upperIndexChar(String value, int index) {
        if (value.length() < index) {
            return value;
        }
        StringBuilder builder = new StringBuilder(value);
        builder.setCharAt(index, Character.toUpperCase(builder.charAt(index)));
        return builder.toString();
    }

}