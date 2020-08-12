package rwbykit.flowable.parser.model;

import rwbykit.flowable.core.model.RunMode;

/**
 * 运行模式
 */
public class RunModeImpl implements RunMode {

    private String runMode;

    private String runValue;

    @Override
    public String getRunValue() {
        return runValue;
    }

    public void setRunValue(String runValue) {
        this.runValue = runValue;
    }

    @Override
    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }
}
