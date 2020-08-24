package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.parser.Listener;
import rwbykit.flowable.core.model.parser.Task;
import rwbykit.flowable.core.model.parser.ValueField;
import rwbykit.flowable.core.model.enumeration.SchedulerType;

import java.util.List;

public class TaskImpl extends RunModeImpl implements Task {

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
     * 监听者
     */
    private List<Listener> listeners;

    /**
     * 结果存储类型
     */
    private String resultStorageType;

    /**
     * 结果存储的key
     */
    private String resultKey;

    @Override
    public String getResultStorageType() {
        return resultStorageType;
    }

    public void setResultStorageType(String resultStorageType) {
        this.resultStorageType = resultStorageType;
    }

    @Override
    public String getResultKey() {
        return resultKey;
    }

    public void setResultKey(String resultKey) {
        this.resultKey = resultKey;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    @Override
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public List<ValueField> getFields() {
        return fields;
    }

    public void setFields(List<ValueField> fields) {
        this.fields = fields;
    }

    @Override
    public List<Listener> getListeners() {
        return listeners;
    }

    public void setListeners(List<Listener> listeners) {
        this.listeners = listeners;
    }

}
