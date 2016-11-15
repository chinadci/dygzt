package software.dygzt.data.research.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchXXZBDO;
@Repository
public class ResearchXXZBDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchXXZBDao.class);
	protected void initDao() {
	}
	
	public ResearchXXZBDO findById(String id) {
		try {
			ResearchXXZBDO instance = (ResearchXXZBDO) getHibernateTemplate().get(
					"software.dygzt.data.research.dataobject.ResearchXXZBDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<ResearchXXZBDO> findAll() {
		try {
			String queryString = "from ResearchXXZBDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
