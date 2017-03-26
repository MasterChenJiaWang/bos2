/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Role;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: IRoleService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����11:35:31
 *@�汾 
 */
public interface IRoleService {

	public void add(Role model,String ids);

	/**
	 * 
	 *@ʱ�� 2017��2��20�� ����11:47:32
	 */
	public List<Role> findAll();

	/**
	 * 
	 *@ʱ�� 2017��2��20�� ����11:48:09
	 */
	public void pageQuery(PageBean pageBean);
}
