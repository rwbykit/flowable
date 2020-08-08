package rwbykit.flowable.model;

import rwbykit.flowable.model.enumeration.SchedulerType;

import java.util.List;

public class Task extends RunMode {

    /**
     * 任务id
     */
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 调度类型
     * @see SchedulerType
     */
    private String scheduleType;

    /**
     * 执行顺序
     */
    private int order;

    /**
     * 任务附带的入参值配置
     */
    private List<ValueField> fields;

    /**
     * 任务执行类配置
     */
    private String taskClassType;

    /**
     * 监听者
     */
    private List<Listener> listeners;

    /**
     * 结果存储类型
     * @see rwbykit.flowableTemp.model.enumeration.ResultStorageType
     */
    private String resultStorageType;

    /**
     * 结果存储的key
     */
    private String resultKey;

    public String getResultStorageType() {
        return resultStorageType;
    }

    public void setResultStorageType(String resultStorageType) {
        this.resultStorageType = resultStorageType;
    }

    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<ValueField> getFields() {
        return fields;
    }

    public void setFields(List<ValueField> fields) {
        this.fields = fields;
    }

    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

    public String getTaskClassType() {
        return taskClassType;
    }

    public void setTaskClassType(String taskClassType) {
        this.taskClassType = taskClassType;
    }
}
