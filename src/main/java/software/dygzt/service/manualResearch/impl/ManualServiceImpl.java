package software.dygzt.service.manualResearch.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;

import software.dygzt.data.manual.dao.ManualResearchDao;
import software.dygzt.data.manual.dataobject.ManualResearchDO;
import software.dygzt.service.manualResearch.ManualService;
import software.dygzt.service.manualResearch.model.ManualDyztEnum;
import software.dygzt.service.manualResearch.model.ManualModel;
import software.dygzt.service.manualResearch.model.ManualSearchConditionVO;
import software.dygzt.service.manualResearch.model.ManualVO;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.model.UserContextModel;
import software.dygzt.util.DateUtil;
import software.dygzt.util.StringUtil;
@Service
public class ManualServiceImpl implements ManualService{
	@Autowired
	private ManualResearchDao manualResearchDao;
	
	/**
	 * 调研申请
	 * @param model
	 * @return
	 */
	//会清除调研人人工调研历史缓存
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	@Transactional(propagation = Propagation.REQUIRED)
	public String manualRequest(ManualModel model) {
		// TODO Auto-generated method stub
		ManualResearchDO manualdo = Convertor.model2do(model);
		long maxid = manualResearchDao.getMaxId();
		maxid++;
		manualdo.setId(Integer.parseInt(maxid + ""));
		manualdo.setDyrq(new Date());
		manualdo.setDyzt(ManualDyztEnum.DSP.getDyzt());
		manualResearchDao.save(manualdo);
		return null;
	}
	/**
	 * 查看待审批列表
	 * @return
	 */
	public List<ManualResearchDO> toApproveList() {
		List<ManualResearchDO> list = manualResearchDao.findByDyzt(ManualDyztEnum.DSP.getDyzt());
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 审批通过
	 * @param researchid
	 * @return
	 */
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SPR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	public String approve(int researchid) {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		//更改案件状态，审批人，审批人意见，审批时间
		String hql = "update ManualResearchDO set spr='"+user.getYhmc()+"',sprfydm='"
				+user.getFydm()+"',spryj='Y', dyzt='"+ManualDyztEnum.DZP.getDyzt()+
				"',sprspsj='"+DateUtil.format(new Date(), DateUtil.timestampFormat)
				+"' where id="+researchid;
		manualResearchDao.update(hql);
		return null;
	}
	/**
	 * 审批不通过
	 * @param researchid
	 * @param reason
	 * @return
	 */
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SPR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	public String reject(int researchid, String reason) {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		//更改案件状态，审批人，审批人意见，审批时间，退回原因
		String hql = "update ManualResearchDO set spr='"+user.getYhmc()+"',sprfydm='"
				+user.getFydm()+"',spryj='N', dyzt='"+ManualDyztEnum.SPBTG.getDyzt()+
				"',sprthyy='"+reason+"',sprspsj='"+DateUtil.format(new Date(), DateUtil.timestampFormat)+
				"' where id="+researchid;
		manualResearchDao.update(hql);
		return null;
	}
	/**
	 * 查看待指派列表
	 */
	public List<ManualResearchDO> toDistributeList() {
		List<ManualResearchDO> list = manualResearchDao.findByDyzt(ManualDyztEnum.DZP.getDyzt());
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 指派
	 * @return
	 */
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	public String distribute(int researchid, String jsr, String jsrfydm) {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		//更改案件状态，计算人，计算人法院
		String hql = "update ManualResearchDO set zpr='"+user.getYhmc()+"',zprfydm='"
				+user.getFydm()+"',jsr='"+jsr+"',jsrfydm='"+jsrfydm+
				"', dyzt='"+ManualDyztEnum.DJS.getDyzt()+
				"',zpsj='"+DateUtil.format(new Date(), DateUtil.timestampFormat)+
				"' where id="+researchid;
		manualResearchDao.update(hql);
		return null;
	}
	/**
	 * 查看待计算列表
	 */
	public List<ManualResearchDO> toComputeList() {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		List<ManualResearchDO> list = manualResearchDao.findToCompute(ManualDyztEnum.DJS.getDyzt(),user.getYhmc(),user.getFydm());
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 调研结果上传
	 * @param researchid
	 * @param reason
	 * @return
	 */
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SPR_CACHE","MANAUL_JSR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	public String compute(int researchid, String dyjgname, byte[] dyjg,
			String dydmname, byte[] dydm) {
		if(dyjg==null){
			return "调研结果为空";
		}
		if(dydm==null){
			return "调研代码为空";
		}
		ManualResearchDO manualdo = manualResearchDao.findById(researchid);
		//更改案件状态，调研结果，调研代码，时间
		manualdo.setDyzt(ManualDyztEnum.DYWC.getDyzt());
		manualdo.setDyjgm(dyjgname);
		manualdo.setDyjg(dyjg);
		manualdo.setDydmm(dydmname);
		manualdo.setDydm(dydm);
		manualdo.setJsrjssj(new Date());
		manualResearchDao.save(manualdo);
		return null;
	}
	/**
	 * 获取调研详细信息
	 * @param researchid
	 * @return
	 */
	public ManualVO find(int researchid) {
		ManualResearchDO manualdo = manualResearchDao.findById(researchid);
		ManualVO vo = Convertor.doToManualVO(manualdo);
		return vo;
	}
	/**
	 * 删除调研人人工调研记录
	 * @return
	 */
	@TriggersRemove(cacheName={"MANAUL_DYR_CACHE","MANAUL_SPR_CACHE","MANAUL_JSR_CACHE","MANAUL_SEARCH_CACHE"}, removeAll=true)
	public String deleteDyxx(int researchid) {
		manualResearchDao.delete(researchid);
		return null;
	}
	/**
	 * 获取调研人人工调研记录
	 * @return
	 */
	@Cacheable(cacheName = "MANAUL_DYR_CACHE")
	public List<ManualResearchDO> listByDyr(String name,String fydm,String ksrq, String jsrq) {
		List<ManualResearchDO> list = manualResearchDao.findByDyr(name, fydm, ksrq, jsrq);
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 获取审批人审批记录
	 * @return
	 */
	@Cacheable(cacheName = "MANAUL_SPR_CACHE")
	public List<ManualResearchDO> listBySpr(String name,String fydm,String ksrq, String jsrq) {
		List<ManualResearchDO> list = manualResearchDao.findBySpr(name, fydm, ksrq, jsrq);
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 获取计算人调研记录
	 * @return
	 */
	@Cacheable(cacheName = "MANAUL_JSR_CACHE")
	public List<ManualResearchDO> listByJsr(String name,String fydm,String ksrq,String jsrq) {
		List<ManualResearchDO> list = manualResearchDao.findByJsr(name, fydm, ksrq, jsrq);
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 查询
	 */
	@Cacheable(cacheName = "MANAUL_SEARCH_CACHE")
	public List<ManualResearchDO> search(ManualSearchConditionVO condition) {
		String hql = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO ";
		String con = "where dyrq >= '"+condition.getKsrq()+"' and dyrq <= '"+condition.getJsrq()+" 23:59:59' and dyzt<>'"+ManualDyztEnum.YSC.getDyzt()+"'";
		if(StringUtil.isNotBlank(condition.getDyzt())){
			con += " and dyzt='"+condition.getDyzt()+"'";
		}
		if(StringUtil.isNotBlank(condition.getDyfy())){
			con += " and fyfw='"+condition.getDyfy()+"'";
		}
		if(StringUtil.isNotBlank(condition.getAjlx())){
			con += " and ajlx like '%"+condition.getAjlx()+"%'";
		}
		if(StringUtil.isNotBlank(condition.getDyr())){
			con += " and dyr like '%"+condition.getDyr()+"%'";
		}
		if(StringUtil.isNotBlank(condition.getDyrfydm())){
			con += " and dyrfydm='"+condition.getDyrfydm()+"'";
		}
		if(StringUtil.isNotBlank(condition.getSpr())){
			con += " and spr like '%"+condition.getSpr()+"%'";
		}
		if(StringUtil.isNotBlank(condition.getSprfydm())){
			con += " and sprfydm='"+condition.getSprfydm()+"'";
		}
		if(StringUtil.isNotBlank(condition.getJsr())){
			con += " and jsr like '%"+condition.getJsr()+"%'";
		}
		hql = hql + con + " order by dyrq desc";
		List<ManualResearchDO> list = manualResearchDao.findByHql(hql);
		return list==null?new ArrayList<ManualResearchDO>():list;
	}
	/**
	 * 获取附件下载
	 * @return
	 */
	public Object[] downloadFj(int researchid) {
		return manualResearchDao.findFjById(researchid);
	}
	/**
	 * 获取调研结果下载
	 * @return
	 */
	public Object[] downloadDyjg(int researchid) {
		return manualResearchDao.findDyjgById(researchid);
	}
	/**
	 * 获取调研代码下载
	 * @return
	 */
	public Object[] downloadDydm(int researchid) {
		return manualResearchDao.findDydmById(researchid);
	}
	
}
