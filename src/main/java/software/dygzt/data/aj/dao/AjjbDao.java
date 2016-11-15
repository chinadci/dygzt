package software.dygzt.data.aj.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import software.dygzt.data.aj.dataobject.AjjbDO;
/**
 * A data access object (DAO) providing persistence and search support for
 * AjjbDO entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see software.dygzt.data.aj.dataobject.AjjbDO
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AjjbDao extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(AjjbDao.class);
	// property constants
	public static final String AH = "ah";
	public static final String AJMC = "ajmc";
	public static final String AJXZ = "ajxz";
	public static final String AJLY = "ajly";
	public static final String SPCX = "spcx";
	public static final String SPCXDZ = "spcxdz";
	public static final String SYCX = "sycx";
	public static final String SFYS = "sfys";
	public static final String SFZDAJ = "sfzdaj";
	public static final String SFJBAJ = "sfjbaj";
	public static final String SFFHCS = "sffhcs";
	public static final String SFWDYJ = "sfwdyj";
	public static final String SX = "sx";
	public static final String FJSX = "fjsx";
	public static final String LYDQ = "lydq";
	public static final String BASPT = "baspt";
	public static final String JYAQ = "jyaq";
	public static final String LAR = "lar";
	public static final String BAFY = "bafy";
	public static final String GKSJG = "gksjg";
	public static final String CBRYJ = "cbryj";
	public static final String HYTYJ = "hytyj";
	public static final String TZHYJ = "tzhyj";
	public static final String YZHYJ = "yzhyj";
	public static final String SWHYJ = "swhyj";
	public static final String JAFS = "jafs";
	public static final String JAYY = "jayy";
	public static final String JAYYDM = "jayydm";
	public static final String BZ = "bz";
	public static final String GDBZ = "gdbz";
	public static final String AJZT = "ajzt";
	public static final String BYCXDZ = "bycxdz";
	public static final String AJWSQK = "ajwsqk";
	public static final String GDXLH = "gdxlh";
	public static final String SLQK = "slqk";
	public static final String SSLX = "sslx";
	public static final String SFGS = "sfgs";
	public static final String FHCSYY = "fhcsyy";
	public static final String GPYY = "gpyy";
	public static final String JJAR = "jjar";
	public static final String SFZSCQ = "sfzscq";
	public static final String SWLX = "swlx";
	public static final String MODFLAG = "modflag";
	public static final String TRANSFLAG = "transflag";
	public static final String SPTG = "sptg";
	public static final String PSYCY = "psycy";
	public static final String MSAJTJS = "msajtjs";
	public static final String MSAJTJSSS = "msajtjsss";
	public static final String SHWDFXPG = "shwdfxpg";
	public static final String SFXESS = "sfxess";
	public static final String SFFCXX = "sffcxx";
	public static final String FXHJCS = "fxhjcs";
	public static final String PJSFFDJFNR = "pjsffdjfnr";
	public static final String SFDTPJ = "sfdtpj";
	public static final String SFHJ = "sfhj";
	public static final String SFKTSL = "sfktsl";
	public static final String SFTZ = "sftz";
	public static final String SFCQYSCS = "sfcqyscs";
	public static final String AJSFYS = "ajsfys";
	public static final String ZYTJ = "zytj";
	public static final String YSFYSFFCZS = "ysfysffczs";
	public static final String SFJGYLA = "sfjgyla";
	public static final String SFSQSFQR = "sfsqsfqr";
	public static final String YFFA = "yffa";
	public static final String SFYYFFA = "sfyyffa";

	protected void initDao() {
		// do nothing
	}

	public boolean save(AjjbDO transientInstance) {
		log.debug("saving AjjbDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return true;
	}

	public void delete(AjjbDO persistentInstance) {
		log.debug("deleting AjjbDO instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public AjjbDO findById(java.lang.Integer id) {
		log.debug("getting AjjbDO instance with id: " + id);
		try {
			AjjbDO instance = (AjjbDO) getHibernateTemplate().get(
					"software.dygzt.data.aj.dataobject.AjjbDO", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<AjjbDO> findByExample(AjjbDO instance) {
		log.debug("finding AjjbDO instance by example");
		try {
			List<AjjbDO> results = (List<AjjbDO>) getHibernateTemplate()
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
		log.debug("finding AjjbDO instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from AjjbDO as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	

	public List<AjjbDO> findByAh(Object ah) {
		return findByProperty(AH, ah);
	}

	public List<AjjbDO> findByAjmc(Object ajmc) {
		return findByProperty(AJMC, ajmc);
	}

	public List<AjjbDO> findByAjxz(Object ajxz) {
		return findByProperty(AJXZ, ajxz);
	}

	public List<AjjbDO> findByAjly(Object ajly) {
		return findByProperty(AJLY, ajly);
	}

	public List<AjjbDO> findBySpcx(Object spcx) {
		return findByProperty(SPCX, spcx);
	}

	public List<AjjbDO> findBySpcxdz(Object spcxdz) {
		return findByProperty(SPCXDZ, spcxdz);
	}

	public List<AjjbDO> findBySycx(Object sycx) {
		return findByProperty(SYCX, sycx);
	}

	public List<AjjbDO> findBySfys(Object sfys) {
		return findByProperty(SFYS, sfys);
	}

	public List<AjjbDO> findBySfzdaj(Object sfzdaj) {
		return findByProperty(SFZDAJ, sfzdaj);
	}

	public List<AjjbDO> findBySfjbaj(Object sfjbaj) {
		return findByProperty(SFJBAJ, sfjbaj);
	}

	public List<AjjbDO> findBySffhcs(Object sffhcs) {
		return findByProperty(SFFHCS, sffhcs);
	}

	public List<AjjbDO> findBySfwdyj(Object sfwdyj) {
		return findByProperty(SFWDYJ, sfwdyj);
	}

	public List<AjjbDO> findBySx(Object sx) {
		return findByProperty(SX, sx);
	}

	public List<AjjbDO> findByFjsx(Object fjsx) {
		return findByProperty(FJSX, fjsx);
	}

	public List<AjjbDO> findByLydq(Object lydq) {
		return findByProperty(LYDQ, lydq);
	}

	public List<AjjbDO> findByBaspt(Object baspt) {
		return findByProperty(BASPT, baspt);
	}

	public List<AjjbDO> findByJyaq(Object jyaq) {
		return findByProperty(JYAQ, jyaq);
	}

	public List<AjjbDO> findByLar(Object lar) {
		return findByProperty(LAR, lar);
	}

	public List<AjjbDO> findByBafy(Object bafy) {
		return findByProperty(BAFY, bafy);
	}

	public List<AjjbDO> findByGksjg(Object gksjg) {
		return findByProperty(GKSJG, gksjg);
	}

	public List<AjjbDO> findByCbryj(Object cbryj) {
		return findByProperty(CBRYJ, cbryj);
	}

	public List<AjjbDO> findByHytyj(Object hytyj) {
		return findByProperty(HYTYJ, hytyj);
	}

	public List<AjjbDO> findByTzhyj(Object tzhyj) {
		return findByProperty(TZHYJ, tzhyj);
	}

	public List<AjjbDO> findByYzhyj(Object yzhyj) {
		return findByProperty(YZHYJ, yzhyj);
	}

	public List<AjjbDO> findBySwhyj(Object swhyj) {
		return findByProperty(SWHYJ, swhyj);
	}

	public List<AjjbDO> findByJafs(Object jafs) {
		return findByProperty(JAFS, jafs);
	}

	public List<AjjbDO> findByJayy(Object jayy) {
		return findByProperty(JAYY, jayy);
	}

	public List<AjjbDO> findByJayydm(Object jayydm) {
		return findByProperty(JAYYDM, jayydm);
	}

	public List<AjjbDO> findByBz(Object bz) {
		return findByProperty(BZ, bz);
	}

	public List<AjjbDO> findByGdbz(Object gdbz) {
		return findByProperty(GDBZ, gdbz);
	}

	public List<AjjbDO> findByAjzt(Object ajzt) {
		return findByProperty(AJZT, ajzt);
	}

	public List<AjjbDO> findByBycxdz(Object bycxdz) {
		return findByProperty(BYCXDZ, bycxdz);
	}

	public List<AjjbDO> findByAjwsqk(Object ajwsqk) {
		return findByProperty(AJWSQK, ajwsqk);
	}

	public List<AjjbDO> findByGdxlh(Object gdxlh) {
		return findByProperty(GDXLH, gdxlh);
	}

	public List<AjjbDO> findBySlqk(Object slqk) {
		return findByProperty(SLQK, slqk);
	}

	public List<AjjbDO> findBySslx(Object sslx) {
		return findByProperty(SSLX, sslx);
	}

	public List<AjjbDO> findBySfgs(Object sfgs) {
		return findByProperty(SFGS, sfgs);
	}

	public List<AjjbDO> findByFhcsyy(Object fhcsyy) {
		return findByProperty(FHCSYY, fhcsyy);
	}

	public List<AjjbDO> findByGpyy(Object gpyy) {
		return findByProperty(GPYY, gpyy);
	}

	public List<AjjbDO> findByJjar(Object jjar) {
		return findByProperty(JJAR, jjar);
	}

	public List<AjjbDO> findBySfzscq(Object sfzscq) {
		return findByProperty(SFZSCQ, sfzscq);
	}

	public List<AjjbDO> findBySwlx(Object swlx) {
		return findByProperty(SWLX, swlx);
	}

	public List<AjjbDO> findByModflag(Object modflag) {
		return findByProperty(MODFLAG, modflag);
	}

	public List<AjjbDO> findByTransflag(Object transflag) {
		return findByProperty(TRANSFLAG, transflag);
	}

	public List<AjjbDO> findBySptg(Object sptg) {
		return findByProperty(SPTG, sptg);
	}

	public List<AjjbDO> findByPsycy(Object psycy) {
		return findByProperty(PSYCY, psycy);
	}

	public List<AjjbDO> findByMsajtjs(Object msajtjs) {
		return findByProperty(MSAJTJS, msajtjs);
	}

	public List<AjjbDO> findByMsajtjsss(Object msajtjsss) {
		return findByProperty(MSAJTJSSS, msajtjsss);
	}

	public List<AjjbDO> findByShwdfxpg(Object shwdfxpg) {
		return findByProperty(SHWDFXPG, shwdfxpg);
	}

	public List<AjjbDO> findBySfxess(Object sfxess) {
		return findByProperty(SFXESS, sfxess);
	}

	public List<AjjbDO> findBySffcxx(Object sffcxx) {
		return findByProperty(SFFCXX, sffcxx);
	}

	public List<AjjbDO> findByFxhjcs(Object fxhjcs) {
		return findByProperty(FXHJCS, fxhjcs);
	}

	public List<AjjbDO> findByPjsffdjfnr(Object pjsffdjfnr) {
		return findByProperty(PJSFFDJFNR, pjsffdjfnr);
	}

	public List<AjjbDO> findBySfdtpj(Object sfdtpj) {
		return findByProperty(SFDTPJ, sfdtpj);
	}

	public List<AjjbDO> findBySfhj(Object sfhj) {
		return findByProperty(SFHJ, sfhj);
	}

	public List<AjjbDO> findBySfktsl(Object sfktsl) {
		return findByProperty(SFKTSL, sfktsl);
	}

	public List<AjjbDO> findBySftz(Object sftz) {
		return findByProperty(SFTZ, sftz);
	}

	public List<AjjbDO> findBySfcqyscs(Object sfcqyscs) {
		return findByProperty(SFCQYSCS, sfcqyscs);
	}

	public List<AjjbDO> findByAjsfys(Object ajsfys) {
		return findByProperty(AJSFYS, ajsfys);
	}

	public List<AjjbDO> findByZytj(Object zytj) {
		return findByProperty(ZYTJ, zytj);
	}

	public List<AjjbDO> findByYsfysffczs(Object ysfysffczs) {
		return findByProperty(YSFYSFFCZS, ysfysffczs);
	}

	public List<AjjbDO> findBySfjgyla(Object sfjgyla) {
		return findByProperty(SFJGYLA, sfjgyla);
	}

	public List<AjjbDO> findBySfsqsfqr(Object sfsqsfqr) {
		return findByProperty(SFSQSFQR, sfsqsfqr);
	}

	public List<AjjbDO> findByYffa(Object yffa) {
		return findByProperty(YFFA, yffa);
	}

	public List<AjjbDO> findBySfyyffa(Object sfyyffa) {
		return findByProperty(SFYYFFA, sfyyffa);
	}

	public List findAll() {
		log.debug("finding all AjjbDO instances");
		try {
			String queryString = "from AjjbDO";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public AjjbDO merge(AjjbDO detachedInstance) {
		log.debug("merging AjjbDO instance");
		try {
			AjjbDO result = (AjjbDO) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public boolean attachDirty(AjjbDO instance) {
		log.debug("attaching dirty AjjbDO instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
		return true;
	}

	public void attachClean(AjjbDO instance) {
		log.debug("attaching clean AjjbDO instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AjjbDao getFromApplicationContext(ApplicationContext ctx) {
		return (AjjbDao) ctx.getBean("AjjbDODAO");
	}
	
}