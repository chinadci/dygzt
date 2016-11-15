package software.dygzt.data.research.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchCellDO;
@Repository
public class ResearchCellDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchCellDao.class);
	protected void initDao() {
	}
	
	public boolean save(ResearchCellDO transientInstance) {
		log.debug("saving ResearchCellDO instance");
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
	 * 批量保存
	 * @param values
	 * @return
	 */
	public boolean saveBatch(List<List<ResearchCellDO>> values) {
		log.debug("saving ResearchCellDO instances");
		try {
			for(List<ResearchCellDO> rowdo:values){
				getHibernateTemplate().saveOrUpdateAll(rowdo);
			}
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return true;
	}
	
	public boolean delete(int tableid) {
		String hql = "DELETE FROM ResearchCellDO WHERE tableid=" + tableid;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("delete ResearchTableCellDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	/**
	 * 通过tableid找
	 * @param tableid
	 * @return
	 */
	public List<ResearchCellDO> findByTableId(Integer tableid) {
		try {
			String queryString = "from ResearchCellDO as model where model.tableid="+tableid+" order by model.rowid asc,model.cellid asc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
