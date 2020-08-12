package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.model.ViewPage;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.ViewPageImpl;

@NodeName(NodeConstants.NODE_NAME_VIEW_PAGE)
public class ViewPageParser extends GenericMemberParser<ViewPage> {
    @Override
    public ViewPage getObject() {
        return new ViewPageImpl();
    }
}
