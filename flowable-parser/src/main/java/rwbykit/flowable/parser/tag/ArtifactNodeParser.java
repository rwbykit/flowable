package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.parser.ArtifactNode;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.ArtifactNodeImpl;
import rwbykit.flowable.parser.model.NodeImpl;

@NodeName(NodeConstants.NODE_TYPE_ARTIFACT)
public class ArtifactNodeParser extends GenericNodeParser<ArtifactNode> {
    @Override
    public ArtifactNode parse(Element element) {
        ArtifactNodeImpl node = (ArtifactNodeImpl) super.parse(element);
        node.setAssignment(this.parseChildren(element.getChild(NodeConstants.NODE_NAME_ASSIGNMENT)));
        node.setProperty(this.parseChildren(element.getChild(NodeConstants.NODE_NAME_PROPERTY)));
        node.setViewPages(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_VIEW_PAGE)));
        return node;
    }

    @Override
    public NodeImpl getNode() {
        return new ArtifactNodeImpl();
    }

}
