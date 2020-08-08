package rwbykit.flowableTemp.object.factory;

import rwbykit.flowableTemp.Constants;
import rwbykit.flowableTemp.FlowableRuntimeException;
import rwbykit.flowable.engine.runtime.actuator.node.ArtificialNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.AutoNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.EndNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.StartNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.SubProcessNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.porcess.ProcessActuator;
import rwbykit.flowable.engine.runtime.actuator.task.AutoTaskActuator;
import rwbykit.flowableTemp.core.customized.actuator.ExpressionRunner;
import rwbykit.flowableTemp.core.customized.actuator.InvokeClassRunner;
import rwbykit.flowableTemp.core.customized.actuator.SpringBeanRunner;
import rwbykit.flowableTemp.core.enumeration.ActuatorType;
import rwbykit.flowableTemp.core.enumeration.NodeType;
import rwbykit.flowableTemp.core.enumeration.RouteType;
import rwbykit.flowable.engine.enumeration.TaskType;
import rwbykit.flowableTemp.core.selector.NodeSelector;
import rwbykit.flowable.engine.runtime.selector.TaskSelector;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowable.engine.util.Maps;
import rwbykit.flowableTemp.model.enumeration.ExecuteMode;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GenericObjectFactory implements ObjectFactory {

    private static Map<String, Map<String, Object>> REGISTER_OBJECT = new ConcurrentHashMap<>();

    private GenericObjectFactory() {}

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getObject(String category, String type) {
        if (REGISTER_OBJECT.containsKey(category)) {
            Map<String, Object> objectMap = REGISTER_OBJECT.get(category);
            if (objectMap.containsKey(type)) {
                return (T) objectMap.get(type);
            }
        }
        throw new FlowableRuntimeException(FlowableHelper.formatMessage("Type[], Key[] not found!", category, type));
    }

    static {

        Map<String, Map<String, Object>> registerObject = new ConcurrentHashMap<>();

        // 流程执行器注册
        Map<String, Object> processObject = Maps.lambdaMap()
                .add(ActuatorType.PROCESS, new ProcessActuator())
                .concurrentHashMap()
                .get();
        register(registerObject, Constants.PROCESS_ACTUATOR, processObject);

        // 节点对象注册
        Map<String, Object> nodeObject = Maps.lambdaMap()
                .add(NodeType.ARTI.toString(), new ArtificialNodeActuator())
                .add(NodeType.AUTO.toString(), new AutoNodeActuator())
                .add(NodeType.END.toString(), new EndNodeActuator())
                .add(NodeType.START.toString(), new StartNodeActuator())
                .add(NodeType.SUB.toString(), new SubProcessNodeActuator())
                .concurrentHashMap()
                .get();
        register(registerObject, Constants.NODE_ACTUATOR, nodeObject);

        // 任务对象注册
        Map<String, Object> taskObject = Maps.lambdaMap()
                .add(TaskType.DEFAULT, new AutoTaskActuator())
                .concurrentHashMap()
                .get();
        register(registerObject, Constants.TASK_ACTUATOR, taskObject);

        // 选择器执行对象注册
        Map<String, Object> selectorObject = Maps.lambdaMap()
                .add(RouteType.NODE.toString(), new NodeSelector())
                .add(RouteType.TASK.toString(), new TaskSelector())
                .concurrentHashMap()
                .get();
        register(registerObject, Constants.ROUTE_SELECTOR, selectorObject);

        // 运行者注册
        Map<String, Object> runnerObject = Maps.lambdaMap()
                .add(ExecuteMode.BEAN.toString(), new SpringBeanRunner())
                .add(ExecuteMode.EXPRESSION.toString(), new ExpressionRunner())
                .add(ExecuteMode.INVOKE.toString(), new InvokeClassRunner())
                .concurrentHashMap()
                .get();
        register(registerObject, Constants.EXECUTION_RUNNER, runnerObject);

        REGISTER_OBJECT.putAll(registerObject);

    }

    private static void register(Map<String, Map<String, Object>> objectMap, String category, Map<String, Object> typeMap) {
        objectMap.putIfAbsent(category, typeMap);
    }


}
