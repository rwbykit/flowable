package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.Link;
import rwbykit.flowable.core.model.ValueField;

import java.util.List;

public class LinkImpl extends RunModeImpl implements Link {

    private String id;

    private String name;

    private String type;

    private String sourceRef;

    private String targetRef;

    private String description;

    private List<ValueField> valueFields;

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
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getSourceRef() {
        return sourceRef;
    }

    public void setSourceRef(String sourceRef) {
        this.sourceRef = sourceRef;
    }

    @Override
    public String getTargetRef() {
        return targetRef;
    }

    public void setTargetRef(String targetRef) {
        this.targetRef = targetRef;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public List<ValueField> getValueFields() {
        return valueFields;
    }

    public void setValueFields(List<ValueField> valueFields) {
        this.valueFields = valueFields;
    }
}
