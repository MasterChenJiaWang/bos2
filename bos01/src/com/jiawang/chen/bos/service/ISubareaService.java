/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface ISubareaService {

	public void add(Subarea subarea);
	public void edit(Subarea subarea);
	public void delete(Subarea subarea);
	public void pageQuery(PageBean pageBean);
	
	public List<Subarea> findAll();
	
	public List<Subarea> findListNotAssociation();
	/**
	 * 
	 *@时间 2017年3月28日 上午9:58:15
	 */
	public void saveBacth(List<Subarea> list);
	/**
	 * 
	 *@时间 2017年3月28日 下午1:28:15
	 */
	public void deleteBatch(String ids);
		
}
