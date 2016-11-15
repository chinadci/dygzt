package software.dygzt.service.fy.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import software.dygzt.data.dmb.dataobject.DmbDO;
import software.dygzt.data.fy.dao.FyDmbDao;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.dmb.model.DmbModel;
import software.dygzt.service.fy.FyService;
import software.dygzt.service.fy.model.FyVO;
import software.dygzt.service.share.Convertor;
@Service
public class FyServiceImpl implements FyService, InitializingBean {
	
	private static Logger logger = Logger.getLogger(FyServiceImpl.class);
	
	@Autowired
	private FyDmbDao fydmbDao;
	private List<DmbDO> tjfyList = new ArrayList<DmbDO>();
	
	/**
	 * 法院树形（不含辖区）
	 */
	@Cacheable(cacheName = "FY_CACHE")
	public FyVO getFyList() {
		List<DmbModel> tjfyModelList = new ArrayList<DmbModel>();
		FyVO tlfy=null, hsfy=null;
		for (DmbDO dmbDO : tjfyList) {
			if(dmbDO.getDmbh().trim().equals("920103 132")){
				tlfy = new FyVO(dmbDO.getDmms(), dmbDO.getDmbh(), dmbDO.getBz(), dmbDO.getXgdm());
			}
			if(dmbDO.getDmbh().trim().equals("960200 230")){
				hsfy = new FyVO(dmbDO.getDmms(), dmbDO.getDmbh(), dmbDO.getBz(), dmbDO.getXgdm());
			}
			DmbModel dmbModel = Convertor.doToModel(dmbDO);
			tjfyModelList.add(dmbModel);
		}
		// 声明并初始化最高院模型
		if (logger.isInfoEnabled()) {
			logger.info("获得天津所有法院列表");
		}
		DmbModel tjgyModel = new DmbModel();// 声明并初始化天津高院模型
		FyVO tjgyVO = new FyVO();
		int index = 0;
		int flag = 0;
		try {
			// 获得天津高院信息
			while (index < tjfyModelList.size() && flag < 1) {
				if (tjfyModelList.get(index).getDmbh().endsWith("00")
						&& !tjfyModelList.get(index).getDmbh().equals("000")) {
					tjgyModel = tjfyModelList.get(index);
					flag++;
				}
				index++;
			}
			tjgyVO.setFydm(tjgyModel.getDmbh());
			tjgyVO.setFymc(tjgyModel.getDmms());
			tjgyVO.setFyjc(tjgyModel.getBz());
			tjgyVO.setXgdm(tjgyModel.getXgdm());
			// 获得天津各中级法院的信息
			List<FyVO> zyList = new ArrayList<FyVO>();
			for (int j = 0; j < tjfyModelList.size(); j++) {
				if (tjgyVO.getFydm().charAt(0) == tjfyModelList.get(j).getDmbh().charAt(0)
						&& tjgyVO.getFydm().charAt(1) == tjfyModelList.get(j).getDmbh().charAt(1)
						&& tjfyModelList.get(j).getDmbh().charAt(8) != '0'
						&& tjfyModelList.get(j).getDmbh().charAt(9) == '0') {
					FyVO newZyVO = new FyVO(tjfyModelList.get(j).getDmms(),
								tjfyModelList.get(j).getDmbh(), tjfyModelList
								.get(j).getBz(),tjfyModelList.get(j).getXgdm());
					zyList.add(newZyVO);
				}
			}
			tjgyVO.setXjfyList(zyList);
			//获得所有基院的信息
			for (int j = 0; j < tjgyVO.getXjfyList().size(); j++) {
				List<FyVO> jyList = new ArrayList<FyVO>();// 声明并初始化基院信息list
				for (int k = 0; k < tjfyModelList.size(); k++) {
					if (tjgyVO.getFydm().charAt(0) == tjfyModelList.get(k)
							.getDmbh().charAt(0)
							&& tjgyVO.getFydm().charAt(1) == tjfyModelList
									.get(j).getDmbh().charAt(1)
							&& tjgyVO.getXjfyList().get(j).getFydm().charAt(8) == tjfyModelList
									.get(k).getDmbh().charAt(8)
							&& tjfyModelList.get(k).getDmbh().charAt(9) != '0') {
						FyVO newJyVO = new FyVO(tjfyModelList.get(k).getDmms(),
								tjfyModelList.get(k).getDmbh(), tjfyModelList
										.get(k).getBz(),tjfyModelList.get(k).getXgdm());
						jyList.add(newJyVO);
					}
				}
				tjgyVO.getXjfyList().get(j).setXjfyList(jyList);
			}
				
			//增加海事法院
			zyList.add(hsfy);
			//增加铁路法院
			zyList.add(tlfy);	
			tjgyVO = new FyVO(tjgyModel.getDmms(), tjgyModel.getDmbh(),
					tjgyModel.getBz(),tjgyModel.getXgdm(),zyList);
			return tjgyVO;
		} catch (Exception e) {
			logger.error("获得全国法院列表信息时出现异常", e);
			return null;
		}
	}
	
	/**
	 * 得到天津法院(非树形)
	 */
	@Cacheable(cacheName = "FY_CACHE")
	public List<DmVO> getTjFyList() {
		return tjfyList.isEmpty() ? new ArrayList<DmVO>() : Convertor.dos2Dmvos(tjfyList);
	}
	/**
	 * 得到天津法院(非树形含辖区)
	 */
	@Cacheable(cacheName = "FY_CACHE")
	public List<DmVO> getTjFyListHxq() {
		List<DmVO> list = tjfyList.isEmpty() ? new ArrayList<DmVO>() : Convertor.dos2Dmvos(tjfyList);
		DmVO vo = new DmVO("qsfy","全市法院");
		list.add(0, vo);
		vo = new DmVO("yzy","第一中级人民法院辖区");
		list.add(1, vo);
		vo = new DmVO("ezy","第二中级人民法院辖区");
		list.add(2, vo);
		vo = new DmVO("bhxq","滨海新区法院辖区");
		list.add(3, vo);
		return list;
	}
	
	/**
	 * 法院树形（不含辖区）
	 */
	@Cacheable(cacheName = "FY_CACHE")
	public FyVO getFyListHxq() {
		FyVO fylist = getFyList();
		
		//拷贝高院信息
		FyVO tjgyVO = new FyVO(fylist.getFymc(),fylist.getFydm(),fylist.getFyjc(),fylist.getXgdm());
		//第一层
		List<FyVO> zylist = new ArrayList<FyVO>();
		zylist.add(tjgyVO);
		//特殊处理一中院二中院
		for(FyVO fy:fylist.getXjfyList()){
			if(fy.getFydm().equals("120100 210")||fy.getFydm().equals("120200 220")){
				FyVO zyxqVO;
				if(fy.getFydm().equals("120100 210")){
					zyxqVO = new FyVO("第一中级人民法院辖区", "yzy","","");
				}else{
					zyxqVO = new FyVO("第二中级人民法院辖区", "ezy","","");
				}
				FyVO zyVO =  new FyVO(fy.getFymc(),fy.getFydm(),fy.getFyjc(),fy.getXgdm());
				List<FyVO> jylist = fy.getXjfyList();
				jylist.add(0, zyVO);
				zyxqVO.setXjfyList(jylist);
				zylist.add(zyxqVO);
			}else{
				zylist.add(fy);
			}
		}
		FyVO bhxqVO = new FyVO("滨海新区法院辖区", "bhxq","","");
		zylist.add(bhxqVO);
		
		FyVO fyhxqlist = new FyVO("全市法院", "qsfy","","",zylist);
		return fyhxqlist;
	}

	public void afterPropertiesSet() throws Exception {
		String curDb = CustomerContextHolder.getCustomerType();
		DataSourceRouter.routerToTjgy();
		tjfyList = fydmbDao.getTjFydmb(); // 获得所有法院的列表
		DataSourceRouter.routerTo(curDb);
	}

}
