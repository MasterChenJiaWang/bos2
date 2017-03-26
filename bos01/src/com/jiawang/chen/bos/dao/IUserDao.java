/**
 * 
 */
package com.jiawang.chen.bos.dao;


import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.entity.User;

public interface IUserDao extends IBaseDao<User>{

	public User loginByUserAndPassword(String username,String password);
	public User findUserByUsername(String username);
}
