package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.Listener;

public class ListenerImpl extends RunModeImpl implements Listener {

    /**
     * 监听者类全路径
     */
    private String classType;

    @Override
    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

}
