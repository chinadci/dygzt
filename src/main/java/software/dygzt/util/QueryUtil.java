package software.dygzt.util;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import software.dygzt.service.research.model.Query;
import software.dygzt.service.research.model.ResearchQueryConditionJzrh;
import software.dygzt.service.research.model.ResearchQueryConditionSepa;
import software.dygzt.service.research.model.ResearchQueryResult;
import software.dygzt.service.research.model.Result;

/**
 * 案件查询工具类
 * 
 * @author Admin
 *
 */
public class QueryUtil {
	/**
	 * 审判库sql
	 * @param model
	 * @param selectModel
	 * @param flag (flag=1:默认pub_aj_jb ;flag=2:默认pub_aj_jb+pub_la_ay)
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getResearchQuerySql(ResearchQueryConditionSepa model, ResearchQueryResult result) throws IllegalArgumentException, IllegalAccessException {
		Set<String> tables = new HashSet<String>();	//保存查询语句中涉及的表
		
		/*
		 * 构造查询语句中where子句部分
		 */
		Class clazz = model.getClass();
		Field[] fields = clazz.getFields();
		String whereCondition = "";
		for(Field field : fields) {
			Object value = field.get(model);
			if (value != null && !"".equals(value)) {
				Query annotation = field.getAnnotation(Query.class);
				String table = annotation.table();
				String sql = annotation.sql();
				sql = sql.replaceAll("\\?", (String)value);
				tables.add(table);
				whereCondition += sql;
			}
		}
		if (whereCondition.endsWith("and ")) {
			whereCondition = whereCondition.substring(0, whereCondition.length()-4);
		}
		
		/*
		 * 构造查询语句中select子句
		 */
		Class resultClazz = result.getClass();
		Field[] resultFields = resultClazz.getFields();
		String selectCondtion = "select distinct ";
		for(Field field : resultFields) {
			Object value = field.get(result);
			if (value != null && !"".equals(value)&&field.getType().getName().equals("java.lang.Boolean")) {
				if ((Boolean)value == true) {
					Result annotation = field.getAnnotation(Result.class);
					String table = annotation.table();
					tables.add(table);
					String column = annotation.column();
					String fieldName = annotation.field();
					column = column.replaceAll("\\?", table+".");
					selectCondtion += column + " as " + fieldName +",";
				}
			}
		}
		if (selectCondtion.endsWith(",")) {
			selectCondtion = selectCondtion.substring(0, selectCondtion.length()-1);
		}
		
		/*
		 * 构造查询语句中的from子句
		 */
		String fromCondition = "PUB_AJ_JB ";
		if (tables.size() > 1) {
			Object[] tablesArr = (Object[])tables.toArray();
			for (int i = 0 ; i < tablesArr.length  ; i++) {
				if(tablesArr[i].equals("PUB_AJ_JB")){
					continue;
				}else{
					fromCondition+= "left join " + tablesArr[i] + " on PUB_AJ_JB.AJXH = "+tablesArr[i]+".AJXH ";
				}
			}
		}
		
		String sql = selectCondtion + " from " + fromCondition + " where " + whereCondition;
		return sql;
	}
	
	/**
	 * 集中融合库sql
	 * @param model
	 * @param result
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static String getResearchQuerySql(ResearchQueryConditionJzrh model, ResearchQueryResult result) throws IllegalArgumentException, IllegalAccessException {
		Set<String> tables = new HashSet<String>();	//保存查询语句中涉及的表
		
		/*
		 * 构造查询语句中where子句部分
		 */
		Class clazz = model.getClass();
		Field[] fields = clazz.getFields();
		String whereCondition = "";
		for(Field field : fields) {
			Object value = field.get(model);
			if (value != null && !"".equals(value)) {
				Query annotation = field.getAnnotation(Query.class);
				String table = annotation.table();
				String sql = annotation.sql();
				sql = sql.replaceAll("\\?", (String)value);
				tables.add(table);
				whereCondition += sql;
			}
		}
		if (whereCondition.endsWith("and ")) {
			whereCondition = whereCondition.substring(0, whereCondition.length()-4);
		}
		
		/*
		 * 构造查询语句中select子句
		 */
		Class resultClazz = result.getClass();
		Field[] resultFields = resultClazz.getFields();
		String selectCondtion = "select distinct ";
		for(Field field : resultFields) {
			Object value = field.get(result);
			if (value != null && !"".equals(value)&&field.getType().getName().equals("java.lang.Boolean")) {
				if ((Boolean)value == true) {
					Result annotation = field.getAnnotation(Result.class);
					String table = annotation.table();
					tables.add(table);
					String column = annotation.column();
					String fieldName = annotation.field();
					column = column.replaceAll("\\?", table+".");
					selectCondtion += column + " as " + fieldName +",";
				}
			}
		}
		if (selectCondtion.endsWith(",")) {
			selectCondtion = selectCondtion.substring(0, selectCondtion.length()-1);
		}
		
		/*
		 * 构造查询语句中的from子句
		 */
		String fromCondition = "BZ_AJ_AJXXB ";
		if (tables.size() > 1) {
			Object[] tablesArr = (Object[])tables.toArray();
			for (int i = 0 ; i < tablesArr.length  ; i++) {
				if(tablesArr[i].equals("BZ_AJ_AJXXB")){
					continue;
				}else{
					fromCondition+= "left join " + tablesArr[i] + " on BZ_AJ_AJXXB.AJBS = "+tablesArr[i]+".AJBS ";
				}
			}
		}
		
		String sql = selectCondtion + " from " + fromCondition + " where " + whereCondition;
		return sql;
	}
	
}
