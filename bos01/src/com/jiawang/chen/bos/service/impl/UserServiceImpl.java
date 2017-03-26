/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IRoleDao;
import com.jiawang.chen.bos.dao.IUserDao;
import com.jiawang.chen.bos.entity.Role;
import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.service.IUserService;
import com.jiawang.chen.bos.web.utils.MD5Utils;
import com.jiawang.chen.bos.web.utils.PageBean;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Autowired
	private  IUserDao userDao;
	
	@Resource
	private IdentityService identityService;
	
	@Resource
	private IRoleDao roleDao;
	/* 
	 *
	 */
	@Override
	public User loginByUsernameAndPassword(String username, String password) {

		return userDao.loginByUserAndPassword(username, password);
	}
	/* 
	 *�޸�����
	 */
	@Override
	public void editpassword(String password, String id) {
		userDao.executeUpdate("editPassword",password,id);
	}
	/* 
	 *����һ���û�  ͬ����activiti��act_id_user  act_id_membership
	 */
	public void save(User user, String[] roleIds) {
		String password = user.getPassword();
		password = MD5Utils.md5(password);
		user.setPassword(password);
		userDao.save(user);//�־ö��� 
//		for (String roleId : roleIds) {
//			Role role = new Role(roleId);
//			//�û�������ɫ
//			user.getRoles().add(role);
//		}
		
		org.activiti.engine.identity.User actUser = new UserEntity(user.getId());
		
		identityService.saveUser(actUser);
		for (String roleId : roleIds) {
			Role role = roleDao.get(roleId);
			//�û�������ɫ
			user.getRoles().add(role);
			identityService.createMembership(actUser.getId(), role.getName());
		}
		
	}
	/* 
	 *
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
//		String hql="from User";
//		int page = pageBean.getCurrentPage();//获取当前页
//		int rows = pageBean.getPageSize();//获取每页显示的数量
//		List<User> list = userDao.find(hql, page, rows);
//		pageBean.setRows(list);
		userDao.pageQuery(pageBean);
	}
	/* 
	 *
	 */
	@Override
	public void add(User model) {
		String username = model.getUsername();
		String password = model.getPassword();//����
		password = MD5Utils.md5(password);//md5����
		model.setPassword(password);
		userDao.save(model);
	}
	/* 
	 *
	 */
	@Override
	public User login(User model) {
		String username = model.getUsername();
		String password = model.getPassword();//����
		password = MD5Utils.md5(password);//md5����
		return userDao.loginByUserAndPassword(username,password);
	}

}
