package rwbykit.flowable.engine.runtime.calculator.approver.assignee;

import java.io.Serializable;
import java.util.Map;

/**
 * 用户信息对象
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午4:29:16
 * @version 1.0
 */
public class AssigneeInformation implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String code;

    private String name;

    private Map<String, Object> details;

    AssigneeInformation(String code, String name, Map<String, Object> details) {
        this.code = code;
        this.name = name;
        this.details = details;
    }

    public static AssigneeInformationBuilder builder() {
        return new AssigneeInformationBuilder();
    }

    public static class AssigneeInformationBuilder {
        private String code;
        private String name;
        private Map<String, Object> details;

        AssigneeInformationBuilder() {
        }

        public AssigneeInformationBuilder code(String code) {
            this.code = code;
            return this;
        }

        public AssigneeInformationBuilder name(String name) {
            this.name = name;
            return this;
        }

        public AssigneeInformationBuilder details(Map<String, Object> details) {
            this.details = details;
            return this;
        }

        public AssigneeInformation build() {
            return new AssigneeInformation(code, name, details);
        }

    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Map<String, Object> getDetails() {
        return details;
    }
}
