package software.dygzt.service.research.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import software.dygzt.util.QueryUtil;
import software.dygzt.util.StringUtil;

/**
 * 调研条件VO
 */
public class ResearchQueryConditionSepa extends ResearchQueryCondition{
	private static Logger logger = Logger.getLogger(ResearchQueryConditionSepa.class);
	//案件状态 
	@Query(table="PUB_AJ_JB",sql="? and ")
	public String ajzt;
	//案件性质str，供table的condition使用，如果table没有condition，取列条件的最大集
	@Query(table="PUB_AJ_JB", sql="(PUB_AJ_JB.AJXZ in (?)) and ")
	public String ajxzStr;
	
	//案件性质，供列条件取最大集
	private Set<String> ajxz;
	
	//开始时间
	private String kssj;
	//结束时间
	private String jssj;
	//案件状态
	private String ajztstr;
	
	//生成的sql语句
	private String sql;
	
	public ResearchQueryConditionSepa() {
		ajxz = new HashSet<String>();
	}

	public String getAjzt() {
		return ajzt;
	}

	public void setAjzt(String ajzt) {
		if("新收".equals(ajzt)){	//相对于一段时间的案件状态
			this.ajzt="(DATEDIFF(DD,'"+kssj+"',PUB_AJ_JB.LARQ) >= 0 and DATEDIFF(DD,'"+jssj+"',PUB_AJ_JB.LARQ) <= 0)";
		}else if("未结".equals(ajzt)){
			this.ajzt="(DATEDIFF(DD,'"+jssj+"',PUB_AJ_JB.LARQ) <= 0 and (DATEDIFF(DD,'"+jssj+"',PUB_AJ_JB.JARQ) >= 0 or PUB_AJ_JB.JARQ = NULL))";
		}else if("旧存".equals(ajzt)){
			this.ajzt="(DATEDIFF(DD,'"+kssj+"',PUB_AJ_JB.LARQ) <= 0 and (DATEDIFF(DD,'"+kssj+"',PUB_AJ_JB.JARQ) >= 0 or PUB_AJ_JB.JARQ = NULL))";
		}else if("已结".equals(ajzt)){
			this.ajzt="(DATEDIFF(DD,'"+kssj+"',PUB_AJ_JB.JARQ) >= 0 and DATEDIFF(DD,'"+jssj+"',PUB_AJ_JB.JARQ) <= 0)";
		}else if(StringUtil.isNotBlank(ajzt)){
			this.ajzt=ajzt;
		}
		this.ajztstr=ajzt;
	}
	
	public String getAjztstr() {
		return ajztstr;
	}

	public void setAjztstr(String ajztstr) {
		this.ajztstr = ajztstr;
	}

	public String getAjxzStr() {
		return ajxzStr;
	}

	public void setAjxzStr(String ajxzStr) {
		if(StringUtil.isNotBlank(ajxzStr)){
			if(ajxzStr.startsWith("'")){
				this.ajxzStr = ajxzStr;
			}else{
				this.ajxzStr = "'"+ajxzStr+"'";
			}
			
		}
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	
	public Set<String> getAjxz() {
		return ajxz;
	}

	public void setAjxz(String ajxz) {
		this.ajxz.add(ajxz);
	}
	
	public String toString(){
		if(StringUtil.isBlank(ajxzStr)&&ajxz.size()>0){
			ajxzStr = stringSetToString(ajxz);
		}
		String result="";
		if(StringUtil.isNotBlank(ajzt)){
			result="ajzt:"+ajzt+";";
		}
		if(StringUtil.isNotBlank(ajxzStr)){
			result += "ajxzStr:"+ajxzStr+";";
		}
		if (result.endsWith(";")) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	public String getSql(ResearchQueryResult result) {
		if(sql==null){
			if(StringUtil.isBlank(ajxzStr)&&ajxz.size()>0){
				ajxzStr = stringSetToString(ajxz);
			}
			try {
				sql=QueryUtil.getResearchQuerySql(this,result);
			} catch (IllegalArgumentException e) {
				logger.error(e);
			} catch (IllegalAccessException e) {
				logger.error(e);
			}
		}
		return sql;
	}
}
