package software.dygzt.service.research.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.dygzt.data.research.dao.SqlResultDao;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.bm.BmbService;
import software.dygzt.service.bm.model.BmModel;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.ResearchVariableService;
import software.dygzt.service.research.model.BbscConditionVO;
import software.dygzt.service.research.model.DataSourcePrefixEnum;
import software.dygzt.service.research.model.ResearchAj;
import software.dygzt.service.research.model.ResearchCellCondition;
import software.dygzt.service.research.model.ResearchQueryConditionJzrh;
import software.dygzt.service.research.model.ResearchQueryResultJzrh;
import software.dygzt.service.research.model.ResearchTableCell;
import software.dygzt.service.research.model.ResearchConditionVO;
import software.dygzt.service.research.model.ResearchTable;
import software.dygzt.service.research.model.ResearchTableColumn;
import software.dygzt.service.research.model.ResearchTableRow;

@Service("researchbmjzrh")
public class ResearchBmJzrhImpl implements ResearchVariableService {
	@Autowired
	private ResearchService researchBaseImpl;
	@Autowired
	private SqlResultDao sqlResultDao;
	@Autowired
	private BmbService bmbServiceImpl;
	
	private static final Logger log = Logger.getLogger(ResearchBmJzrhImpl.class);
	
	public ResearchTable createTable(ResearchConditionVO condition) throws Exception {
		ResearchQueryConditionJzrh base = new ResearchQueryConditionJzrh();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,null);
		
		table.setRowlevel(1);	//因为部门，固定行层数是1
		String curDb = CustomerContextHolder.getCustomerType();
		List<ResearchCellCondition> queryCondition = table.getQueryList();
		
		//设置选取的列
		ResearchQueryResultJzrh result = new ResearchQueryResultJzrh();
		result.setAjxh(true);
		result.setLarq(true);
		result.setJarq(true);
		result.setAjxz(true);
		result.setSpcx(true);
		result.setSpcxdz(true);
		result.setSpt(true);
		result.setFybh(true);
		
		//basecase
		List<ResearchAj> baseCase = new ArrayList<ResearchAj>();
		
		// 得到每家法院编号
		List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(table.getFyfw());
		for (FYDataSourceEnum dataSource : dataSources) {
			base.addFybh(dataSource.getFybh()); //增加一个法院选择，而不是set
		}
		DataSourceRouter.routerToJzrh();
		try{
			baseCase = sqlResultDao.getSqlResult(table.getBaseCondition().getSql(result),result,"-1");
		}catch(Exception e){
			log.error("集中融合库获取失败");
			table.addSbfy("jzrh");
		}

		//初步筛选
		for(ResearchCellCondition con:queryCondition){
			if(!con.isIssum()){
				con.setCases(baseCase);
				con.filter();
			}
		}
		int i = 0;	//记录行数
		FYDataSourceEnum fyEnum= FYDataSourceEnum.getDataSourceByFydm(table.getFyfw());
		Map<String, BmModel> bmlist = bmbServiceImpl.getBmList(fyEnum.getFybh());
		for(BmModel bm:bmlist.values()){
			i++;
			//建row
			ResearchTableRow row = new ResearchTableRow();
			ResearchTableColumn rowAttr = new ResearchTableColumn(i,bm.getBmmc(), 1);
			row.setRowInfo(rowAttr);
			List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
			for(ResearchCellCondition con:queryCondition){
				//baseCase要保留，以便下次案由筛选时还是原来的
				List<ResearchAj> oldCase = con.getCases();
				con.setBaspt(bm.getBmbh());
				researchBaseImpl.generateCell(con,cellList,DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),2,table.getCondition());
				con.setCases(oldCase);
			}
			row.setValue(cellList);
			table.getRowList().add(row);
		}
		
		
		//总计
		ResearchTableRow row = new ResearchTableRow();
		ResearchTableColumn rowAttr = new ResearchTableColumn(table.getRowList().size()+1,"合计", 1);
		row.setRowInfo(rowAttr);
		List<ResearchTableCell> value = researchBaseImpl.generateSumRow(0,table.getRowList(),queryCondition.size());
		row.setValue(value);
		table.getRowList().add(row);
		
		//赋值调研条件
		table.setDytj(condition.toString());
		//切换数据库
		DataSourceRouter.routerTo(curDb);
		return table;
	}

	public ResearchTable createTable(BbscConditionVO condition)
			throws Exception {
		ResearchQueryConditionJzrh base = new ResearchQueryConditionJzrh();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,null);
		// 遍历每家法院
		List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(table.getFyfw());
		for (FYDataSourceEnum dataSource : dataSources) {
			base.addFybh(dataSource.getFybh()); //增加一个法院选择，而不是set
		}
		
		// 设置选取的列
		ResearchQueryResultJzrh result = new ResearchQueryResultJzrh();
		result.setAjxh(true);
		result.setLarq(true);
		result.setJarq(true);
		result.setAjxz(true);
		result.setSpcx(true);
		result.setSpcxdz(true);
		result.setSpt(true);
		result.setFybh(true);
		
		table.setRowlevel(1);
		
		//拆分为多个表
		String[] yjls = condition.getYjCondition().split("\\+");
		ResearchTable[] tables = researchBaseImpl.splitTable(yjls,table);

		//查询获得basecase
		List<ResearchAj>[] baseCase = new List[yjls.length];
		String curDb = CustomerContextHolder.getCustomerType();		
		DataSourceRouter.routerToJzrh();
		try{
			for(int i=0;i<tables.length;i++){
				ResearchTable tmpTable = tables[i];
				baseCase[i] = sqlResultDao.getSqlResult(tmpTable.getBaseCondition().getSql(result),result,"-1");
			}
		}catch(Exception e){
			log.error("集中融合库获取失败");
			table.addSbfy("jzrh");
		}
		
		//计算之初步筛选
		for(int i=0;i<tables.length;i++){
			ResearchTable tmpTable = tables[i];
			tmpTable.setRowlevel(1);
			List<ResearchCellCondition> queryCondition = tmpTable.getQueryList();
			//初步筛选
			for(ResearchCellCondition con:queryCondition){
				if(!con.isIssum()){
					con.setCases(baseCase[i]);
					con.filter();
				}
			}
		}
		
		//分表计算
		int i = 0;	//记录行数
		FYDataSourceEnum fyEnum= FYDataSourceEnum.getDataSourceByFydm(table.getFyfw());
		Map<String, BmModel> bmlist = bmbServiceImpl.getBmList(fyEnum.getFybh());
		for(BmModel bm:bmlist.values()){
			i++;
			//每个table都算一遍
			for(int j=0;j<tables.length;j++){
				ResearchTable tmpTable = tables[j];
				List<ResearchCellCondition> queryCondition = tmpTable.getQueryList();
				//建row
				ResearchTableRow row = new ResearchTableRow();
				ResearchTableColumn rowAttr = new ResearchTableColumn(i,bm.getBmmc(), 1);
				row.setRowInfo(rowAttr);
				List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
				for(ResearchCellCondition con:queryCondition){
					//baseCase要保留，以便下次案由筛选时还是原来的
					List<ResearchAj> oldCase = con.getCases();
					con.setBaspt(bm.getBmbh());
					researchBaseImpl.generateCell(con,cellList,DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),2,tmpTable.getCondition());
					con.setCases(oldCase);
				}
				row.setValue(cellList);
				tmpTable.getRowList().add(row);
			}
		}
		//合并表
		researchBaseImpl.unionTable(table,tables);

		//列加总计
		ResearchCellCondition con = new ResearchCellCondition();
		con.setIssum(true);
		con.setStartIndex(0);
		table.getQueryList().add(con);	//querylist增加合计
		ResearchTableColumn sumCol = new ResearchTableColumn(table.getColList().size()+1,"合计",1);
		table.getColList().add(sumCol);		//colList增加合计
		for(ResearchTableRow row:table.getRowList()){
			researchBaseImpl.generateCell(con,row.getValue(),DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),2,null);
		}
						
		//行加总计
		ResearchTableRow row = new ResearchTableRow();
		ResearchTableColumn rowAttr = new ResearchTableColumn(table.getRowList().size()+1,"合计", 1);
		row.setRowInfo(rowAttr);
		List<ResearchTableCell> value = researchBaseImpl.generateSumRow(0,table.getRowList(),table.getColList().size());
		row.setValue(value);
		table.getRowList().add(row);
				
		//赋值表格名称
		table.setBbmc("案件信息统计表");
		//赋值调研条件
		table.setDytj(condition.toString());
		//切换数据库
		DataSourceRouter.routerTo(curDb);
		return table;
	}
}
