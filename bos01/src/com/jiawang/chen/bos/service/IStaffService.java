/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IStaffService {

	public void save(Staff staff);
	
	public void update(Staff staff);
	
	public void delete(Staff staff);
	
	public void restoreBatch(String ids);
	
	public void pageQuery(PageBean pageBean);
	
	public void deleteBatch(String ids);
	
	public Staff findById(String id);
	
	public List<Staff> findNotDelete();


}
