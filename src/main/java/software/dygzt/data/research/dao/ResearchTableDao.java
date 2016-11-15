package software.dygzt.data.research.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchTableDO;
@Repository
public class ResearchTableDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchTableDao.class);
	protected void initDao() {
	}
	/**
	 * 保存
	 * @param transientInstance
	 * @return
	 */
	public boolean save(ResearchTableDO transientInstance) {
		log.debug("saving ResearchTableDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return true;
	}
	/**
	 * 最大id
	 * @return
	 */
	public long getMaxId() {
		String hql = "select max(id) from ResearchTableDO";
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		int maxbh = 0;
		if (query.uniqueResult() != null){
			maxbh = (Integer) query.uniqueResult();
		}
		// 释放数据库连接！！！
		this.releaseSession(s);
		return maxbh;
	}
	/**
	 * 通过id找
	 * @param id
	 * @return
	 */
	public ResearchTableDO findById(Integer id) {
		try {
			ResearchTableDO instance = (ResearchTableDO) getHibernateTemplate().get(
					"software.dygzt.data.research.dataobject.ResearchTableDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 根据条件找
	 * @return
	 */
	public ResearchTableDO findByDytj(String dytj) {
		String queryString = "from ResearchTableDO as model where model.dytj like '"+dytj+"'";
		try {
			List<ResearchTableDO> tables = getHibernateTemplate().find(queryString);
			return tables.isEmpty() ? null : tables.get(0);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
