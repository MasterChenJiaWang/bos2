/**
 * 
 */
package com.jiawang.chen.bos.service;

import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.web.utils.PageBean;


public interface IDecidedzoneService {

	public void add(Decidedzone decidedzone,String[] subareaid);
	public void delete(Decidedzone decidedzone);
	public void edit(Decidedzone decidedzone);
	public void deleteBatch(String ids);
	public void pageQuery(PageBean pageBean);
	
}
