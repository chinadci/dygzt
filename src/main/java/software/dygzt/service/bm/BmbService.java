package software.dygzt.service.bm;

import java.util.Map;

import software.dygzt.service.bm.model.BmModel;

public interface BmbService {

	/**
	 * 通过部门编号取部门对象（用缓存）
	 * @param bmbh 部门编号
	 * @return 部门model
	 */
	public BmModel getBm(String bmbh);
	/**
	 * 通过部门编号取部门对象（预先取出list）
	 * @param bmbh
	 * @param fydm
	 * @return
	 */
	public BmModel getBm(String bmbh,String fydm);
	/**
	 * 融合库通过部门编号取部门对象（）
	 * @param bmbh
	 * @param fybh
	 * @return
	 */
	public BmModel getBm(String bmbh,int fybh);
	/**
	 * 得到部门表
	 * @return
	 */
	public Map<String, BmModel> getBmList(String fydm);
	/**
	 * 部门表（集中融合库）
	 * @param fybh
	 * @return
	 */
	public Map<String, BmModel> getBmList(int fybh);
}
