package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.model.parser.Listener;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.ListenerImpl;

@NodeName(NodeConstants.NODE_NAME_LISTENER)
public class ListenerParser extends GenericMemberParser<Listener> {

    @Override
    public Listener getObject() {
        return new ListenerImpl();
    }
}
