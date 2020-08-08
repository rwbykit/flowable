package rwbykit.flowable.model;

public class Assignee {

    /**
     * 审批人类型
     */
    private String type;

    /**
     * 配置值，多个暂定英文逗号分隔
     */
    private String value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
