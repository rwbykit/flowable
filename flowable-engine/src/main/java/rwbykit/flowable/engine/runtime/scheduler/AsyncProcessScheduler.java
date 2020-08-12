package rwbykit.flowable.engine.runtime.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.engine.Actuator;
import rwbykit.flowable.engine.Constants;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;
import rwbykit.flowable.engine.factory.ThreadPoolFactory;

import java.util.List;

/**
 * 异步流程度调度
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午8:45:25
 * @version 1.0
 */
@Type(category = Constants.TYPE_SCHEDULER, type = "Async")
public class AsyncProcessScheduler extends AbstractProcessScheduler {
    
    private final static Logger logger = LoggerFactory.getLogger(AsyncProcessScheduler.class);

    public AsyncProcessScheduler(List<SchedulerPostProcessor> schedulerPostProcessors) {
        super(schedulerPostProcessors);
    }

    @Override
    public Context schedule(Actuator<Context, Context> actuator, Context context) throws FlowableException {
        ThreadPoolFactory.factory().addRunnable(AsyncRunner.of(actuator, context));
        return context;
    }

    private static class AsyncRunner implements Runnable {

        private Actuator<Context, Context> actuator;
        private Context context;

        private AsyncRunner(Actuator<Context, Context> actuator, Context context) {
            this.actuator = actuator;
            this.context = context;
        }

        public static AsyncRunner of(Actuator<Context, Context> actuator, Context context) {
            return new AsyncRunner(actuator, context);
        }

        @Override
        public void run() {
            try {
                actuator.execute(context);
            } catch (Exception e) {
                logger.error("Async execute occur exception!", e);
            }
        }
    }


    
    /*public AsyncProcessScheduler(Context context, NvProcess process) {
        this.context = context;
        this.context.setProcessStatus(ProcessConstants.STATUS_RUNNING);
        this.process = process;
    }
    
    @Override
    public Context dispatch(Context context) throws FlowableException {

        ProcessActuator actuator = ActuatorFactory.factory().getProcessActuator(ActuatorType.PROCESS);
        CoreSelector<NvNode> routeNodeSelector = SelectorFactory.factory().getRouteSelector(RouteType.NODE);
        while (true) {
            context = super.processSchedule(actuator, context);
            
            if (ProcessConstants.STATUS_END.equals(context.getNodeStatus())) {
                NvNode nextNode = routeNodeSelector.select(context);
                if (Objects.isNull(nextNode)) {
                    logger.info("流程实例[{}]已执行结束!", context.getProcessInstId());
                    Processes.updateStatus(context.getProcessInstId(), ProcessConstants.STATUS_END);
                    // 迁移至历史记录
                    toHistory(context.getProcessInstId());
                    break;
                } else {
                    logger.info("流程实例[{}], 下一个需要执行的节点为:{}", context.getProcessInstId(), nextNode.getId());
                    initNextNode(context, nextNode);
                }
                
            }
            if (ProcessConstants.STATUS_APPROVAL.equals(context.getNodeStatus())) {
                logger.info("流程实例[{}], 当前节点[{}]状态为审批中, 请进行人工审批!", context.getProcessInstId(), context.getNodeId());
                context.setProcessStatus(ProcessConstants.STATUS_APPROVAL);
                break;
            }
            
        }
        
        return context;
    }

    @Override
    public Result<?> call() throws Exception {
        ProcessConfigContext.createContext(process);
        // 此处更新流程状态是为了处理人工那边重办的一些操作所做的冗余
        Processes.updateStatus(context.getProcessInstId(), context.getProcessStatus());
        try {
            logger.info("流程实例[{}]异步调度开始", context.getProcessInstId());
            context = dispatch(context);
            logger.info("流程实例[{}]异步调度结束", context.getProcessInstId());
        } catch (Exception e) {
            logger.error(FlowableHelper.formatMessage("流程实例[{}]执行异常!", context.getProcessInstId()), e);
        } finally {
            Processes.updateStatus(context.getProcessInstId(), context.getProcessStatus());
        }
        return DefaultThreadResult.createSuccess(null);
    }*/

}
