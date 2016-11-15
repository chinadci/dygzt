package software.dygzt.data.research.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchXXBDO;
@Repository
public class ResearchXXBDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchXXBDao.class);
	protected void initDao() {
	}
	
	public ResearchXXBDO findById(String id) {
		try {
			ResearchXXBDO instance = (ResearchXXBDO) getHibernateTemplate().get(
					"software.dygzt.data.research.dataobject.ResearchXXBDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<ResearchXXBDO> findAll() {
		try {
			String queryString = "from ResearchXXBDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
