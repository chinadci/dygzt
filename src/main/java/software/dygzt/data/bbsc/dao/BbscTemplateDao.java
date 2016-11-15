package software.dygzt.data.bbsc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.bbsc.dataobject.BbscTemplateDO;

@Repository
public class BbscTemplateDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(BbscTemplateDao.class);
	protected void initDao() {
	}
	
	/**
	 * 最大id(ok)
	 * @return
	 */
	public long getMaxId() {
		String hql = "select max(id) from BbscTemplateDO";
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
	 * 保存(ok)
	 * @param transientInstance
	 * @return
	 */
	public boolean save(BbscTemplateDO transientInstance) {
		log.debug("saving BbscTemplateDO instance");
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
	 * 修改名字
	 * @param tableid
	 * @param dyr
	 * @param dyrfydm
	 * @return
	 */
	public boolean updateName(int id,String name) {
		String hql = "UPDATE BbscTemplateDO SET name='"+name+"' WHERE id=" + id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("update BbscTemplateDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	
	/**
	 * 删除
	 * @param tableid
	 * @param dyr
	 * @param dyrfydm
	 * @return
	 */
	public boolean delete(int id) {
		String hql = "DELETE FROM BbscTemplateDO WHERE id=" + id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("delete BbscTemplateDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	/**
	 * 通过id找(ok)
	 * @param tableid
	 * @param dyr
	 * @param dyrfydm
	 * @return
	 */
	public BbscTemplateDO findById(int id){
		String hql = "FROM BbscTemplateDO WHERE id=" + id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		BbscTemplateDO info = null;
		if (query.uniqueResult()!=null) {
			info = (BbscTemplateDO)query.uniqueResult();
		}
		//释放数据库连接！！！
		this.releaseSession(s);
		return info;
	}
	/**
	 * 调研人模板(ok)
	 * @param username
	 * @param userfydm
	 * @return
	 */
	public List<BbscTemplateDO> findByDyr(String username,String userfydm) {
		try {
			String queryString = "from BbscTemplateDO as model where model.username = '"+username+"' and model.userfydm = '"+userfydm+"' order by model.cjsj desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 所有模板(ok)
	 * @return
	 */
	public List<BbscTemplateDO> findAll() {
		try {
			String queryString = "from BbscTemplateDO as model order by model.cjsj desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 通过condition查询(ok)
	 * @param sql
	 * @return
	 */
	public List<BbscTemplateDO> findByCondition(String yjCondition,String colCondition,String bblx,String fyfw,String bbbh) {
		try {
			String queryString = "from BbscTemplateDO as model where model.yjCondition like '"+yjCondition+"' and model.colCondition like '"+colCondition+
					"' and model.bblx='"+bblx+"' and model.fyfw='"+fyfw+"' and model.bbbh='"+bbbh+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
