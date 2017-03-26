/**
 * 
 */
package com.jiawang.chen.bos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jiawang.chen.bos.dao.IUserDao;
import com.jiawang.chen.bos.dao.base.impl.BaseDaoImpl;
import com.jiawang.chen.bos.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

	/* 
	 *
	 */
	@Override
	public User loginByUserAndPassword(String username, String password) {
//		String hql="from User u where u.username='"+username+"' and u.password='"+password+"'";
		String hql="from User u where u.username=? and u.password=?";
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		List<User> list = this.find( hql, map);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/* 
	 *
	 */
	@Override
	public User findUserByUsername(String username) {
		String hql = "FROM User u WHERE u.username = ? ";
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("username", username);
		List<User> list = this.find( hql, map);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}
