package software.dygzt.web.controller;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import software.dygzt.data.dmb.dao.DyDmbDao;
import software.dygzt.data.dmb.dataobject.DyDmbDO;
import software.dygzt.data.research.dataobject.ResearchTableDO;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.fy.FyService;
import software.dygzt.service.research.ResearchService;
import software.dygzt.service.research.ResearchVariableFactory;
import software.dygzt.service.research.ResearchVariableService;
import software.dygzt.service.research.model.*;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ChartDataVO;
import software.dygzt.service.share.model.ChartSeriesVO;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.share.model.DatatablesPagedVO;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.util.*;
import software.dygzt.web.ResponseBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
public class ResearchController implements InitializingBean{
	@Autowired
	private ResearchVariableFactory factory;
	@Autowired
	private ResearchService researchBaseImpl;
	@Autowired
	private FyService fyServiceImpl;
	@Autowired
	private DyDmbDao dydmbDao;
	
	private Map<String,String> databaseMap = new HashMap<String,String>();
	
	private static Logger logger = Logger.getLogger(ResearchController.class);
	
	/**
	 * 显示自助调研的form
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autoresearch.do", method = RequestMethod.GET)
	public String showAutoIndex(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String bbbh= request.getParameter("bbbh");
		if(bbbh==null){
			bbbh="xstj";
		}
		String tab = "auto_"+bbbh;
		ContextHolder.setTabContext(tab);
		model.addAttribute("bbbh",bbbh);
		//特殊form显示
//		if(bbbh.equals("")){
//			return "autoResearchQt";
//		}
		return "autoResearch";
	}
	/**
	 * 自助调研报表生成
	 * @param request
	 * @param response
	 * @param model
	 * @param condition
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/showAutoTable.aj", method = RequestMethod.POST)
	public String show(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ResearchConditionVO condition, BindingResult result) {
		String bblx = databaseMap.get(condition.getBblx());
		condition.setBblx(bblx);
		ResearchVariableService researchService = factory.getServiceByName(condition.getBblx());
		ResearchTableDO po = researchBaseImpl.findTable(condition.toString());
		if(po==null){	//没生成过
			try {
				ResearchTable table = researchService.createTable(condition);
				if(DateUtil.getDiffDays(DateUtil.parse(condition.getJsrq(),
					DateUtil.webFormat),new Date())!=0){
					Runnable r = new SaveThread(table,-1,1);
					Thread t = new Thread(r);
					t.start();
				}
				model.addAttribute("table",Convertor.model2vo(table));
				return "pop/researchTable";
			} catch (Exception e) {
				logger.error("生成表格错误：",e);
				model.addAttribute("error",e.getMessage());
				return "inc/error.inc";
			}
		}else if(StringUtil.isNotBlank(po.getSbfy())){	//生成失败过
			String fylist[] = po.getSbfy().split(";");
			boolean connect = false;
			String curDb = CustomerContextHolder.getCustomerType();
			for(String fy:fylist){
				if(fy.equals("jzrh")){
					DataSourceRouter.routerToJzrh();
				}else{
					DataSourceRouter.routerTo(fy);// 各家法院库
				}
				logger.info("连接"+fy);
				if(researchBaseImpl.databaseIsConnected()){
					connect = true;
					break;
				}
			}
			//切换数据库
			DataSourceRouter.routerTo(curDb);
			if(connect){	//如果曾经没连上的连上了，重新create
				try {
					ResearchTable table = researchService.createTable(condition);
					Runnable r = new SaveThread(table,po.getId(),1);
					Thread t = new Thread(r);
					t.start();
					model.addAttribute("table",Convertor.model2vo(table));
					return "pop/researchTable";
				} catch (Exception e) {
					logger.error("生成表格错误：",e);
					model.addAttribute("error",e.getMessage());
					return "inc/error.inc";
				}
			}else{
				model.addAttribute("table",researchBaseImpl.findSavedTable(po.getId()));
				return "pop/researchTable";
			}
		}else{
			model.addAttribute("table",researchBaseImpl.findSavedTable(po.getId()));
			return "pop/researchTable";
		}
	}
	
	/**
	 * 得到案件列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/researchlb.do", method = RequestMethod.GET)
	public String showResearchAjList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String fydm = request.getParameter("fydm").trim();
		
		//将法院代码去除JZRH_，再把辖区代码转换
		String zhhfydm;
		if(fydm.startsWith(DataSourcePrefixEnum.JZRH.getPrefix())){
			zhhfydm  = fydm.substring(DataSourcePrefixEnum.JZRH.getPrefix().length());
		}else{
			zhhfydm  = fydm;
		}
		zhhfydm = FYDataSourceEnum.xqToFy(zhhfydm); //转换后法院代码，将法院辖区转换为权限对等的法院代码
		//判别权限
		UserContextModel userContext = (UserContextModel) ContextHolder.getUserContext();
		if(FYDataSourceEnum.isXjFy(userContext.getFydm(), zhhfydm)){	//有权限查看
			String base = request.getParameter("base");
			String sql = request.getParameter("sql");
			try {
				base = new String(base.getBytes("ISO-8859-1"),"utf-8");
				sql = new String(sql.getBytes("ISO-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				logger.error("参数转换错误",e);
			}
			model.addAttribute("base",base.replace("%", "%25"));
			model.addAttribute("sql",sql);
			model.addAttribute("fydm",fydm);
			return "researchAjList";
		}else{	//没有权限查看
			model.addAttribute("error", "您没有查看该法院案件的权利！您只能查看所在法院或所在法院下级法院的案件列表。");
			return "error";
		}
		
	}
	/**
	 * 得到案件列表分页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/researchlbpage.aj", method = RequestMethod.GET)
	public void showResearchAjPagedList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,DatatablesPagedVO pagedVO) throws IOException {
		//获取列表
		String base = request.getParameter("base");
		String sql = request.getParameter("sql");
		String fydm = request.getParameter("fydm").trim();
		try {
			base = new String(base.getBytes("ISO-8859-1"),"utf-8").trim().replaceAll(":", "=");
			sql = new String(sql.getBytes("ISO-8859-1"),"utf-8").trim().replaceAll(":", "=");
		} catch (UnsupportedEncodingException e) {
			logger.error("参数转换错误",e);
		}
		List<ResearchAjVO> list;
		try {
			if(fydm.startsWith(DataSourcePrefixEnum.JZRH.getPrefix())){
				fydm = fydm.substring(DataSourcePrefixEnum.JZRH.getPrefix().length());
				list = researchBaseImpl.getJzrhAjList(fydm, base, sql);
			}else{
				list = researchBaseImpl.getAjList(fydm, base, sql);
			}
		} catch (Exception e) {
			logger.error("生成案件列表：",e);
			return;
		}
		//搜索的抬头
		List<String> searchHeader = new ArrayList<String>();
		searchHeader.add("ah");
		searchHeader.add("ajmc");
		searchHeader.add("larq");
		searchHeader.add("jarq");
		//排序的抬头
		String[] sortHeader = new String[]{"xh","ah","ajmc","larq","jarq"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(list, pagedVO, searchHeader, sortHeader, response);
	}
	
	/**
	 * 对接到法综系统
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ajxq.do", method = RequestMethod.GET)
	public String showAjxq(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String fydm = request.getParameter("fydm");
		long ajxh = Long.parseLong(request.getParameter("ajxh"));
		FYDataSourceEnum fy = FYDataSourceEnum.getDataSourceByFydm(fydm);
		String dz = fy.getIp();
		UserContextModel userContext = (UserContextModel) ContextHolder.getUserContext();
		Date today = new Date();
		String s = DateUtil.format(today, DateUtil.webFormat) + " " + ajxh
				+ " " + dz;
		String sign = EncodeUtil.doMd5(s);
		String yhmc = null, yhsf = null;
		if (!StringUtil.isBlank(userContext.getYhmc()))
			yhmc = EncodeUtil.encode(userContext.getYhmc());
		if (!StringUtil.isBlank(userContext.getYhsf()))
			yhsf = EncodeUtil.encode(userContext.getYhsf());
		String s_destUrl = "ajxq.do";
		return "redirect:http://" + dz + "/SptsTrustController.do?ajxh=" + ajxh
				+ "&fydm=" + fydm + "&yhmc=" + yhmc + "&yhsf=" + yhsf
				+ "&destUrl=" + s_destUrl + "&sign=" + sign;
	}
	
	/**
	 * 保存历史记录时填写描述
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveForm.aj", method = RequestMethod.POST)
	public String saveForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "pop/saveDiscribe";
	}
	
	/**
	 * 保存自主调研历史（已填写描述）保存在 ResearchSavedInfoDO 表中
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/saveAuto.aj", method = RequestMethod.POST)
	public void saveResearch(HttpServletRequest request,
			HttpServletResponse response,ModelMap model) throws IOException {
		Integer type = Integer.parseInt(request.getParameter("type"));
		String bgms = request.getParameter("bgms");
		String dytj = request.getParameter("dytj");
		boolean success = false;	//false
		String data=null;	//保存失败！
		ResearchTableDO po = researchBaseImpl.findTable(dytj);
		if(po!=null){
			data = researchBaseImpl.saveInfo(po.getId(), bgms,type);
		}else{
			data = "报表尚未生成";
		}
		if(data==null){
			success = true;	
			data = "保存成功";
		}
		String text = "{\"success\":"+success+",\"text\":\""+data+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}

	@RequestMapping(value = "/tableExport.aj", method = RequestMethod.GET)
	public void export(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws UnsupportedEncodingException {
		int tableid = 0;
		String dytj = request.getParameter("dytj");
		if(dytj!=null){
			try {
				dytj = new String(dytj.getBytes("ISO-8859-1"),"utf-8").trim();
			} catch (UnsupportedEncodingException e) {
				logger.error("参数转换错误",e);
			}
			ResearchTableDO po = researchBaseImpl.findTable(dytj);
			if(po!=null){
				tableid = po.getId();
			}
		}else{
			tableid = Integer.parseInt(request.getParameter("tableid"));
		}
		
		if(tableid!=0){
			ResearchTableVO tablevo = researchBaseImpl.findSavedTable(tableid);
			response.reset();
	    	response.setContentType("application/octet-stream");
	    	response.addHeader("Content-Disposition", "inline;filename=\""+URLEncoder.encode(tablevo.getBbmc()+".xls", "UTF-8")+"\";");
	    	OutputStream out=null;
	    	try{
	    		out = response.getOutputStream();
	    	}catch(IOException e){
				logger.error("发生系统异常",e);
			}
	    	if(out!=null){
	    		ExcelUtil.exportExcel(tablevo.getBbmc(), Convertor.vo2Excel(tablevo), out);
	    		try{
	    			out.flush();
	    			out.close();
	    		}catch(IOException e){
	    			logger.error("发生系统异常",e);
	    		}
	    	}
		}
	}
	
	/**
	 * 显示自主调研历史记录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autoHistory.do", method = RequestMethod.GET)
	public String showResearchhistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "history_auto";
		ContextHolder.setTabContext(tab);
		return "historyAuto";
	}
	
	/**
	 * 显示自主调研历史纪录list（分页）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/autoHistoryList.aj", method = RequestMethod.GET)
	public void showResearchHistoryList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,DatatablesPagedVO pagedVO) throws IOException {
		//获取列表
		String ksrq = request.getParameter("ksrq");	//开始日期
		String jsrq = request.getParameter("jsrq");	//结束日期
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		List<Object[]> dos= researchBaseImpl.savedTables(user.getYhmc(),user.getFydm(),ksrq, jsrq,1);
		List<AutoHistoryVO> vos=new ArrayList<AutoHistoryVO>();
		for(Object[] researchdo:dos){
			AutoHistoryVO vo = new AutoHistoryVO();
			vo.setDyrq(DateUtil.format((Date)researchdo[1], DateUtil.webFormat));
			vo.setBgmc(String.valueOf(researchdo[2]));
			vo.setBgms(String.valueOf(researchdo[3]).equals("null")?"":String.valueOf(researchdo[3]));
			vo.setKsrq(DateUtil.format((Date)researchdo[4], DateUtil.webFormat));
			vo.setJsrq(DateUtil.format((Date)researchdo[5], DateUtil.webFormat));
			vo.setAjzt(String.valueOf(researchdo[6]));
			vo.setBtn("<div class='cpt_menu_view' data-url='historyAutoTable.do?tableid="+(Integer)researchdo[0]+"' title='查看'>"+
			"</div><div class='cpt_menu_del' title='删除' data-tableid='"+(Integer)researchdo[0]+"'></div>");
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","bgmc","bgms","ksrq","jsrq","ajzt"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}
	
	/**
	 * 显示保存的历史记录表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/historyAutoTable.do", method = RequestMethod.GET)
	public String showResearchHistoryTable(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int tableid = Integer.parseInt(request.getParameter("tableid"));
		model.addAttribute("table",researchBaseImpl.findSavedTable(tableid));
		return "historyAutoTable";
	}
	
	/**
	 * 删除保存的历史记录
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException 
	 */
	@RequestMapping(value = "/autoHistoryDelete.aj", method = RequestMethod.POST)
	public void deleteResearchHistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int tableid = Integer.parseInt(request.getParameter("tableid"));
		boolean success = false;	
		String result = researchBaseImpl.deleteSavedTable(tableid);
		if(result==null){
			success = true;	
			result = "已删除";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	/**
	 * 自助调研查询form
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autoSearchForm.do", method = RequestMethod.GET)
	public String showSearchForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "search_auto";
		ContextHolder.setTabContext(tab);
		model.addAttribute("fylist",fyServiceImpl.getTjFyList());
		model.addAttribute("fylistHxq",fyServiceImpl.getTjFyListHxq());
		return "autoSearch";
	}
	
	/**
	 * 显示自主调研查询list（分页）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/autoSearchList.aj", method = RequestMethod.GET)
	public void showSearchList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			SearchConditionVO condition,DatatablesPagedVO pagedVO,BindingResult result) throws IOException {
		List<Object[]> dos= researchBaseImpl.search(condition);
		List<AutoHistoryVO> vos=new ArrayList<AutoHistoryVO>();
		for(Object[] researchdo:dos){
			AutoHistoryVO vo = new AutoHistoryVO();
			vo.setDyrq(DateUtil.format((Date)researchdo[1], DateUtil.webFormat));
			vo.setBgmc(String.valueOf(researchdo[2]));
			vo.setKsrq(DateUtil.format((Date)researchdo[3], DateUtil.webFormat));
			vo.setJsrq(DateUtil.format((Date)researchdo[4], DateUtil.webFormat));
			vo.setAjzt(String.valueOf(researchdo[5]));
			vo.setDyr(String.valueOf(researchdo[6]));
			vo.setDyrfydm(FYDataSourceEnum.getDataSourceByFydm(String.valueOf(researchdo[7])).getFymc());
			vo.setBtn("<div class='cpt_menu_view' data-url='historyAutoTable.do?tableid="+(Integer)researchdo[0]+"' title='查看'></div>");
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","bgmc","ksrq","jsrq","ajzt","dyr","dyrfydm"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}
	/**
	 * 统计分析调研人法院(结果数据)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/statisticsResearchDyrfy.aj", method = RequestMethod.POST)
	public void showStatisticsDyrfy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		boolean success = false;
		String result = "\"信息获取错误\"";
		String ksrq = request.getParameter("ksrq");	//开始日期
		String jsrq = request.getParameter("jsrq");	//结束日期
		//自助，为0不null
		ChartSeriesVO autosery = new ChartSeriesVO();
		autosery.setName("自助调研");
		autosery.setStack("自助调研");
		List<DmVO> fys = fyServiceImpl.getTjFyList();
		List data = new ArrayList();
		for(DmVO fy:fys){
			SearchConditionVO condition = new SearchConditionVO();
			condition.setKsrq(ksrq);
			condition.setJsrq(jsrq);
			condition.setDyrfydm(fy.getBh());
			List<Object[]> dos= researchBaseImpl.search(condition);
			ChartDataVO da = new ChartDataVO(dos.size(),"autoStatisticForm.do?dyrfydm="+fy.getBh()+"&ksrq="+ksrq+"&jsrq="+jsrq);
			data.add(da);
		}
		autosery.setData(data);
		success = true;
		Gson gson = new Gson();
		result = gson.toJson(autosery);
		String text = "{\"success\":"+success+",\"text\":"+result+"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 统计分析调研时间(结果数据)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/statisticsResearchSj.aj", method = RequestMethod.POST)
	public void showStatisticsSj(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		String sjhf = request.getParameter("sjhf");
		Date ksrq = DateUtil.parse(request.getParameter("ksrq"),DateUtil.webFormat);
		Date jsrq = DateUtil.parse(request.getParameter("jsrq"),DateUtil.webFormat);
		boolean success = false;
		String result = "\"信息获取错误\"";
		if(StringUtil.isNotBlank(sjhf)){
			List<List<String>> intervals = DateUtil.dateIntervals(ksrq, jsrq, sjhf);
			List<String> ksrqList = intervals.get(0);
			List<String> jsrqList = intervals.get(1);
			//自助，为0不null
			ChartSeriesVO autosery = new ChartSeriesVO();
			autosery.setName("自助调研");
			List data = new ArrayList();
			for(int i=0;i<ksrqList.size();i++){
				String ksrqCon = ksrqList.get(i);
				String jsrqCon = jsrqList.get(i);
				SearchConditionVO condition = new SearchConditionVO();
				condition.setKsrq(ksrqCon);
				condition.setJsrq(jsrqCon);
				List<Object[]> dos= researchBaseImpl.search(condition);
				ChartDataVO da = new ChartDataVO(dos.size(),"autoStatisticForm.do?ksrq="+ksrqCon+"&jsrq="+jsrqCon);
				data.add(da);
			}
			autosery.setData(data);
			success = true;
			Gson gson = new Gson();
			result = gson.toJson(autosery);
		}
		String text = "{\"success\":"+success+",\"text\":"+result+"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 自助调研统计列表表头
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/autoStatisticForm.do", method = RequestMethod.GET)
	public String showStatistic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			SearchConditionVO condition, BindingResult result) {
		model.addAttribute("condition",condition.toString());
		return "autoStatisticsTable";
	}
	
	class SaveThread implements Runnable{

		private ResearchTable table;
		private int tableid;
		private int type;
		
		public SaveThread(ResearchTable table,int tableid,int type) {
			super();
			this.table = table;
			this.tableid = tableid;
			this.type = type;
		}

		public void run() {
			researchBaseImpl.saveTable(tableid,table,type);
		}

	}

	public void afterPropertiesSet() throws Exception {
		List<DyDmbDO> dmbs = dydmbDao.getDmListByLbbh("ResearchDB");
		for(DyDmbDO dydmb:dmbs){
			databaseMap.put(dydmb.getDmbh(), dydmb.getDmms());
		}
	}
}

