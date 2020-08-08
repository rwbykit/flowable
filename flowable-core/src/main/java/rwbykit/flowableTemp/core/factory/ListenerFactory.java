package rwbykit.flowableTemp.core.factory;

public class ListenerFactory {

    private static class ListenerFactoryHolder {
        private static final ListenerFactory FACTORY = new ListenerFactory();
    }

    private ListenerFactory() {}

    public final static ListenerFactory factory() {
        return ListenerFactory.ListenerFactoryHolder.FACTORY;
    }

    public <T> T getObject() {
        return null;
    }

}
