/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IUserService {
	public User loginByUsernameAndPassword(String username,String password);
	public void editpassword(String password,String id);

	public void save(User user, String[] roleIds);

	public void pageQuery(PageBean pageBean);
	
	public void add(User model);
	
	public User login(User model);
	
}
