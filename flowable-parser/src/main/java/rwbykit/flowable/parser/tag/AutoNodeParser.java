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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@NodeName(NodeConstants.NODE_TYPE_AUTO)
public class AutoNodeParser extends AbstractParser<AutoNode> {
    @Override
    public AutoNode parse(Element element) {
        AutoNodeImpl node = new AutoNodeImpl();
        this.fillByAttribute(node, element);
        node.setListeners(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LISTENER))));
        node.setLinks(Lists.immutable(this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_LINK))));
        List<Task> tasks = this.parseChildrens(element.getChildren(NodeConstants.NODE_NAME_TASK));
        Map<String, Task> taskMap = new ConcurrentHashMap<>(tasks.size());
        tasks.forEach(task -> taskMap.put(task.getId(), task));
        node.setTasks(Maps.immutable(taskMap));
        return node;
    }
}
