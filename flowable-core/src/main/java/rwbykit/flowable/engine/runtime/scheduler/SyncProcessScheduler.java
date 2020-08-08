package rwbykit.flowable.engine.runtime.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.Actuator;
import rwbykit.flowable.engine.Context;
import rwbykit.flowable.engine.FlowableException;

import java.util.List;

/**
 * 流程调度器
 * 
 * @author Cytus_
 * @since 2018年12月18日 上午8:37:00
 * @version 1.0
 */
public class SyncProcessScheduler extends AbstractProcessScheduler {

    private final static Logger logger = LoggerFactory.getLogger(SyncProcessScheduler.class);

    public SyncProcessScheduler(List<SchedulerPostProcessor> schedulerPostProcessors) {
        super(schedulerPostProcessors);
    }

    @Override
    public Context schedule(Actuator<Context, Context> object, Context inArgs) throws FlowableException {
        return null;
    }

    /*@Autowired
    @Qualifier("asyncProcessThreadPool")
    private ThreadPool threadPool;
    
    @Override
    public Context dispatch(Context context) throws FlowableException {
        
        
        ProcessActuator actuator = ActuatorFactory.factory().getProcessActuator(ActuatorType.PROCESS);

        CoreSelector<NvNode> nodeRouteSelector = SelectorFactory.factory().getRouteSelector(RouteType.NODE);
        *//*
         * 当前节点为空，应为以下几种情况
         * 1：自动流程第一次调用
         * 2：自动流程异常后重新调起未传入当前节点ID
         * 3：人工流程异常
         *//*
        if (Strings.isEmpty(context.getNodeId())) {
        
            // 1：自动流程第一次调用
            if (Strings.isEmpty(context.getNodeInstId())) {
                NvNode node = nodeRouteSelector.select(context);
                context.setNodeId(node.getId());
                context = super.processSchedule(actuator, context);
            } else {
                // 2：自动流程异常后重新调起未传入当前节点ID
                // 3：人工流程异常
                NvInsNode insNode = FlowableHelper.ofNullableElse(Nodes.getByInstaceId(context.getNodeInstId()), new NvInsNode());
                context.setNodeId(insNode.getNodeId());
                context.setNodeStatus(insNode.getStatus());
                if (ProcessConstants.STATUS_END.equals(insNode.getStatus())) {
                    logger.warn("流程实例[{}], 节点实例[{}], 已执行结束", context.getProcessInstId(), context.getNodeInstId());
                } else if (Strings.isNotEmpty(insNode.getInstanceId())) {
                    logger.warn("流程实例[{}], 节点实例[{}], 当前状态为[{}], 当前重新执行!", context.getProcessInstId(), context.getNodeInstId(), context.getNodeStatus());
                    super.processSchedule(actuator, context);
                }
            }
            
        } 
        *//*
         * 当前节点不为空，应为以下几种情况
         * 1：人工节点提交
         * 2：自动流程异常后重新调起
         *//*
        else {
            
            NvNode currNode = ProcessConfigContext.getContext().getProcess().getNode(context.getNodeId());
            
            *//*
             *  第一种情况处理，即人工节点提交，此时需要判断以下情况
             *  1：人工第一个节点提交
             *  2：非人工第一个节点提交
             *//*
            if (NodeType.compare(NodeType.ARTI, currNode.getType())) {
                NvNode startNode = this.getPreStartNode(ProcessConfigContext.getContext().getProcess(), context.getNodeId());
                // 当前节点前置节点为开始节点， 单独处理
                if (Objects.nonNull(startNode)) {
                    doStartNodeScheduler(context, startNode);
                } 
            } 
            *//*
             * 第二种情况， 自动流程异常后重新调起
             *//*
            context = super.processSchedule(actuator, context);
            
            *//*else if (NodeType.compare(NodeType.AUTO, currNode.getType())) {
                nova = super.processSchedule(actuator, nova);
            }*//*
        }
        
        
        if (ProcessConstants.STATUS_APPROVAL.equals(context.getNodeStatus())) {
            logger.info("流程实例[{}], 当前节点[{}]状态为审批中, 请进行人工审批!", context.getProcessInstId(), context.getNodeId());
            context.setProcessStatus(ProcessConstants.STATUS_APPROVAL);
        } else if (ProcessConstants.STATUS_END.equals(context.getNodeStatus())) {
            
            NvNode nextNode = nodeRouteSelector.select(context);
            initNextNode(context, nextNode);
            try {
                if (NodeType.compare(NodeType.AUTO, nextNode.getType()) || NodeType.compare(NodeType.END, nextNode.getType())) {
                        logger.info("流程实例[{}], 当前节点[{}]为自动节点或结束节点, 进入异步执行", context.getProcessInstId(), nextNode.getId());
                        threadPool.add(new AsyncProcessScheduler(context, ProcessConfigContext.getContext().getProcess()));
                } else {
                    ProcessConfigContext.getContext().setNodeId(nextNode.getId());
                    logger.info("流程实例[{}], 当前节点[{}]为人工审批节点", context.getProcessInstId(), nextNode.getId());
                    context = super.processSchedule(actuator, context);
                    if (ProcessConstants.STATUS_APPROVAL.equals(context.getNodeStatus())) {
                        logger.info("流程实例[{}], 当前节点[{}]状态为审批中, 请进行人工审批!", context.getProcessInstId(), context.getNodeId());
                        context.setProcessStatus(ProcessConstants.STATUS_APPROVAL);
                    }
                }
            } catch (Exception e) {
                String errorMessage = FlowableHelper.formatMessage("流程实例[{}], 自动节点[{}]调度异常!", context.getProcessInstId(), nextNode.getId());
                logger.error(errorMessage, e);
                context.setProcessStatus(ProcessConstants.STATUS_EXCEPTION);
                throw new FlowableException(errorMessage, e);
            } finally {
                Processes.updateStatus(context.getProcessInstId(), context.getProcessStatus());
            }
        } else {
            logger.warn("流程实例[{}], 当前节点[{}]状态为:{}, 退出本次执行", context.getProcessInstId(), context.getNodeId(), context.getNodeStatus());
        }
        
        return context;
        
    }*/
    
}
