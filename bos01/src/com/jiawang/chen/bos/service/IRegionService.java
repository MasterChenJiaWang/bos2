/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IRegionService {

	public void save(Region region);
	
	public void delete(Region region);
	
	public void saveBacth(List<Region> list);
	
	public void pageQuery(PageBean pageBean);

	
	public List<Region> findAll();

	
	public List<Region> findByQ(String q);

	
	public void update(Region region);

	/**
	 * 
	 */
	public void deleteBatch(String ids);
}
