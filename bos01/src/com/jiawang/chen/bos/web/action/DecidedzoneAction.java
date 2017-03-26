/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.web.action.base.BaseAction;

/**
 *<p>����: DecidedzoneAction </p>
 *<p>���������� </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��17�� ����4:29:09
 *@�汾 
 */
@Controller
@Scope("prototype")
public class DecidedzoneAction extends BaseAction<Decidedzone> {

	private static final Logger logger = Logger.getLogger(DecidedzoneAction.class);
	
	//���ܷ���Id
	private String[] subareaid;
	
	private Integer[] customerIds;
	
	/**
	 * @param subareaid Ҫ���õ� subareaid
	 */
	public void setSubareaid(String[] subareaid) {
		this.subareaid = subareaid;
	}

	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��17�� ����4:31:27
	 */
	public String add(){
		logger.info("���ڽ��ж������");
		decidedzoneService.add(model,subareaid);
		logger.info("���������ɣ�");
		return "list";
	}
	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��17�� ����4:41:15
	 */
	public String delete(){
		logger.info("���ڽ��ж���ɾ����");
		decidedzoneService.delete(model);
		logger.info("����ɾ����ɣ�");
		return "list";
	}
	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��17�� ����4:42:08
	 */
	public String edit(){
		logger.info("���ڽ��ж����޸ģ�");
		decidedzoneService.edit(model);
		logger.info("�����޸���ɣ�");
		return "list";
	}
	
	
	public String pageQuery() throws Exception{
		decidedzoneService.pageQuery(pageBean);
		String[] excludes=new String[]{"decidedzones","subareas","currentPage","detachedCriteria","pageSize"};
		this.WritePageBean2Json(pageBean, excludes);
		return NONE;
	}
	
	public String assigncustomerstodecidedzone(){
		customerService.assignCustomersToDecidedZone(customerIds,model.getId());
		return "list";
	}
}
