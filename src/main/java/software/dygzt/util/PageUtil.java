package software.dygzt.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	/**
	 * 给list分页
	 * @param vo
	 * @param currentCount
	 * @param pageSize
	 * @return
	 */
	public static List listPaging(List vo, int currentCount,int pageSize) {
		List resList = new ArrayList();
		for(int i = 0;i<pageSize&&currentCount<vo.size();i++,currentCount++){
			Object object = vo.get(currentCount);
			resList.add(object);
		}
		return resList;
	}
}
