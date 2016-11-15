package software.dygzt.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.gson.Gson;

import software.dygzt.data.manual.dataobject.ManualResearchDO;
import software.dygzt.service.ajlx.AjlxService;
import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.fy.FyService;
import software.dygzt.service.manualResearch.ManualService;
import software.dygzt.service.manualResearch.model.ManualDyztEnum;
import software.dygzt.service.manualResearch.model.ManualHistoryVO;
import software.dygzt.service.manualResearch.model.ManualModel;
import software.dygzt.service.manualResearch.model.ManualSearchConditionVO;
import software.dygzt.service.manualResearch.model.ManualVO;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ChartDataVO;
import software.dygzt.service.share.model.ChartSeriesVO;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.share.model.DatatablesPagedVO;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.DyXtyhVO;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.util.CompareModel;
import software.dygzt.util.CompareUtil;
import software.dygzt.util.DataTablePageUtil;
import software.dygzt.util.DateUtil;
import software.dygzt.util.PageUtil;
import software.dygzt.util.StringUtil;
import software.dygzt.web.ResponseBuilder;
@Controller
public class ManualController {
	private static Logger logger = Logger.getLogger(ManualController.class);
	@Autowired
	private ManualService manualServiceImpl;
	@Autowired
	private FyService fyServiceImpl;
	@Autowired
	private XtyhService xtyhService;
	@Autowired
	private AjlxService ajlxService;
	
	@RequestMapping(value = "/xzajlx.aj", method = RequestMethod.POST)
	public void chooseAjlx(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, ajlxService.getAjlxTree());
	}
	/**
	 * 得到人工调研form
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manual.do", method = RequestMethod.GET)
	public String showManualForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		//提前填好联系方式等信息
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		model.addAttribute("yhxx", user);
		String tab = "manual";
		ContextHolder.setTabContext(tab);
		return "manualResearch";
	}
	
	/**
	 * 新建人工调研请求
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception 
	 */
	@RequestMapping(value = "/manualRequest.aj", method = RequestMethod.POST)
	public void newManualRequest(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,ManualModel manualmodel,BindingResult result) throws Exception {
		//获得附件
		String fjname = request.getParameter("fjname");
		if(StringUtil.isNotBlank(fjname)){
			fjname = fjname.substring(fjname.lastIndexOf("\\")+1);
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile fjFile = multipartRequest.getFile("fj");
			byte[] fj = null;
			try {
				fj = fjFile.getBytes();
			} catch (IOException e) {
				logger.error("文件转换错误：",e);
			}
			manualmodel.setFjm(fjname);
			manualmodel.setFj(fj);
		}
		//填写调研人信息
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		manualmodel.setDyr(user.getYhmc());
		manualmodel.setDyrfydm(user.getFydm());
		String data = manualServiceImpl.manualRequest(manualmodel);
		boolean success = false;
		if(data==null){
			success = true;
			data = "提交成功！";
		}
		String text = "{\"success\":"+success+",\"text\":\""+data+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponseForAjaxUpload(response, text);
	}
	
	/**
	 * 人工调研历史（申请调研者）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manualHistory.do", method = RequestMethod.GET)
	public String showResearchhistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "history_manual";
		ContextHolder.setTabContext(tab);
		return "historyManual";
	}
	
	/**
	 * 显示人工调研列表（申请调研者）(ok)
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/manualHistoryList.aj", method = RequestMethod.GET)
	public void showResearchHistoryList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,DatatablesPagedVO pagedVO) throws IOException {
		//获取列表
		String ksrq = request.getParameter("ksrq");	//开始日期
		String jsrq = request.getParameter("jsrq");	//结束日期
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		List<ManualResearchDO> dos = manualServiceImpl.listByDyr(user.getYhmc(),user.getFydm(),ksrq, jsrq);
		//构造volist
		List<ManualHistoryVO> vos=new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			String btn = "<div class='cpt_menu_view' data-url='showManualResearch.do?researchid="+manualdo.getId()+"' title='查看'></div>";
			if(manualdo.getDyzt().equals(ManualDyztEnum.SPBTG.getDyzt())){
				btn +="<div class='cpt_menu_reason' data-reason='"+manualdo.getSprthyy()+"' title='查看原因'></div>";
			}else if(manualdo.getDyzt().equals(ManualDyztEnum.DYWC.getDyzt())){
				btn +="<div class='cpt_menu_download' data-url='manualTableDownload.aj?researchid="+manualdo.getId()+"' title='下载'></div>";
			}
			btn +="<div class='cpt_menu_del' data-bbid='"+manualdo.getId()+"' title='删除'></div>";
			vo.setBtn(btn);
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","dymd","ksrq","jsrq","dyfw","dyxq","dyzt"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}
	
	/**
	 * 显示调研记录详情
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/showManualResearch.do", method = RequestMethod.GET)
	public String showManualResearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		ManualVO vo = manualServiceImpl.find(researchid);
		model.addAttribute("vo",vo);
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		model.addAttribute("user",user);
		return "manualResearchDetail";
	}
	
	/**
	 * 调研附件下载
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/manualFjDownload.aj", method = RequestMethod.GET)
	public void fjDownload(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
    	int researchid = Integer.parseInt(request.getParameter("researchid"));
		Object[]  file = manualServiceImpl.downloadFj(researchid);
    	response.reset();
    	response.setContentType("application/x-download");
    	response.addHeader("Content-Disposition", "inline;filename=\""+encodeFilename((String)file[0])+"\";");
    	OutputStream out= response.getOutputStream();
    	out.write((byte[])file[1]);
		out.flush();
		out.close();
	}
	
	/**
	 * 人工调研结果（申请调研者、计算者）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/manualTableDownload.aj", method = RequestMethod.GET)
	public void resultDownload(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		Object[]  file = manualServiceImpl.downloadDyjg(researchid);
    	response.reset();
    	response.setContentType("application/x-download");
    	response.addHeader("Content-Disposition", "inline;filename=\""+encodeFilename((String)file[0])+"\";");
    	OutputStream out= response.getOutputStream();
    	out.write((byte[])file[1]);
		out.flush();
		out.close();
	}
	
//	/**
//	 * 人工调研结果退回
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 * @throws IOException 
//	 */
//	@RequestMapping(value = "/manualTableUndo.aj", method = RequestMethod.POST)
//	public void undoResearchTable(HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) throws IOException {
//		boolean success = true;	//false
//		String result = "已退回";	//退回失败！
//		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
//		ResponseBuilder builder = new ResponseBuilder();
//		builder.writeJsonResponse(response, text);
//	}
	
	/**
	 * 删除申请的人工调研
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException 
	 */
	@RequestMapping(value = "/manualHistoryDelete.aj", method = RequestMethod.POST)
	public void deleteResearchHistory(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		boolean success = false;	
		String result = manualServiceImpl.deleteDyxx(researchid);
		if(result==null){
			success = true;
			result = "已删除";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 待审批列表
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approve.do", method = RequestMethod.GET)
	public String toApproveList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "approve";
		ContextHolder.setTabContext(tab);
		List<ManualResearchDO> dos = manualServiceImpl.toApproveList();
		List<ManualHistoryVO> vos = new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			vo.setBtn(String.valueOf(manualdo.getId()));
			vos.add(vo);
		}
		model.addAttribute("list", vos);
		return "approve";
	}
	
	/**
	 * 通过审批
	 * @throws IOException 
	 */
	@RequestMapping(value = "/approve.aj", method = RequestMethod.POST)
	public void approveResearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		String result = manualServiceImpl.approve(researchid);
		boolean success = false;
		if(result==null){
			success = true;
			result = "审批通过";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	/**
	 * 拒绝审批界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/rejectReason.aj", method = RequestMethod.POST)
	public String rejectForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "pop/rejectReason";
	}
	
	/**
	 * 拒绝审批
	 * @throws IOException 
	 */
	@RequestMapping(value = "/reject.aj", method = RequestMethod.POST)
	public void rejectResearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		String reason = request.getParameter("thyy");
		String result = manualServiceImpl.reject(researchid,reason);
		boolean success = false;
		if(result==null){
			success = true;
			result = "审批不通过";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 已审批界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/approved.do", method = RequestMethod.GET)
	public String approvedList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "approved";
		ContextHolder.setTabContext(tab);
		return "approved";
	}
	/**
	 * 审批过列表（后台分页）
	 * @param request
	 * @param response
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(value = "/manualApproveList.aj", method = RequestMethod.GET)
	public void showApprovedList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,DatatablesPagedVO pagedVO) throws IOException {
		String ksrq = request.getParameter("ksrq");
		String jsrq = request.getParameter("jsrq");
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		//构造list
		List<ManualResearchDO> dos = manualServiceImpl.listBySpr(user.getYhmc(),user.getFydm(),ksrq, jsrq);
		List<ManualHistoryVO> vos=new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			String btn = "<div class='cpt_menu_view' data-url='showManualResearch.do?researchid="+manualdo.getId()+"' title='查看'></div>";
			vo.setBtn(btn);
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","dymd","ksrq","jsrq","dyfw","dyxq","dyzt"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}
	
	/**
	 * 待指派界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/distribute.do", method = RequestMethod.GET)
	public String toDistributeList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "distribute";
		ContextHolder.setTabContext(tab);
		List<ManualResearchDO> dos = manualServiceImpl.toDistributeList();
		List<ManualHistoryVO> vos = new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			vo.setBtn(String.valueOf(manualdo.getId()));
			vos.add(vo);
		}
		model.addAttribute("list", vos);
		
		List<String> qxList = new ArrayList<String>();
		qxList.add("计算人");
		List<DyXtyhVO> jsrs = xtyhService.getListByQx(qxList);
		model.addAttribute("jsrs", jsrs);
		return "distribute";
	}
	/**
	 * 指派
	 * @throws IOException 
	 */
	@RequestMapping(value = "/distribute.aj", method = RequestMethod.POST)
	public void distributeResearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		String jsr = request.getParameter("jsr");
		String jsrfydm = request.getParameter("jsrfydm");
		String result = manualServiceImpl.distribute(researchid, jsr, jsrfydm);
		boolean success = false;
		if(result==null){
			success = true;
			result = "已指派";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	/**
	 * 待计算界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/compute.do", method = RequestMethod.GET)
	public String toComputeList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "compute";
		ContextHolder.setTabContext(tab);
		List<ManualResearchDO> dos = manualServiceImpl.toComputeList();
		List<ManualHistoryVO> vos = new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			vo.setBtn(String.valueOf(manualdo.getId()));
			vos.add(vo);
		}
		model.addAttribute("list", vos);
		return "compute";
	}
	
	/**
	 * 计算结果界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/computeResult.aj", method = RequestMethod.POST)
	public String computeForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "pop/computeResult";
	}
	
	/**
	 * 上传计算结果
	 * @throws Exception 
	 */
	@RequestMapping(value = "/compute.aj", method = RequestMethod.POST)
	public void computeResearch(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws Exception {
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		String dyjgname = request.getParameter("dyjg");
		byte[] dyjg = null;
		String dydmname = request.getParameter("dydm");
		byte[] dydm = null;
		if(StringUtil.isNotBlank(dyjgname)){
			dyjgname = dyjgname.substring(dyjgname.lastIndexOf("\\")+1);
			MultipartFile dyjgFile = multipartRequest.getFile("dyjg");
			try {
				dyjg = dyjgFile.getBytes();
			} catch (IOException e) {
				logger.error("文件转换错误：",e);
			}
		}
		if(StringUtil.isNotBlank(dydmname)){
			dydmname = dydmname.substring(dydmname.lastIndexOf("\\")+1);
			MultipartFile dydmFile = multipartRequest.getFile("dydm");
			try {
				dydm = dydmFile.getBytes();
			} catch (IOException e) {
				logger.error("文件转换错误：",e);
			}
		}
		//返回json
		String result = manualServiceImpl.compute(researchid, dyjgname, dyjg, dydmname, dydm);
		boolean success = false;
		if(result==null){
			success = true;
			result = "已解决";
		}
		String text = "{\"success\":"+success+",\"text\":\""+result+"\"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponseForAjaxUpload(response, text);
	}
	
	/**
	 * 已计算界面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/computed.do", method = RequestMethod.GET)
	public String computedList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "computed";
		ContextHolder.setTabContext(tab);
		return "computed";
	}
	/**
	 * 已计算界面(分页)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manualComputedList.aj", method = RequestMethod.GET)
	public void showComputedList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,DatatablesPagedVO pagedVO) throws IOException {
		String ksrq = request.getParameter("ksrq");	//开始日期
		String jsrq = request.getParameter("jsrq");	//结束日期
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		//构造list
		List<ManualResearchDO> dos = manualServiceImpl.listByJsr(user.getYhmc(),user.getFydm(),ksrq, jsrq);
		List<ManualHistoryVO> vos=new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			String btn = "<div class='cpt_menu_view' data-url='showManualResearch.do?researchid="+manualdo.getId()+"' title='查看'></div>"
			+"<div class='cpt_menu_download' data-url='manualTableDownload.aj?researchid="+manualdo.getId()+"' title='下载结果'></div>"
			+"<div class='cpt_menu_download' data-url='computeCode.aj?researchid="+manualdo.getId()+"' title='下载代码'></div>";
			vo.setBtn(btn);
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","dymd","ksrq","jsrq","dyfw","dyxq"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}
	/**
	 * 下载调研代码
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value="computeCode.aj",method = RequestMethod.GET)
    public void codeDownload(Model model,HttpServletResponse response,HttpServletRequest request) throws Exception{
		int researchid = Integer.parseInt(request.getParameter("researchid"));
		Object[]  file = manualServiceImpl.downloadDydm(researchid);
    	response.reset();
    	response.setContentType("application/x-download");
    	response.addHeader("Content-Disposition", "inline;filename=\""+encodeFilename((String)file[0])+"\";");
    	OutputStream out= response.getOutputStream();
    	out.write((byte[])file[1]);
		out.flush();
		out.close();
	}
	
	/**
	 * 人工调研查询form
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manualSearchForm.do", method = RequestMethod.GET)
	public String showSearchForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "search_manual";
		ContextHolder.setTabContext(tab);
		model.addAttribute("fylist",fyServiceImpl.getTjFyList());
		model.addAttribute("fylistHxq",fyServiceImpl.getTjFyListHxq());
		return "manualSearch";
	}
	/**
	 * 显示人工调研查询list（分页）
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/manualSearchList.aj", method = RequestMethod.GET)
	public void showSearchList(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ManualSearchConditionVO condition,DatatablesPagedVO pagedVO, BindingResult result) throws IOException {
		List<ManualResearchDO> dos = manualServiceImpl.search(condition);
		//构造list
		List<ManualHistoryVO> vos=new ArrayList<ManualHistoryVO>();
		for(ManualResearchDO manualdo:dos){
			ManualHistoryVO vo = Convertor.do2vo(manualdo); 
			String btn = "<div class='cpt_menu_view' data-url='showManualResearch.do?researchid="+manualdo.getId()+"' title='查看'></div>";
			vo.setBtn(btn);
			vos.add(vo);
		}
		//排序的抬头
		String[] sortHeader = new String[]{"dyrq","dymd","ksrq","jsrq","dyfw","dyxq","dyzt"};
		//搜索、排序、分页
		DataTablePageUtil.search_sort_page(vos, pagedVO, null, sortHeader, response);
	}

	/**
	 * 统计分析调研人法院
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/statisticsDyrfy.do", method = RequestMethod.GET)
	public String showStatisticsDyrfyForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "sta_dyrfy";
		ContextHolder.setTabContext(tab);
		return "statisticsDyrfy";
	}
	

	/**
	 * 统计x轴——调研人法院
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/statisticXAxisDyrfy.aj", method = RequestMethod.POST)
	public void showStatisticXAxisDyrfy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		boolean success = false;
		String result = "\"信息获取错误\"";
		List<DmVO> fys = fyServiceImpl.getTjFyList();
		List<String> fyList = new ArrayList<String>();
		for(DmVO fy:fys){
			fyList.add(fy.getMs());
		}
		if(fyList.size()>0){
			success = true;
			Gson gson = new Gson();
			result = gson.toJson(fyList);
		}
		String text = "{\"success\":"+success+",\"text\":"+result+"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 统计分析调研人法院(结果数据)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/statisticsManualDyrfy.aj", method = RequestMethod.POST)
	public void showStatisticsDyrfy(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		boolean success = false;
		String result = "\"信息获取错误\"";
		String ksrq = request.getParameter("ksrq");	//开始日期
		String jsrq = request.getParameter("jsrq");	//结束日期
		//人工调研，为0不null
		ChartSeriesVO manualsery = new ChartSeriesVO();
		manualsery.setName("人工调研");
		manualsery.setStack("人工调研");
		List<DmVO> fys = fyServiceImpl.getTjFyList();
		List data = new ArrayList();
		for(DmVO fy:fys){
			ManualSearchConditionVO condition = new ManualSearchConditionVO();
			condition.setKsrq(ksrq);
			condition.setJsrq(jsrq);
			condition.setDyrfydm(fy.getBh());
			List<ManualResearchDO> dos = manualServiceImpl.search(condition);
			ChartDataVO da = new ChartDataVO(dos.size(),"manualStatisticForm.do?dyrfydm="+fy.getBh()+"&ksrq="+ksrq+"&jsrq="+jsrq);
			data.add(da);
		}
		manualsery.setData(data);	
		success = true;
		Gson gson = new Gson();
		result = gson.toJson(manualsery);
		String text = "{\"success\":"+success+",\"text\":"+result+"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 统计分析调研时间
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/statisticsSj.do", method = RequestMethod.GET)
	public String showStatisticsSjForm(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		String tab = "sta_sj";
		ContextHolder.setTabContext(tab);
		return "statisticsSj";
	}
	
	/**
	 * 统计分析调研时间(x轴)
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/statisticsXAxisSj.aj", method = RequestMethod.POST)
	public void showStatisticsXAxisSj(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) throws IOException {
		String sjhf = request.getParameter("sjhf");
		Date ksrq = DateUtil.parse(request.getParameter("ksrq"),DateUtil.webFormat);
		Date jsrq = DateUtil.parse(request.getParameter("jsrq"),DateUtil.webFormat);
		boolean success = false;
		String result = "\"信息获取错误\"";
		if(StringUtil.isNotBlank(sjhf)){
			List<String> xAxis = DateUtil.dateInterval(ksrq,jsrq,sjhf);
			if(xAxis.size()>31){
				result = "\"日期数过多，超出调研范围\"";
			}else{
				success = true;
				Gson gson = new Gson();
				result = gson.toJson(xAxis);
			}
		}
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
	@RequestMapping(value = "/statisticsManualSj.aj", method = RequestMethod.POST)
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
			//人工调研，为0不null
			ChartSeriesVO manualsery = new ChartSeriesVO();
			manualsery.setName("人工调研");
			List data = new ArrayList();
			for(int i=0;i<ksrqList.size();i++){
				ManualSearchConditionVO condition = new ManualSearchConditionVO();
				String ksrqCon = ksrqList.get(i);
				String jsrqCon = jsrqList.get(i);
				condition.setKsrq(ksrqCon);
				condition.setJsrq(jsrqCon);
				List<ManualResearchDO> dos = manualServiceImpl.search(condition);
				ChartDataVO da = new ChartDataVO(dos.size(),"manualStatisticForm.do?ksrq="+ksrqCon+"&jsrq="+jsrqCon);
				data.add(da);
			}
			manualsery.setData(data);	
			success = true;
			Gson gson = new Gson();
			result = gson.toJson(manualsery);
		}
		String text = "{\"success\":"+success+",\"text\":"+result+"}";
		ResponseBuilder builder = new ResponseBuilder();
		builder.writeJsonResponse(response, text);
	}
	
	/**
	 * 人工调研统计列表表头
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/manualStatisticForm.do", method = RequestMethod.GET)
	public String showStatistic(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,
			ManualSearchConditionVO condition, BindingResult result) {
		model.addAttribute("condition",condition.toString());
		return "manualStatisticsTable";
	}
	
	private String encodeFilename(String fileName) {
		logger.info("encodeFilename:" + fileName);
		String newFileName = "";
		try {
			newFileName = URLEncoder.encode(fileName, "UTF-8");
//			newFileName = StringUtils.replace(newFileName, "%2B", "+");
//			newFileName = StringUtils.replace(newFileName, " ", "%20");
			newFileName = StringUtils.replace(newFileName, "%28", "(");
			newFileName = StringUtils.replace(newFileName, "%29", ")");
		} catch (Exception ex) {
			logger.error("Error. Using default name -- Default", ex);
			newFileName = "Default";
		}
		return newFileName;
	}
	
}
