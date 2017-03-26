/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Workordermanage;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.opensymphony.xwork2.ActionContext;

/**
 *<p>����: WorkordermanageAction </p>
 *<p>���������������� </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��21�� ����12:04:16
 *@�汾 
 */
@Controller
@Scope("prototype")
public class WorkordermanageAction extends BaseAction<Workordermanage> {

	private static final Logger logger = Logger.getLogger(WorkordermanageAction.class);
	
	
	public String add() throws Exception{
		logger.info("������ӹ�����");
		String flag = "1";
		try{
			workordermanageService.save(model);
		}catch (Exception e) {
			flag = "0";
		}
		ServletActionContext.getResponse().setContentType(
				"text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		logger.info("��ӹ�������ɡ�������������������");
		return NONE;
	}
	
	/**
	 * ��ѯstart Ϊ0�Ĺ�����
	 * 
	 *@ʱ�� 2017��2��21�� ����12:08:55
	 */
	public String list(){
		logger.info("���ڲ�ѯstart Ϊ0�Ĺ�����");
		List<Workordermanage> list=workordermanageService.findListNotStart();
		ActionContext.getContext().getValueStack().set("list", list);
		logger.info("��ѯstart Ϊ0�Ĺ�������ɡ���������������������������");
		return "list";
	}
	
	/**
	 * ����������������
	 * 
	 *@ʱ�� 2017��2��21�� ����1:47:19
	 */
	public String start(){
		logger.info("��������������������");
		String id = model.getId();//������id
		workordermanageService.start(id);//��������ʵ��
		logger.info("�������������������");
		return "toList";
	}
	
}
