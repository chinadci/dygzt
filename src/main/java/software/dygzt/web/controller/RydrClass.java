package software.dygzt.web.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import software.dygzt.data.user.dao.DyXtyhDao;
import software.dygzt.data.user.dao.XtyhDao;
import software.dygzt.data.user.dataobject.DyXtyhDO;
import software.dygzt.data.user.dataobject.XtglYhbDO;
import software.dygzt.dynamicds.DataSourceRouter;
import software.dygzt.dynamicds.FYDataSourceEnum;

public class RydrClass {
	
	DyXtyhDao dyXtyhDao;
	XtyhDao  xtyhDao;
	public static void main(String[] args) {
		new RydrClass().addPersons();
	}
	
	private void addPersons(){
		String qx="调研人";
//		String qx="调研人;审批人";
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		dyXtyhDao = (DyXtyhDao) context.getBean("dyXtyhDao");
		xtyhDao = (XtyhDao) context.getBean("xtyhDao");
		for (FYDataSourceEnum dataSource : FYDataSourceEnum.values()) {
			DataSourceRouter.routerTo(dataSource.getFydm());// 各家法院库
			List<XtglYhbDO> xtyhdos;
			if(dataSource.getFydm().equals("120000 200")){
				xtyhdos=xtyhDao.findByHql("where yhbm<>'28'");
			}else{
				xtyhdos=xtyhDao.findByHql("");
			}
			System.out.println(dataSource.getFymc()+xtyhdos.size());
			DataSourceRouter.routerToYzjcJzk();
			for(XtglYhbDO xtyh:xtyhdos){
				DyXtyhDO dyxtyh = new DyXtyhDO();
				dyxtyh.setYhm(xtyh.getYhdm());
				dyxtyh.setFydm(dataSource.getFydm());
//				dyxtyh.setPassword("123456");
				dyxtyh.setName(xtyh.getYhmc());
				dyxtyh.setBm(xtyh.getYhbm());
				dyxtyh.setQx(qx);
				dyxtyh.setPhone(xtyh.getPhone());
				dyxtyh.setLxfs(xtyh.getCocall());
				dyXtyhDao.save(dyxtyh);
			}
		}
	}
}
