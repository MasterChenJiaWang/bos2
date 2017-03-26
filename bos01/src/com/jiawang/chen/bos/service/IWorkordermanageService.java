/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Workordermanage;


public interface IWorkordermanageService {


	public List<Workordermanage> findListNotStart();

	
	public void start(String id);

	
	public void save(Workordermanage model);

	
	public Workordermanage findById(String workordermanageId);


	public void checkWorkordermanage(String taskId, Integer check, String workordermanageId);

}
