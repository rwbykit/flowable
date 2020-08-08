package rwbykit.flowableTemp.core.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowableTemp.FlowableException;
import rwbykit.flowableTemp.FlowableRuntimeException;
import rwbykit.flowable.engine.Selector;
import rwbykit.flowableTemp.core.ProcessConstants;
import rwbykit.flowable.engine.runtime.parameter.LinkParameter;
import rwbykit.flowableTemp.core.customized.link.RouteRunner;
import rwbykit.flowableTemp.core.enumeration.NodeType;
import rwbykit.flowableTemp.core.enumeration.RouteConditionType;
import rwbykit.flowableTemp.core.runtime.Context;
import rwbykit.flowableTemp.core.runtime.model.NodeInstance;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowable.engine.runtime.LoggerHelper;
import rwbykit.flowable.engine.runtime.parameter.ParameterHelper;
import rwbykit.flowableTemp.core.util.Strings;
import rwbykit.flowable.model.Link;
import rwbykit.flowable.model.Node;
import rwbykit.flowable.model.Process;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 节点路由选择器
 *
 * @author Cytus_
 * @version 1.0
 * @since 2018年6月26日 下午8:55:29
 */
public class NodeSelector implements Selector<Context, Node> {

    private final static Logger logger = LoggerFactory.getLogger(NodeSelector.class);

    @Override
    public Node select(Context context) {

        //初始化第一个节点，即开始节点
        if (Strings.isEmpty(context.getInstance().getNodeId())) {

            NodeInstance nodeInstance = context.getRuntimeService().getNodeService().getInstanceByProcessInstanceId(context.getInstance().getProcessInstanceId());

            if (Objects.isNull(nodeInstance) || Strings.isEmpty(nodeInstance.getInstanceId())) {
                return getStartNode(context);
            } else {
                // 当前节点执行完成
                context.getInstance().setRuntimeNode(nodeInstance.getNodeId(), nodeInstance.getInstanceId(), nodeInstance.getStatus());
                if (ProcessConstants.STATUS_END.equals(nodeInstance.getStatus())) {
                    logger.info(LoggerHelper.select_node_nextNodeMessage(context));
                    // 获取当前节点的下一节点
                    return selectNextNode(context);
                }

                logger.info(LoggerHelper.select_node_returnNonCompleteMessage(context));
                // 未执行完成获取当前节点    
                return context.getConfigService().getNode(nodeInstance.getNodeId());
            }
        }
        // 存在当前执行节点
        else {

            // 当前节点执行完成
            if (ProcessConstants.STATUS_END.equals(context.getInstance().getNodeStatus())) {
                if (Strings.nonEmpty(context.getParam("nextNodeId"))) {
                    logger.info(LoggerHelper.select_node_contextNextNodeMessage(context));
                    return context.getConfigService().getNode(context.getParam("nextNodeId"));
                } else {
                    logger.info(LoggerHelper.select_node_ctxNonNextStartMessage(context));
                    return selectNextNode(context);
                }
            }

            // 拒绝  查询结束节点
            if (ProcessConstants.STATUS_REFUSE.equals(context.getInstance().getNodeStatus())) {
                logger.info("流程实例[{}], 当前节点[{}]已拒绝, 查找结束节点", context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId());
                return findEndNode(context);
            } else {
                logger.info("流程实例[{}], 当前节点[{}]未执行完成, 返回当前节点信息", context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId());
                return context.getConfigService().getNode(context.getInstance().getNodeId());

            }
        }
    }

    /**
     * 针对指定节点，判断配置的关联关系
     *
     * @param srcNodeId
     * @param targetNodeId
     * @throws FlowableException
     */
    /*protected void assertNodeRelation(String srcNodeId, String targetNodeId) throws FlowableException {
        List<NvRoute> routes = ProcessConfigContext.getContext().getProcess().getRoute(srcNodeId);
        Asserts.assertCollectionNullOrEmpty(routes, "源节点[{}]未配置路由", srcNodeId);
        long count = routes.parallelStream().filter(s -> s.getTargetRef().equals(targetNodeId)).count();
        Asserts.assertMinNumber(count, 0, "源节点[{}], 目标节点[{}], 不存在路由关系", srcNodeId, targetNodeId);
    }*/

    /**
     * 查询结束节点
     *
     * @return
     */
    protected Node findEndNode(Context context) {
        Process process = context.getConfigService().getProcess();
        Node node = process.getNodes().values().parallelStream().filter(s -> NodeType.compare(NodeType.END, s.getType())).findFirst().orElse(null);
        if (Objects.isNull(node)) {
            throw new FlowableRuntimeException("流程[" + process.getId() + "]未配置结束节点!");
        }
        return node;
    }

    /**
     * 根据当前节点获取下一节点
     *
     * @param context
     * @return
     */
    protected Node selectNextNode(Context context) {

        List<Link> links = context.getConfigService().getNode(context.getInstance().getNodeId()).getLinks();
        if (FlowableHelper.isNullOrEmpty(links)) {
            return null;
        }

        Link usefulLink = null;
        for (Link link : links) {
            if (usefulLink == null && RouteConditionType.compare(RouteConditionType.DEFAULT, link.getType())) {
                logger.info("流程实例[{}], 当前节点[{}], 路由[{}]为默认路由", context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId(), link.getId());
                usefulLink = link;
            } else if (RouteConditionType.compare(RouteConditionType.CONDITION, link.getType())) {
                LinkParameter parameter = ParameterHelper.createLinkParameter(context,
                        context.getRuntimeService().getApprovalService().getAllByNodeInstanceId(context.getInstance().getNodeInstanceId()),
                        link.getValueFields());
                boolean result = RouteRunner.executeRoute(ExecuteMode.get(link.getRunMode()), link.getRunValue(), parameter);
                logger.info("流程实例[{}], 当前节点[{}], 路由[{}]执行结果为:{}", context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId(), link.getId(), result);
                if (result) {
                    usefulLink = link;
                    break;
                }
            } else {
                logger.warn("流程实例[{}], 当前节点[{}], 存在多个默认路由");
            }
        }

        if (Objects.nonNull(usefulLink)) {
            logger.info("流程实例[{}], 当前节点[{}], 查找的下一路由为:RouteID[{}], RouteName[{}]",
                    context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId(), usefulLink.getId(), usefulLink.getName());
            logger.info("流程实例[{}], 当前节点[{}]通过路由条件查找的下一节点为[{}]",
                    context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId(), usefulLink.getId());
            return context.getConfigService().getNode(usefulLink.getTargetRef());
        }
        logger.warn("流程实例[{}], 当前节点[{}]通过路由条件查找的下一节点为空", context.getInstance().getProcessInstanceId(), context.getInstance().getNodeId());
        return null;

    }

    protected Node getStartNode(Context context) {
        logger.info("当前流程[{}]为寻找开始节点", context.getInstance().getProcessInstanceId());
        List<Node> nodes = context.getConfigService()
                .getProcess()
                .getNodes()
                .values()
                .parallelStream()
                .filter(node -> NodeType.compare(NodeType.START, node.getType()))
                .collect(Collectors.toList());

        if (FlowableHelper.isNullOrEmpty(nodes) || nodes.size() > 1) {
            String errorMsg = FlowableHelper.formatMessage("当前流程[{}]不存在开始节点或者存在多个开始节点!", context.getInstance().getProcessInstanceId());
            logger.error(errorMsg);
            throw new FlowableRuntimeException(errorMsg);
        }
        return nodes.get(0);
    }


}
