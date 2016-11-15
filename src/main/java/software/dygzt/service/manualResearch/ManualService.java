package software.dygzt.service.manualResearch;

import java.util.List;

import software.dygzt.data.manual.dataobject.ManualResearchDO;
import software.dygzt.service.manualResearch.model.ManualHistoryVO;
import software.dygzt.service.manualResearch.model.ManualModel;
import software.dygzt.service.manualResearch.model.ManualSearchConditionVO;
import software.dygzt.service.manualResearch.model.ManualVO;

public interface ManualService {
	/**
	 * 调研申请
	 * @param model
	 * @return
	 */
	public String manualRequest(ManualModel model);
	/**
	 * 查看待审批列表
	 * @return
	 */
	public List<ManualResearchDO> toApproveList();
	/**
	 * 审批通过
	 * @param researchid
	 * @return
	 */
	public String approve(int researchid);
	/**
	 * 审批不通过
	 * @param researchid
	 * @param reason
	 * @return
	 */
	public String reject(int researchid,String reason);
	/**
	 * 查看待指派列表
	 * @return
	 */
	public List<ManualResearchDO> toDistributeList();
	/**
	 * 指派
	 * @return
	 */
	public String distribute(int researchid,String jsr,String jsrfydm);
	/**
	 * 查看待计算列表
	 * @return
	 */
	public List<ManualResearchDO> toComputeList();
	/**
	 * 调研结果上传
	 * @param researchid
	 * @param reason
	 * @return
	 */
	public String compute(int researchid,String dyjgname,byte[] dyjg,String dydmname,byte[] dydm);
	/**
	 * 获取调研详细信息
	 * @param researchid
	 * @return
	 */
	public ManualVO find(int researchid);
	/**
	 * 获取附件下载
	 * @return
	 */
	public Object[] downloadFj(int researchid);
	/**
	 * 获取调研结果下载
	 * @return
	 */
	public Object[] downloadDyjg(int researchid);
	/**
	 * 获取调研代码下载
	 * @return
	 */
	public Object[] downloadDydm(int researchid);
	/**
	 * 删除调研人人工调研记录
	 * @return
	 */
	public String deleteDyxx(int researchid);
	/**
	 * 获取调研人人工调研记录
	 * @return
	 */
	public List<ManualResearchDO> listByDyr(String name,String fydm,String ksrq,String jsrq);
	/**
	 * 获取审批人审批记录
	 * @return
	 */
	public List<ManualResearchDO> listBySpr(String name,String fydm,String ksrq,String jsrq);
	/**
	 * 获取计算人调研记录
	 * @return
	 */
	public List<ManualResearchDO> listByJsr(String name,String fydm,String ksrq,String jsrq);
	/**
	 * 查询
	 * @return
	 */
	public List<ManualResearchDO> search(ManualSearchConditionVO condition);
	
}
