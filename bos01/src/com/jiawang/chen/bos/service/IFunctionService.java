/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.jiawang.chen.bos.entity.Function;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IFunctionService {

	public void pageQuery(PageBean pageBean);
	
	public List<Function> findAll();
	
	public  void add(Function model);

	
	public List<Function> findMenu();
}
