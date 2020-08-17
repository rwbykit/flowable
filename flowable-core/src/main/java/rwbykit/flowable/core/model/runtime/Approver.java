package rwbykit.flowable.core.model.runtime;

import rwbykit.flowable.core.util.Asserts;

import java.util.Map;

public class Approver {

    /**
     * 审批人代码
     */
    private String code;

    /**
     * 审批人名称
     */
    private String name;

    private Map<String, Object> details;

    public final static Approver of(String code, String name) {
        return of(code, name, null);
    }

    public final static Approver of(String code, String name, Map<String, Object> details) {
        Asserts.nonEmpty(code);
        return new Approver(code, name, details);
    }

    private Approver(String code, String name, Map<String, Object> details) {
        this.code = code;
        this.name = name;
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }

        if (object instanceof Approver) {
            return this.getCode().equals(((Approver) object).getCode());
        }
        return false;
    }

}
