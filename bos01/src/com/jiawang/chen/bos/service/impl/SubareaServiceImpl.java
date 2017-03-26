/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.ISubareaDao;
import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.service.ISubareaService;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>标题: SubareaServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class SubareaServiceImpl implements ISubareaService {

	@Resource
	private ISubareaDao subareaDao;
	/* 
	 *
	 */
	@Override
	public void add(Subarea subarea) {
			subareaDao.save(subarea);
	}

	/* 
	 *
	 */
	@Override
	public void edit(Subarea subarea) {
		subareaDao.update(subarea);
	}

	/* 
	 *
	 */
	@Override
	public void delete(Subarea subarea) {
		subareaDao.delete(subarea);
	}

	/* 
	 *
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		subareaDao.pageQuery(pageBean);
	}

	/* 
	 *
	 */
	@Override
	public List<Subarea> findAll() {
		return subareaDao.find();
	}

	@Override
	public List<Subarea> findListNotAssociation() {
		Decidedzone decidedzone = new Decidedzone(); 
		if (decidedzone != null && !decidedzone.equals("")) {
			String hql="select * from Subarea  s where s.decidedzone=:decidedzone ";
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("decidedzone",decidedzone);
			List<Subarea> list = subareaDao.find(hql, map);
			return list;
		}
		return null;
		
		
		
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Subarea.class);
//		detachedCriteria.add(Restrictions.isNull("decidedzone"));
//		return subareaDao.findByCriteria(detachedCriteria);
	}

}
