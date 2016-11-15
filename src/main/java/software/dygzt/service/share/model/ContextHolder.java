package software.dygzt.service.share.model;

import org.apache.log4j.Logger;
import software.dygzt.util.StringUtil;

/**
 * 保存Session中关于调研信息和用户基本信息的类
 */
public class ContextHolder {
	private static final Logger log = Logger.getLogger(ContextHolder.class);
	
	private static final ThreadLocal userContextHolder = new ThreadLocal(); 
	private static final ThreadLocal tabContextHolder = new ThreadLocal();
	private static final ThreadLocal tabContextHolder2 = new ThreadLocal();  
	
    @SuppressWarnings("unchecked")
	public static void setUserContext(Object context) {  	
    	userContextHolder.set(context);  
    }  
      
    public static Object getUserContext() {  
    	return (Object) userContextHolder.get();  
    }  
      
    public static void clearUserCustomerType() {  
    	userContextHolder.remove();  
    }
    
    @SuppressWarnings("unchecked")
	public static void setTabContext(Object context) {  	
    	tabContextHolder.set(context);  
    }  
      
    public static Object getTabContext() {  
    	return (Object) tabContextHolder.get();  
    }  
      
    public static void clearTabCustomerType() {  
    	tabContextHolder.remove();  
    }
    
    @SuppressWarnings("unchecked")
   	public static void setTabContext2(Object context) {  	
       	tabContextHolder2.set(context);  
    }  
         
    public static Object getTabContext2() {  
      	return (Object) tabContextHolder2.get();  
    }  
         
    public static void clearTabCustomer2Type() {  
       	tabContextHolder2.remove();  
    }

}
