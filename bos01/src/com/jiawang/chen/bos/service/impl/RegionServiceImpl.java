/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IRegionDao;
import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.service.IRegionService;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>标题: RegionServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService{

	@Resource
	private IRegionDao regionDao;
	/* 
	 *
	 */
	@Override
	public void save(Region region) {
//		String id = UUID.randomUUID().toString();
//		region.setId(id);
		regionDao.save(region);
	}

	/* 
	 *
	 */
	@Override
	public void delete(Region region) {
		regionDao.delete(region);
	}

	/* 
	 *
	 */
	@Override
	public void saveBacth(List<Region> list) {
		regionDao.batchSave(list);
	}

	/* 
	 *
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		
		regionDao.pageQuery(pageBean);
	}

	/* 
	 *
	 */
	@Override
	public List<Region> findAll() {
	   return	regionDao.find();
		
	}

	/* 
	 *
	 */
	@Override
	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
		
	}

	/* 
	 *
	 */
	@Override
	public void update(Region region) {
		regionDao.update(region);
	}

	/* 
	 *
	 *2017年3月28日上午8:31:45
	 */
	@Override
	public void deleteBatch(String ids) {
		
		String[] idString = ids.split(",");
		if( StringUtils.isNoneBlank(idString)&& idString.length>0){
			
			for(String id:idString){
				regionDao.delete(id);
			}
		}
	}
	
}
