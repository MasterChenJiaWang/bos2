/**
 * 
 */
package com.jiawang.chen.bos.dao;

import java.util.List;

import com.jiawang.chen.bos.dao.base.IBaseDao;
import com.jiawang.chen.bos.entity.Region;

/**
 *<p>����: IRegionDao </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��16�� ����10:22:52
 *@�汾 
 */
public interface IRegionDao extends IBaseDao<Region> {

	//ģ����ѯ
	public List<Region> findByQ(String q);
}
