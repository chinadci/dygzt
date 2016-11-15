package software.dygzt.util;

import java.util.ArrayList;
import java.util.List;

public class SearchUtil {

	/**
	 * 查询list
	 * @return
	 */
	public static List listSearching(List vos, String searchText,List<String> headers) {
		List resList = new ArrayList();
		for(Object vo:vos){
			boolean contain = false;
			for(String header:headers){
				String value = String.valueOf(ReflectionUtil.getFieldValue(vo, header));
				if(value.contains(searchText)){
					contain = true;
					break;
				}
			}
			if(contain==true){
				resList.add(vo);
			}
		}
		return resList;
	}
}
