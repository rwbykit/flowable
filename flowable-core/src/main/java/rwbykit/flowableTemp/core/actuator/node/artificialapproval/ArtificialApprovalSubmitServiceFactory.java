package rwbykit.flowableTemp.core.actuator.node.artificialapproval;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rwbykit.flowable.engine.FlowableRuntimeException;
import rwbykit.flowableTemp.core.enumeration.ApproverType;
import rwbykit.flowableTemp.core.util.FlowableHelper;
import rwbykit.flowableTemp.core.util.SpringContexts;
import rwbykit.flowableTemp.core.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * 
 * 人工审批提交服务工厂
 * 
 * @author Cytus_
 * @since 2018年6月26日 下午2:14:20
 * @version 1.0
 *
 */
public class ArtificialApprovalSubmitServiceFactory {
    
    private final static Logger logger = LoggerFactory.getLogger(ArtificialApprovalSubmitServiceFactory.class);
    
    private static String defaultServiceBeanName = null;
    
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
    public ArtificialApprovalSubmitService getArtificialApprovalSubmitService(ApproverType approverType) {
        if (Objects.isNull(approverType)) {
            String errorMsg = FlowableHelper.formatMessage("Unsupported Approver Type[{}]!", approverType);
            logger.error(errorMsg);
            //throw new UnsupportedArgumentException(errorMsg);
        }
        String bean = null;//Factory.factory().getMapper(Constants.ARTIFICIAL_APPROVAL_SUBMIT_SERVICE, approverType);
        if (Strings.isEmpty(bean)) {
            if (Strings.isEmpty(defaultServiceBeanName)) {
                initDefaultServiceBeanName();
            }
            bean = defaultServiceBeanName;
        }
        ArtificialApprovalSubmitService artificialApprovalSubmitService = SpringContexts.getBean(bean, ArtificialApprovalSubmitService.class);
        return artificialApprovalSubmitService;
    }
    
    /**
     * 初始化defaultServiceBeanName
     */
    private synchronized void initDefaultServiceBeanName() {
        if (Strings.isEmpty(defaultServiceBeanName)) {
            String[] beanNames = SpringContexts.getBeanNamesForAnnotation(DefaultArtificialApprovalSubmit.class);
            List<String> primaryKey = new ArrayList<>();
            for (String beanName : beanNames) {
                ArtificialApprovalSubmitService artificialApprovalSubmitService = SpringContexts.getBean(beanName, ArtificialApprovalSubmitService.class);
                DefaultArtificialApprovalSubmit defaultArtificialApprovalSubmit = artificialApprovalSubmitService.getClass().getAnnotation(DefaultArtificialApprovalSubmit.class);
                if (Objects.nonNull(defaultArtificialApprovalSubmit) && defaultArtificialApprovalSubmit.isPrimary()) {
                    primaryKey.add(beanName);
                }
            }
            
            if (FlowableHelper.isNullOrEmpty(primaryKey)) {
                if (beanNames.length > 1) {
                    String errorMsg = FlowableHelper.formatMessage("There [{}] are many default implementation classes. Please specify a major implementation class", ArtificialApprovalSubmitService.class.getName());
                    logger.error(errorMsg);
                    throw new FlowableRuntimeException(errorMsg) ;
                } else {
                    defaultServiceBeanName = beanNames[0];
                }
            } else if (primaryKey.size() > 1) {
                String errorMsg = FlowableHelper.formatMessage("There [{}] are many primary implementation classes!", ArtificialApprovalSubmitService.class.getName());
                logger.error(errorMsg);
                throw new FlowableRuntimeException(errorMsg) ;
            } else {
                defaultServiceBeanName = primaryKey.get(0);
            }
            
        }
    }
    
}
