package software.dygzt.service.research.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.dygzt.data.ay.dao.AydmbDao;
import software.dygzt.data.ay.dataobject.JzrhAydmbDO;
import software.dygzt.data.research.dao.SqlResultDao;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.ResearchVariableService;
import software.dygzt.service.research.model.*;

import java.util.ArrayList;
import java.util.List;

@Service("researchayjzrh")
public class ResearchAyJzrhImpl implements ResearchVariableService {
	@Autowired
	private ResearchService researchBaseImpl;
	@Autowired
	private SqlResultDao sqlResultDao;
	@Autowired
	private AydmbDao aydmbDao;

	private int mount = 0;//统计
    private String printString = "";
	
	private static final Logger log = Logger.getLogger(ResearchAyJzrhImpl.class);
	
	public ResearchTable createTable(ResearchConditionVO condition) throws Exception {
		ResearchQueryConditionJzrh base = new ResearchQueryConditionJzrh();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,"Ayjzrh");

		table.setRowlevel(1);
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
		result.setLaay(true);
		result.setFybh(true);
		
		//basecase
		List<ResearchAj> baseCase = new ArrayList<ResearchAj>();
				
		// 遍历每家法院
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
		List<JzrhAydmbDO> ayList = aydmbDao.getJzrhAyDmListByCondition(table.getRowvarlist());
		for(JzrhAydmbDO ay:ayList){
			List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
			for(ResearchCellCondition con:queryCondition){
				//baseCase要保留，以便下次案由筛选时还是原来的
				List<ResearchAj> oldCase = con.getCases();
				con.setLaay(ay.getAydmbh());
				researchBaseImpl.generateCell(con,cellList,DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),1,table.getCondition());
				con.setCases(oldCase);
			}
			if(researchBaseImpl.hasValue(cellList)){
				//建row
				i++;
				ResearchTableRow row = new ResearchTableRow();
				ResearchTableColumn rowAttr = new ResearchTableColumn(i,ay.getAymc().trim()+"("+ay.getBbh()+"版)", 1);
				row.setRowInfo(rowAttr);
				row.setValue(cellList);
				table.getRowList().add(row);
			}
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
	
	public ResearchTable createTable(BbscConditionVO condition) throws Exception {
		ResearchQueryConditionJzrh base = new ResearchQueryConditionJzrh();
		ResearchTable table = researchBaseImpl.generateQueryConditionCol(condition,base,"Ayjzrh");
		// 遍历每家法院
		List<FYDataSourceEnum> dataSources = FYDataSourceEnum.getFyDataSourceList(table.getFyfw());
		for (FYDataSourceEnum dataSource : dataSources) {
			base.addFybh(dataSource.getFybh()); //增加一个法院选择，而不是set

/*遍历25家法院，统计需要的数据*/
//            DataSourceRouter.routerTo(dataSource.getFydm());//跳转到指定数据库
//            /*各家法院指定数据的统计*/
//            String sqlNum = "SELECT COUNT(*) AS 'count' FROM FTNQ_FTSYSQDJ WHERE ( DATEDIFF(DD,'2016-10-01',FTNQ_FTSYSQDJ.RQ) >= 0 and DATEDIFF(DD,'2016-10-31',FTNQ_FTSYSQDJ.RQ) <= 0)";
//            int temp = sqlResultDao.getSqlResult2(sqlNum);
//            printString = printString + dataSource.getFymc() + temp + "\n";
//            mount += temp;
//            System.out.print("该法院的条目数量是：" + temp);
//            System.out.print("现在总该条目数量是：" + mount);
//                /*统计结束*/


		}
//        System.out.print("输出:" + printString);
//        System.out.print("一共:" + mount);
//        printString = "";
//		mount = 0;
		
		//设置选取的列
		ResearchQueryResultJzrh result = new ResearchQueryResultJzrh();
		result.setAjxh(true);
		result.setLarq(true);
		result.setJarq(true);
		result.setAjxz(true);
		result.setSpcx(true);
		result.setSpcxdz(true);
		result.setLaay(true);
		result.setFybh(true);
		
		table.setRowlevel(1);
		
		//拆分为多个表，根据一级列的数目来拆分表格
		String[] yjls = condition.getYjCondition().split("\\+");
		ResearchTable[] tables = researchBaseImpl.splitTable(yjls,table);

		//查询获得basecase，baseCase 保存的是符合一定条件的所有 ResearchAj 集合，供后面使用
		List<ResearchAj>[] baseCase = new List[yjls.length];
		List<ResearchAj>[] baseCaseSamePeriodLastYear = new List[yjls.length]; //保存的是去年同期的数字

		String curDb = CustomerContextHolder.getCustomerType();		
		DataSourceRouter.routerToJzrh();

		/*
		* 计算之一级列筛选
		* 根据 table 的个数，初始化每个 table 对应的 baseCase，(baseCase 保存的是符合一级列的所有案件集合)
		* 表格生成的数据查询操作最费时的就在这，因为后面的操作都是基于这里的查询结果。
		* */
		try{
			for(int i=0;i<tables.length;i++){
				ResearchTable tmpTable = tables[i];
				baseCase[i] = sqlResultDao.getSqlResult(tmpTable.getBaseCondition().getSql(result),result,"-1");

			}
		}catch(Exception e){
			log.error("集中融合库获取失败");
			table.addSbfy("jzrh");
		}

		/*
		* 计算之二级列筛选，一直在设置 tables 数组的值，这个变量在该方法中都有效，筛选出来的是符合一级列所对应的二级列的所有案件；
		* 方便之后再用行条件对初步筛选的案件 list 做准确地筛选。
		* */
		for(int i=0;i<tables.length;i++){
			ResearchTable tmpTable = tables[i];
			tmpTable.setRowlevel(1);
			List<ResearchCellCondition> queryCondition = tmpTable.getQueryList(); //设置 queryCondition 的部分属性值，cases，该 table 中符合当前一级和二级列条件的所有案件数，即 sum 值。
			//初步筛选，queryCondition 其实是行中的一个列所对应的 cell 的查询条件，如果有多个列，则一行就会对应多个cell，queryCondition 就有多个
			for(ResearchCellCondition con:queryCondition){
				if(!con.isIssum()){
					con.setCases(baseCase[i]);
					con.filter(); //根据二级列进行筛选
					con.getCases().size();
				}
			}
		}
		
		/*
		* 计算之行筛选
		* 分表计算: 为什么要做分表计算，因为每个统计列的一级列是不同的查询条件(大类查询条件)，要分别处理。
		* 行条件是案由，根据需要的案由，先根据查询条件查询出所有的案由，
		* */
		int i = 0;	//记录行数
		List<JzrhAydmbDO> ayList = aydmbDao.getJzrhAyDmListByCondition(table.getRowvarlist());
		/*遍历 ayList 计算每个案由，案由/行所对应的数据，案由的数量决定了最终表格具有多少行*/
		for(JzrhAydmbDO ay:ayList){
			i++;
			//每个table都算一遍
			/*现在每个案由所对应的行有多个table(按统计列一来区分tables)，所以为每个 table 都设置当前案由的 cell 值*/
			for(int j=0;j<tables.length;j++){
				ResearchTable tmpTable = tables[j];
				List<ResearchCellCondition> queryCondition = tmpTable.getQueryList();
				//每个列算一遍
				List<ResearchTableCell> cellList = new ArrayList<ResearchTableCell>();
				for(ResearchCellCondition con:queryCondition){
					//baseCase要保留，以便下次案由筛选时还是原来的
					List<ResearchAj> oldCase = con.getCases();
					con.setLaay(ay.getAydmbh());
					//初始化每个具体的 cell,存入cellList，一次 for 循环
					researchBaseImpl.generateCell(con,cellList,DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),1,tmpTable.getCondition());
					con.setCases(oldCase);
				}
				//无条件建row，不管有没有值
				ResearchTableRow row = new ResearchTableRow();
				ResearchTableColumn rowAttr = new ResearchTableColumn(i,ay.getAymc().trim()+"("+ay.getBbh()+"版)", 1);
				row.setRowInfo(rowAttr);
				//把每一行的 cellList 传入到 ResearchTableRow row 中，在把 row 添加到 table 的 List<ResearchTableRow> rowList 中。
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
			researchBaseImpl.generateCell(con,row.getValue(),DataSourcePrefixEnum.JZRH.getPrefix()+table.getFyfw(),1,null);
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
