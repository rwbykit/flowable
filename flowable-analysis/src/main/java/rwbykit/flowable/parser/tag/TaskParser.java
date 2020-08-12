package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.Task;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.TaskImpl;

@NodeName(NodeConstants.NODE_NAME_TASK)
public class TaskParser extends AbstractParser<Task> {
    @Override
    public Task parse(Element element) {
        TaskImpl task = new TaskImpl();
        this.fillByAttribute(task, element);
        task.setFields(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_VALUE_FIELD))));
        task.setListeners(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LISTENER))));
        return task;
    }
}
