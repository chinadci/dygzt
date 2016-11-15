package software.dygzt.service.research;

import java.util.List;
import java.util.Map;

import software.dygzt.data.bbsc.dataobject.BbscTemplateDO;
import software.dygzt.data.research.dataobject.ResearchTableDO;
import software.dygzt.service.research.model.BbscConditionVO;
import software.dygzt.service.research.model.ResearchAjVO;
import software.dygzt.service.research.model.ResearchCellCondition;
import software.dygzt.service.research.model.ResearchConditionVO;
import software.dygzt.service.research.model.ResearchQueryCondition;
import software.dygzt.service.research.model.ResearchTable;
import software.dygzt.service.research.model.ResearchTableCell;
import software.dygzt.service.research.model.ResearchTableRow;
import software.dygzt.service.research.model.ResearchTableVO;
import software.dygzt.service.research.model.ResearchXXBModel;
import software.dygzt.service.research.model.SearchConditionVO;

public interface ResearchService {
	/**
	 * 生成列条件
	 * @param table
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public ResearchTable generateQueryConditionCol(ResearchConditionVO condition,ResearchQueryCondition base,String type) throws Exception;
	/**
	 * 生成列条件(报表生成系统)
	 * @param condition
	 * @param base
	 * @return
	 * @throws Exception
	 */
	public ResearchTable generateQueryConditionCol(BbscConditionVO condition,ResearchQueryCondition base,String type) throws Exception;
	/**
	 * 生成案件列表
	 * @param condition
	 * @return
	 */
	public List<ResearchAjVO> getAjList(String fydm,String baseCondition,String condition) throws Exception;
	/**
	 * 生成集中融合库案件列表
	 * @param fydm
	 * @param baseCondition
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List<ResearchAjVO> getJzrhAjList(String fydm, String baseCondition,String condition) throws Exception;
	/**
	 * 生成cell
	 * @param con 
	 * @param cellList
	 * @param fydm
	 * @param type 0：法院 1：案由 2：部门
	 * @param base 基本条件
	 * @return
	 * @throws Exception
	 */
	public ResearchTableCell generateCell(ResearchCellCondition con,List<ResearchTableCell> cellList,String fydm,int type,String base) throws Exception;
	/**
	 * 得到行总计
	 * @param startIndex
	 * @param rowlist
	 * @param row
	 */
	public List<ResearchTableCell> generateSumRow(int startIndex,List<ResearchTableRow> rowlist,int size);
	/**
	 * 保存调研表
	 * @param table
	 * @return
	 */
	public int saveTable(int tableid,ResearchTable table,int type);
	/**
	 * 保存调研记录(两系统共用)
	 * @return
	 */
	public String saveInfo(int tableid,String bgms,int type);
	/**
	 * 自助调研历史(两系统共用)
	 * @param ksrq
	 * @param jsrq
	 * @return
	 */
	public List<Object[]> savedTables(String name,String fydm,String ksrq,String jsrq,int type);
	/**
	 * 删除自助调研历史(两系统共用)
	 * @param tableid
	 * @return
	 */
	public String deleteSavedTable(int tableid);
	/**
	 * 查看保存的自主调研表
	 * @param tableid
	 * @return
	 */
	public ResearchTableVO findSavedTable(int tableid);
	/**
	 * 查询是否有这个表
	 * @param dytj
	 * @return
	 */
	public ResearchTableDO findTable(String dytj);
	/**
	 * 自助调研查询
	 * @return
	 */
	public List<Object[]> search(SearchConditionVO condition);

	/**
	 * 自助调研查询(带type 历史查询查询)
	 * @return
	 */
	public List<Object[]> search(SearchConditionVO condition,int type);


	/**
	 * 检查数据库连接状态
	 */
	public boolean databaseIsConnected();
	/**
	 * 是否有值
	 * @param list
	 * @return
	 */
	public boolean hasValue(List<ResearchTableCell> list);
	/**
	 * 得到和类别,有关的信息项
	 * 刑事(里面有很多项目),民事(里面有很多项)等等,键(民事):值(民事一审,民事二审等)
	 */
	public Map<String, List<ResearchXXBModel>> getXxbbMap();
	/**
	 * 拆分表格（报表生成系统）
	 */
	public ResearchTable[] splitTable(String[] yjls,ResearchTable table) throws CloneNotSupportedException;
	/**
	 * 拆分表格（报表生成系统）
	 */
	public ResearchTable unionTable(ResearchTable table,ResearchTable[] tables);
	/**
	 * 我的模板（报表生成系统）
	 * @return
	 */
	public List<BbscTemplateDO> myTemplateList(String name,String fydm);
	/**
	 * 所有模板（报表生成系统）
	 * @return
	 */
	public List<BbscTemplateDO> allTemplateList();
	/**
	 * 通过模板得到条件（报表生成系统）
	 * @return
	 */
	public BbscConditionVO getConditionByTemplate(int id);
	/**
	 * 保存模板（报表生成系统）
	 * @return
	 */
	public String saveTemplate(BbscConditionVO condition,String name);
	/**
	 * 修改模板（报表生成系统）
	 * @return
	 */
	public String modifyTemplate(int id,String name);
	/**
	 * 删除模板（报表生成系统）
	 * @return
	 */
	public String deleteTemplate(int id);
}
