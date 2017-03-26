/**
 * 
 */
package com.jiawang.chen.bos.dao;

import java.util.List;

import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.entity.Function;

public interface IFunctionDao extends IBaseDao<Function> {

	/**
	 * 
	 */
	public List<Function> findListByUserid(String userid);

	/**
	 * 
	 */
	public List<Function> findAllMenu();

	/**
	 * 
	 */
	public List<Function> findMenuByUserid(String id);

}
