/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Role;
import com.jiawang.chen.bos.web.action.base.BaseAction;

/**
 *<p>����: RoleAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����11:33:09
 *@�汾 
 */
@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role> {

	private static final Logger logger = Logger.getLogger(RoleAction.class);
	
	private String ids;

	/**
	 * @return ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids Ҫ���õ� ids
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	

	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��20�� ����11:46:21
	 */
	public String add(){
		roleService.add(model,ids);
		logger.info("��ӽ�ɫȨ�޳ɹ�");
		return "list";
	}
	
	
	/**
	 * ��ҳ��ѯ����
	 * @throws IOException 
	 */
	public String pageQuery() throws Exception{
		logger.info("���ڷ�ҳ��ѯ��ɫ");
		roleService.pageQuery(pageBean);
		//��PageBean����תΪjson����
		this.WriteObject2Json(pageBean, new String[]{"functions","users","currentPage","detachedCriteria","pageSize"});
		logger.info("��ҳ��ѯ��ɫ��ɡ�������������������������");
		return NONE;
	}
	
	/**
	 * ��ѯ���еĽ�ɫ���ݣ�����json
	 * @return
	 * @throws IOException
	 */
	public String listajax() throws Exception{
		
		logger.info("���ڲ�ѯ���еĽ�ɫ���ݣ�����json");
		List<Role> list = roleService.findAll();
		String[] excludes = new String[]{"functions","users"};
		this.WriteList2Json(list, excludes );
		logger.info("��ѯ���еĽ�ɫ���� ��ɡ�������������������������");
		return NONE;
	}
}
