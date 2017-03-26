/**
 * 
 */
package com.jiawang.chen.bos.web.utils;

import org.apache.struts2.ServletActionContext;

import com.jiawang.chen.bos.entity.User;

/**
 *<p>标题: BOSContext </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class BOSContext {

	public static User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}
}