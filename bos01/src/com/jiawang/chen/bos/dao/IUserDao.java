/**
 * 
 */
package com.jiawang.chen.bos.dao;


import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.entity.User;

/**
 *<p>����: IUserDao </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��15�� ����3:42:53
 *@�汾 
 */
public interface IUserDao extends IBaseDao<User>{

	public User loginByUserAndPassword(String username,String password);
	public User findUserByUsername(String username);
}
