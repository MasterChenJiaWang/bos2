/**
 * 
 */
package com.jiawang.chen.bos.dao;

import java.util.List;

import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.entity.Region;

public interface IRegionDao extends IBaseDao<Region> {

	public List<Region> findByQ(String q);
}
