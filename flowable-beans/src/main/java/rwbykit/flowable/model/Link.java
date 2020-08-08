package rwbykit.flowable.model;

import java.util.List;

public class Link extends RunMode {

    private String id;

    private String name;

    private String type;

    private String sourceRef;

    private String targetRef;

    private String description;

    private List<ValueField> valueFields;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSourceRef() {
        return sourceRef;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    public String getTargetRef() {
        return targetRef;
    }

    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ValueField> getValueFields() {
        return valueFields;
    }

    public void setValueFields(List<ValueField> valueFields) {
        this.valueFields = valueFields;
    }
}
