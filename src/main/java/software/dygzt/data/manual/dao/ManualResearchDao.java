package software.dygzt.data.manual.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.manual.dataobject.ManualResearchDO;
import software.dygzt.service.manualResearch.model.ManualDyztEnum;

/**
 * A data access object (DAO) providing persistence and search support for
 * ManualResearchDO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see software.dygzt.data.aj.dataobject.ManualResearchDO
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ManualResearchDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(ManualResearchDao.class);

	protected void initDao() {
		// do nothing
	}
	/**
	 * 保存
	 * @param transientInstance
	 * @return
	 */
	public boolean save(ManualResearchDO transientInstance) {
		log.debug("saving ManualResearchDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return true;
	}
	
	public boolean update(String hql) {
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		int re = query.executeUpdate();
		// 释放数据库连接！！！
		this.releaseSession(s);
		return re>0;
	}
	
	/**
	 * 标志位删除
	 * @param researchid
	 * @return
	 */
	public boolean delete(int researchid) {
		String hql = "UPDATE ManualResearchDO SET dyzt='已删除' WHERE id=" + researchid;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("delete ResearchTableDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	/**
	 * 最大id
	 * @return
	 */
	public long getMaxId() {
		String hql = "select max(id) from ManualResearchDO";
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
	 * 通过id寻找
	 * @param id
	 * @return
	 */
	public ManualResearchDO findById(java.lang.Integer id) {
		log.debug("getting ManualResearchDO instance with id: " + id);
		try {
			ManualResearchDO instance = (ManualResearchDO) getHibernateTemplate().get(
					"software.dygzt.data.manual.dataobject.ManualResearchDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 附件
	 * @param id
	 * @return
	 */
	public Object[] findFjById(int id) {
		String hql = "select fjm,fj from ManualResearchDO where id = "+id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		Object[] po = null;
		if (query.uniqueResult() != null){
			po = (Object[]) query.uniqueResult();
		}
		// 释放数据库连接！！！
		this.releaseSession(s);
		return po;
	}
	/**
	 * 调研结果
	 * @param id
	 * @return
	 */
	public Object[] findDyjgById(int id) {
		String hql = "select dyjgm,dyjg from ManualResearchDO where id = "+id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		Object[] po = null;
		if (query.uniqueResult() != null){
			po = (Object[]) query.uniqueResult();
		}
		// 释放数据库连接！！！
		this.releaseSession(s);
		return po;
	}
	/**
	 * 调研代码
	 * @param id
	 * @return
	 */
	public Object[] findDydmById(int id) {
		String hql = "select dydmm,dydm from ManualResearchDO where id = "+id;
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		Object[] po = null;
		if (query.uniqueResult() != null){
			po = (Object[]) query.uniqueResult();
		}
		// 释放数据库连接！！！
		this.releaseSession(s);
		return po;
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding ManualResearchDO instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from ManualResearchDO as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/**
	 * 根据调研状态查询
	 * @param dyzt
	 * @return
	 */
	public List<ManualResearchDO> findByDyzt(String dyzt) {
		try {
			String queryString = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO where dyzt='"
								+dyzt+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 查找要计算的
	 * @param dyzt
	 * @param jsr
	 * @param jsrfydm
	 * @return
	 */
	public List<ManualResearchDO> findToCompute(String dyzt,String jsr,String jsrfydm) {
		try {
			String queryString = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO where dyzt='"
								+dyzt+"' and jsr = '"+jsr+"' and jsrfydm = '"+jsrfydm+"'";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	/**
	 * 根据调研人查找（不含已删除）
	 * @param dyr
	 * @param dyrfydm
	 * @param ksrq
	 * @param jsrq
	 * @return
	 */
	public List<ManualResearchDO> findByDyr(String dyr,String dyrfydm,String ksrq,String jsrq) {
		try {
			String queryString = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO where dyr = '"
					+dyr+"' and dyrfydm = '"+dyrfydm+"' and dyrq >= '"+ksrq
					+"' and dyrq <= '"+jsrq+" 23:59:59'  and dyzt<>'已删除' order by dyrq desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 根据审批人查找（不含已删除）
	 * @param spr
	 * @param sprfydm
	 * @param ksrq
	 * @param jsrq
	 * @return
	 */
	public List<ManualResearchDO> findBySpr(String spr,String sprfydm,String ksrq,String jsrq) {
		try {
			String queryString = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO where spr = '"
					+spr+"' and sprfydm = '"+sprfydm+"' and dyrq >= '"+ksrq+
					"' and dyrq <= '"+jsrq+" 23:59:59'  and dyzt<>'已删除' order by dyrq desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	/**
	 * 根据计算人查找（含已删除）
	 * @param jsr
	 * @param jsrfydm
	 * @param ksrq
	 * @param jsrq
	 * @return
	 */
	public List<ManualResearchDO> findByJsr(String jsr,String jsrfydm,String ksrq,String jsrq) {
		try {
			String queryString = "select new ManualResearchDO(id,dyrq,ksrq,jsrq,dyzt,fyfw,dymd,dyxq,sprthyy) from ManualResearchDO where jsr = '"
					+jsr+"' and jsrfydm = '"+jsrfydm+"' and dyrq >= '"+ksrq+
					"' and dyrq <= '"+jsrq+" 23:59:59' and dyzt in ('"+
					ManualDyztEnum.DYWC.getDyzt()+"','"+ManualDyztEnum.YSC.getDyzt()
					+"')order by dyrq desc";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List<ManualResearchDO> findByHql(String hql) {
		try {
			return getHibernateTemplate().find(hql);
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
}