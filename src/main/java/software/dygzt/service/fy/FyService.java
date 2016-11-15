package software.dygzt.service.fy;

import java.util.List;

import software.dygzt.service.dmb.model.DmVO;
import software.dygzt.service.fy.model.FyVO;

public interface FyService {
	/**
	 * 获得天津所有法院
	 * @param fydmbService
	 */
	public FyVO getFyList();
	/**
	 * 获得天津所有法院(含辖区)
	 * @param fydmbService
	 */
	public FyVO getFyListHxq();
	
	/**
	 * 得到天津法院(无层级关系)
	 */
	public List<DmVO> getTjFyList();
	
	/**
	 * 得到天津法院(无层级关系含辖区)
	 * @return
	 */
	public List<DmVO> getTjFyListHxq();
}
