package rwbykit.flowable;

import rwbykit.flowable.core.Configuration;
import rwbykit.flowable.core.GenericBootstrap;
import rwbykit.flowable.core.annotation.Type;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Lists;
import rwbykit.flowable.core.util.Maps;
import rwbykit.flowable.core.util.Objects;
import rwbykit.flowable.engine.runtime.actuator.node.ArtificialNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.AutoNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.EndNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.StartNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.node.SubProcessNodeActuator;
import rwbykit.flowable.engine.runtime.actuator.porcess.ProcessActuator;
import rwbykit.flowable.engine.runtime.actuator.task.AutoTaskActuator;
import rwbykit.flowable.engine.runtime.calculator.approver.GenericApproverCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.GenericAssigneeCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.assignee.RunnerAssigneeCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.polymerization.ApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.polymerization.IntersectionApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.calculator.approver.polymerization.UnionApproverPolymerizationCalculator;
import rwbykit.flowable.engine.runtime.runner.InvokeClassRunner;
import rwbykit.flowable.engine.runtime.scheduler.AsyncProcessScheduler;
import rwbykit.flowable.engine.runtime.scheduler.SyncProcessScheduler;
import rwbykit.flowable.engine.runtime.selector.TaskSelector;
import rwbykit.flowable.extension.calculator.approver.AppointApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.CompeteApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.MultiApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.MultiJointlySignApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.PoolApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.RandomApproverCalculator;
import rwbykit.flowable.extension.calculator.approver.assignee.InitiatorRelationshipCalculator;
import rwbykit.flowable.extension.calculator.approver.pool.DefaultApproverTaskPoolCalculator;
import rwbykit.flowable.extension.calculator.artificial.approval.HalfVetoMultiJoinSignRuleCalculator;
import rwbykit.flowable.extension.calculator.artificial.approval.OneVoteVetoMultiJoinSignRuleCalculator;
import rwbykit.flowable.extension.calculator.artificial.approval.Two_ThirdsPassedMultiJoinSignRuleCalculator;
import rwbykit.flowable.extension.runner.ExpressionRunner;
import rwbykit.flowable.extension.runner.SpringBeanRunner;
import rwbykit.flowable.parser.tag.ArtifactNodeParser;
import rwbykit.flowable.parser.tag.AssigneeParser;
import rwbykit.flowable.parser.tag.AssignmentParser;
import rwbykit.flowable.parser.tag.AutoNodeParser;
import rwbykit.flowable.parser.tag.GenericNodeParser;
import rwbykit.flowable.parser.tag.LinkParser;
import rwbykit.flowable.parser.tag.ListenerParser;
import rwbykit.flowable.parser.tag.ProcessParser;
import rwbykit.flowable.parser.tag.PropertyParser;
import rwbykit.flowable.parser.tag.TaskParser;
import rwbykit.flowable.parser.tag.ViewPageParser;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Flowable {

    public static GenericBootstrap byDefaultRegister() {
        return new GenericBootstrapImpl();
    }

    private static class GenericBootstrapImpl implements GenericBootstrap {

        private static Map<String, Map<String, Object>> registers = new ConcurrentHashMap<>();

        static {
            Map<String, Map<String, Object>> registerObject = new ConcurrentHashMap<>();

            // register parser
            registerObject(registerObject, Lists.newArrayList(ArtifactNodeParser.class,
                    AssigneeParser.class,
                    AssignmentParser.class,
                    AutoNodeParser.class,
                    LinkParser.class,
                    ListenerParser.class,
                    GenericNodeParser.class,
                    ProcessParser.class,
                    PropertyParser.class,
                    TaskParser.class,
                    ViewPageParser.class));

            // register actuator
            registerObject(registerObject, Lists.newArrayList(ProcessActuator.class,
                    AutoNodeActuator.class,
                    ArtificialNodeActuator.class,
                    StartNodeActuator.class,
                    EndNodeActuator.class,
                    SubProcessNodeActuator.class,
                    AutoTaskActuator.class));

            // register polymerization calculator
            registerObject(registerObject, Lists.newArrayList(ApproverPolymerizationCalculator.class,
                    IntersectionApproverPolymerizationCalculator.class,
                    UnionApproverPolymerizationCalculator.class));

            // register assignee calculator
            registerObject(registerObject, Lists.newArrayList(GenericAssigneeCalculator.class,
                    RunnerAssigneeCalculator.class,
                    InitiatorRelationshipCalculator.class));

            // register approver calculator
            registerObject(registerObject, Lists.newArrayList(GenericApproverCalculator.class,
                    CompeteApproverCalculator.class,
                    AppointApproverCalculator.class,
                    MultiApproverCalculator.class,
                    MultiJointlySignApproverCalculator.class,
                    PoolApproverCalculator.class,
                    RandomApproverCalculator.class));

            // register runner
            registerObject(registerObject, Lists.newArrayList(InvokeClassRunner.class,
                    ExpressionRunner.class,
                    SpringBeanRunner.class));

            // register approval rule calculator
            registerObject(registerObject, Lists.newArrayList(HalfVetoMultiJoinSignRuleCalculator.class,
                    OneVoteVetoMultiJoinSignRuleCalculator.class,
                    Two_ThirdsPassedMultiJoinSignRuleCalculator.class));

            // register approver task pool calculator
            registerObject(registerObject, Lists.newArrayList(DefaultApproverTaskPoolCalculator.class));

            // register scheduler
            registerObject(registerObject, Lists.newArrayList(AsyncProcessScheduler.class,
                    SyncProcessScheduler.class));

            // register selector
            registerObject(registerObject, Lists.newArrayList(TaskSelector.class));

            registers.putAll(registerObject);

        }

        private static void registerObject(Map<String, Map<String, Object>> registerMap, List<Class<?>> classTypes) {
            for (Class<?> classType : classTypes) {
                Object object = Objects.newInstance(classType);
                registerObject(registerMap, object);
            }
        }

        public static void registerObject(Map<String, Map<String, Object>> registerMap, Object object) {
            if (Objects.nonNull(object)) {
                Class<?> classType = object.getClass();
                if (classType.isAnnotationPresent(Type.class)) {
                    Type type = classType.getAnnotation(Type.class);
                    registerObject(registerMap, type.category(), type.type(), object);
                } else if (classType.isAnnotationPresent(Type.Types.class)) {
                    Type.Types types = classType.getAnnotation(Type.Types.class);
                    Arrays.stream(types.value()).forEach(type -> registerObject(registerMap, type.category(), type.type(), object));
                }
            }
        }

        private static void registerObject(Map<String, Map<String, Object>> registerMap, String category, String type, Object object) {
            Map<String, Object> typeMap = registerMap.get(category);
            if (Objects.isNull(typeMap)) {
                typeMap = new ConcurrentHashMap<>(8);
                registerMap.put(category, typeMap);
            }
            typeMap.put(type, object);
        }

        @Override
        public Configuration<?> configure() {
            registers.entrySet().stream().forEach(entry -> {
                registers.put(entry.getKey(), Maps.immutable(entry.getValue()));
            });
            registers = Maps.immutable(registers);
            return new ConfigurationImpl(registers);
        }

        @Override
        public GenericBootstrap register(Object object) {
            registerObject(registers, object);
            return this;
        }

        @Override
        public GenericBootstrap register(Class<?> classType) {
            return register(Objects.newInstance(classType));
        }


        @Override
        public GenericBootstrap register(String category, String type, Object object) {
            Asserts.nonEmpty(category, "Category must not empty!");
            Asserts.nonEmpty(type, "Type must not empty!");
            Asserts.nonNull(object, "Register object must not null!");
            registerObject(registers, category, type, object);
            return this;
        }

        @Override
        public GenericBootstrap register(String category, String type, Class<?> classType) {
            return register(category, type, Objects.newInstance(classType));
        }

    }


}
