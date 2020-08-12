package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.Assignment;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.AssignmentImpl;
import rwbykit.flowable.parser.model.AssignmentModeImpl;

@NodeName(NodeConstants.NODE_NAME_ASSIGNMENT)
public class AssignmentParser extends AbstractParser<Assignment> {
    @Override
    public Assignment parse(Element element) {
        AssignmentImpl assignment = new AssignmentImpl();
        this.fillByAttribute(assignment, element);
        assignment.setAssignees(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_ASSIGNEE))));
        AssignmentModeImpl mode = new AssignmentModeImpl();
        this.fillByAttribute(mode, element, "runMode");
        this.fillByAttribute(mode, element, "runValue");
        assignment.setAfterAssignmentMode(mode);
        return assignment;
    }
}
