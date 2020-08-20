package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.model.parser.ViewPage;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.model.ViewPageImpl;

@Type(category = NodeConstants.CATEGORY_PARSER, type = NodeConstants.NODE_NAME_VIEW_PAGE)
public class ViewPageParser extends GenericMemberParser<ViewPage> {


    @Override
    public ViewPage getObject() {
        return new ViewPageImpl();
    }
}
