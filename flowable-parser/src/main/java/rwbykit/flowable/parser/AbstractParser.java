package rwbykit.flowable.parser;

import org.jdom2.Attribute;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.parser.Parser;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.parser.converter.Converters;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractParser<T> implements Parser<Element, T> {

    private final static Logger logger = LoggerFactory.getLogger(AbstractParser.class);

    protected <R> List<R> parseChildrens(List<Element> elements) {
        if (Objects.isNull(elements) || elements.isEmpty()) {
            return Lists.emptyList();
        }

        return elements.stream()
                .map(element -> {
                    AbstractParser<R> parser = getParser(element);
                    return Objects.nonNull(parser) ? parser.parse(element) : null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }


    protected <R> R parseChildren(Element element) {
        AbstractParser<R> parser = getParser(element);
        return parser.parse(element);
    }

    protected <R> R fillByAttribute(R r, Element element) {
        List<Attribute> attributes = element.getAttributes();
        attributes.forEach(attribute -> fillByAttribute(r, attribute));
        return r;
    }

    protected <R> R fillByAttribute(R r, Attribute attribute) {
        return fillByAttribute(r, attribute, null);
    }

    protected <C, R> R fillByAttribute(R r, Attribute attribute, Converter<C> converter) {
        String name = attribute.getName();
        Field field = getField(r.getClass(), name);
        Method method = getSetterMethod(r.getClass(), field, name);
        if (Objects.nonNull(method)) {
            try {
                Object value = Objects.nonNull(Converters.getConverter(converter, field.getType())) ?
                        converter.convert(attribute.getValue()) : attribute.getValue();
                method.invoke(r, value);
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        return r;
    }

    protected <R> R fillByAttribute(R r, Element element, String name) {
        return fillByAttribute(r, element, name, null);
    }

    protected <C, R> R fillByAttribute(R r, Element element, String name, Converter<C> converter) {
        Attribute attribute = element.getAttribute(name);
        if (Objects.nonNull(attribute)) {
            fillByAttribute(r, attribute, converter);
        }
        return r;
    }

    private <R> AbstractParser<R> getParser(Element element) {
        AbstractParser<R> parser = null;
        String type = element.getAttributeValue("type");
        if (Strings.isEmpty(type)) {
            parser = ParserFactory.factory().getParser(element.getName());
        } else {
            parser = ParserFactory.factory().getParser(type);
            if (Objects.isNull(parser)) {
                parser = ParserFactory.factory().getParser(element.getName());
            }
        }
        return parser;
    }

    private static Method getSetterMethod(Class<?> classType, Field field, String name) {
        Method method = null;
        try {
            if (Objects.nonNull(field)) {
                String upperIndexName = Strings.upperIndexChar(name, 0);
                method = classType.getMethod("set" + upperIndexName, field.getType());
                if (Objects.isNull(method)) {
                    method = classType.getMethod("add" + upperIndexName, field.getType());
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return method;

    }

    private static Field getField(Class<?> classType, String name) {
        try {
            if (classType.equals(Object.class)) {
                return null;
            }
            return classType.getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            return getField(classType.getSuperclass(), name);
        }
    }

}
