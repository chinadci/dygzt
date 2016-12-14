package software.dygzt.service.dmb.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import software.dygzt.service.dmb.DmService;
import software.dygzt.data.dmb.dao.DmbDao;

/**
 * 代码表服务的实现
 */
@Service
public class DmServiceImpl {
	private static final Logger log = Logger.getLogger(DmServiceImpl.class);
	@Autowired
	private DmbDao dmbDao;
}
