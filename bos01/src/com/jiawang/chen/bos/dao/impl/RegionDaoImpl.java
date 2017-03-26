/**
 * 
 */
package com.jiawang.chen.bos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jiawang.chen.bos.dao.IRegionDao;
import com.jiawang.chen.bos.dao.base.impl.BaseDaoImpl;
import com.jiawang.chen.bos.entity.Region;

/**
 *<p>标题: RegionDaoImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Repository
public class RegionDaoImpl extends BaseDaoImpl<Region> implements IRegionDao {

	/* 
	 *
	 */
	@Override
	public List<Region> findByQ(String q) {
		String hql="FROM Region WHERE province LIKE ? OR city LIKE ? OR district LIKE ?";
		
		Map<String, Object> map= new HashMap<String, Object>();
		map.put("q",q);
		
		return 	this.find(hql, map);
		
		
	}

	
}
