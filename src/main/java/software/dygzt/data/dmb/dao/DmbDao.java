package software.dygzt.data.dmb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.LockMode;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.dmb.dataobject.DmbDO;
import software.dygzt.data.dmb.dataobject.DmbDOId;
import software.dygzt.util.LogUtil;
import software.dygzt.util.StringUtil;

/**
 * A data access object (DAO) providing persistence and search support for DmbDO
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see software.dygzt.data.dmb.dataobject.DmbDO
 * @author MyEclipse Persistence Tools
 */
@Repository
public class DmbDao extends HibernateDaoSupport {
	private static final Logger log = Logger.getLogger(DmbDao.class);

	// property constants
	public static final String DMMS = "dmms";
	public static final String XGDM = "xgdm";
	public static final String BZ = "bz";
	public static final String MODFLAG = "modflag";
	public static final String TRANSFLAG = "transflag";
	public static final String XSSX = "xssx";
	public static final String DQBS = "dqbs";
	public static final String FYBH = "fybh";
	public static final String FYSX = "fysx";
	public static final String FYLBDM = "fylbdm";
	public static final String XZQDMBH = "xzqdmbh";
	public static final String DMBBH = "dmbbh";
	public static final String SPBM = "spbm";
	public static final String BMZT = "bmzt";
	public static final String XGDM2 = "xgdm2";

	protected void initDao() {
		// do nothing
	}

	public void save(DmbDO transientInstance) {
		log.debug("saving DmbDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(DmbDO persistentInstance) {
		log.debug("deleting DmbDO instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public DmbDO findById(DmbDOId id) {
		log.debug("getting DmbDO instance with id: " + id);
		try {
			DmbDO instance = (DmbDO) getHibernateTemplate().get(
					"software.dygzt.data.dmb.dataobject.DmbDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<DmbDO> findByExample(DmbDO instance) {
		log.debug("finding DmbDO instance by example");
		try {
			List<DmbDO> results = (List<DmbDO>) getHibernateTemplate()
					.findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding DmbDO instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from DmbDO as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<DmbDO> findByDmms(Object dmms) {
		return findByProperty(DMMS, dmms);
	}

	public List<DmbDO> findByXgdm(Object xgdm) {
		return findByProperty(XGDM, xgdm);
	}

	public List<DmbDO> findByBz(Object bz) {
		return findByProperty(BZ, bz);
	}

	public List<DmbDO> findByModflag(Object modflag) {
		return findByProperty(MODFLAG, modflag);
	}

	public List<DmbDO> findByTransflag(Object transflag) {
		return findByProperty(TRANSFLAG, transflag);
	}

	public List<DmbDO> findByXssx(Object xssx) {
		return findByProperty(XSSX, xssx);
	}

	public List<DmbDO> findByDqbs(Object dqbs) {
		return findByProperty(DQBS, dqbs);
	}

	public List<DmbDO> findByFybh(Object fybh) {
		return findByProperty(FYBH, fybh);
	}

	public List<DmbDO> findByFysx(Object fysx) {
		return findByProperty(FYSX, fysx);
	}

	public List<DmbDO> findByFylbdm(Object fylbdm) {
		return findByProperty(FYLBDM, fylbdm);
	}

	public List<DmbDO> findByXzqdmbh(Object xzqdmbh) {
		return findByProperty(XZQDMBH, xzqdmbh);
	}

	public List<DmbDO> findByDmbbh(Object dmbbh) {
		return findByProperty(DMBBH, dmbbh);
	}

	public List<DmbDO> findBySpbm(Object spbm) {
		return findByProperty(SPBM, spbm);
	}

	public List<DmbDO> findByBmzt(Object bmzt) {
		return findByProperty(BMZT, bmzt);
	}

	public List findAll() {
		log.debug("finding all DmbDO instances");
		try {
			String queryString = "from DmbDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public DmbDO merge(DmbDO detachedInstance) {
		log.debug("merging DmbDO instance");
		try {
			DmbDO result = (DmbDO) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(DmbDO instance) {
		log.debug("attaching dirty DmbDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(DmbDO instance) {
		log.debug("attaching clean DmbDO instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static DmbDao getFromApplicationContext(ApplicationContext ctx) {
		return (DmbDao) ctx.getBean("DmbDAO");
	}
	
	/**
	 * 代码编号，类别编号，法院编号获得代码列表
	 * 
	 * @param dmbh
	 *            代码编号
	 * @param lbbh
	 *            类别编号
	 * @param fybh
	 *            法院编号
	 * @return
	 */
	public DmbDO getDmByDmbhAndLbbh(String dmbh, String lbbh, Integer fybh) {
		List<DmbDO> dmb = new ArrayList<DmbDO>();
		try {
			String hql = "";
			if (fybh == null) {
				hql = "from DmbDO where dmbh = '" + dmbh + "' and lbbh ='"
						+ lbbh + "'";
			} else {
				hql = "from DmbDO where dmbh = '" + dmbh + "' and lbbh ='"
						+ lbbh + "' and fybh = " + fybh;
			}

			dmb = getHibernateTemplate().find(hql);
			if (log.isInfoEnabled()) {
				log.info("getDmByDmbhAndLbbh by sql: " + hql);
			}
		} catch (Exception e) {
			LogUtil.logging("代码表搜索lbbh：" + lbbh + " dmbh:" + dmbh
					+ " 无法使用hql,自动转为sql模式。", log, LogUtil.type_warn);
			String sql = "SELECT LBBH, DMBH, DMMS, XGDM, BZ FROM PUB_DMB WHERE DMBH = '"
					+ dmbh + "' and LBBH = '" + lbbh + "'";
			ConnectionProvider cp = null;
			Connection connection = null;
			Statement statement = null;
			ResultSet resultSet = null;
			dmb = new ArrayList<DmbDO>();
			try {
				cp = ((SessionFactoryImplementor) this.getSessionFactory())
						.getConnectionProvider();
				connection = cp.getConnection();
				statement = connection.createStatement();
				statement.executeQuery(sql);
				resultSet = statement.executeQuery(sql);

				int num = 0;
				while (resultSet.next()) {
					DmbDO dmbdo = new DmbDO();
					dmbdo.setLbbh(StringUtil.trim(resultSet.getString("LBBH")));
					dmbdo.setDmbh(StringUtil.trim(resultSet.getString("DMBH")));
					dmbdo.setDmms(StringUtil.trim(resultSet.getString("DMMS")));
					dmbdo.setXgdm(StringUtil.trim(resultSet.getString("XGDM")));
					dmbdo.setBz(StringUtil.trim(resultSet.getString("BZ")));
					dmb.add(dmbdo);
					num++;
				}
			} catch (SQLException ex) {
				String errstr = "代码表执行sql语句错误：" + sql;
				LogUtil.logging(errstr, log, LogUtil.type_error, e);
				throw new RuntimeException(errstr);
			} finally {
				try {
					if (resultSet != null)
						resultSet.close();
					if (statement != null)
						statement.close();
					if (cp != null)
						cp.closeConnection(connection);
				} catch (SQLException e1) {
					log.error("关闭数据库连接出错。", e1);
					e1.printStackTrace();
				}

			}
		}

		if (dmb.size() > 1) {
			String errstr = "代码表搜索lbbh：" + lbbh + " dmbh:" + dmbh + " 发现了"
					+ dmb.size() + "条记录，自动返回第一条。";
			LogUtil.logging(errstr, log, LogUtil.type_error);
		}
		return dmb.isEmpty() ? null : dmb.get(0);
	}
	
	/**
	 * 根据类别编号和法院编号获得符合要求的代码列表
	 * 
	 * @param lbbh
	 *            类别编号
	 * @param fybh
	 *            法院编号
	 * @return 代码列表
	 */
	public List<DmbDO> getDmListByLbbh(String lbbh, Integer fybh) {
		String hql = "";
		hql = "from DmbDO where lbbh = '" + lbbh + "' order by dmbh";

		if (log.isInfoEnabled()) {
			log.info("getDmListByLbbh by sql: " + hql);
		}
		return getHibernateTemplate().find(hql);
	}
	
	/**
	 * 根据ID得到DMB对象
	 * 
	 * @param lbbh
	 *            类别编号
	 * @param dmbh
	 *            代码编号
	 * @return 查询得到的代码表对象
	 */
	public DmbDO findById(String lbbh, String dmbh) {
		log.debug("finding all fy inofmation from DmbDO instances");
		try {
			String queryString = "from DmbDO where lbbh='" + lbbh
					+ "' and dmbh='" + dmbh + "'";
			List<DmbDO> tempData = getHibernateTemplate().find(queryString);
			return tempData.isEmpty() ? null : tempData.get(0);
		} catch (RuntimeException re) {
			log.error("find failed", re);
			throw re;
		}
	}

}