/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Role;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IRoleService {

	public void add(Role model,String ids);

	
	public List<Role> findAll();

	
	public void pageQuery(PageBean pageBean);
}
