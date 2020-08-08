package rwbykit.flowable.engine.runtime.parameter;

import java.io.Serializable;
import java.util.Map;

/**
 * 任务调度参数
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午8:55:09
 * @version 1.0
 */
public class TaskParameter implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String bizNo;
    
    private Map<String, Object> params;

    TaskParameter(String bizNo, Map<String, Object> params) {
        this.bizNo = bizNo;
        this.params = params;
    }

    public static TaskParameterBuilder builder() {
        return new TaskParameterBuilder();
    }

    public String getBizNo() {
        return bizNo;
    }

    public void setBizNo(String bizNo) {
        this.bizNo = bizNo;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public static class TaskParameterBuilder {
        private String bizNo;
        private Map<String, Object> params;

        TaskParameterBuilder() {
        }

        public TaskParameterBuilder bizNo(String bizNo) {
            this.bizNo = bizNo;
            return this;
        }

        public TaskParameterBuilder params(Map<String, Object> params) {
            this.params = params;
            return this;
        }

        public TaskParameter build() {
            return new TaskParameter(bizNo, params);
        }

    }
}
