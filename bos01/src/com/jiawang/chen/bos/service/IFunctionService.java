/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.jiawang.chen.bos.entity.Function;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: IFunctionService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����10:47:10
 *@�汾 
 */
public interface IFunctionService {

	public void pageQuery(PageBean pageBean);
	
	public List<Function> findAll();
	
	public  void add(Function model);

	/**
	 * 
	 *@ʱ�� 2017��2��21�� ����9:22:44
	 */
	public List<Function> findMenu();
}
