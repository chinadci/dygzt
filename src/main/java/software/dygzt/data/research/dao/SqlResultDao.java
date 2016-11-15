package software.dygzt.data.research.dao;

import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import software.dygzt.service.research.model.ResearchAj;
import software.dygzt.service.research.model.ResearchQueryResult;
import software.dygzt.service.research.model.Result;
import software.dygzt.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
@Repository
public class SqlResultDao extends HibernateDaoSupport{
	private static final Logger log = LoggerFactory.getLogger(SqlResultDao.class);
	
	protected void initDao() {
	}
	/**
	 * 
	 * @param sql 查询语句
	 * @param result 获取的列
	 * @param fydm fydm，为-1时不赋值
	 * @return
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public List<ResearchAj> getSqlResult(String sql, ResearchQueryResult result,String fydm) throws SQLException, IllegalArgumentException, IllegalAccessException {
		ConnectionProvider cp = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		List<ResearchAj> res = new ArrayList<ResearchAj>();
		try {
			cp = ((SessionFactoryImplementor) this.getSessionFactory()).getConnectionProvider();
			connection = cp.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			while(rs.next()){
				ResearchAj crm = new ResearchAj();
				Class resultClazz = result.getClass();
				Field[] resultFields = resultClazz.getFields();
				for(Field field : resultFields) {
					Object value = field.get(result);
					if (value != null && !"".equals(value)&&field.getType().getName().equals("java.lang.Boolean")) {
						if ((Boolean)value == true) {
							Result annotation = field.getAnnotation(Result.class);
							String fieldName = annotation.field();
							ReflectionUtil.invokeSetter(crm, fieldName, rs.getObject(fieldName));
						}
					}
				}
				if(!fydm.equals("-1")){
					crm.setFydm(fydm);
				}
				res.add(crm);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			logger.error(e1);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (cp != null)
					cp.closeConnection(connection);
			} catch (SQLException e) {
				log.error("关闭数据库连接出错。",e);
			}

		}
		
		return res;
	}







	/**
	 *
	 * @param sql 统计指定条件指定字段的数量
	 * @return
	 * @throws SQLException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public int getSqlResult2(String sql) throws SQLException, IllegalArgumentException, IllegalAccessException {
		ConnectionProvider cp = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;

		int num = 0; //用来该法院相关记录条目数
		try {
			cp = ((SessionFactoryImplementor) this.getSessionFactory()).getConnectionProvider();
			connection = cp.getConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);

			while(rs.next()){
				num = rs.getInt("count");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			logger.error(e1);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (statement != null)
					statement.close();
				if (cp != null)
					cp.closeConnection(connection);
			} catch (SQLException e) {
				log.error("关闭数据库连接出错。",e);
			}
		}

		return num;
	}









	
	public boolean isConnected(){
		ConnectionProvider cp = null;
		Connection connection = null;
		try {
			cp = ((SessionFactoryImplementor) this.getSessionFactory()).getConnectionProvider();
			connection = cp.getConnection();
			if (cp != null){
				return true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			logger.error(e1);
		} finally {
			try {
				if (cp != null)
					cp.closeConnection(connection);
			} catch (SQLException e) {
				log.error("关闭数据库连接出错。",e);
			}

		}
		
		return false;
	}
}
