package rwbykit.flowable.model;

import java.util.List;

public class AutoNode extends Node {

    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
