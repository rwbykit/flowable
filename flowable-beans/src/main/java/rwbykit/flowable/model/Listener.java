package rwbykit.flowable.model;

public class Listener extends RunMode {

    /**
     * 监听者类全路径
     */
    private String classType;

    public String getClassType() {
        return classType;
    }

    public void setClassType(String classType) {
        this.classType = classType;
    }

}
