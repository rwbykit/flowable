package rwbykit.flowable.parser.tag;

import org.jdom2.Element;
import rwbykit.flowable.core.model.parser.AutoNode;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.parser.AbstractParser;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;
import rwbykit.flowable.parser.model.AutoNodeImpl;
import rwbykit.flowable.parser.model.NodeImpl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NodeName(NodeConstants.NODE_TYPE_AUTO)
public class AutoNodeParser extends GenericNodeParser<AutoNode> {
    @Override
    public AutoNode parse(Element element) {
        AutoNodeImpl node = (AutoNodeImpl) super.parse(element);
        List<Task> tasks = this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_TASK));
        Map<String, Task> taskMap = new ConcurrentHashMap<>(tasks.size());
        tasks.forEach(task -> taskMap.put(task.getId(), task));
        node.setTasks(Maps.immutable(taskMap));
        return node;
    }

    @Override
    public NodeImpl getNode() {
        return new AutoNodeImpl();
    }
}
