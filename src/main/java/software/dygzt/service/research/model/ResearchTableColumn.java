package software.dygzt.service.research.model;

import java.util.ArrayList;
import java.util.List;

public class ResearchTableColumn implements Cloneable{
	private int colNum; //第几列
	private String colName;
	private List<String> fatherName;
	/**
	 * 同类子类型个数
	 */
	private List<Integer> brotherSize;
	/**
	 * 是否为第一个
	 */
	private boolean firstChild;
	/**
	 * 处于列的第几行
	 */
	private int level;
	
	public ResearchTableColumn(int colNum, String colName, int level) {
		super();
		this.colNum = colNum;
		this.colName = colName;
		fatherName = new ArrayList<String>();
		brotherSize = new ArrayList<Integer>();
		firstChild = false;
		this.level = level;
	}
	public int getColNum() {
		return colNum;
	}
	public void setColNum(int colNum) {
		this.colNum = colNum;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public List<String> getFatherName() {
		return fatherName;
	}
	public void setFatherName(List<String> fatherName) {
		this.fatherName = fatherName;
	}
	public List<Integer> getBrotherSize() {
		return brotherSize;
	}
	public void setBrotherSize(List<Integer> brotherSize) {
		this.brotherSize = brotherSize;
	}
	public boolean isFirstChild() {
		return firstChild;
	}
	public void setFirstChild(boolean firstChild) {
		this.firstChild = firstChild;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		ResearchTableColumn col = new ResearchTableColumn(colNum,colName,level);
		col.setFirstChild(firstChild);
		for(String father:fatherName){
			col.getFatherName().add(father);
		}
		for(Integer brother:brotherSize){
			col.getBrotherSize().add(brother);
		}
		return col;
	}
	
}
