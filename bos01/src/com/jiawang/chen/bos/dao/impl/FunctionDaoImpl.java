/**
 * 
 */
package com.jiawang.chen.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.jiawang.chen.bos.dao.IFunctionDao;
import com.jiawang.chen.bos.dao.base.impl.BaseDaoImpl;
import com.jiawang.chen.bos.entity.Function;

/**
 *<p>标题: FunctionDaoImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Repository
public class FunctionDaoImpl extends BaseDaoImpl<Function> implements IFunctionDao{

	/* 
	 *根据用户id 查询对应的权限
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findListByUserid(String userid) {
		String hql="select distinct f from Function f left outer join f.roles"
				+ "r left outer join r.users u where u.id=?";
//		//List<Function> list= (List<Function>)this.getHibernateTemplate().get(hql, userid);
////		List<Function> list =this.getCurrentSession().get(hql, userid)
////				Object object = this.getCurrentSession().get(hql, userid);
//		
//				get(hql, userid);
		
		List<Function> list= (List<Function>)this.getCurrentSession().get(hql, userid);
		return list;
	}

	/* 
	 *查询所有的菜单
	 */
	@Override
	public List<Function> findAllMenu() {
    
		String hql="FROM Function f WHERE f.generatemenu='1' ORDER BY f.zindex DESC";
		 Query query = this.getCurrentSession().createQuery(hql);
		 List<Function>  list = query.list();
		 if (list != null && list.size() > 0){
			 return  list; 
		 }
		return  null; 
	}

	/* 
	 *根据用户id查询对应的菜单
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Function> findMenuByUserid(String id) {
		String hql="select distinct f from Function f left outer join f.roles r"
				+ "left outer join r.roles u where u.id=? and f.generatemenu='1' order by f.zindex desc";
		return  (List<Function>)this.getCurrentSession().get(hql, id);
				
	}

}
