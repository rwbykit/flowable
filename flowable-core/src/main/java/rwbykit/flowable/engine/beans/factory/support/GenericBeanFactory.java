package rwbykit.flowable.engine.beans.factory.support;

import rwbykit.flowable.engine.beans.factory.BeanFactory;
import rwbykit.flowableTemp.FlowableRuntimeException;
import rwbykit.flowableTemp.core.util.FlowableHelper;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class GenericBeanFactory implements BeanFactory {

    private static Map<String, Map<String, Object>> REGISTER_OBJECT = new ConcurrentHashMap<>();

    public GenericBeanFactory() {
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getBean(String category, String type) {
        if (REGISTER_OBJECT.containsKey(category)) {
            Map<String, Object> objectMap = REGISTER_OBJECT.get(category);
            if (objectMap.containsKey(type)) {
                return (T) objectMap.get(type);
            }
        }
        throw new FlowableRuntimeException(FlowableHelper.formatMessage("Type[], Key[] not found!", category, type));
    }

    public void registerRuntimeObject(String category, String type, Object runtimeObject) {
        synchronized (REGISTER_OBJECT) {
            Map<String, Object> typeMap = REGISTER_OBJECT.get(category);
            if (Objects.isNull(typeMap)) {
                typeMap = new ConcurrentHashMap<>(8);
            }
            typeMap.putIfAbsent(type, runtimeObject);
            REGISTER_OBJECT.put(category, typeMap);
        }
    }

    /*static {

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

    }*/

    /*private static void register(Map<String, Map<String, Object>> objectMap, String category, Map<String, Object> typeMap) {
        objectMap.putIfAbsent(category, typeMap);
    }*/

}
