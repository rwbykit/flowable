package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.model.parser.Listener;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.model.ListenerImpl;

@Type(category = NodeConstants.CATEGORY_PARSER, type = NodeConstants.NODE_NAME_LISTENER)
public class ListenerParser extends GenericMemberParser<Listener> {

    @Override
    public Listener getObject() {
        return new ListenerImpl();
    }
}
