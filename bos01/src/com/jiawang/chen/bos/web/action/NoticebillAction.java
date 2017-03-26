/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Noticebill;
import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.BOSContext;

import cn.jiawang.chen.crm.entity.Customer;

/**
 *<p>����: NoticebillAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����8:53:17
 *@�汾 
 */
@Controller
@Scope("prototype")
public class NoticebillAction  extends BaseAction<Noticebill>{

	private static final Logger logger = Logger.getLogger(NoticebillAction.class);
	
	/**
	 * 
	 * �ֻ���ѯ�ͻ�
	 *@ʱ�� 2017��2��20�� ����9:13:23
	 */
	public String findCustomerByTelephone() throws Exception{
		logger.info("�������ֻ���ѯ�ͻ�");
		String telephone = model.getTelephone();
		Customer customer = customerService.findCustomerByPhonenumber(telephone);
		String[] excludes=new String[]{ }; 
		this.WriteObject2Json(customer, excludes);
		logger.info("�ֻ���ѯ�ͻ���ɡ���������");
		return NONE;
	}
	
	
	public String add(){
		logger.info("������ӹ���");
		User user = BOSContext.getLoginUser();
		model.setUser(user);
		noticebillService.save(model);
		return "addUI";
	}
}
