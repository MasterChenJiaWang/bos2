/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: ISubareaService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��17�� ����9:31:44
 *@�汾 
 */
public interface ISubareaService {

	public void add(Subarea subarea);
	public void edit(Subarea subarea);
	public void delete(Subarea subarea);
	public void pageQuery(PageBean pageBean);
	/**
	 * 
	 *@ʱ�� 2017��2��17�� ����10:06:12
	 */
	public List<Subarea> findAll();
	/**
	 * 
	 *@ʱ�� 2017��2��17�� ����4:08:29
	 */
	public List<Subarea> findListNotAssociation();
	
}
