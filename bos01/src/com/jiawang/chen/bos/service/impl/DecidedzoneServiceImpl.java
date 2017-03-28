/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IDecidedzoneDao;
import com.jiawang.chen.bos.dao.ISubareaDao;
import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.service.IDecidedzoneService;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>标题: DecidedzoneServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class DecidedzoneServiceImpl implements IDecidedzoneService {

	@Resource
	private IDecidedzoneDao decidedzoneDao;
	
	@Resource
	private ISubareaDao subareaDao;
	/* 
	 *
	 */
	@Override
	public void add(Decidedzone decidedzone,String[] subareaid) {
		decidedzoneDao.save(decidedzone);
		for(String id:subareaid){
			Subarea subarea = subareaDao.get(id);
			subarea.setDecidedzone(decidedzone);
		}
	}

	/* 
	 *
	 */
	@Override
	public void delete(Decidedzone decidedzone) {

		decidedzoneDao.delete(decidedzone);
	}

	/* 
	 *
	 */
	@Override
	public void edit(Decidedzone decidedzone) {
		decidedzoneDao.update(decidedzone);
	}

	/* 
	 *
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

	@Override
	public void deleteBatch(String ids) {
		String[] idSubarea = ids.split(",");
		for(String id:idSubarea){
			decidedzoneDao.delete(id);
		}
	}
}
