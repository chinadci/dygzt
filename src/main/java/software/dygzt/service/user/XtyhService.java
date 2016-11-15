package software.dygzt.service.user;

import java.util.List;

import software.dygzt.service.user.model.BmVO;
import software.dygzt.service.user.model.DyXtyhVO;
import software.dygzt.service.user.model.UserCheckResult;

public interface XtyhService {
	
	public UserCheckResult getXtyh(String fydm, String yhm, String password);
	/**
	 * 根据法院和用户名得到用户
	 * @param fydm
	 * @param yhm
	 * @return
	 */
	public DyXtyhVO getXtyh(String fydm, String yhm);

	/**
	 * 增加用户
	 * @param vo
	 * @return
	 */
	public String addDyXtyh(DyXtyhVO vo);
	/**
	 * 删除用户
	 * @param fydm
	 * @param yhm
	 * @return
	 */
	public String deleteDyXtyh(String fydm,String yhm);
	/**
	 * 修改用户
	 * @param vo
	 * @return
	 */
	public String changeDyXtyh(DyXtyhVO vo);
	/**
	 * 根据权限获取用户表
	 * @param qx
	 * @return
	 */
	public List<DyXtyhVO> getListByQx(List<String> qxList);
	/**
	 * 更改联系方式
	 * @param phone
	 * @param lxfs
	 * @return
	 */
	public String changeLxfs(String phone,String lxfs);
	/**
	 * 修改密码
	 * @param 
	 * @param 
	 * @return
	 */
	public String changePassword(String oldPass,String newPass);
	/**
	 * 得到所有的系统用户
	 * @param fydm
	 * @return
	 */
	public List<BmVO> getAllXtyh(String fydm);
}
