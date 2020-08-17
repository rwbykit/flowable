package rwbykit.flowable.parser.tag;

import org.jdom2.Attribute;
import org.jdom2.Element;
import rwbykit.flowable.core.model.parser.Property;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.PropertyImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NodeName(NodeConstants.NODE_NAME_PROPERTY)
public class PropertyParser extends AbstractParser<Property> {
    @Override
    public Property parse(Element element) {
        List<Attribute> attributeList = element.getAttributes();
        Map<String, String> propertyMap = new ConcurrentHashMap<>(attributeList.size());
        attributeList.forEach(attribute -> {
            propertyMap.put(attribute.getName(), attribute.getValue());
        });
        return new PropertyImpl(Maps.immutable(propertyMap));
    }
}
