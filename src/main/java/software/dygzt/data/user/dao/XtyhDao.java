package software.dygzt.data.user.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.user.dataobject.XtglYhbDO;
@Repository("xtyhDao")
public class XtyhDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(XtyhDao.class);
	
	/**
	 * 在个法院数据库中通过yhdm找用户
	 * @param value
	 * @return
	 */
	public List<XtglYhbDO> findByYhdm(String value) {
		try {
			String queryString = "from XtglYhbDO as model where model.yhdm = ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by yhdm failed", re);
			throw re;
		}
	}
	/**
	 * 在个法院数据库中通过yhmc找用户
	 * @param value
	 * @return
	 */
	public List<XtglYhbDO> findByYhmc(String value) {
		try {
			String queryString = "from XtglYhbDO as model where model.yhmc = ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by yhdm failed", re);
			throw re;
		}
	}
	/**
	 * 将所有用户按部门分组
	 * @return
	 */
	public List<XtglYhbDO> findAllByBm() {
		try {
			String queryString = "from XtglYhbDO order by yhbm";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by yhbm failed", re);
			throw re;
		}
	}
	public List<XtglYhbDO> findByHql(String hql) {
		try {
			String queryString = "from XtglYhbDO "+hql;
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find by hql failed", re);
			throw re;
		}
	}
}
