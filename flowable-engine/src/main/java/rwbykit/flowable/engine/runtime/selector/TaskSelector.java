package rwbykit.flowable.engine.runtime.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.Selector;
import rwbykit.flowable.engine.runtime.LoggerHelper;
import rwbykit.flowable.engine.runtime.model.TaskInstance;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.core.model.AutoNode;
import rwbykit.flowable.core.model.Task;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Cytus_
 * @version 1.0
 * @since 2018年12月15日 下午3:04:45
 */
public class TaskSelector implements Selector<Context, Task> {

    private final static Logger logger = LoggerFactory.getLogger(TaskSelector.class);

    @Override
    public Task select(Context context) {
        logger.info(LoggerHelper.select_task_selectStartMessage(context));
        Task nextTask = this.doSelect(context);
        logger.info(LoggerHelper.select_task_nextTaskMessage(context, nextTask.getId()));
        return nextTask;
    }

    public Task doSelect(Context context) {
        if (Strings.isEmpty(context.getCurrentInstance().getTaskId())) {

            List<TaskInstance> taskInstances = context.getRuntimeService().getTaskService().getAllTaskInstances(context.getCurrentInstance().getNodeInstanceId());
            if (Collections.isEmpty(taskInstances)) {
                return findStartTask(context);
            } else {
                TaskInstance lastTaskInstance = findLastExeTask(taskInstances);
                if (!Constants.STATUS_END.equals(lastTaskInstance.getTaskStatus())) {
                    return context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), lastTaskInstance.getTaskId());
                } else {
                    return nextTask(context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId()),
                            context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), lastTaskInstance.getTaskId()));
                }
            }
        } else {
            return nextTask(context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId()),
                    context.getProcessConfigService().getTask(context.getCurrentInstance().getNodeId(), context.getCurrentInstance().getTaskId()));
        }
    }

    /**
     * 查找下一个任务
     *
     * @param task
     * @return
     */
    protected Task nextTask(AutoNode node, Task task) {
        List<Task> tasks = node.getTasks().stream().sorted((o1, o2) -> Integer.valueOf(o1.getOrder()).compareTo(o2.getOrder())).collect(Collectors.toList());
        for (int i = 0; i < tasks.size(); i++) {
            if (task.getOrder() == tasks.get(i).getOrder() && i + 1 < tasks.size()) {
                return tasks.get(i + 1);
            }
        }
        return null;
    }

    protected Task findStartTask(Context context) {
        AutoNode node = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());
        return node.getTasks().stream().sorted((o1, o2) -> Integer.valueOf(o1.getOrder()).compareTo(o2.getOrder())).findFirst().orElse(null);
    }


    /**
     * 查找最后一个执行的任务
     *
     * @param taskInstances
     * @return
     */
    protected TaskInstance findLastExeTask(List<TaskInstance> taskInstances) {
        return taskInstances.stream().sorted((o1, o2) -> Integer.valueOf(o2.getOrder()).compareTo(o1.getOrder())).findFirst().orElse(null);
    }


}
