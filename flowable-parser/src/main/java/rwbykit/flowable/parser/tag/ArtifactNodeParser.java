package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.ArtifactNode;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.ArtifactNodeImpl;

@NodeName(NodeConstants.NODE_TYPE_ARTIFACT)
public class ArtifactNodeParser extends AbstractParser<ArtifactNode> {
    @Override
    public ArtifactNode parse(Element element) {

        ArtifactNodeImpl node = new ArtifactNodeImpl();
        this.fillByAttribute(node, element);
        node.setAssignment(this.parseChildren(element.getChild(NodeConstants.NODE_NAME_ASSIGNMENT)));
        node.setProperty(this.parseChildren(element.getChild(NodeConstants.NODE_NAME_PROPERTY)));
        node.setViewPages(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_VIEW_PAGE)));
        node.setLinks(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LINK))));
        node.setListeners(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LISTENER))));
        return node;
    }
}
