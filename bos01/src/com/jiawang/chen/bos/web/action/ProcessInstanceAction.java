/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *<p>����: ProcessInstanceAction </p>
 *<p>����������ʵ������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��21�� ����10:31:10
 *@�汾 
 */
@Controller
@Scope("prototype")
public class ProcessInstanceAction extends ActionSupport {

	@Resource
	private RuntimeService runtimeService;
	@Resource
	private RepositoryService repositoryService;
	
	private static final Logger logger = Logger.getLogger(ProcessDefinitionAction.class);


	/**
	 * ��ѯ����ʵ���б�����
	 * 
	 *@ʱ�� 2017��2��21�� ����10:32:48
	 */
	public String list(){
		logger.info("���ڲ�ѯ����ʵ���б�����");
		ProcessInstanceQuery query = runtimeService.createProcessInstanceQuery();
		query.orderByProcessInstanceId().desc();
		List<ProcessInstance> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		logger.info("��ѯ����ʵ���б�������ɡ�������������������������");
		return "list";
	}


	private String id;//��������ʵ��id
	
	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id Ҫ���õ� id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * ��������ʵ��id��ѯ��Ӧ�����̱�������
	 * 
	 *@ʱ�� 2017��2��21�� ����10:40:05
	 */
	public String findData() throws Exception{
		Map<String, Object> map= runtimeService.getVariables(id);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(map.toString());
		return NONE;
	}

	private String	deploymentId;
	private String	imageName;
	/**
	 * ��������ʵ��id��ѯ���ꡢ����id ͼƬ����
	 * 
	 *@ʱ�� 2017��2��21�� ����10:47:27
	 */
	public String showPng(){
		logger.info("����  ��������ʵ��id��ѯ���ꡢ����id ͼƬ����");
		//1����������ʵ��id��ѯ����ʵ������
				ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(id).singleResult();
				//2����������ʵ�������ѯ���̶���id
				String processDefinitionId = processInstance.getProcessDefinitionId();
				//3���������̶���id��ѯ���̶������
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
				//4���������̶�������ѯ����id
				deploymentId = processDefinition.getDeploymentId();
				imageName = processDefinition.getDiagramResourceName();
//				String deploymentId = processDefinition.getDeploymentId();
				//��ѯ����
				//1����õ�ǰ����ʵ��ִ�е��ĸ��ڵ�
				String activityId = processInstance.getActivityId();//usertask1
				//2������bpmn��xml���ļ������һ�����̶������
				ProcessDefinitionEntity pd = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processDefinitionId);//��ѯact_ge_bytearray
				//3������activitiId��ȡ����������Ϣ�Ķ���
				ActivityImpl findActivity = pd.findActivity(activityId);
				int x = findActivity.getX();
				int y = findActivity.getY();
				int width = findActivity.getWidth();
				int height = findActivity.getHeight();
				
				ActionContext.getContext().getValueStack().set("x", x);
				ActionContext.getContext().getValueStack().set("y", y);
				ActionContext.getContext().getValueStack().set("width", width);
				ActionContext.getContext().getValueStack().set("height", height);
				logger.info("��������ʵ��id��ѯ���ꡢ����id ͼƬ������ɡ�����������������");
				return "showPng";

	}


	/**
	 * ��ȡpng������
	 * 
	 *@ʱ�� 2017��2��21�� ����10:57:19
	 */
	public String viewImage(){
		logger.info("���ڻ�ȡpng������");
		InputStream pngStream = repositoryService.getResourceAsStream(deploymentId, imageName);
		ActionContext.getContext().getValueStack().set("pngSteam",pngStream);
		logger.info("��ȡpng��������ɡ���������������������");
		return "viewImage";
	}








}
