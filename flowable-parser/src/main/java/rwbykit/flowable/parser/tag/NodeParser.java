package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.model.Node;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.NodeImpl;

@NodeName(NodeConstants.NODE_NAME_NODE)
public class NodeParser extends GenericMemberParser<Node> {

    @Override
    public Node getObject() {
        return new NodeImpl();
    }
}
