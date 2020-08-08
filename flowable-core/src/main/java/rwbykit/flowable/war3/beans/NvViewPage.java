package rwbykit.flowable.war3.beans;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;

/**
 * 审批业务挂在页面配置
 * 
 * @author Cytus_
 * @since 2018年12月25日 下午2:05:41
 * @version 1.0
 */
public class NvViewPage extends ToString implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @JacksonXmlProperty(isAttribute=true)
    private String path;
    
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


}
