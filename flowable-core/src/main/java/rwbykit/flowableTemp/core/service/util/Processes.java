/*
package rwbykit.flowableTemp.core.service.util;

import rwbykit.flowableTemp.core.runtime.Context;
import com.war3.nova.beans.NvInsProcess;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.service.runtime.ProcessService;
import rwbykit.flowableTemp.core.util.Dates;
import rwbykit.flowableTemp.core.util.SpringContexts;
import rwbykit.flowableTemp.core.util.Strings;

*/
/**
 * 
 * 
 * @author Cytus_
 * @since 2018年12月13日 上午10:34:30
 * @version 1.0
 *//*

public final class Processes {
	
	private final static ProcessService insProcessService = SpringContexts.getBean(ProcessService.class);
	
	*/
/**
	 * 获得流程实例信息
	 * @param processInstanceId
	 * @return
	 *//*

	public final static NvInsProcess getInitProcessInfo(String processInstanceId) {
		return insProcessService.getByProcessInstId(processInstanceId);
	}
	
	*/
/**
	 * 初始化流程信息
	 * @param process
	 *//*

	public final static void initProcessInfo(NvInsProcess process) {
		insProcessService.insert(process);
	}
	
	*/
/**
	 * 更新当前执行节点实例编号
	 * @param processInstId
	 * @param nodeInstId
	 *//*

	public final static void updateCurrentNodeInstId(String processInstId, String nodeInstId) {
	    NvInsProcess process = new NvInsProcess();
	    process.setInstanceId(processInstId);
	    process.setNodeInstanceId(nodeInstId);
	    insProcessService.update(process);
	}
	
	*/
/**
	 * 更新状态
	 * @param processInstId
	 * @param processStatus
	 *//*

	public final static void updateStatus(String processInstId, String processStatus) {
	    NvInsProcess process = new NvInsProcess();
        process.setInstanceId(processInstId);
        process.setStatus(processStatus);
        if (ProcessConstants.STATUS_END.equals(processStatus)) {
            process.setEndTime(Dates.formatDateTimeByDef());
        }
        insProcessService.update(process);
	}
	
	*/
/**
	 * 流程实例信息转nova对象
	 * @param context
	 * @param insProcess
	 *//*

	public final static void toNova(Context context, NvInsProcess insProcess) {
		context.setProcessId(insProcess.getProcessId());
		context.setBizSerno(insProcess.getBizSerno());
		context.setInstOrgId(insProcess.getInstOrgId());
		context.setOrgId(insProcess.getOrgId());
		context.setMainProcInstId(insProcess.getMainInstanceId());
		context.setProcessStatus(insProcess.getStatus());
		context.setVersion(insProcess.getVersion());
		if (Strings.isNotEmpty(insProcess.getNodeInstanceId()) && Strings.isEmpty(context.getNodeInstId())) {
			context.setNodeInstId(insProcess.getNodeInstanceId());
		}
	}
	

}
*/
