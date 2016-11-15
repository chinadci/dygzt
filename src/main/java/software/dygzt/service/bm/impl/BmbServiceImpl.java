package software.dygzt.service.bm.impl;

import com.googlecode.ehcache.annotations.Cacheable;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.dygzt.data.bm.dao.JzrhBmbDao;
import software.dygzt.data.bm.dataobject.JzrhBmbDO;
import software.dygzt.data.dmb.dao.DmbDao;
import software.dygzt.data.dmb.dataobject.DmbDO;
import software.dygzt.dynamicds.CustomerContextHolder;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;
import software.dygzt.service.bm.BmbService;
import software.dygzt.service.bm.model.BmModel;
import software.dygzt.service.share.Convertor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 部门表服务实现
 * 
 */
@Service
public class BmbServiceImpl implements BmbService, InitializingBean  {

	static final Logger log = Logger.getLogger(BmbServiceImpl.class);
	@Autowired
	private DmbDao dmbDao;
	@Autowired
	private JzrhBmbDao jzrhBmbDao;
	private Map<String,Map<String, BmModel>> bmList = new HashMap<String,Map<String, BmModel>>();
	private Map<Integer,Map<String, BmModel>> bmJzrhList = new HashMap<Integer,Map<String, BmModel>>();
	
	@Cacheable(cacheName = "BM_CACHE")
	public BmModel getBm(String bmbh) {
		DmbDO Bmb = dmbDao.findById("USR206-99", bmbh);
		if (Bmb == null) {
			return null;
		}
		return Convertor.doToBmModel(Bmb);
	}
	
	public BmModel getBm(String bmbh, String fydm) {
		return bmList.get(fydm).get(bmbh);
	}
	
	public BmModel getBm(String bmbh, int fybh) {
		return bmJzrhList.get(fybh).get(bmbh);
	}
	
	public Map<String, BmModel> getBmList(String fydm) {
		return bmList.get(fydm);
	}
	
	public Map<String, BmModel> getBmList(int fybh) {
		return bmJzrhList.get(fybh);
	}

	public void afterPropertiesSet() throws Exception {
		String curDb = CustomerContextHolder.getCustomerType();
		/*遍历所有法院，在南京要注释该for 循环*/
		//FYDataSourceEnum.values() 返回一个枚举中所有数据的数组
		//数组中一条数据为: TJGY("120000 200",51,"Tjgy","天津市高级人民法院","130.1.1.111"),
		for(FYDataSourceEnum fy:FYDataSourceEnum.values()){
			DataSourceRouter.routerTo(fy.getFydm());
			Map<String, BmModel> submap = new HashMap<String, BmModel>();
			List<DmbDO> bms = dmbDao.getDmListByLbbh("USR206-99",-1);
			for(DmbDO bm:bms){
				BmModel bmModel = Convertor.doToBmModel(bm);
				submap.put(bm.getDmbh(), bmModel);
			}
			bmList.put(fy.getFydm(), submap);
		}
		DataSourceRouter.routerToJzrh();
		List<JzrhBmbDO> bmdos = jzrhBmbDao.findAll();
		for(JzrhBmbDO bm:bmdos){
			Map<String, BmModel> submap = bmJzrhList.get(bm.getFybh());
			if(submap==null){
				submap = new HashMap<String, BmModel>();
				bmJzrhList.put(bm.getFybh(), submap);
			}
			submap.put(String.valueOf(bm.getBmbh()), Convertor.doToBmModel(bm));
		}
		DataSourceRouter.routerTo(curDb);
	}

}
