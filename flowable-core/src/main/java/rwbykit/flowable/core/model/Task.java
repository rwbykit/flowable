package rwbykit.flowable.core.model;

import java.util.List;

public interface Task extends RunMode {

    public String getResultStorageType();


    public String getResultKey();


    public String getId();


    public String getName();


    public String getScheduleType();


    public int getOrder();


    public List<ValueField> getFields();


    public List<Listener> getListeners();


    public String getTaskClassType();

}
