package software.dygzt.data.research.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.research.dataobject.ResearchSavedInfoDO;
@Repository
public class ResearchSavedInfoDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(ResearchSavedInfoDao.class);
	protected void initDao() {
	}
	/**
	 * 保存
	 * @param transientInstance
	 * @return
	 */
	public boolean save(ResearchSavedInfoDO transientInstance) {
		log.debug("saving ResearchSavedInfoDO instance");
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
	 * 删除
	 * @param tableid
	 * @param dyr
	 * @param dyrfydm
	 * @return
	 */
	public boolean delete(int tableid,String dyr,String dyrfydm) {
		String hql = "DELETE FROM ResearchSavedInfoDO WHERE tableid=" + tableid+" and dyr='"+dyr+"' and dyrfydm='"+dyrfydm+"'";
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("delete ResearchSavedInfoDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	/**
	 * 通过id找
	 * @param tableid
	 * @param dyr
	 * @param dyrfydm
	 * @return
	 */
	public ResearchSavedInfoDO findById(int tableid,String dyr,String dyrfydm){
		String hql = "FROM ResearchSavedInfoDO WHERE tableid=" + tableid+" and dyr='"+dyr+"' and dyrfydm='"+dyrfydm+"'";
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		ResearchSavedInfoDO info = null;
		if (query.uniqueResult()!=null) {
			info = (ResearchSavedInfoDO)query.uniqueResult();
		}
		//释放数据库连接！！！
		this.releaseSession(s);
		return info;
	}
	/**
	 * 调研人历史
	 * @param dyr
	 * @param dyrfydm
	 * @param ksrq
	 * @param jsrq
	 * @return
	 */
	public List<Object[]> findByDyr(String dyr,String dyrfydm,String ksrq,String jsrq,int type) {
		String sql = "select model.tableid, model.dyrq, ta.bbmc, model.bgms, ta.ksrq, ta.jsrq, ta.ajzt from DY_AUTO_SAVEDINFO as model left join DY_AUTO_TABLE as ta on ta.id = model.tableid where model.type = "+type+" and model.dyr = '"+dyr+"' and model.dyrfydm = '"+dyrfydm+"' and model.dyrq >= '"+ksrq+"' and model.dyrq <= '"+jsrq+" 23:59:59' order by model.dyrq desc";
		Session s = this.getSession();
		SQLQuery query = s.createSQLQuery(sql);
		List<Object[]> result = query.list();
		//释放数据库连接！！！
		this.releaseSession(s);
		return result;
	}
	/**
	 * 通过sql查询
	 * @param sql
	 * @return
	 */
	public List<Object[]> findBySql(String sql) {
		Session s = this.getSession();
		SQLQuery query = s.createSQLQuery(sql);
		List<Object[]> result = query.list();
		//释放数据库连接！！！
		this.releaseSession(s);
		return result;
	}
}
