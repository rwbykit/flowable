package rwbykit.flowable.engine.runtime.processor;

public interface PostProcessor<In, Out> {

    Out beforePostProcessor(In in);

    Out afterPostProcessor(In in);

}
