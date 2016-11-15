package software.dygzt.dynamicds;

import software.dygzt.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 法院数据源Enum
 */
public enum FYDataSourceEnum {
	//法院代码，法院编号，源，法院名称，IP
	TJGY("120000 200",51,"Tjgy","天津市高级人民法院","130.1.1.111"),
	TJYZY("120100 210",52,"Tjyzy","第一中级人民法院","130.2.0.110"),
	TJEZY("120200 220",62,"Tjezy","第二中级人民法院","130.3.100.36"),
	TJHSFY("960200 230",72,"Tjhsfy","天津海事法院","130.4.1.196"),
	TJHPFY("120101 211",53,"Tjhpfy","和平区人民法院","130.6.0.200"),
	TJNKFY("120104 212",54,"Tjnkfy","南开区人民法院","130.5.0.14"),
	TJHBFY("120105 213",55,"Tjhbfy","河北区人民法院","130.7.0.7"),
	TJHQFY("120106 214",56,"Tjhqfy","红桥区人民法院","130.8.0.73"),
	TJXQFY("120107 215",57,"Tjxqfy","西青区人民法院","130.11.1.5"),
	TJBCFY("120108 216",58,"Tjbcfy","北辰区人民法院","130.12.0.2"),
	TJHDFY("120202 221",63,"Tjhdfy","河东区人民法院","130.9.40.13"),
	TJHXFY("120203 222",64,"Tjhxfy","河西区人民法院","130.10.0.167"),
	TJTGFY("120204 223",65,"Tjtgfy","塘沽审判区","130.15.0.21"),
	TJHGFY("120205 224",66,"Tjhgfy","汉沽审判区","130.16.0.18"),
	TJDGFY("120206 225",67,"Tjdgfy","大港审判区","130.17.0.12"),
	TJDLFY("120207 226",68,"Tjdlfy","东丽区人民法院","130.13.0.13"),
	TJJNFY("120208 227",69,"Tjjnfy","津南区人民法院","130.14.0.22"),
	TJNHFY("120221 228",70,"Tjnhfy","宁河区人民法院","130.18.0.11"),
	TJWQFY("120222 217",59,"Tjwqfy","武清区人民法院","130.19.0.12"),
	TJJHFY("120223 218",60,"Tjjhfy","静海区人民法院","130.20.1.8"),
	TJBDFY("120224 219",61,"Tjbdfy","宝坻区人民法院","130.21.0.5"),
	TJJXFY("120225 21A",73,"Tjjxfy","蓟县人民法院","130.22.0.8"),
	TJKFQFY("120241 229",71,"Tjkfqfy","功能区审判区","130.23.0.15"),
	TJBHXQFY("120242 22A",74,"Tjbhxqfy","天津滨海新区人民法院","130.25.1.13"),
	TJTLFY("920103 132",24,"Tjtlfy","天津铁路运输法院","130.26.0.7")
	;
	
	String fydm;
	
	int fybh;
	
	String source;
	
	String fymc;
	
	String ip;

	private FYDataSourceEnum(String fydm, int fybh, String source, String fymc,
			String ip) {
		this.fydm = fydm;
		this.fybh = fybh;
		this.source = source;
		this.fymc = fymc;
		this.ip = ip;
	}

	public static String getFydmBySource(String source){
		for (FYDataSourceEnum src : FYDataSourceEnum.values()) {
			if (StringUtil.equals(source, src.getSource())) {
				return src.getFydm();
			}
		}
		return "";
	}
	
	public static String getFymcByFydm(String fydm){
		if(fydm!=null){
			if(fydm.equals("qsfy")){
				return "全市法院";
			}else if(fydm.equals("yzy")){
				return "第一中级人民法院辖区";
			}else if(fydm.equals("ezy")){
				return "第二中级人民法院辖区";
			}else if(fydm.equals("bhxq")){
				return "滨海新区法院辖区";
			}
			for (FYDataSourceEnum src : FYDataSourceEnum.values()) {
				if (StringUtil.equals(fydm, src.getFydm())) {
					return src.getFymc();
				}
			}
		}
		return "";
	}
	
	public static FYDataSourceEnum getDataSourceByFydm(String fydm){
		for (FYDataSourceEnum src : FYDataSourceEnum.values()) {
			if (StringUtil.equals(fydm, src.getFydm())) {
				return src;
			}
		}
		return null;
	}
	
	public static FYDataSourceEnum getDataSourceByFybh(Integer fybh){
		for (FYDataSourceEnum src : FYDataSourceEnum.values()) {
			if (fybh==src.getFybh()) {
				return src;
			}
		}
		return null;
	}
	
	public static FYDataSourceEnum getEnumByAccessIp(String ip){
		String[] list = ip.split("\\.");
		if(list == null || list.length != 4){
			return null;
		}
		String temp = list[0]+"."+list[1];
		for(FYDataSourceEnum fy:FYDataSourceEnum.values()){
			if(fy.getIp().contains(temp)){
				return fy;
			}
		}
		return null;
	}
	
	public static List<FYDataSourceEnum> getFyDataSourceList(String type){
		List<FYDataSourceEnum> list=new ArrayList<FYDataSourceEnum>();
		if(type.equals("qsfy")){
			Collections.addAll(list, FYDataSourceEnum.values());
		}else if(type.equals("yzy")||type.equals("ezy")){
			String sjfydm;	//上级法院代码
			if(type.equals("yzy")){
				sjfydm="120100 210";
			}else{
				sjfydm="120200 220";
			}
			for (FYDataSourceEnum src : FYDataSourceEnum.values()) {
				String fydm=src.getFydm();
				if (fydm.charAt(0)==sjfydm.charAt(0)&&fydm.charAt(1)==sjfydm.charAt(1)
						&&fydm.charAt(8)==sjfydm.charAt(8)) {
					list.add(src);
				}
			}
		}else if(type.equals("bhxq")){
			list.add(TJTGFY);
			list.add(TJHGFY);
			list.add(TJDGFY);
			list.add(TJKFQFY);
			list.add(TJBHXQFY);
		}else {
			FYDataSourceEnum src = getDataSourceByFydm(type);
			list.add(src);
		}
		
		return list;
	}
	/**
	 * 将辖区转换为与其权限对等的法院
	 * @param xqdm
	 * @return
	 */
	public static String xqToFy(String xqdm){
		if(xqdm.equals("qsfy")){
			return TJGY.getFydm();
		}else if(xqdm.equals("yzy")){
			return TJYZY.getFydm();
		}else if(xqdm.equals("ezy")){
			return TJEZY.getFydm();
		}else if(xqdm.equals("bhxq")){
			return TJBHXQFY.getFydm();
		}
		return xqdm;
	}
	/**
	 * 
	 * @param sjfy 上级法院代码(不含qsfy、yzy、ezy、bhfy等辖区符号)
	 * @param xjfy 下级法院代码(不含qsfy、yzy、ezy、bhfy等辖区符号)
	 * @return
	 */
	public static boolean isXjFy(String sjfy,String xjfy){
		//自己可以看自己
		if(sjfy.equals(xjfy)){
			return true;
		}
		//高院还可以看所有
		if(sjfy.equals(TJGY.getFydm())){
			return true;
		}
		//一中院和二中院可以看自己和辖区内
		if(sjfy.equals(TJYZY.getFydm())||sjfy.equals(TJEZY.getFydm())){
			if (xjfy.charAt(0)==sjfy.charAt(0)&&xjfy.charAt(1)==sjfy.charAt(1)
					&&xjfy.charAt(8)==sjfy.charAt(8)) {
				return true;
			}
		}
		//滨海新区可以看自己和辖区内
		if(sjfy.equals(TJBHXQFY.getFydm())&&(xjfy.equals(TJBHXQFY.getFydm())
				||xjfy.equals(TJTGFY.getFydm())||xjfy.equals(TJHGFY.getFydm())
				||xjfy.equals(TJDGFY.getFydm())||xjfy.equals(TJKFQFY.getFydm()))){
			return true;
		}
		return false;
	}
	
	/**
	 * @return the fydm
	 */
	public String getFydm() {
		return fydm;
	}

	public int getFybh() {
		return fybh;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * @return the fymc
	 */
	public String getFymc() {
		return fymc;
	}

	public String getIp() {
		return ip;
	}
	
}
