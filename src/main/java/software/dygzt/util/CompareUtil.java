package software.dygzt.util;

import java.lang.reflect.Field;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class CompareUtil {

    public static String getSortColumn(String[] header,int index,String defaultHeader){
    	if(header.length>index){
    		return header[index];
    	}
    	return defaultHeader;
    }
	
	//sort asc 正序 asc 倒序 desc  filed 多字段排序
    public static <T> Comparator createComparator(List<CompareModel> cmList) {
        return new ImComparator(cmList);
    }

    public static class ImComparator implements Comparator {
        List<CompareModel> compareModels;

        public ImComparator(List<CompareModel> cmList) {
            this.compareModels = cmList;
        }

        public int compare(Object o1, Object o2) {
            int result = 0;	//1为在前，-1为在后，0为相等，默认相等
            for (CompareModel compareModel : compareModels) {
            	String field = compareModel.getField();
            	Field type = ReflectionUtil.getAccessibleField(o1, field);	//得到排序列
            	boolean sort = StringUtil.equals(compareModel.getSort(), "asc");	//true为正序，false为倒序，缺省值倒序
                Object value1 = ReflectionUtil.invokeGetter(o1, field);		//得到object1的列值
                Object value2 = ReflectionUtil.invokeGetter(o2, field);	    //得到object2的列值
                if ((value1 == null || value2 == null) && !(type.getType().getName().equals(String.class.getName()))) {//非String类的对象不支持null对比
                    continue;
                }
                if (value1 instanceof Integer) { //Integer整数序排序
                    int v1 = Integer.valueOf(value1.toString());
                    int v2 = Integer.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort) {
                        return v1 - v2;
                    } else {
                        return v2 - v1;
                    }
                } else if (value1 instanceof Date) {  //Date类型排序
                    Date d1 = (Date) value1;
                    Date d2 = (Date) value2;
                    if (d1.compareTo(d2) == 0) continue;
                    if (sort) {
                        return d1.compareTo(d2);
                    } else{
                        return d2.compareTo(d1);
                    }
                } else if (value1 instanceof Long) { //Long排序
                    long v1 = Long.valueOf(value1.toString());
                    long v2 = Long.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort) {
                        return v1 > v2 ? 1 : -1;
                    } else {
                        return v2 > v1 ? 1 : -1;
                    }
                } else if (value1 instanceof Double) { //Double排序
                    Double v1 = Double.valueOf(value1.toString());
                    Double v2 = Double.valueOf(value2.toString());
                    if (v1 == v2) continue;
                    if (sort) {
                        return v1 > v2 ? 1 : -1;
                    } else{
                        return v2 > v1 ? 1 : -1;
                    }
                }else if (type.getType().getName().equals(String.class.getName())) {
                	String v1 = "";
                	String v2 = "";
                	if (value1 != null) {
                		v1 = value1.toString();
                	}
                	if (value2 != null) {
                		v2 = value2.toString();
                	}
                	int res = StringUtil.compareTo(v1, v2);
                	if (res == 0) {
                		continue;
                	}else {
                		if (sort) {
                			return res;
                		}else{
                			return -res;
                		}
                	}
                	
                }

            }

            return result;
        }
    }

}

