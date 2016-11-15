package software.dygzt.dynamicds;

import software.dygzt.util.StringUtil;

public class DataSourceRouter {
	
	/**
	 * 切换到指定Fydm数据库
	 * @param fydm
	 */
	public static void routerTo(String fydm) {
		if(fydm==null){
			fydm="Default";
		}
		if(CustomerContextHolder.getCustomerType() == null || ! CustomerContextHolder.getCustomerType().equals(fydm)){
			String datasource =DataSourceMap.getDataSourceKey(fydm);
			if(StringUtil.isBlank(datasource)){
				datasource = DataSourceMap.getDataSourceKey(DataSourceEnum.getFydmBySource(fydm));
				if(StringUtil.isBlank(datasource)){
					throw new RuntimeException("切换法院数据库失败，法院代码为："+fydm);
				}
			}
			CustomerContextHolder.setCustomerType(DataSourceMap
					.getDataSourceKey(fydm));
		}
	}
	
	/**
	 * 切换到本院数据源
	 * @param fydm
	 */
	public static void routerToDefault() {
		//切换回本院数据源 
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.DEFAULT.getFydm()));
	}
	
	public static void routerToTjgy() {
		//切换回本院数据源 
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGY.getFydm()));
	}
	
	/**
	 * 切换到高院集中库
	 */
	public static void routerToJzk() {
		//切换回高院集中库
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYJZK.getFydm()));
	}
	
	/**
	 * 切换到高院院长决策集中库
	 */
	public static void routerToYzjcJzk() {
		//切换回高院集中库
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYYZJCJZK.getFydm()));
	}
	
	/**
	 * 切换到高院信访库
	 */
	public static void routerToXfk() {
		CustomerContextHolder.setCustomerType(DataSourceMap
				.getDataSourceKey(DataSourceEnum.TJGYXFK.getFydm()));
	}
	
	/**
	 * 切换到人事库
	 */
	public static void routerToFyrs(){
		//切换到系统人事库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.FYRS.getFydm()));
	}
	
	/**
	 * 切换到陪审员库
	 */
	public static void routerToPsy(){
		//切换到陪审员人事库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.PSY.getFydm()));
//		System.out.println("psy");
	}
	
	/**
	 * 切换到司法统计库
	 */
	public static void routerToSftj(){
		//切换到系统司法统计库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.SFTJ.getFydm()));
	}
	
	/**
	 * 切换到司法统计库
	 */
	public static void routerToJzrh(){
		//切换到系统集中融合库
		CustomerContextHolder.setCustomerType(DataSourceMap.getDataSourceKey(DataSourceEnum.JZRH.getFydm()));
	}
}
