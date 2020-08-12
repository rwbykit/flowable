package rwbykit.flowable.engine.runtime.actuator.node.artificial.approval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.FlowableRuntimeException;
import rwbykit.flowable.engine.factory.support.GenericFactoryAware;
import rwbykit.flowable.core.util.Asserts;
import rwbykit.flowable.core.util.Collections;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * 
 * 人工审批提交服务工厂
 * 
 * @author Cytus_
 * @since 2018年6月26日 下午2:14:20
 * @version 1.0
 *
 */
public class ArtificialApprovalSubmitServiceFactory extends GenericFactoryAware {
    
    private final static Logger logger = LoggerFactory.getLogger(ArtificialApprovalSubmitServiceFactory.class);
    
    private static ArtificialApprovalSubmitActuator defaultSubmitActuator = null;
    
    private static class ArtificialApprovalSubmitServiceFactoryHolder {
        private static final ArtificialApprovalSubmitServiceFactory FACTORY = new ArtificialApprovalSubmitServiceFactory();
    }
    
    private ArtificialApprovalSubmitServiceFactory() {
        
        
    } 

    public final static ArtificialApprovalSubmitServiceFactory factory() {
        return ArtificialApprovalSubmitServiceFactoryHolder.FACTORY;
    }
    
    /**
     * 获取审批类型提交处理
     * @param approverType 审批类型
     * @return
     * @throws
     */
    public ArtificialApprovalSubmitActuator getArtificialApprovalSubmitService(String approverType) {
        Asserts.nonEmpty(approverType, "Unsupported Approver Type[{}]!", approverType);
        ArtificialApprovalSubmitActuator actuator = this.getObjectFactory().getObject("", approverType);
        if (Objects.nonNull(actuator)) {
            if (Objects.nonNull(defaultSubmitActuator)) {
                initDefaultServiceBeanName();
            }
            actuator = defaultSubmitActuator;
        }
       return actuator;
    }
    
    /**
     * 初始化defaultServiceBeanName
     */
    private synchronized void initDefaultServiceBeanName() {
        if (Objects.nonNull(defaultSubmitActuator)) {
            List<ArtificialApprovalSubmitActuator> actuators = this.getObjectFactory().getAllType("");
            List<ArtificialApprovalSubmitActuator> defaultActuators = actuators.stream()
                    .filter(actuator -> actuator.getClass().isAnnotationPresent(DefaultArtificialApprovalSubmit.class))
                    .filter(actuator -> actuator.getClass().getAnnotation(DefaultArtificialApprovalSubmit.class).isPrimary())
                    .collect(Collectors.toList());

            if (Collections.nonEmpty(defaultActuators)) {
                Asserts.maxNumber(1, 1, "There [{}] are many default implementation classes. Please specify a major implementation class", ArtificialApprovalSubmitActuator.class.getName());
                defaultSubmitActuator = defaultActuators.get(0);
            }
            throw new FlowableRuntimeException();
        }
    }
    
}
