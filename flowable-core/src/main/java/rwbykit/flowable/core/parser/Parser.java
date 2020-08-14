package rwbykit.flowable.core.parser;

public interface Parser<T, R> {

    R parse(T t);

}
