package com.example.demo.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;

import com.example.demo.common.PageInfo;

@Component
@Intercepts({
		@Signature(type = StatementHandler.class, args = { Connection.class, Integer.class }, method = "prepare") })
public class MyPageIntercept implements Interceptor {

	static ThreadLocal<PageInfo> pageLocal = new ThreadLocal<PageInfo>();

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
				SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
		MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
		// 通过MetaObject元数据取得方法名id：com.XXX.queryMessageListByPage
		String id = mappedStatement.getId();
		// 匹配在mybatis中定义的与分页有关的查询id
		if (id.endsWith("Page")) {
			// BoundSql中有原始的sql语句和对应的查询参数
			BoundSql boundSql = statementHandler.getBoundSql();
			Object obj = boundSql.getParameterObject();

			PageInfo pageInfo = null;
			if (obj instanceof PageInfo) {
				pageInfo = (PageInfo) obj;
			} else if (obj instanceof Map) {
				// 如果Dao中有多个参数，则分页的注解参数名必须是pageInfo
				try {
					pageInfo = (PageInfo) ((Map) obj).get("pageInfo");
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}

			String sql = boundSql.getSql();
			String countSql = "select count(1) from (" + sql + ") a";
			Connection connection = (Connection) invocation.getArgs()[0];
			PreparedStatement countStatement = connection.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler) metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(countStatement);
			ResultSet rs = countStatement.executeQuery();
			if (rs.next()) {
				// 因为数据表的列是从1开始计数，所以getInt（1）
				pageInfo.setTotalSize(rs.getInt(1));
				System.out.println("该查询结果记录总数为：" + pageInfo.getTotalSize());
				pageLocal.set(pageInfo);
			}
			String pageSql = sql + " limit " + (pageInfo.getPageNum() - 1) * pageInfo.getPageSize() + ", "
					+ pageInfo.getPageSize();
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	@Override
	public void setProperties(Properties properties) {

	}

	public static ThreadLocal<PageInfo> getPageLocal() {
		return pageLocal;
	}

	public static void setPageLocal(ThreadLocal<PageInfo> pageLocal) {
		MyPageIntercept.pageLocal = pageLocal;
	}

}
