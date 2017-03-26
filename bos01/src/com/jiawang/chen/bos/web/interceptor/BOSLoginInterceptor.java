/**
 * 
 */
package com.jiawang.chen.bos.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.jiawang.chen.bos.entity.User;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 *<p>����: BOSLoginInterceptor </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��15�� ����1:38:00
 *@�汾 
 */
public class BOSLoginInterceptor extends MethodFilterInterceptor {

	/* 
	 *���ط���
	 *2017��2��15������1:43:20
	 */
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		if(user==null){
			return "login";
		}else{
			return invocation.invoke();// ����
		}
		
	}

	
}
