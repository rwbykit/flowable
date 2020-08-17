package rwbykit.flowable.parser.tag;

import rwbykit.flowable.core.model.parser.Assignee;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.AssigneeImpl;

@NodeName(NodeConstants.NODE_NAME_ASSIGNEE)
public class AssigneeParser extends GenericMemberParser<Assignee> {
    @Override
    public Assignee getObject() {
        return new AssigneeImpl();
    }
}
