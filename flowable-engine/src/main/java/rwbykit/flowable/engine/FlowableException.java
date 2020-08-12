package rwbykit.flowable.engine;

/**
 * 异常
 * 
 * @author Cytus_
 * @since 2018年12月13日 下午7:26:44
 * @version 1.0
 */
public class FlowableException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public final static String ERROR_CODE = "999999";
    
    private String errorCode = ERROR_CODE;
    
    private String errorMessage = "System Exception!";
    
    public FlowableException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public FlowableException(String errorMessage, Throwable throwable) {
        super(throwable);
        this.errorMessage = errorMessage;
    }
    
    public FlowableException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }
    
    
    public FlowableException(Throwable throwable) {
        super(throwable);
        this.errorMessage = throwable.getMessage();
    }
    
    public FlowableException(String errorCode, String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
    
    public FlowableException() {
        super();
    }
    
    public String getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    
}
