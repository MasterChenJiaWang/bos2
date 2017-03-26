/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 *<p>����: ProcessDefinitionAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��21�� ����9:50:07
 *@�汾 
 */
@Controller
@Scope("prototype")
public class ProcessDefinitionAction extends ActionSupport {

	@Resource
	private RepositoryService repositoryService;
	
	private static final Logger logger = Logger.getLogger(ProcessDefinitionAction.class);
	/**
	 * ��ѯ���°汾���̶����б�����
	 * 
	 *@ʱ�� 2017��2��21�� ����9:58:18
	 */
	public String list(){
		logger.info("���ڲ�ѯ���°汾���̶����б�����");
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.latestVersion();//��ѯ���°汾
		query.orderByProcessDefinitionName().desc();//����
		List<ProcessDefinition>   list=query.list();//ִ�в�ѯ
		// ѹջ
		ActionContext.getContext().getValueStack().set("list", list);
		logger.info("��ѯ���°汾���̶����б�������ɡ���������");
		return "list";
	}
	
	
	// �����ϴ����ļ�
		private File zipFile;

		public void setZipFile(File zipFile) {
			this.zipFile = zipFile;
		}
	/**
	 * �������̶���
	 * 
	 *@ʱ�� 2017��2��21�� ����10:04:59
	 */
	public String deploy( ) throws FileNotFoundException{
		logger.info("���ڲ������̶���");
		DeploymentBuilder deploymentBuilder = repositoryService
				.createDeployment();
		deploymentBuilder.addZipInputStream(new ZipInputStream(
				new FileInputStream(zipFile)));
		deploymentBuilder.deploy();
		
		logger.info("�������̶�����ɡ�������");
		return "toList";
	}
	
	
	
	//�������̶���id
	private String id;
	public void setId(String id) {
		this.id = id;
	}
	
	/**
	 * չʾpngͼƬ
	 */
	public String showpng(){
		//��ȡpngͼƬ��Ӧ��������
		InputStream pngStream = repositoryService.getProcessDiagram(id);
		ActionContext.getContext().getValueStack().set("pngStream", pngStream);
		return "showpng";
	}
	
	/**
	 * ɾ�����̶���
	 */
	public String delete(){
		String deltag = "0";
		//�������̶���id��ѯ����id
		ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
		query.processDefinitionId(id);//����id����
		ProcessDefinition processDefinition = query.singleResult();
		String deploymentId = processDefinition.getDeploymentId();
		try{
			repositoryService.deleteDeployment(deploymentId);
		}catch (Exception e) {
			//��ǰҪɾ�������̶�������ʹ��
			deltag = "1";
			ActionContext.getContext().getValueStack().set("deltag", deltag);
			ProcessDefinitionQuery query2 = repositoryService
					.createProcessDefinitionQuery();
			query2.latestVersion();// ��ѯ���°汾
			query2.orderByProcessDefinitionName().desc();// ����
			List<ProcessDefinition> list = query2.list();// ִ�в�ѯ
			// ѹջ
			ActionContext.getContext().getValueStack().set("list", list);
			return "list";
		}
		return "toList";
	}

}
