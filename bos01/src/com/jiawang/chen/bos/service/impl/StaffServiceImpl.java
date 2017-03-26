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
 *<p>����: StaffServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��15�� ����7:07:09
 *@�汾 
 */
@Service
@Transactional
public class StaffServiceImpl implements IStaffService{

	private static final Logger logger = Logger.getLogger(StaffServiceImpl.class);
	@Resource
	private IStaffDao staffDao;
	/* 
	 *
	 *2017��2��15������7:07:21
	 */
	@Override
	public void save(Staff staff) {
		logger.info("service-->>>>>ȡ��Ա���ڱ���----");
		staffDao.save(staff);
		logger.info("service-->>>>>ȡ��Ա����ɹ�----");
	}

	/* 
	 *
	 *2017��2��15������7:07:21
	 */
	@Override
	public void update(Staff staff) {
		logger.info("service-->>>>>ȡ��Ա�����޸�----");
		staffDao.update(staff);
		logger.info("service-->>>>>ȡ��Ա�޸ĳɹ�----");
	}

	/* 
	 *
	 *2017��2��15������7:07:21
	 */
	@Override
	public void delete(Staff staff) {
		logger.info("service-->>>>>ȡ��Աɾ��----");
		staffDao.delete(staff);
		logger.info("service-->>>>>ȡ��Աɾ���ɹ�----");
	}

	/* 
	 *
	 *2017��2��15������7:07:21
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		staffDao.pageQuery(pageBean);
	}

	/* 
	 *
	 *2017��2��15������7:07:21
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
	 *2017��2��15������7:07:21
	 */
	@Override
	public Staff findById(String id) {
		
		return staffDao.get(id);
		
	}

	/* 
	 *��ԭ
	 *2017��2��16������2:54:05
	 */
	@Override
	public void restoreBatch(String ids) {
		String[] idStaff = ids.split(",");
		if(idStaff!=null && idStaff.length>0){
			for(String id:idStaff){
				Staff staff = staffDao.get(id);
				String deltag = staff.getDeltag();
				if(deltag=="1"){
					staffDao.executeUpdate("staff.restore", id);
				}
				
			}
		}
	}

	@Override
	public List<Staff> findNotDelete() {
		
		String deltag="0";
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
