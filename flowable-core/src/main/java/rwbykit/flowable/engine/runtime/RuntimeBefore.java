package rwbykit.flowable.engine.runtime;

public interface RuntimeBefore<T> {

    void before(T t);

}
