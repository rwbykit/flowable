package rwbykit.flowable.core.current;

import java.util.Map;

/**
 * 发起者
 * @author Cytus_
 */
public class Initiator {

    private String code;

    private String name;

    private Map<String, Object> details;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getDetails() {
        return details;
    }

    public void setDetails(Map<String, Object> details) {
        this.details = details;
    }
}
