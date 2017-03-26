/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Role;
import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.service.IUserService;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.MD5Utils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ValueStack;

/**
 *<p>����: UserAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 *@�汾 
 */
@Controller
@Scope("prototype")
public class UserAction extends BaseAction<User> {

	
	private static final Logger logger = Logger.getLogger(UserAction.class);
	private String checkcode;
	/**
	 * @param checkcode Ҫ���õ� checkcode
	 */
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public String login(){
		logger.info("���ڵ�½------------------------------");
		ValueStack stack = ActionContext.getContext().getValueStack();
		//���ɵ���֤��
		String key=(String)ServletActionContext.getRequest().getSession().getAttribute("key");
		
		//�ж��û��������֤���Ƿ���ȷ
		if(StringUtils.isNotBlank(key) && checkcode.equals(key)){
			String username = model.getUsername();
			String password = model.getPassword();
			logger.info("���ڵ�½------------------------------"+username);
			Subject subject = SecurityUtils.getSubject();//״̬Ϊ��δ��֤��
			password = MD5Utils.md5(password);
			
			UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), password);
			
			try {
				subject.login(token);
			} catch (AuthenticationException e) {
				e.printStackTrace();
				this.addActionError(this.getText("usernamenotfound"));
				logger.info("��¼ʧ��----");
				return "login";
			}catch (Exception e) {
				e.printStackTrace();
				//���ô�����Ϣ
				this.addActionError(this.getText("loginError"));
				return "login";
			}
			//��ȡ��֤��Ϣ�����д洢��User����
			User user = (User) subject.getPrincipal();
			ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
			logger.info("��¼�ɹ�----");
			return "home";
		}else{
			//��֤�����,���ô�����ʾ��Ϣ����ת����¼ҳ��
			this.addActionError(this.getText("validateCodeError"));
			logger.info("��֤��ʧ��----");
			return "login";
		}	
			//	User user = userService.loginByUsernameAndPassword(username, password);
//			if(user!=null){
//				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
//				logger.info("��¼�ɹ�----");
//				return "home";
//			}else{
//				this.addActionError(this.getText("loginError"));
//				logger.info("��¼ʧ��----");
//				return "login";
//			}
//		}else{
//			this.addActionError(this.getText("validateCodeError"));
//			logger.info("��֤�����----");
//			return "login";
//		}
	}
	
	
	public String login_back(){
		ValueStack valueStack = ActionContext.getContext().getValueStack();
		
		//���ɵ���֤��
		String key = (String) ServletActionContext.getRequest().getSession().getAttribute("key");
		
		//�ж��û��������֤���Ƿ���ȷ
		if(StringUtils.isNotBlank(checkcode) && checkcode.equals(key)){
			//��֤����ȷ
			User user = userService.login(model);
			if(user != null){
				//��¼�ɹ�,��User����session����ת��ϵͳ��ҳ
				ServletActionContext.getRequest().getSession().setAttribute("loginUser", user);
				return "home";
			}else{
				//��¼ʧ�ܣ����ô�����ʾ��Ϣ����ת����¼ҳ��
				this.addActionError(this.getText("loginError"));
				return "login";
			}
		}else{
			//��֤�����,���ô�����ʾ��Ϣ����ת����¼ҳ��
			this.addActionError(this.getText("validateCodeError"));
			return "login";
		}
	}
	
	/**
	 * ע���û�
	 * 
	 *@ʱ�� 2017��2��16�� ����8:56:21
	 */
	public String logout(){
		logger.info("����ע��----");
		ServletActionContext.getRequest().getSession().invalidate();
		logger.info("ע���ɹ�----");
		return "login";
	}
	
	@RequiresPermissions(value="staff")
	@RequiresRoles(value="abc")
	public void eidtPassword() throws Exception{
		logger.info("�����޸��û�����----");
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		
		String password = model.getPassword();
		password= MD5Utils.md5(password);
		String flag="1";
		String id = user.getId();
		try {
			userService.editpassword(password, id);
			logger.info("�޸�����ɹ�----");
		} catch (Exception e) {
			logger.info("�޸�����ʧ��----");
			flag="0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
	}
	
	
	//���ս�ɫ����
		private String[] roleIds;
		
		
	/**
		 * @return roleIds
		 */
		public String[] getRoleIds() {
			return roleIds;
		}

		/**
		 * @param roleIds Ҫ���õ� roleIds
		 */
		public void setRoleIds(String[] roleIds) {
			this.roleIds = roleIds;
		}

/**
 * ����û���ɫ
 * 
 *@ʱ�� 2017��2��21�� ����9:02:05
 */
	public String add(){
		logger.info("��������û���ɫ");
		userService.save(model, roleIds);
		logger.info("����û���ɫ���");
		return "list";
	}
	
	/**
	 * �û���ҳ��ѯ
	 * @throws IOException 
	 */
	public String pageQuery() throws Exception{
		logger.info("�����û���ҳ��ѯ");
		userService.pageQuery(pageBean);
		String[] excludes = new String[]{"noticebills","roles"};
		this.WriteObject2Json(pageBean, excludes );
		logger.info("�û���ҳ��ѯ���");
		return NONE;
	}
	
	
	
}
