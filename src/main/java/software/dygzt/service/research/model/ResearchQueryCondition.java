package software.dygzt.service.research.model;

import java.util.Set;

/**
 * 调研条件VO
 */
public abstract class ResearchQueryCondition implements Cloneable{
	
	public abstract String getAjzt();

	public abstract void setAjzt(String ajzt);
	
	public abstract String getAjztstr();

	public abstract void setAjztstr(String ajztstr);

	public abstract String getAjxzStr();

	public abstract void setAjxzStr(String ajxzStr);

	public abstract String getKssj();

	public abstract void setKssj(String kssj);

	public abstract String getJssj();

	public abstract void setJssj(String jssj);
	
	public abstract Set<String> getAjxz();

	public abstract void setAjxz(String ajxz);
	
	public String stringSetToString(Set<String> set){
		Object[] array = (Object[])set.toArray();
		String str = "";
		for (int i = 0 ; i < array.length  ; i++) {
			str += "'"+array[i]+"'"+",";
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}
	
	public String intSetToString(Set<Integer> set){
		Object[] array = (Object[])set.toArray();
		String str = "";
		for (int i = 0 ; i < array.length  ; i++) {
			str += array[i]+",";
		}
		if (str.endsWith(",")) {
			str = str.substring(0, str.length()-1);
		}
		return str;
	}

	public abstract String toString();
	
	public abstract String getSql(ResearchQueryResult result);

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
}
