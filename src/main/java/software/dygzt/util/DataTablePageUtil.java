package software.dygzt.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import software.dygzt.service.research.model.ResearchAjVO;
import software.dygzt.service.share.model.DatatablesPagedVO;
import software.dygzt.web.ResponseBuilder;

public class DataTablePageUtil {
	/**
	 * 给list分页
	 * @return
	 * @throws IOException 
	 */
	public static void search_sort_page(List list,DatatablesPagedVO pagedVO,List<String> searchHeader,
			String[] sortHeader,HttpServletResponse response) throws IOException {
		//搜索
		if(searchHeader!=null&&StringUtil.isNotBlank(pagedVO.getsSearch())){
			list = SearchUtil.listSearching(list, pagedVO.getsSearch(), searchHeader);
		}
		//排序
		String sortField = CompareUtil.getSortColumn(sortHeader, pagedVO.getiSortCol_0(), sortHeader[0]);
		List<CompareModel> models = new ArrayList<CompareModel>();
		models.add(new CompareModel(pagedVO.getsSortDir_0() , sortField));
		Collections.sort(list, CompareUtil.createComparator(models));
		//分页
		List<ResearchAjVO> pagedAjs = PageUtil.listPaging(list, pagedVO.getiDisplayStart(),
						pagedVO.getiDisplayLength());
		//标记值加一
		pagedVO.setsEcho(pagedVO.getsEcho()+1);
		//返回值
		Map map = new HashMap();
		map.put("data",pagedAjs);
		map.put("recordsTotal",list.size());
		map.put("recordsFiltered", list.size());
		map.put("sEcho",pagedVO.getsEcho());
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, map);
	}
	
}
