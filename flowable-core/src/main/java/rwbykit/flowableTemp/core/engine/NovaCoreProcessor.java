/*
package rwbykit.flowableTemp.core.engine;

import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.core.runtime.Context;
import com.war3.nova.beans.NvInsProcess;
import com.war3.nova.beans.NvProcess;
import com.war3.nova.cache.CacheManager;
import rwbykit.flowableTemp.core.CoreProcessor;
import rwbykit.flowableTemp.core.CoreScheduler;
import rwbykit.flowableTemp.core.ProcessConfigContext;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowableTemp.core.service.util.ExtParameters;
import rwbykit.flowableTemp.core.service.util.Processes;
import rwbykit.flowable.engine.util.Asserts;
import rwbykit.flowableTemp.core.util.Dates;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowable.engine.runtime.parameter.ParameterHelper;
import rwbykit.flowableTemp.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

*/
/**
 * 默认引擎实现
 * 
 * @author Cytus_
 * @since 2018年12月13日 下午8:00:29
 * @version 1.0
 *//*

public class NovaCoreProcessor implements CoreProcessor {
    
    private final static Logger logger = LoggerFactory.getLogger(NovaCoreProcessor.class);
    
    @Autowired
    private CoreScheduler scheduler;

    @Override
    public Context initProcess(Context context) throws FlowableException {
        
        Asserts.assertNullOrEmpty(context.getProcessId(), "尚未传入流程编号!");
        NvProcess nvProcess = CacheManager.getLatestProcessById(context.getProcessId());
        Asserts.assertNull(nvProcess, "流程[{}]未获取到最新的流程配置信息!", context.getProcessId());
        NvInsProcess insProcess = new NvInsProcess();
        insProcess.setInstanceId(Strings.getSequence());
        insProcess.setBizSerno(context.getBizSerno());
        insProcess.setInstOrgId(context.getInstOrgId());
        insProcess.setMainInstanceId(context.getMainProcInstId());
        insProcess.setOrgId(context.getOrgId());
        insProcess.setStartUser(context.getStartUser());
        insProcess.setStartTime(Dates.formatDateTimeByDef());
        insProcess.setVersion(nvProcess.getVersion());
        insProcess.setProcessId(context.getProcessId());
        insProcess.setStatus(ProcessConstants.STATUS_RUNNING);
        Processes.initProcessInfo(insProcess);
        if (FlowableHelper.nonNullOrEmpty(context.getExtParams())) {
            Map<String, Object> contextMap = ParameterHelper.paramExchange(context.getExtParams(), nvProcess.getFields());
            if (FlowableHelper.nonNullOrEmpty(contextMap)) {
                ExtParameters.insert(insProcess.getInstanceId(), contextMap);
            }
        }
        context.setProcessInstId(insProcess.getInstanceId());
        context.setVersion(context.getVersion());
        logger.info("流程[{}]实例化成功, 实例号[{}], 版本号[{}]对应的业务流水号为:{}", nvProcess.getId(), insProcess.getInstanceId(), nvProcess.getVersion(), context.getBizSerno());
        return context;
    }

    @Override
    public Context submit(Context context) throws FlowableException {
        if (Strings.isEmpty(context.getProcessInstId())) {
            initProcess(context);
        }
        
        logger.info("流程实例号[{}], 流程[{}]执行提交操作开始", context.getProcessInstId(), context.getProcessId());
        NvInsProcess insProcess = Processes.getInitProcessInfo(context.getProcessInstId());
        
        //判断是否实例化了事件信息，事件实例化应该在调用时已经处理，此处不再处理
        Asserts.assertNull(insProcess, "流程实例[{}]尚未找到对应的实例化流程信息!", context.getProcessInstId());
        Asserts.assertNull(insProcess.getProcessId(), "流程实例[{}]尚未找到对应的实例化流程信息!", context.getProcessInstId());
        Asserts.assertEquals(ProcessConstants.STATUS_END, insProcess.getStatus(), "流程实例[{}]已结束!", context.getProcessInstId());
        
        Processes.toNova(context, insProcess);
        NvProcess process = CacheManager.getProcessByIdAndVersion(context.getProcessId(), context.getVersion());
        ProcessConfigContext.createContext(process);
        
        Map<String, Object> contextMapParams = ExtParameters.queryExtParams(context.getProcessInstId());
        if (FlowableHelper.isNullOrEmpty(contextMapParams)) {
            contextMapParams = ParameterHelper.paramExchange(context.getExtParams(), process.getFields());
            if (FlowableHelper.nonNullOrEmpty(contextMapParams)) {
                ExtParameters.insert(context.getProcessInstId(), contextMapParams);
            }
        } 
        context.setExtParams(contextMapParams);
        
        context = scheduler.dispatch(context);
        logger.info("流程实例号[{}], 流程[{}]执行提交操作结束", context.getProcessInstId(), context.getProcessId());
        return context;
        
    }

}
*/
