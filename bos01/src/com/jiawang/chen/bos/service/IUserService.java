/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: IUserService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��15�� ����3:59:03
 *@�汾 
 */
public interface IUserService {
	public User loginByUsernameAndPassword(String username,String password);
	public void editpassword(String password,String id);
	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����8:17:35
	 */
	public void save(User user, String[] roleIds);
	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����8:50:02
	 */
	public void pageQuery(PageBean pageBean);
	/**
	 * ����û�
	 *@ʱ�� 2017��2��21�� ����8:52:28
	 */
	public void add(User model);
	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����8:55:00
	 */
	public User login(User model);
	
}
