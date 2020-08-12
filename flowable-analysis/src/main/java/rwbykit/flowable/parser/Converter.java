package rwbykit.flowable.parser;

public interface Converter<T> {

    T convert(String value);

}
