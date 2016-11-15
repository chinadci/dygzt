package software.dygzt.data.ay.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.ay.dataobject.AydmbDO;
import software.dygzt.data.ay.dataobject.JzrhAydmbDO;

/**
 * A data access object (DAO) providing persistence and search support for
 * AydmbDO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see software.tjspxt.data.dataobject.AydmbDO
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AydmbDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AydmbDao.class);
	
	protected void initDao() {
		// do nothing
	}
	
	public List<AydmbDO> getAyDmListByAylb(int aylb) {
		String hql = "from AydmbDO where aylb = " + aylb + " and dqbs='1'";

		if(log.isInfoEnabled()){
			log.info("getAyDmListByAylb by sql: "+hql);
		}
		return getHibernateTemplate().find(hql);
	}
	/**
	 * 根据条件查
	 * @param condition
	 * @return
	 */
	public List<AydmbDO> getAyDmListByCondition(String condition) {
		String hql = "from AydmbDO where " + condition;

		if(log.isInfoEnabled()){
			log.info("getAyDmList by sql: "+hql);
		}
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据条件查(集中融合库)
	 * @param condition
	 * @return
	 */
	public List<JzrhAydmbDO> getJzrhAyDmListByCondition(String condition) {
		String hql = "from JzrhAydmbDO where " + condition;
		if(log.isInfoEnabled()){
			log.info("getAyDmList by sql: "+hql);
		}
		return getHibernateTemplate().find(hql);
	}
	
	public List<AydmbDO> getAyDmList() {
		String hql = "from AydmbDO where dqbs='1'";

		if(log.isInfoEnabled()){
			log.info("getAyDmList by sql: "+hql);
		}
		return getHibernateTemplate().find(hql);
	}

	public List<AydmbDO> getAyDmListByBbh(String bbh) {
		String hql = "from AydmbDO where bbh = '" + bbh + "' and dqbs='1'";

		if(log.isInfoEnabled()){
			log.info("getAyDmListByBbh by sql: "+hql);
		}
		return getHibernateTemplate().find(hql);
	}
}