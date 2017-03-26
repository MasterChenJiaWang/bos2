/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Workordermanage;
import com.jiawang.chen.bos.service.IWorkordermanageService;
import com.jiawang.chen.bos.web.utils.BOSContext;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *<p>����: TaskAction </p>
 *<p>������������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��21�� ����1:54:26
 *@�汾 
 */
@Controller
@Scope("prototype")
public class TaskAction extends ActionSupport {

	@Resource
	private TaskService taskService;
	@Resource
	private IWorkordermanageService workordermanageService;
	@Resource
	private RuntimeService runtimeService;
	
	private static final Logger logger = Logger.getLogger(TaskAction.class);
	/**
	 * ��ѯ������
	 * 
	 *@ʱ�� 2017��2��21�� ����1:59:28
	 */
	public String findGroupTask(){
		logger.info("���ڲ�ѯ������");
		TaskQuery query = taskService.createTaskQuery();
		String candidateUser = BOSContext.getLoginUser().getId();
		//���������
		query.taskCandidateUser(candidateUser);
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		logger.info("��ѯ��������ɡ�����������������");
		return "grouptasklist";
	}
	
	
	private String taskId;
	
	/**
	 * @return taskId
	 */
	public String getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId Ҫ���õ� taskId
	 */
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	/**
	 * ʰȡ������
	 * 
	 *@ʱ�� 2017��2��21�� ����2:07:16
	 */
	public String showData(){
		logger.info("����ʰȡ������");
		String userId = BOSContext.getLoginUser().getId();
		taskService.claim(taskId, userId);
		logger.info("ʰȡ��������ɡ�������������������������");
		return "togrouptasklist";
	}
	
	/**
	 * 
	 * ��ѯ��������
	 *@ʱ�� 2017��2��21�� ����2:12:39
	 */
	public String findPersonalTask(){
		logger.info("���ڲ�ѯ��������");
		TaskQuery query = taskService.createTaskQuery();
		String assignee = BOSContext.getLoginUser().getId();
		//�����������
		query.taskAssignee(assignee);
		List<Task> list = query.list();
		ActionContext.getContext().getValueStack().set("list", list);
		logger.info("��ѯ����������ɡ�������������������������������");
		return "personaltasklist";
	}
	
	private Integer  check;
	


	/**
	 * @return check
	 */
	public Integer getCheck() {
		return check;
	}

	/**
	 * @param check Ҫ���õ� check
	 */
	public void setCheck(Integer check) {
		this.check = check;
	}

	/**
	 * ������˹���������
	 * 
	 *@ʱ�� 2017��2��21�� ����2:18:45
	 */
	public String checkworkorderManage(){
		logger.info("���ڰ�����˹���������");
		// ��������id��ѯ�������
				Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
				// ������������ѯ����ʵ��id
				String processInstanceId = task.getProcessInstanceId();
				// ��������ʵ��id��ѯ����ʵ������
				ProcessInstance processInstance = runtimeService
						.createProcessInstanceQuery()
						.processInstanceId(processInstanceId).singleResult();
				String workordermanageId = processInstance.getBusinessKey();
				Workordermanage workordermanage = workordermanageService.findById(workordermanageId);
				if(check == null){
					//��ת�����ҳ��
					// ��ת��һ����˹�����ҳ�棬չʾ��ǰ��Ӧ�Ĺ�������Ϣ
					ActionContext.getContext().getValueStack().set("map", workordermanage);
					logger.info("������˹���������  ʧ�ܡ�����");
					return "check";
				}else{
					workordermanageService.checkWorkordermanage(taskId,check,workordermanageId);
					logger.info("������˹�����������ɡ�������������������������");
					return "topersonaltasklist";
				}
	}
	
	
	/**
	 * 
	 * �����������
	 *@ʱ�� 2017��2��21�� ����2:34:11
	 */
	public String outStore(){
		logger.info("���ڰ����������");
		taskService.complete(taskId);
		logger.info("�������������ɡ�����������������������");
		return "topersonaltasklist";
	}
	
	/**
	 * ������������
	 * 
	 *@ʱ�� 2017��2��21�� ����2:35:53
	 */
	public String transferGoods(){
		logger.info("���ڰ�����������");
		taskService.complete(taskId);
		
		logger.info("��������������ɡ�������������������������");
		return "topersonaltasklist";
	}
	
	/**
	 * 
	 * ����ǩ������
	 *@ʱ�� 2017��2��21�� ����2:36:15
	 */
	public String receive(){
		logger.info("���ڰ���ǩ������");
		taskService.complete(taskId);
		logger.info("����ǩ��������ɡ���������������������");
		return "topersonaltasklist";
	}
}
