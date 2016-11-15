package software.dygzt.data.ajlx.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.ajlx.dataobject.AjlxbDO;
@Repository
public class AjlxbDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(AjlxbDao.class);
	protected void initDao() {
	}
	/**
	 * 通过tableid找
	 * @param tableid
	 * @return
	 */
	public List<AjlxbDO> findAll() {
		try {
			String queryString = "from AjlxbDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
