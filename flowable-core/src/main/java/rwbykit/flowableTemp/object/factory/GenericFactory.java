package rwbykit.flowableTemp.object.factory;

public class GenericFactory {

    private ObjectFactory objectFactory;

    private boolean hasSetObjectFactory = false;

    public void setObjectFactory(ObjectFactory factory) {
        if (!hasSetObjectFactory) {
            synchronized (this) {
                if (!hasSetObjectFactory) {
                    objectFactory = factory;
                    hasSetObjectFactory = true;
                }
            }
        }
    }

    public ObjectFactory getObjectFactory() {
        return this.objectFactory;
    }

}
