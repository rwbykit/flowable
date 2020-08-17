package rwbykit.flowable.core.model.parser;

import java.util.List;

public interface Link extends RunMode {

    public String getId();


    public String getName();


    public String getType();


    public String getSourceRef();


    public String getTargetRef();


    public String getDescription();


    public List<ValueField> getValueFields();

}
