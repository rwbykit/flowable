package rwbykit.flowableTemp.core;

import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.core.runtime.Context;

/**
 * 核心流程处理器
 * 
 * @author Cytus_
 * @since 2018年12月27日 下午2:30:41
 * @version 1.0
 */
public interface CoreProcessor {
	
    /**
     * 初始化流程
     * @param context
     * @return
     * @throws FlowableException
     */
	Context initProcess(Context context) throws FlowableException;
	
	/**
	 * 提交流程
	 * @param context
	 * @return
	 * @throws FlowableException
	 */
	Context submit(Context context) throws FlowableException;
	
}
