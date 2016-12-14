package software.dygzt.service.user.impl;

import com.googlecode.ehcache.annotations.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.dygzt.data.user.dao.DyXtyhDao;
import software.dygzt.data.user.dao.XtyhDao;
import software.dygzt.data.user.dataobject.DyXtyhDO;
import software.dygzt.data.user.dataobject.XtglYhbDO;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.service.bm.BmbService;
import software.dygzt.service.bm.model.BmModel;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.ContextHolder;
import software.dygzt.service.user.XtyhService;
import software.dygzt.service.user.model.*;
import software.dygzt.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

@Service
public class XtyhServiceImpl implements XtyhService{

	@Autowired
	private XtyhDao xtyhDao;
	@Autowired
	private DyXtyhDao dyXtyhDao;
	@Autowired
	private BmbService bmbServiceImpl;
	/**
	 * 登陆
	 */
	public UserCheckResult getXtyh(String fydm, String yhm, String password) {
		UserCheckResult result = new UserCheckResult();
		result.setSuccess(false);
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(fydm,yhm);
		/*系统用户表中有该用户：如果是计算人或者管理人权限且密码一致，成功。如果没有这两个权限，则跳转到该用户所在的库，取得系统管理用户表中的用户数据，对比密码，密码一致，则登录成功
		* 系统用户表中无该用户：直接登录失败，显示无该用户名
		* 这是之前和 PUB_XTGL_YHB 共同进行验证的逻辑，现在调研工作台已经有了独立的用户系统，所以不需要再访问集中库的 PUB_XTGL_YHB 了
		* */

		if(xtyh!=null){
			/*在 dyxtyhb 查询到的对象用户有计算人或者管理人的权限，且密码一致，则登录成功，直接跳转
			* 否则：就是其他权限，
			* */
			if(xtyh.getQx().contains("计算人")||xtyh.getQx().contains("管理员")){
				if(xtyh.getPassword().equals(password)){
					UserContextModel user = new UserContextModel();
					user.setFydm(fydm);
					user.setYhdm(yhm);
					user.setYhmc(xtyh.getName());
					user.setYhqx(xtyh.getQx());
					user.setPassword(xtyh.getPassword());
					user.setPhone(xtyh.getPhone());
					user.setLxfs(xtyh.getLxfs());
					result.setSuccess(true);
					result.setXtyh(user);
				}else{
					result.setResultCode(ResultCodeEnum.ILLEGAL_PASSWORD);
				}
			}else{
				//String curDb = CustomerContextHolder.getCustomerType();
				//DataSourceRouter.routerTo(fydm);//切换相应用户所属的数据库
				//如果dyxtyh中有这个用户，并且不是管理员权限，那么就到PUB_XTGL_YHB中去查找用户的详细信息
                if (xtyh.getPassword().equals(password)) {
                    UserContextModel user = new UserContextModel();
                    user.setFydm(fydm);
                    user.setYhdm(yhm);
                    user.setYhmc(xtyh.getName());
                    user.setYhqx(xtyh.getQx());
                    user.setPhone(xtyh.getPhone());
                    user.setLxfs(xtyh.getLxfs());
                    result.setSuccess(true);
                    result.setXtyh(user); //白色初始化好的用户返回
                    return result;
                }else {
                    result.setResultCode(ResultCodeEnum.ILLEGAL_PASSWORD);
                }
				//DataSourceRouter.routerTo(curDb);
			}
		}else{
			result.setResultCode(ResultCodeEnum.ILLEGAL_USERNAME);
		}
		return result;
	}
//	/**
//	 * 得到法院用户
//	 */
//	public DyXtyhVO getXtyh(String fydm, String yhm) {
//		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
//		DyXtyhDO xtyh = dyXtyhDao.getXtyh(user.getFydm(),user.getYhdm());
//		return Convertor.do2vo(xtyh);
//	}

	/**
	 * 得到调研工作台用户
	 */
	public DyXtyhVO getXtyh(String fydm, String yhm) {
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(fydm,yhm);
		return Convertor.do2vo(xtyh);
	}

	/**
	 * 获取 PUB_XTGL_YHB 中的用户
	 * */
	public XtglyhModel getYhByYhm(String yhm) {
		// TODO Auto-generated method stub
		List<XtglYhbDO> doList  = xtyhDao.findByYhmc(yhm);//xtyhDao 操作的是 PUB_XTGL_YHB
		if(doList!=null && doList.size()>0){
			XtglYhbDO xtglYhbDO = doList.get(0);
			return Convertor.do2model(xtglYhbDO); //DO 转为简单一点的 model
		}else{
			return null;
		}
	}



	/**
	 * 增加用户
	 * @param vo
	 * @return
	 */
	public String addDyXtyh(DyXtyhVO vo) {
		String qx = vo.getQx();
		if(qx.contains("计算人")||qx.contains("管理员")){
			vo.setFydm("120000 200");
		}
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(vo.getFydm(),vo.getYhm());
		DyXtyhDO xtyh2 = dyXtyhDao.getXtyhByName(vo.getFydm(),vo.getName());
		if(xtyh!=null||xtyh2!=null){
			return "此用户已存在";
		}
		//查看对应法综表
		String curDb = CustomerContextHolder.getCustomerType();
		DataSourceRouter.routerTo(vo.getFydm());
		List<XtglYhbDO> xtyhs = xtyhDao.findByYhdm(vo.getYhm());
		List<XtglYhbDO> xtyhs2 = xtyhDao.findByYhmc(vo.getName());
		DataSourceRouter.routerTo(curDb);
		if(qx.contains("计算人")||qx.contains("管理员")){
			if(xtyhs.size()>0){
				return "已存在对应法院用户名";
			}
			if(xtyhs2.size()>0){
				return "已存在对应法院用户姓名";
			}
			vo.setPassword("123456");
		}else{
			if(xtyhs.size()==0||xtyhs2.size()==0){
				return "不存在对应法院用户";
			}
			//拷贝联系方式和cocall
			XtglYhbDO xtyhDO = xtyhs.get(0);
			if(StringUtil.isNotBlank(xtyhDO.getPhone())&&StringUtil.isBlank(vo.getPhone())){
				vo.setPhone(xtyhDO.getPhone());
			}
			if(StringUtil.isNotBlank(xtyhDO.getCocall())&&StringUtil.isBlank(vo.getLxfs())){
				vo.setLxfs(xtyhDO.getCocall());
			}
		}
		DyXtyhDO dy = Convertor.vo2do(vo);
		dyXtyhDao.save(dy);
		return null;
	}
	/**
	 * 修改人员
	 */
	public String changeDyXtyh(DyXtyhVO vo) {
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(vo.getFydm(),vo.getYhm());
		if(xtyh==null){
			return "无此用户！";
		}
		xtyh.setQx(vo.getQx());
		xtyh.setPhone(vo.getPhone());
		xtyh.setLxfs(vo.getLxfs());
		dyXtyhDao.save(xtyh);
		return null;
	}
	/**
	 * 删除人员
	 */
	public String deleteDyXtyh(String fydm, String yhm) {
		dyXtyhDao.delete(fydm, yhm);
		return null;
	}
	/**
	 * 得到不同权限的人员列表
	 */
	public List<DyXtyhVO> getListByQx(List<String> qxList) {
		List<DyXtyhDO> dos = dyXtyhDao.findByQx(qxList);
		return Convertor.dos2dyXtyhVos(dos);
	}
	/**
	 * 修改联系方式
	 */
	public String changeLxfs(String phone, String lxfs) {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(user.getFydm(),user.getYhdm());
		if(xtyh==null){
			return "无此用户！";
		}
		xtyh.setPhone(phone);
		xtyh.setLxfs(lxfs);
		dyXtyhDao.save(xtyh);
		return null;
	}
	/**
	 * 修改密码
	 */
	public String changePassword(String oldPass, String newPass) {
		UserContextModel user = (UserContextModel) ContextHolder.getUserContext();
		DyXtyhDO xtyh = dyXtyhDao.getXtyh(user.getFydm(),user.getYhdm());
		if(xtyh==null){
			return "无此用户！";
		}
		if(!xtyh.getPassword().equals(oldPass)){
			return "密码错误！";
		}
		xtyh.setPassword(newPass);
		dyXtyhDao.save(xtyh);
		return null;
	}

	@Cacheable(cacheName = "YH_CACHE")
	public List<BmVO> getAllXtyh(String fydm) {
		List<BmVO> bmList = new ArrayList<BmVO>();
		BmVO currentbm = null;
		List<XtglYhbDO> xtyhdos=xtyhDao.findAllByBm();
		for(XtglYhbDO xtyh:xtyhdos){
			if(currentbm!=null&&xtyh.getYhbm().equals(currentbm.getBmbh())){
				XtyhVO xtyhvo = new XtyhVO(xtyh.getYhdm(),xtyh.getYhmc());
				currentbm.getXtyhs().add(xtyhvo);
			}else{
				BmModel bm = bmbServiceImpl.getBm(xtyh.getYhbm());
				if(bm!=null){
					if(currentbm!=null){
						bmList.add(currentbm);
					}
					currentbm = new BmVO();
					currentbm.setBmbh(xtyh.getYhbm());
					currentbm.setBmmc(bm.getBmmc());
					List<XtyhVO> xtyhs = new ArrayList<XtyhVO>();
					XtyhVO xtyhvo = new XtyhVO(xtyh.getYhdm(),xtyh.getYhmc());
					xtyhs.add(xtyhvo);
					currentbm.setXtyhs(xtyhs);
				}
			}
		}
		if(currentbm!=null){
			bmList.add(currentbm);
		}
		return bmList;
	}

}
