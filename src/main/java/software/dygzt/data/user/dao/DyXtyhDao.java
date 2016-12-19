package software.dygzt.data.user.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.user.dataobject.DyXtyhDO;
@Repository("dyXtyhDao")
public class DyXtyhDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(DyXtyhDao.class);
	
	public boolean save(DyXtyhDO  transientInstance) {
		log.debug("saving DyXtyhDO  instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return true;
	}
	
	public boolean delete(String fydm,String yhm) {
		String hql = "DELETE FROM DyXtyhDO WHERE fydm='" + fydm+"' and yhm='"+yhm+"'";
		Session s = this.getSession();
		Query query = s.createQuery(hql);
		if (log.isInfoEnabled()) {
			log.info("delete DyXtyhDO");
		}
		query.executeUpdate();
		//释放数据库连接！！！
		this.releaseSession(s);
		return true;
	}
	
	public List<DyXtyhDO> findByQx(List<String> qxList){
		if(qxList!=null&&qxList.size()>0){
			/*模糊查询，这里是查出符合 qxList 中任一值得用户，的权限是通过字段组合保存在数据库中的一个字段中的*/
			String hql = "from DyXtyhDO where qx like '%" + qxList.get(0) + "%'";
			for(int i=1;i<qxList.size();i++){
				String qx = qxList.get(i);
				hql += " or qx like '%" + qx + "%'";
			}
			return getHibernateTemplate().find(hql);
		}else{
			return null;
		}
		
	}
	
	/**
	 * 根据法院代码和用户编号得到系统用户信息（集中库）
	 * 
	 * @param fydm
	 *            法院编号
	 * @param yhm
	 *            用户名
	 * @return XtyhDO
	 */
	public DyXtyhDO getXtyh(String fydm, String yhm){
		String hql = "from DyXtyhDO where fydm = '" + fydm + "' and yhm ='" + yhm + "'";
		List<DyXtyhDO> xtyh = getHibernateTemplate().find(hql);
		return xtyh.isEmpty() ? null : xtyh.get(0);
	}


	/**
	 * 根据法院代码和用户编号得到系统用户信息（集中库）
	 *
	 * @param fydm
	 *            法院代码
	 * @return XtyhDO
	 */
	public DyXtyhDO getXtyh(String fydm){
		String hql = "from DyXtyhDO where fydm = '" + fydm + "'";
		List<DyXtyhDO> xtyh = getHibernateTemplate().find(hql);
		return xtyh.isEmpty() ? null : xtyh.get(0);
	}


	/**
	 * 根据法院代码和用户姓名得到系统用户信息（集中库）
	 * 
	 * @param fydm
	 *            法院编号
	 * @param name
	 *            用户名
	 * @return XtyhDO
	 */
	public DyXtyhDO getXtyhByName(String fydm, String name){
		String hql = "from DyXtyhDO where fydm = '" + fydm + "' and name ='" + name + "'";
		List<DyXtyhDO> xtyh = getHibernateTemplate().find(hql);
		return xtyh.isEmpty() ? null : xtyh.get(0);
	}
	
}
