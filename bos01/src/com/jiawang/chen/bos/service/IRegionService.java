/**
 * 
 */
package com.jiawang.chen.bos.service;

import java.util.List;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: IRegionService </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��16�� ����11:22:27
 *@�汾 
 */
public interface IRegionService {

	public void save(Region region);
	
	public void delete(Region region);
	
	public void saveBacth(List<Region> list);
	
	public void pageQuery(PageBean pageBean);

	/**
	 * 
	 *@ʱ�� 2017��2��16�� ����5:01:17
	 */
	public List<Region> findAll();

	/**
	 * 
	 *@ʱ�� 2017��2��16�� ����5:14:38
	 */
	public List<Region> findByQ(String q);

	/**
	 * 
	 *@ʱ�� 2017��2��17�� ����9:17:02
	 */
	public void update(Region region);
}
