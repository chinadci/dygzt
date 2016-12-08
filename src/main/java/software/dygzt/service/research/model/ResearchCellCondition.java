package software.dygzt.service.research.model;

import org.apache.log4j.Logger;
import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 调研条件VO
 */
public class ResearchCellCondition implements Cloneable{
	private static Logger logger = Logger.getLogger(ResearchCellCondition.class);
	//案件性质，比如: 新收，旧存，已结，未结
	private String ajxz;
	//审判程序，比如: 民事一审等(二级列)
	private String spcx;
	//审判程序类型
	private String spcxlx;
	//审判程序类型特别，由于新旧不同或者组合原因
	private String spcxlxtb;
	//立案案由，行条件
	private String laay;
	//办案审判庭
	private String baspt;
	
	//是否是总计
	private boolean issum;
	//总计开始的index
	private int startIndex;
	
	//显示的结果，1：案件数目
	private String col;
	//案件列表
	private List<ResearchAj> cases;
	
	public String getAjxz() {
		return ajxz;
	}
	public void setAjxz(String ajxz) throws Exception {
		if(StringUtil.isNotBlank(ajxz)){
			if(this.ajxz==null){
				this.ajxz = ajxz;
			}else if(this.ajxz!=null&&(!this.ajxz.equals(ajxz))){
				throw new Exception("与上级调研条件冲突");
			}
		}
	}
	public void fil_ajxz(){
		if(ajxz!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			for(ResearchAj aj:cases){
				if(ajxz.equals(aj.getAjxz())){
					newcases.add(aj);
				}
			}
			cases = newcases;
		}
		
	}
	
	public String getSpcx() {
		return spcx;
	}
	public void setSpcx(String spcx) throws Exception {
		if(StringUtil.isNotBlank(spcx)){
			if(this.spcx==null){
				this.spcx = spcx;
			}else if(this.spcx!=null&&(!this.spcx.equals(spcx))){
				throw new Exception("与上级调研条件冲突");
			}
		}
	}
	public void fil_spcx(){
		if(spcx!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			for(ResearchAj aj:cases){
				if(spcx.equals(aj.getSpcx())){
					newcases.add(aj);
				}
			}
			cases = newcases;
		}
	}
	
	public String getSpcxlx() {
		return spcxlx;
	}
	public void setSpcxlx(String spcxlx) throws Exception {
		if(StringUtil.isNotBlank(spcxlx)){
			if(this.spcxlx==null){
				this.spcxlx = spcxlx;
			}else if(this.spcxlx!=null&&(!this.spcxlx.equals(spcxlx))){
				throw new Exception("与上级调研条件冲突");
			}
		}
	}
	public void fil_spcxlx(){
		if(spcxlx!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			for(ResearchAj aj:cases){
				if(spcxlx.equals(aj.getSpcxlx())){
					newcases.add(aj);
				}
			}
			cases = newcases;
		}
	}
	
	public String getSpcxlxtb() {
		return spcxlxtb;
	}
	public void setSpcxlxtb(String spcxlxxj) throws Exception {
		if(StringUtil.isNotBlank(spcxlxxj)){
			if(this.spcxlxtb==null){
				this.spcxlxtb = spcxlxxj;
			}else if(this.spcxlxtb!=null&&(!this.spcxlxtb.equals(spcxlxxj))){
				throw new Exception("与上级调研条件冲突");
			}
		}
	}
	public void fil_spcxlxtb(){
		if(spcxlxtb!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			Date boundary = new Date(2016,1,1);
			for(ResearchAj aj:cases){
				if(spcxlxtb.equals("再审")){//行政再审，刑事再审，民事再审
					if(DateUtil.compareDate(boundary, aj.getLarq())<=0){	//16年之后
						if("3".equals(aj.getSpcx())&&"4".equals(aj.getSpcxlx())){
							newcases.add(aj);
						}
					}else{	//16年之前
						if("3".equals(aj.getSpcx())){
							newcases.add(aj);
						}
					}
				}else if(spcxlxtb.equals("再审审查")){//行政，刑事，民事
					if(DateUtil.compareDate(boundary, aj.getLarq())<=0){	//16年之后
						if("3".equals(aj.getSpcx())&&("1".equals(aj.getSpcxlx())||"2".equals(aj.getSpcxlx())||"3".equals(aj.getSpcxlx()))){
							newcases.add(aj);
						}
					}else{	//16年之前
						if("A".equals(aj.getSpcx())){
							newcases.add(aj);
						}
					}
				}else if(spcxlxtb.equals("行政赔偿再审审查")){
					if("B".equals(aj.getSpcx())&&("3".equals(aj.getSpcxlx())||"4".equals(aj.getSpcxlx())||"5".equals(aj.getSpcxlx()))){
						newcases.add(aj);
					}
				}else if(spcxlxtb.equals("刑罚变更")){//刑事
					if(DateUtil.compareDate(boundary, aj.getLarq())<=0){	//16年之后
						if("E".equals(aj.getSpcx())){
							newcases.add(aj);
						}
					}else{	//16年之前
						if("5".equals(aj.getSpcx())||"6".equals(aj.getSpcx())||"7".equals(aj.getSpcx())||"8".equals(aj.getSpcx())||"9".equals(aj.getSpcx())){
							newcases.add(aj);
						}
					}
				}
			}
			cases = newcases;
		}
	}
	
	public String getLaay() {
		return laay;
	}
	public void setLaay(String laay) {
		//行变量不必做冲突处理
		this.laay = laay;
	}
	public void fil_laay(){
		if(laay!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			for(ResearchAj aj:cases){
				if(laay.equals(aj.getLaay())){
					newcases.add(aj);
				}
			}
			cases = newcases;
		}
	}
	public String getBaspt() {
		return baspt;
	}
	public void setBaspt(String baspt) {
		//行变量不必做冲突处理
		this.baspt = baspt;
	}
	public void fil_baspt(){
		if(baspt!=null&&cases!=null){
			List<ResearchAj> newcases = new ArrayList<ResearchAj>();
			for(ResearchAj aj:cases){
				if(baspt.equals(aj.getBaspt())){
					newcases.add(aj);
				}
			}
			cases = newcases;
		}
	}
	public List<ResearchAj> getCases() {
		return cases;
	}
	public void setCases(List<ResearchAj> cases) {
		this.cases = cases;
	}
	public boolean isIssum() {
		return issum;
	}
	public void setIssum(boolean issum) {
		this.issum = issum;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getCol() {
		return col;
	}
	public void setCol(String col) throws Exception {
		if(StringUtil.isNotBlank(col)){
			if(this.col==null){
				this.col = col;
			}else if(this.col!=null&&(!this.col.equals(col))){
				throw new Exception("与上级调研条件冲突");
			}
		}
	}
	
	public void filter() throws Exception{
		Class clazz = this.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			if (method.getName().startsWith("fil_")) {
				try {
					method.invoke(this, new Object[]{});
				} catch (Exception e) {
					throw e;
				}
			}
		}
	}
	
	public Double result(){
		if(StringUtil.isBlank(col)||col.equals("1")){
			return (double) cases.size();
		}
		return 0.0;
	}
	
	public String toString(){
		String result="";
		if(StringUtil.isNotBlank(ajxz)){
			result += "ajxz:"+ajxz+";";
		}
		if(StringUtil.isNotBlank(spcx)){
			result += "spcx:"+spcx+";";
		}
		if(StringUtil.isNotBlank(spcxlx)){
			result += "spcxlx:"+spcxlx+";";
		}
		if(StringUtil.isNotBlank(spcxlxtb)){
			result += "spcxlxtb:"+spcxlxtb+";";
		}
		if(StringUtil.isNotBlank(laay)){
			result += "laay:"+laay+";";
		}
		if(StringUtil.isNotBlank(baspt)){
			result += "baspt:"+baspt+";";
		}
		if(StringUtil.isNotBlank(col)){
			result += "col:"+col+";";
		}
		if (result.endsWith(";")) {
			result = result.substring(0, result.length()-1);
		}
		return result;
	}
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}
