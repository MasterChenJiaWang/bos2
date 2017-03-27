/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IStaffDao;
import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.entity.Workordermanage;
import com.jiawang.chen.bos.service.IStaffService;
import com.jiawang.chen.bos.web.action.StaffAction;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>标题: StaffServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService{

	private static final Logger logger = Logger.getLogger(StaffServiceImpl.class);
	@Resource
	private IStaffDao staffDao;
	/* 
	 *
	 */
	@Override
	public void save(Staff staff) {
		logger.info("service-->>>>>取派员正在保存----");
		staffDao.save(staff);
		logger.info("service-->>>>>取派员保存成功----");
	}

	/* 
	 *
	 */
	@Override
	public void update(Staff staff) {
		logger.info("service-->>>>>取派员正在修改----");
		staffDao.update(staff);
		logger.info("service-->>>>>取派员修改成功----");
	}

	/* 
	 *
	 */
	@Override
	public void delete(Staff staff) {
		logger.info("service-->>>>>取派员删除----");
		staffDao.delete(staff);
		logger.info("service-->>>>>取派员删除成功----");
	}

	/* 
	 *
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	/* 
	 *
	 */
	@Override
	public void deleteBatch(String ids) {
			
		String[] idStaff = ids.split(",");
	//	if(idStaff!=null && idStaff.length>0){
			for(String id:idStaff){
				staffDao.executeUpdate("staff.delete", id);
			}
	//	}
	}

	/* 
	 *
	 */
	@Override
	public Staff findById(String id) {
		
		return staffDao.get(Staff.class,id);
		
	}

	/* 
	 *还原
	 */
	@Override
	public void restoreBatch(String ids) {
		String[] idStaff = ids.split(",");
		if(idStaff!=null && idStaff.length>0){
			for(String  id:idStaff){
					staffDao.executeUpdate("staff.restore", id);
			}
		
		}
	}

	/* 
	 *查询没有作废的取派员 
	 */
	@Override
	public List<Staff> findNotDelete() {
		
		String deltag="1";
		String hql="select * from Staff  s where s.deltag=:deltag ";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("deltag", deltag);
		List<Staff> list = staffDao.find(hql, map);
		return list;
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Staff.class);
//		detachedCriteria.add(Restrictions.ne("deltag","1"));
//		return staffDao.findByCriteria(detachedCriteria);
		
	}


}
