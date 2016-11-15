package software.dygzt.service.share.model;

import java.io.UnsupportedEncodingException;

public class DatatablesPagedVO {
	int sEcho; //操作号
	int iDisplayStart;	//开始数
	int iDisplayLength; //每页数
	int iSortCol_0;		//排序的列，值从0开始
	String sSortDir_0;	//升序降序
	String sSearch;	//查询内容
	public int getsEcho() {
		return sEcho;
	}
	public void setsEcho(int sEcho) {
		this.sEcho = sEcho;
	}
	public int getiDisplayStart() {
		return iDisplayStart;
	}
	public void setiDisplayStart(int iDisplayStart) {
		this.iDisplayStart = iDisplayStart;
	}
	public int getiDisplayLength() {
		return iDisplayLength;
	}
	public void setiDisplayLength(int iDisplayLength) {
		this.iDisplayLength = iDisplayLength;
	}
	public int getiSortCol_0() {
		return iSortCol_0;
	}
	public void setiSortCol_0(int iSortCol_0) {
		this.iSortCol_0 = iSortCol_0;
	}
	public String getsSortDir_0() {
		return sSortDir_0;
	}
	public void setsSortDir_0(String sSortDir_0) {
		this.sSortDir_0 = sSortDir_0;
	}
	public String getsSearch() {
		return sSearch;
	}
	public void setsSearch(String sSearch) throws UnsupportedEncodingException {
		this.sSearch = new String(sSearch.getBytes("ISO-8859-1"),"utf-8");
	}
	
}
