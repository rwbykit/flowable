package rwbykit.flowable.engine;

import rwbykit.flowable.core.Result;

/**
 * @param <T>
 * @author Cytus_
 * @version 1.0
 * @since 2018年5月28日 上午11:15:43
 */
public abstract class AbstractResult<T> implements Result<T> {

    protected boolean isSuccess;

    protected String errorCode;

    protected String errorMessage;

    public AbstractResult() {
        super();
    }

    public AbstractResult(boolean isSuccess, String errorCode, String errorMessage) {
        super();
        this.isSuccess = isSuccess;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public String errorCode() {
        return this.errorCode;
    }

    @Override
    public String errorMessage() {
        return this.errorMessage;
    }

}
