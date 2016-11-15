package software.dygzt.service.research.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.dygzt.data.research.dao.SqlResultDao;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.ResearchVariableService;
import software.dygzt.service.research.model.*;

import java.util.ArrayList;
import java.util.List;

@Service("researchfy")
public class ResearchFyImpl implements ResearchVariableService {
	@Autowired
	private ResearchService researchBaseImpl;
	@Autowired
	private SqlResultDao sqlResultDao;
	private static final Logger log = Logger.getLogger(ResearchFyImpl.class);
	
	public ResearchTable createTable(ResearchConditionVO condition) throws Exception {
		ResearchQueryConditionSepa base = new ResearchQueryConditionSepa();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,null);
		
		int i = 0;	//记录行数
		table.setRowlevel(1);	//因为法院，固定行层数是1
		String curDb = CustomerContextHolder.getCustomerType();
		List<ResearchCellCondition> queryCondition = table.getQueryList();
		
		//设置选取的列
		ResearchQueryResultSepa result = new ResearchQueryResultSepa();
		result.setAjxh(true);
		result.setLarq(true);
		result.setJarq(true);
		result.setAjxz(true);
		result.setSpcx(true);
		result.setSpcxdz(true);
		
		List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(table.getFyfw());
		// 遍历每家法院
		for (FYDataSourceEnum dataSource : dataSources) {
			DataSourceRouter.routerTo(dataSource.getFydm());// 各家法院库
			log.info("连接"+dataSource.getFymc());
			List<ResearchAj> baseCase;
			try{
				baseCase = sqlResultDao.getSqlResult(table.getBaseCondition().getSql(result),result,dataSource.getFydm());
			}catch(Exception e){
				log.error(dataSource.getFymc()+"连接失败");
				table.addSbfy(dataSource.getFydm());
				continue;
			}
			i++;
			//建row
			ResearchTableRow row = new ResearchTableRow();
			ResearchTableColumn rowAttr = new ResearchTableColumn(i,dataSource.getFymc(), 1);
			row.setRowInfo(rowAttr);
			List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
			for(ResearchCellCondition con:queryCondition){
				con.setCases(baseCase);
				researchBaseImpl.generateCell(con,cellList,dataSource.getFydm(),0,table.getCondition());
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
		ResearchQueryConditionSepa base = new ResearchQueryConditionSepa();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,null);
		
		// 设置选取的列
		ResearchQueryResultSepa result = new ResearchQueryResultSepa();
		result.setAjxh(true);
		result.setLarq(true);
		result.setJarq(true);
		result.setAjxz(true);
		result.setSpcx(true);
		result.setSpcxdz(true);
		
		table.setRowlevel(1);	//因为法院，固定行层数是1
		
		// 拆分为多个表
		String[] yjls = condition.getYjCondition().split("\\+");
		ResearchTable[] tables = researchBaseImpl.splitTable(yjls, table);
		
		int i = 0;	//记录行数
		String curDb = CustomerContextHolder.getCustomerType();
		List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(table.getFyfw());
		// 遍历每家法院
		for (FYDataSourceEnum dataSource : dataSources) {
			DataSourceRouter.routerTo(dataSource.getFydm());// 切换各家法院库
			log.info("连接"+dataSource.getFymc());
			i++;
			for(int j=0;j<tables.length;j++){
				ResearchTable tmpTable = tables[j];
				List<ResearchAj> baseCase;
				try{
					baseCase = sqlResultDao.getSqlResult(tmpTable.getBaseCondition().getSql(result),result,dataSource.getFydm());
				}catch(Exception e){
					log.error(dataSource.getFymc()+"连接失败");
					table.addSbfy(dataSource.getFydm());
					continue;
				}
				//建row
				ResearchTableRow row = new ResearchTableRow();
				ResearchTableColumn rowAttr = new ResearchTableColumn(i,dataSource.getFymc(), 1);
				row.setRowInfo(rowAttr);
				List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
				for(ResearchCellCondition con:tmpTable.getQueryList()){
					con.setCases(baseCase);
					researchBaseImpl.generateCell(con,cellList,dataSource.getFydm(),0,tmpTable.getCondition());
				}
				row.setValue(cellList);
				tmpTable.getRowList().add(row);
			}
		}
		
		// 合并表
		researchBaseImpl.unionTable(table, tables);

		// 列加总计
		ResearchCellCondition con = new ResearchCellCondition();
		con.setIssum(true);
		con.setStartIndex(0);
		table.getQueryList().add(con); // querylist增加合计
		ResearchTableColumn sumCol = new ResearchTableColumn(table.getColList()
				.size() + 1, "合计", 1);
		table.getColList().add(sumCol); // colList增加合计
		for (ResearchTableRow row : table.getRowList()) {
			researchBaseImpl.generateCell(con, row.getValue(),table.getFyfw(), 0,null);
		}

		// 行加总计
		ResearchTableRow row = new ResearchTableRow();
		ResearchTableColumn rowAttr = new ResearchTableColumn(table.getRowList().size() + 1, "合计", 1);
		row.setRowInfo(rowAttr);
		List<ResearchTableCell> value = researchBaseImpl.generateSumRow(0,table.getRowList(), table.getColList().size());
		row.setValue(value);
		table.getRowList().add(row);

		// 赋值表格名称
		table.setBbmc("案件信息统计表");
		// 赋值调研条件
		table.setDytj(condition.toString());
		// 切换数据库
		DataSourceRouter.routerTo(curDb);
		return table;
	}
}
