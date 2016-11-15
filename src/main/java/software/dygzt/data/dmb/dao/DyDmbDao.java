package software.dygzt.data.dmb.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.dmb.dataobject.DyDmbDO;
/**
 * A data access object (DAO) providing persistence and search support for DyDmbDO
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see software.dygzt.data.dmb.dataobject.DyDmbDO
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DyDmbDao extends HibernateDaoSupport {
	private static final Logger log = Logger.getLogger(DyDmbDao.class);
	
	/**
	 * 根据类别编号获得符合要求的代码列表
	 * 
	 * @param lbbh
	 *            类别编号
	 * @param fybh
	 *            法院编号
	 * @return 代码列表
	 */
	public List<DyDmbDO> getDmListByLbbh(String lbbh) {
		String hql = "";
		hql = "from DyDmbDO where lbbh = '" + lbbh + "' order by dmbh";

		if (log.isInfoEnabled()) {
			log.info("getDmListByLbbh by sql: " + hql);
		}
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据ID得到DMB对象
	 * 
	 * @param lbbh
	 *            类别编号
	 * @param dmbh
	 *            代码编号
	 * @return 查询得到的代码表对象
	 */
	public DyDmbDO findById(String lbbh, String dmbh) {
		log.debug("finding dydmb inofmation from DyDmbDO instances");
		try {
			String queryString = "from DyDmbDO where lbbh='" + lbbh
					+ "' and dmbh='" + dmbh + "'";
			List<DyDmbDO> tempData = getHibernateTemplate().find(queryString);
			return tempData.isEmpty() ? null : tempData.get(0);
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

}