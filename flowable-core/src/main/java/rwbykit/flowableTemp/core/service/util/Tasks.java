package rwbykit.flowableTemp.core.service.util;

import rwbykit.flowableTemp.core.runtime.Context;
import com.war3.nova.beans.NvInsTask;
import com.war3.nova.beans.NvTask;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.service.runtime.TaskService;
import rwbykit.flowableTemp.core.util.Dates;
import rwbykit.flowableTemp.core.util.SpringContexts;

import java.util.List;

/**
 * 
 * 
 * @author Cytus_
 * @since 2018年12月15日 上午10:53:22
 * @version 1.0
 */
public class Tasks {
    
    private static final TaskService insTaskService = SpringContexts.getBean(TaskService.class);
    
    public final static void initInsTask(NvInsTask insTask) {
        insTaskService.insert(insTask);
    }
    
    public final static NvInsTask createInsTask(Context context, NvTask task) {
        NvInsTask insTask = new NvInsTask();
        insTask.setStartTime(Dates.formatDateTimeByDef());
        insTask.setExecutionMode(task.getExecutionMode());
        insTask.setNodeInstanceId(context.getNodeInstId());
        insTask.setOrder(task.getOrder());
        insTask.setScheduleType(task.getScheduleType());
        insTask.setStatus(context.getTaskStatus());
        insTask.setTaskId(task.getId());
        insTask.setTaskName(task.getName());
        insTask.setTaskValue(task.getValue());
        if (ProcessConstants.STATUS_END.equals(insTask.getStatus())) {
            insTask.setEndTime(Dates.formatDateTimeByDef());
        }
        insTask.setProcessInstId(context.getProcessInstId());
        return insTask;
    }
    
    public final static void updateInsTaskStatus(String nodeInstanceId, String taskId, String status, String errorMsg) {
        NvInsTask insTask = new NvInsTask();
        insTask.setNodeInstanceId(nodeInstanceId);
        insTask.setTaskId(taskId);
        insTask.setStatus(status);
        if (ProcessConstants.STATUS_END.equals(status)) { 
            insTask.setEndTime(Dates.formatDateTimeByDef());
        }
        
        if (ProcessConstants.STATUS_EXCEPTION.equals(status)) {
        	insTask.setErrorMsg(errorMsg);
        }
        
        insTaskService.update(insTask);
    }
    
    public final static List<NvInsTask> queryAllByNodeInstanceId(String nodeInstanceId) {
        return insTaskService.queryAllByNodeInstId(nodeInstanceId);
    }

    
    public final static NvInsTask queryByNodeInstIdAndTaskId(String nodeInstId, String taskId) {
    	return insTaskService.queryByNodeInstIdAndTaskId(nodeInstId, taskId);
    }
    
}
