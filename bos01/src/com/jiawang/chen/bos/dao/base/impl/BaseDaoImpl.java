/**
 * 
 */
package com.jiawang.chen.bos.dao.base.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.web.utils.PageBean;
import com.jiawang.chen.bos.web.utils.ReflectionUtils;

/**
  *<p>标题: BaseDaoImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 */
public class BaseDaoImpl<T> implements IBaseDao<T> {

	private static final Logger logger = Logger.getLogger(BaseDaoImpl.class);
	
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> entityClass;
	
	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}
	
	/**
	 * 反射获得类型
	 */
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		ParameterizedType  genericSuperclass = (ParameterizedType)this.getClass().getGenericSuperclass();
		Type[] actualTypeArguments = genericSuperclass.getActualTypeArguments();
		entityClass=(Class<T>)actualTypeArguments[0];
	}
	/* 
	 *注册用户
	 */
	@Override
	public void regist(T entity) {

		this.getCurrentSession().save(entity);
	}


	/* 
	 *保存一个对象
	 */
	@Override
	public Serializable save(T entity) {
		if(entity!=null){
			Serializable result = this.getCurrentSession().save(entity);
			this.getCurrentSession().flush();
			return result;
		}
		return null;
	}

	/* 
	 *批量保存对象
	 */
	@Override
	public void batchSave(List<T> entitys) {

		for(int i=0;i<entitys.size();i++){
			this.getCurrentSession().save(entitys.get(i));
			if(i%20==0){
				//20个对象后才清理缓存，写入数据库
				this.getCurrentSession().flush();
				this.getCurrentSession().clear();
			}
		}
	}

	/* 
	 *删除一个对象
	 */
	@Override
	public void delete(T entity) {

		this.getCurrentSession().delete(entity);
	}

	/* 
	 * 删除一个对象
	 */
	@Override
	public void delete(Serializable id) {

		this.getCurrentSession().delete(this.get(id));
		this.getCurrentSession().flush();
	}

	/* 
	*更新一个对象
	 */
	@Override
	public void update(T entity) {

		if(entity!=null){
			this.getCurrentSession().update(entity);
			this.getCurrentSession().flush();
		}
	
	}

	/* 
	 *保存或更新一个对象
	 */
	@Override
	public void saveUpdate(T entity) {
		if(entity!=null){
			this.getCurrentSession().saveOrUpdate(entity);
			this.getCurrentSession().flush();
		}
	}

	
	
	public List<T> findByNameQuery(String queryName,Object... objects) {
		Query query = this.getCurrentSession().getNamedQuery(queryName);
		// 为HQL语句中的？赋值
				int i = 0;
				for (Object arg : objects) {
					query.setParameter(i++, arg);
				}
		List<T> list = query.list();
		return list;
	}
	/**
	 * 通用更新方法
	 */
	public void executeUpdate(String queryName, Object... objects) {
			//	this.getCurrentSession();// 从本地线程中获得session对象
		Session session = this.getCurrentSession();
		// 使用命名查询语句获得一个查询对象
		Query query = session.getNamedQuery(queryName);
		// 为HQL语句中的？赋值
		int i = 0;
		for (Object arg : objects) {
			query.setParameter(i++, arg);
		}
		query.executeUpdate();// 执行更新
	}
	
	/* 
	 *通过主键获得对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(Class<T> c, Serializable id) {
		return 	(T)this.getCurrentSession().get(c, id);
	}

	/* 
	 *通过主键获得对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		
		return (T)this.getCurrentSession().get(entityClass, id);
	}

	/* 
	 *通过HQL语句获取一个对象
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getBySql(String hql) {
		Query q = this.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/* 
	 *通过HQL语句获取一个对象
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T get(String hql, Map<String, Object> params) {
		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	/* 
	 *获得对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find( ) {
		String hql="from  "+entityClass.getSimpleName();
		return this.getCurrentSession().createQuery(hql).list();
	}

	public List<T> find( String hql) {
		return this.getCurrentSession().createQuery(hql).list();
	}
	/* 
	 *获得分页后的对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Map<String, Object> params) {
	
		Query q = this.getCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object value = params.get(key);

				if (value instanceof List) {
					q.setParameterList(key, (List<Object>) value);
				} else {
					q.setParameter(key, value);
				}
			}
		}
		return q.list();
	}

	/* 
	*获得分页后的对象列表
	 */
	@Override
	public List findBySql(Class transFormClass, String sql, Map<String, Object> params) {
		return null;
	}

	/* 
	 *获得分页后的对象列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, int page, int rows) {
		Query query = this.getCurrentSession().createQuery(hql);
		return query.setFirstResult((page-1)*rows).setMaxResults(rows).list();
	}

	/* 
	 *  
	 */
	@Override
	public List<T> find(String hql,  int page, int rows,Map<String, Object> params) {
		
		Query q = this.getCurrentSession().createQuery(hql);
		if (!CollectionUtils.isEmpty(params)) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (entry.getValue() instanceof List) {
					q.setParameterList(entry.getKey(), (List<Object>) entry.getValue());
				} else {
					q.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/* 
	  *统计数目
	 */
	@Override
	public Long count(String hql) {

		return (Long)this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	/* 
	 *统计数目
	 */
	@Override
	public long  getcount(String hql) {
		
		return (Long )this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	/* 
	 *统计数目
	 */
	@Override
	public long count(String hql, Map<String, Object> params) {

		Query q = this.getCurrentSession().createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object value = params.get(key);

				if (value instanceof List) {
					q.setParameterList(key, (List<Object>) value);
				} else {
					q.setParameter(key, value);
				}
			}
		}
		return (Long) q.uniqueResult();
	}

	/* 
	 *执行一条HQL语句
	 */
	@Override
	public int executeHql(String hql) {

		Query query = this.getCurrentSession().createQuery(hql);
		return query.executeUpdate();
	}

	/* 
	 *执行一条HQL语句
	 */
	@Override
	public int executeHql(String hql, Map<String, Object> params) {

		Query q = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String  key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.executeUpdate();
	}

	/* 
	  *获得结果集
	 */
	@Override
	public List<Object[]> findBySql(String sql) {
		SQLQuery query = this.getCurrentSession().createSQLQuery(sql);
		return query.list();
	}

	/* 
	 *获得结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql, int page, int rows) {
		Query query = this.getCurrentSession().createQuery(sql);
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/* 
	 *获得结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	/* 
	  *获得结果集
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findBySql(String sql,int page, int rows,Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/* 
	 *
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> sqlFind(String hql, int page, int rows, Map<String, Object> params) {

		Query query = this.getCurrentSession().createSQLQuery(hql).addEntity("t",entityClass);
		if (!CollectionUtils.isEmpty(params)) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}
		return query.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	/* 
	  *执行SQL语句
	 */
	@Override
	public long countBySql(String sql) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		return ((BigInteger) q.uniqueResult()).longValue();
	}

	/* 
	 *
	 */
	@Override
	public long countBySql(String sql, Map<String, Object> params) {
		SQLQuery q = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				q.setParameter(key, params.get(key));
			}
		}
		return ((BigInteger) q.uniqueResult()).longValue();
	}

//	public void pageQuery(PageBean pageBean) {
//		String hql1="from  "+entityClass.getSimpleName();
//		String hql2="select count(*)  from   "+entityClass.getSimpleName();
//		int currentPage = pageBean.getCurrentPage();//获取当前页
//    	int pageSize = pageBean.getPageSize();//获取每页显示的数量
//    	Long total = this.getcount(hql2);
//		List<T> list = this.find(hql1, currentPage, pageSize);
//		pageBean.setTotal(total.intValue());
//		pageBean.setRows(list);
//	}

	public void pageQuery(PageBean pageBean) {
		int currentPage = pageBean.getCurrentPage();//获取当前页
    	int pageSize = pageBean.getPageSize();//获取每页显示的数量
		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
		Criteria criteria= detachedCriteria.getExecutableCriteria(this.getCurrentSession());
		Long total = (Long) criteria.setProjection(Projections.rowCount())
                .uniqueResult();
        criteria.setProjection(null);
        criteria.setFirstResult( (currentPage - 1) * pageSize);  
        criteria.setMaxResults(pageSize);  
  
        List  list = criteria.list();  
		 pageBean.setTotal(total.intValue());//设置总数量
		 pageBean.setRows(list);
		
	}
		
		
		
		
		
		
		
		
		
		
//	CriteriaImpl impl = (CriteriaImpl) c;  
//    Projection projection = impl.getProjection();  
//    ResultTransformer transformer = impl.getResultTransformer();  
//
//      
//    List<CriteriaImpl.OrderEntry> orderEntries = (List<CriteriaImpl.OrderEntry>) ReflectionUtils.getFieldValue(impl, "orderEntries");  
//    ReflectionUtils.setFieldValue(impl, "orderEntries", new ArrayList<CriteriaImpl.OrderEntry>());  
//      
//      
//    // 执行Count查询  
//    c.setResultTransformer(CriteriaImpl.DISTINCT_ROOT_ENTITY);  
//   Long total = (Long) c.setProjection(Projections.countDistinct("id")).uniqueResult();  
//  
//    // 将之前的Projection和OrderBy条件重新设回去  
//    c.setProjection(projection);  
//    c.setResultTransformer(transformer);  
//    ReflectionUtils.setFieldValue(impl, "orderEntries", orderEntries);  
		
		
		
		
		
		
		
		
		
		
		
		
////		CriteriaImpl impl = (CriteriaImpl) criteria;  
////		criteria.setProjection(arg0)
//		criteria.setProjection(Projections.rowCount());
//		 Long total = (Long) criteria.setProjection(Projections.rowCount())
//	                .uniqueResult();
//		//修改sql的形式为select * from ....
//		 criteria.setProjection(null);
//		//重置表和类的映射关系
//		 criteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
//		 criteria.setFirstResult((currentPage - 1) *pageSize);
//		 criteria.setMaxResults(pageSize);
//		 List rows= criteria.list();
//		 pageBean.setTotal(total.intValue());//设置总数量
//		 pageBean.setRows(rows);
	
	

//	@Override
//	public void pageQuery(PageBean pageBean) {
//		int currentPage = pageBean.getCurrentPage();//获取当前页
//		int pageSize = pageBean.getPageSize();//获取每页显示的数量
//		DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
//		
//		//总数据量----select count(*) from bc_staff
//				//改变Hibernate框架发出的sql形式
//		detachedCriteria.setProjection(Projections.rowCount());
//		List<Long> list = this.getCurrentSession().findByCriteria(detachedCriteria);
//		Long total = list.get(0);
//		pageBean.setTotal(total.intValue());//设置总数量
//		detachedCriteria.setProjection(null);//修改sql的形式为select * from ....
//		
//		//重置表和类的映射关系
//		detachedCriteria.setResultTransformer(DetachedCriteria.ROOT_ENTITY);
//		//当前页展示的数据集合
//		int firstResult = (currentPage - 1) * pageSize;
//		int maxResults = pageSize;
//		List rows = this.getCurrentSession().findByCriteria(detachedCriteria, firstResult, maxResults);
//		pageBean.setRows(rows);
//		
//	}
	
//	/* 
//	 *通用  有条件的查询
//	 */
//	@Override
//	public List<T> findByCriteria(DetachedCriteria detachedCriteria) {
//
//		 this.getCurrentSession().f
//		return this.getCurrentSession().createCriteria(detachedCriteria);
//				
//				findByCriteria(detachedCriteria);
//	}
}
