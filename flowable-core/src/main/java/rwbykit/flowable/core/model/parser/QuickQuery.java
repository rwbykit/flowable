package rwbykit.flowable.core.model.parser;

public interface QuickQuery {

    <T> T find(String property, String id);

}
