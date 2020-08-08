package rwbykit.flowableTemp.core.util;

import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.core.runtime.Context;
import com.war3.nova.beans.NvListener;
import com.war3.nova.beans.NvNode;
import com.war3.nova.beans.NvProcess;
import com.war3.nova.beans.NvTask;
import rwbykit.flowableTemp.core.notice.Notification;
import rwbykit.flowableTemp.core.ProcessConfigContext;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;
import rwbykit.flowableTemp.core.enumeration.NodeType;
import rwbykit.flowable.engine.factory.ObjectFactory;
import rwbykit.flowableTemp.core.listener.DelegateNode;
import rwbykit.flowableTemp.core.listener.DelegateProcess;
import rwbykit.flowableTemp.core.listener.DelegateTask;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 监听器工具
 * 
 * @author Cytus_
 * @since 2018年12月17日 下午1:01:17
 * @version 1.0
 */
public final class Listeners {
    
    /**
     * 创建监听器
     * @param nvListeners
     * @return
     * @throws FlowableException
     */
    public final static List<? extends Notification<?>> initListener(List<NvListener> nvListeners) throws FlowableException {
        
        if (FlowableHelper.nonNullOrEmpty(nvListeners)) {
            
            List<Notification<?>> list = new CopyOnWriteArrayList<>();
            for (NvListener nvs : nvListeners) {
                Notification<?> listener;
                try {
                    listener = ObjectFactory.factory().create(ExecuteMode.get(nvs.getExecutionMode()), nvs.getValue());
                    list.add(listener);
                } catch (FlowableException e) {
                    LoggerFactory.getLogger(Listeners.class).error("监听器创建异常!");
                    throw e;
                }
            }
            
            return list;
        }
        
        return null;
    }
    
    /**
     * 创建流程监听对象
     * @param context
     * @param e
     * @return
     */
    public final static DelegateProcess createProcessDelegate(Context context, Exception e) {
        NvProcess process = ProcessConfigContext.getContext().getProcess();
        DelegateProcess delegate = new DelegateProcess();
        delegate.setProcessId(process.getId());
        delegate.setBizSerno(context.getBizSerno());
        delegate.setProcessName(process.getName());
        delegate.setProcessStatus(context.getProcessStatus());
        delegate.setException(e);
        return delegate;
    }
    
    /**
     * 创建节点监听对象
     * @param context
     * @param e
     * @return
     */
    public final static DelegateNode createNodeDelegate(Context context, Exception e) {
        DelegateNode delegate = new DelegateNode();
        NvNode node = ProcessConfigContext.getContext().getCurrentNode();
        delegate.setProcessId(context.getProcessId());
        delegate.setBizSerno(context.getBizSerno());
        delegate.setNodeId(node.getId());
        delegate.setException(e);
        delegate.setNodeName(node.getName());
        delegate.setNodeStatus(context.getNodeStatus());
        delegate.setNodeType(NodeType.get(node.getType()));
        delegate.setNextNodeId(context.getNextNodeId());
        return delegate;
    }

    /**
     * 创建任务监听对象
     * @param context
     * @param e
     * @return
     */
    public final static DelegateTask createTaskDelegate(Context context, Exception e) {
        NvTask task = ProcessConfigContext.getContext().getCurrentTask();
        DelegateTask delegate = new DelegateTask();
        delegate.setTaskId(context.getNodeId());
        delegate.setBizSerno(context.getBizSerno());
        delegate.setTaskName(task.getName());
        delegate.setException(e);
        return delegate;
    }
    
    
}
