/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface ISubareaService {

	public void add(Subarea subarea);
	public void edit(Subarea subarea);
	public void delete(Subarea subarea);
	public void pageQuery(PageBean pageBean);
	
	public List<Subarea> findAll();
	
	public List<Subarea> findListNotAssociation();
	
}
