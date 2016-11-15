package software.dygzt.dynamicds;

import java.util.ArrayList;
import java.util.List;

import software.dygzt.util.StringUtil;


/**
 */



/**
 * 数据源Enum
 * @author zym
 * 
 */
public enum DataSourceEnum {
	
	
	/**
	 * 高院集中库 
	 */
	DEFAULT("000000","Default"),
	/**
	 * 高院集中库 
	 */
	TJGYJZK("111111","Tjgyjzk"),
	/**
	 * 高院集中库 
	 */
	TJGYYZJCJZK("1200","Yzjcjzk"),
	/**
	 * 高院信访库 
	 */
	TJGYXFK("222222","Tjgyxfk"),
	
	/**
	 * 人事库
	 */
	FYRS("666666","Fyrs"),
	
	/**
	 * 陪审员库
	 */
	PSY("777777","Psy"),
	
	/**
	 * 高院司法统计库
	 */
	SFTJ("555555","Tjgysftj"),
	
	/**
	 * 高院集中融合库
	 */
	JZRH("999999","Tjgyjzrh"),
	
//	/**
//	 * 一中院集中库 
//	 */
//	TJYZYJZK("1201","Tjyzyjzk"),
//	/**
//	 * 二中院集中库 
//	 */
//	TJEZYJZK("1202","Tjezyjzk"),
	/**
	 * 天津高院
	 */
	TJGY("120000 200","Tjgy"),
	/**
	 * 天津一中院
	 */
	TJYZY("120100 210","Tjyzy"),
	/**
	 * 天津二中院
	 */
	TJEZY("120200 220","Tjezy"),
	/**
	 * 天津海事法院
	 */
	TJHSFY("960200 230","Tjhsfy"),
	/**
	 * 天津和平法院
	 */
	TJHPFY("120101 211","Tjhpfy"),
	/**
	 * 天津南开法院
	 */
	TJNKFY("120104 212","Tjnkfy"),
	/**
	 * 天津河北法院
	 */
	TJHBFY("120105 213","Tjhbfy"),
	/**
	 * 天津红桥法院
	 */
	TJHQFY("120106 214","Tjhqfy"),
	/**
	 * 天津西青法院
	 */
	TJXQFY("120107 215","Tjxqfy"),
	/**
	 * 天津北辰法院
	 */
	TJBCFY("120108 216","Tjbcfy"),
	/**
	 * 天津河东法院
	 */
	TJHDFY("120202 221","Tjhdfy"),
	
	/**
	 * 天津河西法院
	 */
	TJHXFY("120203 222","Tjhxfy"),
	
	/**
	 * 天津塘沽法院
	 */
	TJTGFY("120204 223","Tjtgfy"),
	
	/**
	 * 天津汉沽法院
	 */
	TJHGFY("120205 224","Tjhgfy"),
	
	/**
	 * 天津大港法院
	 */
	TJDGFY("120206 225","Tjdgfy"),
	
	/**
	 * 天津东丽法院
	 */
	TJDLFY("120207 226","Tjdlfy"),
	
	/**
	 * 天津津南法院
	 */
	TJJNFY("120208 227","Tjjnfy"),
	
	/**
	 * 天津宁河法院
	 */
	TJNHFY("120221 228","Tjnhfy"),
	
	/**
	 * 天津武清法院
	 */
	TJWQFY("120222 217","Tjwqfy"),
	
	/**
	 * 天津静海法院
	 */
	TJJHFY("120223 218","Tjjhfy"),
	
	/**
	 * 天津宝坻法院
	 */
	TJBDFY("120224 219","Tjbdfy"),
	
	/**
	 * 天津蓟县法院
	 */
	TJJXFY("120225 21A","Tjjxfy"),
	/**
	 * 天津开发区人民法院
	 */
	TJKFQFY("120241 229","Tjkfqfy"),
	/**
	 * 天津滨海新区法院
	 */
	TJBHXQFY("120242 22A","Tjbhxqfy"),
	/**
	 * 天津铁路法院
	 */
	TJTLFY("920103 132","Tjtlfy");
	
	
	String fydm;
	
	String source;

	/**
	 * @param fydm
	 * @param source
	 */
	private DataSourceEnum(String fydm, String source) {
		this.fydm = fydm;
		this.source = source;
	}
	
	public static String getFydmBySource(String source){
		for (DataSourceEnum src : DataSourceEnum.values()) {
			if (StringUtil.equals(source, src.getSource())) {
				return src.getFydm();
			}
		}
		return "";
	}
	
	public static List<String> getSubFydm(String fydm) {
		List<String> fydmList = new ArrayList<String>();
		if (StringUtil.equals(TJGY.getFydm(), fydm)) {
			for (DataSourceEnum dataSource : DataSourceEnum.values()) {
				if (dataSource.getFydm().equals(
						DataSourceEnum.DEFAULT.getFydm())
						|| dataSource.getFydm().equals(
								DataSourceEnum.TJGY.getFydm())
						|| dataSource.getFydm().equals(
								DataSourceEnum.TJYZY.getFydm())
						|| dataSource.getFydm().equals(
								DataSourceEnum.TJEZY.getFydm())
						)
					continue;
				fydmList.add(dataSource.getFydm());
			}
		} else if (StringUtil.equals(TJYZY.getFydm(), fydm)) {
			fydmList.add(TJHPFY.getFydm());
			fydmList.add(TJNKFY.getFydm());
			fydmList.add(TJHBFY.getFydm());
			fydmList.add(TJHQFY.getFydm());
			fydmList.add(TJXQFY.getFydm());
			fydmList.add(TJBCFY.getFydm());
			fydmList.add(TJWQFY.getFydm());
			fydmList.add(TJJHFY.getFydm());
			fydmList.add(TJBDFY.getFydm());
			fydmList.add(TJJXFY.getFydm());
		} else if (StringUtil.equals(TJEZY.getFydm(), fydm)) {
			fydmList.add(TJHDFY.getFydm());
			fydmList.add(TJHXFY.getFydm());
			fydmList.add(TJTGFY.getFydm());
			fydmList.add(TJHGFY.getFydm());
			fydmList.add(TJDGFY.getFydm());
			fydmList.add(TJDLFY.getFydm());
			fydmList.add(TJJNFY.getFydm());
			fydmList.add(TJNHFY.getFydm());
			fydmList.add(TJKFQFY.getFydm());
			fydmList.add(TJBHXQFY.getFydm());
		} else if (StringUtil.equals(TJHSFY.getFydm(), fydm)) {
			fydmList.add(TJTGFY.getFydm());
			fydmList.add(TJHGFY.getFydm());
			fydmList.add(TJDGFY.getFydm());
			fydmList.add(TJBHXQFY.getFydm());
			fydmList.add(TJKFQFY.getFydm());
		} else {
			fydmList.add(fydm);
		}

		return fydmList;
	}

	/**
	 * @return the fydm
	 */
	public String getFydm() {
		return fydm;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
}
