package software.dygzt.data.bm.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.bm.dataobject.JzrhBmbDO;

@Repository
public class JzrhBmbDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(JzrhBmbDao.class);
	protected void initDao() {
	}
	
	public List<JzrhBmbDO> findAll() {
		try {
			String queryString = "from JzrhBmbDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
