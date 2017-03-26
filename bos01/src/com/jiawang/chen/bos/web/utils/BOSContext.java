/**
 * 
 */
package com.jiawang.chen.bos.web.utils;

import org.apache.struts2.ServletActionContext;

import com.jiawang.chen.bos.entity.User;

/**
 *<p>����: BOSContext </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 *@�汾 
 */
public class BOSContext {

	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}
