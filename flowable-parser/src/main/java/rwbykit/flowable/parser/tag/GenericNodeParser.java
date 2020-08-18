package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.NodeImpl;

@NodeName(NodeConstants.NODE_NAME_NODE)
public class GenericNodeParser<T extends Node> extends AbstractParser<T> {

    @Override
    @SuppressWarnings("unchecked")
    public T parse(Element element) {
        NodeImpl node = getNode();
        this.fillByAttribute(node, element);
        node.setLinks(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LINK))));
        node.setListeners(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LISTENER))));
        return (T) node;
    }

    public NodeImpl getNode() {
        return new NodeImpl();
    }
}
