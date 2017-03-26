/**
 * 
 */
package com.jiawang.chen.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.jiawang.chen.bos.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 *<p>标题: BOSLoginInterceptor </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	/* 
	 *拦截方法
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user==null){
			return "login";
		}else{
			return invocation.invoke();// 放行
		}
		
	}

	
}