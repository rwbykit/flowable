package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.AutoNode;
import rwbykit.flowable.core.model.parser.QuickQuery;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.parser.NodeConstants;
import rwbykit.flowable.parser.NodeName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@NodeName(NodeConstants.NODE_NAME_LISTENER)
public class AutoNodeImpl extends NodeImpl implements AutoNode, QuickQuery {

    private Map<String, Task> tasks;

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void setTasks(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public <T> T find(String property, String id) {
        if ("tasks".equals(property)) {
            return (T) tasks.get(id);
        }
        return null;
    }
}
