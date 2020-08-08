package rwbykit.flowableTemp.core.service.util;

import rwbykit.flowableTemp.core.service.HistoryService;
import rwbykit.flowableTemp.core.util.SpringContexts;

public final class Historys {

	private final static HistoryService historyService = SpringContexts.getBean(HistoryService.class);
	
	
	public final static void toHistory(String processInstId) {
		historyService.toHistory(processInstId);
	}
	
	
}
