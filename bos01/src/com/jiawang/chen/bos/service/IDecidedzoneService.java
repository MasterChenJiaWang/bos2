/**
 * 
 */
package com.jiawang.chen.bos.service;

import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: IDecidedzoneService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��17�� ����4:32:15
 *@�汾 
 */
public interface IDecidedzoneService {

	public void add(Decidedzone decidedzone,String[] subareaid);
	public void delete(Decidedzone decidedzone);
	public void edit(Decidedzone decidedzone);
	
	public void pageQuery(PageBean pageBean);
	
}
