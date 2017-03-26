/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IWorkordermanageDao;
import com.jiawang.chen.bos.entity.Workordermanage;
import com.jiawang.chen.bos.service.IWorkordermanageService;

/**
 *<p>����: WorkordermanageServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 *@�汾 
 */
@Service
@Transactional
public class WorkordermanageServiceImpl  implements IWorkordermanageService{

	@Resource
	private IWorkordermanageDao workordermanageDao;
	@Resource
	private RuntimeService runtimeService;
	@Resource
	private TaskService taskService;
	
	@Resource
	private HistoryService historyService;
	/* 
	 *
	 */
	@Override
	public List<Workordermanage> findListNotStart() {
		String start="0";
		String hql="select * from Workordermanage  w where w.start=:start ";
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("start", start);
		List<Workordermanage> list = workordermanageDao.find(hql, map);
		return list;
		
//		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Workordermanage.class);
//		detachedCriteria.add(Restrictions.eq("start", "0"));
//		return workordermanageDao.findByCriteria(detachedCriteria);
	}

	@Override
	public void start(String id) {
		Workordermanage workordermanage = workordermanageDao.get(id);
		workordermanage.setStart("1");//������
		String processDefinitionKey="transfer";//���̶���key
		String businessKey=id;//ҵ������ ������������ҵ���������������ֵ
		Map<String, Object> map = new HashMap<String,Object>();//���̱���
		map.put("ҵ������", workordermanage);
		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey,map);
	}
	/* 
	 *
	 */
	@Override
	public void save(Workordermanage model) {
		
		workordermanageDao.save(model);
	}
	/* 
	 *
	 *2017��2��21������2:21:43
	 */
	@Override
	public Workordermanage findById(String workordermanageId) {
		
		Workordermanage workordermanage = workordermanageDao.get(workordermanageId);
		return workordermanage;
	}
	/* 
	 *
	 *2017��2��21������2:24:03
	 */
	@Override
	public void checkWorkordermanage(String taskId, Integer check, String workordermanageId) {
		//�����˲�ͨ�����޸Ĺ�����startΪ0
		Workordermanage workordermanage = workordermanageDao.get(workordermanageId);
		//��˲�ͨ��
		//ɾ����ʷ����ʵ������
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		//������������ѯ����ʵ��Id
		Map<String, Object> variables = new HashMap<String,Object>();
		variables.put("check", check);
		//������˹���������
		String processInstanceId = task.getProcessInstanceId();
		taskService.complete(taskId, variables);
		if(check==0){
			workordermanage.setStart("0");
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}
	}

}
