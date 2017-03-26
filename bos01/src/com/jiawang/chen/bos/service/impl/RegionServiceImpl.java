/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IRegionDao;
import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.service.IRegionService;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: RegionServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��16�� ����11:22:59
 *@�汾 
 */
@Service
@Transactional
public class RegionServiceImpl implements IRegionService{

	@Resource
	private IRegionDao regionDao;
	/* 
	 *
	 *2017��2��16������11:35:06
	 */
	@Override
	public void save(Region region) {
		
		regionDao.save(region);
	}

	/* 
	 *
	 *2017��2��16������11:35:06
	 */
	@Override
	public void delete(Region region) {
		regionDao.delete(region);
	}

	/* 
	 *
	 *2017��2��16������11:39:39
	 */
	@Override
	public void saveBacth(List<Region> list) {
		regionDao.batchSave(list);
	}

	/* 
	 *
	 *2017��2��16������1:30:28
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		
		regionDao.pageQuery(pageBean);
	}

	/* 
	 *
	 *2017��2��16������5:01:26
	 */
	@Override
	public List<Region> findAll() {
	   return	regionDao.find();
		
	}

	/* 
	 *
	 *2017��2��16������5:14:46
	 */
	@Override
	public List<Region> findByQ(String q) {
		return regionDao.findByQ(q);
		
	}

	/* 
	 *
	 *2017��2��17������9:17:30
	 */
	@Override
	public void update(Region region) {
		regionDao.update(region);
	}
	
}
