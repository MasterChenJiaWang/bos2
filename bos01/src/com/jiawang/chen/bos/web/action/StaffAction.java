/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.IOError;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.PageBean;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 *<p>����: StaffAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��15�� ����7:19:08
 *@�汾 
 */
@Controller
@Scope("prototype")
public class StaffAction extends BaseAction<Staff>{

	private static final Logger logger = Logger.getLogger(StaffAction.class);
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}

	
	/**
	 * ȡ��Ա ��ҳ
	 * 
	 *@ʱ�� 2017��2��16�� ����8:44:23
	 */
	public String pageQuery() throws Exception{
		
		logger.info("���ڽ���ȡ��Ա��ҳ-------");
		staffService.pageQuery(pageBean);
		//��PageBean����תΪjson����
		String[] excludes=new String[]{"decidedzones","currentPage","detachedCriteria","pageSize"};
		this.WritePageBean2Json(pageBean, excludes);
		logger.info("ȡ��Ա��ҳ�ɹ�-------");
		return NONE;
	}
	
	/**
	 * ȡ��Ա ���
	 * 
	 *@ʱ�� 2017��2��16�� ����8:46:44
	 */
	public String  add(){
		logger.info("������� ȡ��Ա-------");
		staffService.save(model);
		logger.info("ȡ��Ա��ӳɹ�-------");
		return "list";
	}
	
	/**
	 * ɾ��ȡ��Ա  ����ɾ��
	 * 
	 *@ʱ�� 2017��2��16�� ����8:50:20
	 */
	public String delete(){
		logger.info("���ڽ���ȡ��Աɾ��-------");
	//	String ids = model.getId();
		staffService.deleteBatch(ids);
		logger.info("ȡ��Աɾ���ɹ�-------");
		return "list";
	}
	
	/**
	 *��ԭ 
	 *@ʱ�� 2017��2��16�� ����3:17:24
	 */
	public String restore( ){
		logger.info("���ڽ���ȡ��Ա��ԭ-------");
	//	String ids = model.getId();
		staffService.restoreBatch(ids);
		logger.info("ȡ��Ա��ԭ�ɹ�-------");
		return "list";
	}
	
	/**
	 * ��ѯ
	 * 
	 *@ʱ�� 2017��2��16�� ����3:26:21
	 */
	public String listajax() throws Exception{
		 List<Staff> list = staffService.findNotDelete();
		 String[] excludes=new String[]{"decidedzones"};
		this .WriteList2Json(list, excludes);
			return NONE;
	}
}
