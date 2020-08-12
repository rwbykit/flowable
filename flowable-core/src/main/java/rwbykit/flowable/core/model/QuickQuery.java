package rwbykit.flowable.core.model;

public interface QuickQuery {

    <T> T find(String property, String id);

}
