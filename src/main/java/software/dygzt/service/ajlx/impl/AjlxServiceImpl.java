package software.dygzt.service.ajlx.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.googlecode.ehcache.annotations.Cacheable;

import software.dygzt.data.ajlx.dao.AjlxbDao;
import software.dygzt.data.ajlx.dataobject.AjlxbDO;
import software.dygzt.service.ajlx.AjlxService;
import software.dygzt.service.ajlx.model.AjlxVO;
import software.dygzt.service.share.Convertor;
import software.dygzt.service.share.model.TreeNodeVO;
import software.dygzt.util.CompareModel;
import software.dygzt.util.CompareUtil;
import software.dygzt.util.StringUtil;
@Service
public class AjlxServiceImpl implements AjlxService, InitializingBean{
	@Autowired
	private AjlxbDao ajlxbDao;

	private List<AjlxVO> ajlxTree = new ArrayList<AjlxVO>();
	private Map<String,AjlxVO> ajlxList = new HashMap<String,AjlxVO>();
	
	private static final Logger log = Logger.getLogger(AjlxServiceImpl.class);
	@Cacheable(cacheName = "AJLX_CACHE")
	public TreeNodeVO getAjlxTree() {
		TreeNodeVO root = new TreeNodeVO("全部案件", true, true, null);
		List<TreeNodeVO> children = new ArrayList<TreeNodeVO>();
		for(AjlxVO ajlx:ajlxTree){
			children.add(convert(ajlx));
		}
		root.setChildren(children);
		return root;
	}
	
	private TreeNodeVO convert(AjlxVO vo){
		TreeNodeVO result = new TreeNodeVO(vo.getMc(),vo.getXjList().size()>0,vo.getId());
		List<TreeNodeVO> children = new ArrayList<TreeNodeVO>();
		for(AjlxVO ajlx:vo.getXjList()){
			children.add(convert(ajlx));
		}
		result.setChildren(children);
		return result;
	}
	
	public void afterPropertiesSet() throws Exception {
		List<AjlxbDO> pos = ajlxbDao.findAll();
		for(AjlxbDO po:pos){
			AjlxVO vo = Convertor.do2vo(po);
			ajlxList.put(po.getId(), vo);
		}
		for(AjlxbDO po:pos){
			if(StringUtil.isNotBlank(po.getSjid())){
				ajlxList.get(po.getSjid()).addXj(ajlxList.get(po.getId()));
			}
		}
		for(AjlxVO vo:ajlxList.values()){
			if(StringUtil.isBlank(vo.getSjid())){
				ajlxTree.add(vo);
			}
		}
		List<CompareModel> models = new ArrayList<CompareModel>();
		models.add(new CompareModel("asc" , "id"));
		Collections.sort(ajlxTree, CompareUtil.createComparator(models));
		System.out.println(ajlxTree.size());
	}
}
