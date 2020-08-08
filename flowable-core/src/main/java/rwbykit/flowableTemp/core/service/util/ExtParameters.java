/*
package rwbykit.flowableTemp.core.service.util;

import com.alibaba.fastjson.JSONObject;
import com.war3.nova.beans.NvInsExtParams;
import rwbykit.flowableTemp.core.service.runtime.ParameterService;
import rwbykit.flowableTemp.core.util.SpringContexts;
import rwbykit.flowableTemp.core.util.Strings;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

*/
/**
 * 流程扩展参数
 * 
 * @author Cytus_
 * @since 2018年12月26日 上午10:42:42
 * @version 1.0
 *//*

public final class ExtParameters {

	private final static ParameterService insExtParamsService = SpringContexts.getBean(ParameterService.class);
	
	
	@SuppressWarnings("unchecked")
	public final static Map<String, Object> queryExtParams(String processInstId) {
		NvInsExtParams params = insExtParamsService.queryByProcessInstId(processInstId);
		Map<String, Object> paramMap = new ConcurrentHashMap<>();
		if (Objects.nonNull(params) && Strings.isNotEmpty(params.getExtParams())) {
			Map<String, Object> extMap = JSONObject.parseObject(params.getExtParams(), Map.class);
			paramMap.putAll(extMap);
		}
		return paramMap;
	}
	
	public final static void insert(String processInstId, Map<String, Object> extParams) {
		String extParamsJsonString = JSONObject.toJSONString(extParams);
		NvInsExtParams params = new NvInsExtParams();
		params.setExtParams(extParamsJsonString);
		params.setProcessInstId(processInstId);
		insExtParamsService.insert(params);
	}
	
}
*/
