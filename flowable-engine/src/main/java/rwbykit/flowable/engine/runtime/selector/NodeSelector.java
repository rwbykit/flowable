package rwbykit.flowable.engine.runtime.selector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.core.Constants;
import rwbykit.flowable.core.Context;
import rwbykit.flowable.core.FlowableException;
import rwbykit.flowable.core.FlowableRuntimeException;
import rwbykit.flowable.core.Result;
import rwbykit.flowable.core.Selector;
import rwbykit.flowable.core.model.parser.Link;
import rwbykit.flowable.core.model.parser.Node;
import rwbykit.flowable.core.model.parser.Process;
import rwbykit.flowable.core.model.runtime.NodeInstance;
import rwbykit.flowable.core.util.Collections;
import rwbykit.flowable.core.util.Strings;
import rwbykit.flowable.core.util.Utils;
import rwbykit.flowable.engine.factory.GenericObjectFactory;
import rwbykit.flowable.engine.runtime.LoggerHelper;
import rwbykit.flowable.engine.runtime.parameter.LinkParameter;
import rwbykit.flowable.engine.runtime.parameter.ParameterHelper;

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
    public Node select(Context context) throws FlowableException {

        //初始化第一个节点，即开始节点
        if (Strings.isEmpty(context.getCurrentInstance().getNodeId())) {
            NodeInstance nodeInstance = context.getRuntimeService().getNodeService().getByProcessInstanceId(context.getCurrentInstance().getProcessInstanceId());
            if (Objects.isNull(nodeInstance) || Strings.isEmpty(nodeInstance.getNodeInstanceId())) {
                return getStartNode(context);
            } else {
                // 当前节点执行完成
                context.getCurrentInstance().setCurrentNode(nodeInstance.getNodeId(), nodeInstance.getNodeInstanceId(), nodeInstance.getNodeStatus());
                if (Constants.STATUS_END.equals(nodeInstance.getNodeStatus())) {
                    logger.info(LoggerHelper.select_node_nextNodeMessage(context));
                    // 获取当前节点的下一节点
                    return selectNextNode(context);
                }

                logger.info(LoggerHelper.select_node_returnNonCompleteMessage(context));
                // 未执行完成获取当前节点
                return context.getProcessConfigService().getNode(nodeInstance.getNodeId());
            }
        }
        // 存在当前执行节点
        else {

            // 当前节点执行完成
            if (Constants.STATUS_END.equals(context.getCurrentInstance().getNodeStatus())) {
                if (Strings.nonEmpty(context.getParam("nextNodeId"))) {
                    logger.info(LoggerHelper.select_node_contextNextNodeMessage(context));
                    return context.getProcessConfigService().getNode(context.getParam("nextNodeId"));
                } else {
                    logger.info(LoggerHelper.select_node_ctxNonNextStartMessage(context));
                    return selectNextNode(context);
                }
            }

            // 拒绝  查询结束节点
            if (Constants.STATUS_REFUSE.equals(context.getCurrentInstance().getNodeStatus())) {
                logger.info("流程实例[{}], 当前节点[{}]已拒绝, 查找结束节点", context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeId());
                return findEndNode(context);
            } else {
                logger.info("流程实例[{}], 当前节点[{}]未执行完成, 返回当前节点信息", context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeId());
                return context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId());

            }
        }
    }

    /**
     * 查询结束节点
     *
     * @return
     */
    protected Node findEndNode(Context context) {
        Process process = context.getProcessConfigService().getProcess();
        Node node = process.getNodes().parallelStream().filter(s -> Strings.ignoreCaseCompare("END", s.getType())).findFirst().orElse(null);
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
    protected Node selectNextNode(Context context) throws FlowableException {

        List<Link> links = context.getProcessConfigService().getNode(context.getCurrentInstance().getNodeId()).getLinks();
        if (Collections.isEmpty(links)) {
            return null;
        }

        Link usefulLink = null;
        for (Link link : links) {
            if (usefulLink == null && Strings.ignoreCaseCompare("DEFAULT", link.getType())) {
                logger.info("流程实例[{}], 当前节点[{}], 路由[{}]为默认路由", context.getCurrentInstance().getProcessInstanceId(),
                        context.getCurrentInstance().getNodeId(), link.getId());
                usefulLink = link;
            } else if (Strings.ignoreCaseCompare("CONDITION", link.getType())) {
                LinkParameter parameter = ParameterHelper.createLinkParameter(context,
                        context.getRuntimeService().getApprovalService().getAllApprovalInstance(context.getCurrentInstance().getNodeInstanceId()),
                        link.getValueFields());
                Result<Boolean> result = (Result<Boolean>) GenericObjectFactory.factory().getRunner(link.getRunMode()).run(link.getRunValue(), parameter);
                logger.info("流程实例[{}], 当前节点[{}], 路由[{}]执行结果为:{}", context.getCurrentInstance().getProcessInstanceId(),
                        context.getCurrentInstance().getNodeId(), link.getId(), result);
                if (result.getResult()) {
                    usefulLink = link;
                    break;
                }
            } else {
                logger.warn("流程实例[{}], 当前节点[{}], 存在多个默认路由", context.getCurrentInstance().getProcessId(), context.getCurrentInstance().getNodeId());
            }
        }

        if (Objects.nonNull(usefulLink)) {
            logger.info("流程实例[{}], 当前节点[{}], 查找的下一路由为:RouteID[{}], RouteName[{}]",
                    context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeId(), usefulLink.getId(), usefulLink.getName());
            logger.info("流程实例[{}], 当前节点[{}]通过路由条件查找的下一节点为[{}]",
                    context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeId(), usefulLink.getId());
            return context.getProcessConfigService().getNode(usefulLink.getTargetRef());
        }
        logger.warn("流程实例[{}], 当前节点[{}]通过路由条件查找的下一节点为空", context.getCurrentInstance().getProcessInstanceId(), context.getCurrentInstance().getNodeId());
        return null;

    }

    protected Node getStartNode(Context context) {
        logger.info("当前流程[{}]为寻找开始节点", context.getCurrentInstance().getProcessInstanceId());
        List<Node> nodes = context.getProcessConfigService()
                .getProcess()
                .getNodes()
                .parallelStream()
                .filter(node -> Strings.ignoreCaseCompare("START", node.getType()))
                .collect(Collectors.toList());

        if (Collections.isEmpty(nodes) || nodes.size() > 1) {
            String errorMsg = Utils.formatMessage("当前流程[{}]不存在开始节点或者存在多个开始节点!", context.getCurrentInstance().getProcessInstanceId());
            logger.error(errorMsg);
            throw new FlowableRuntimeException(errorMsg);
        }
        return nodes.get(0);
    }


}

