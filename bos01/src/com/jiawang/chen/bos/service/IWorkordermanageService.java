/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Workordermanage;

/**
 *<p>����: IWorkordermanageService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��21�� ����12:06:52
 *@�汾 
 */
public interface IWorkordermanageService {

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����12:08:07
	 */
	public List<Workordermanage> findListNotStart();

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����1:46:40
	 */
	public void start(String id);

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����1:56:12
	 */
	public void save(Workordermanage model);

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����2:21:35
	 */
	public Workordermanage findById(String workordermanageId);

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����2:23:52
	 */
	public void checkWorkordermanage(String taskId, Integer check, String workordermanageId);

}
