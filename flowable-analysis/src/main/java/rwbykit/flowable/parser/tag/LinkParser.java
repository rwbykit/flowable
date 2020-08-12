package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.Link;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.LinkImpl;

@NodeName(NodeConstants.NODE_NAME_LINK)
public class LinkParser extends AbstractParser<Link> {
    @Override
    public Link parse(Element element) {
        LinkImpl link = new LinkImpl();
        this.fillByAttribute(link, element);
        link.setValueFields(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_VALUE_FIELD))));
        return link;
    }
}
