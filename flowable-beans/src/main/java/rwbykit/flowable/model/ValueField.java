package rwbykit.flowable.model;


/**
 * 自定义当前字段
 * 
 * @author Cytus_
 * @since 2018年12月17日 上午10:19:47
 * @version 1.0
 */
public class ValueField {
    
    /**
     * 字段名
     */
    private String name;
    
    /**
     * 字段默认值
     */
    private String value;

    /**
     * 值类型
     * @see rwbykit.flowableTemp.model.enumeration.ValueType
     */
    private String valueType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
}
