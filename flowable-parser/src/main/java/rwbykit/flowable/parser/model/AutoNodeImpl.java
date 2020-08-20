package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.AutoNode;
import rwbykit.flowable.core.model.parser.QuickSearch;
import rwbykit.flowable.core.model.parser.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AutoNodeImpl extends NodeImpl implements AutoNode, QuickSearch {

    private Map<String, Task> tasks;

    @Override
    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void setTasks(Map<String, Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public <T> T search(String property, String id) {
        if ("tasks".equals(property)) {
            return (T) tasks.get(id);
        }
        return null;
    }
}
