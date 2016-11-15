package software.dygzt.data.fy.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.dmb.dataobject.DmbDO;
@Repository
public class FyDmbDao extends HibernateDaoSupport {
	
	private static final Logger log = LoggerFactory.getLogger(FyDmbDao.class);

	/**
	 * 得到天津法院代码列表
	 * @return List<DmbDO>
	 */
	public List<DmbDO> getTjFydmb() {
		// TODO Auto-generated method stub
		String hql = "from DmbDO where lbbh=? and (dmbh like ? or dmbh like ? or dmbh like ?)";
		@SuppressWarnings("unchecked")
		List<DmbDO> dmb = getHibernateTemplate().find(hql, "FBZ0001-97", "12%",
				"960200%", "920103%");
		if (dmb == null) {
			dmb = new ArrayList<DmbDO>();
		}
		if (log.isInfoEnabled()) {
			log.info("getDmInTj by sql: " + hql);
		}
		return dmb;
	}

}
