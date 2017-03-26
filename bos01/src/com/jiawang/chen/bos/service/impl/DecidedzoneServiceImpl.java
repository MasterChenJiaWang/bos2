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
 *<p>����: DecidedzoneServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��17�� ����4:34:20
 *@�汾 
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
	 *2017��2��17������4:34:44
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
	 *2017��2��17������4:34:44
	 */
	@Override
	public void delete(Decidedzone decidedzone) {

		decidedzoneDao.delete(decidedzone);
	}

	/* 
	 *
	 *2017��2��17������4:34:44
	 */
	@Override
	public void edit(Decidedzone decidedzone) {
		decidedzoneDao.update(decidedzone);
	}

	/* 
	 *
	 *2017��2��17������4:34:44
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		decidedzoneDao.pageQuery(pageBean);
	}

}
