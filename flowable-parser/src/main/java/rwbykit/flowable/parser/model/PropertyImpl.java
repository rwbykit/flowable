package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.Property;

import java.util.Map;

public class PropertyImpl implements Property {

    private final Map<String, String> properties;

    public PropertyImpl(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
