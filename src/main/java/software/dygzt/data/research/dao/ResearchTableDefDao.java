package software.dygzt.data.research.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchTableDefDO;
@Repository
public class ResearchTableDefDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchTableDefDao.class);
	protected void initDao() {
	}
	
	public ResearchTableDefDO findById(String id) {
		try {
			ResearchTableDefDO instance = (ResearchTableDefDO) getHibernateTemplate().get(
					"software.dygzt.data.research.dataobject.ResearchTableDefDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public List<ResearchTableDefDO> findAll() {
		try {
			String queryString = "from ResearchTableDefDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
