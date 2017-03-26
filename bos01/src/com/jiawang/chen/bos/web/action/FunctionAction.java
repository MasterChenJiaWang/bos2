/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Function;
import com.jiawang.chen.bos.web.action.base.BaseAction;

/**
 *<p>����: FunctionAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����10:44:49
 *@�汾 
 */
@Controller
@Scope("prototype")
public class FunctionAction extends BaseAction<Function> {

	private static final Logger logger = Logger.getLogger(FunctionAction.class);
	
	
	public String pageQuery() throws Exception{
		functionService.pageQuery(pageBean);
		String[] excludes=new String[]{"function","functions","roles","currentPage","detachedCriteria","pageSize"};
		this.WriteObject2Json(pageBean, excludes);
		logger.info("Ȩ�޷�ҳ�ɹ�");
		return NONE;
	}
	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��20�� ����10:53:45
	 */
	public String listajax() throws Exception{
		List<Function> list = functionService.findAll();
		String[] excludes = new String[]{"function","functions","roles"};
		this.WriteList2Json(list, excludes );
		logger.info("Ȩ���첽����ɹ�");
		return NONE;
	}
	
	/**
	 * ���Ȩ��
	 */
	public String add(){
		functionService.add(model);
		return "list";
	}
	
	/**
	 * 
	 * ���ݵ�½�˲�ѯ��Ӧ�Ĳ˵����ݣ���Ȩ�ޱ��в�ѯ��
	 *@ʱ�� 2017��2��21�� ����9:23:26
	 */
	public String findMenu() throws Exception{
		List<Function> list=functionService.findMenu();
		String[] excludes=new String[]{"functions","function","roles"};
		this.WriteList2Json(list, excludes);
		return NONE;
	}
}
