package software.dygzt.service.research;

import software.dygzt.service.research.model.BbscConditionVO;
import software.dygzt.service.research.model.ResearchConditionVO;
import software.dygzt.service.research.model.ResearchTable;

public interface ResearchVariableService {
	/**
	 * 生成调研表
	 * @param condition
	 * @return
	 */
	public ResearchTable createTable(ResearchConditionVO condition) throws Exception;
	/**
	 * 报表生成系统生成报表
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public ResearchTable createTable(BbscConditionVO condition) throws Exception;
}
