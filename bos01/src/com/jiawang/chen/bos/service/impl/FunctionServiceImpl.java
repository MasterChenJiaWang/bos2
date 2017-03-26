/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jiawang.chen.bos.dao.IFunctionDao;
import com.jiawang.chen.bos.entity.Function;
import com.jiawang.chen.bos.entity.User;
import com.jiawang.chen.bos.service.IFunctionService;
import com.jiawang.chen.bos.web.utils.BOSContext;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: FunctionServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����10:47:46
 *@�汾 
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{

	@Resource
	private IFunctionDao functionDao;
	/* 
	 *
	 *2017��2��20������10:48:06
	 */
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
		
	}
	/* 
	 *
	 *2017��2��20������10:59:39
	 */
	@Override
	public List<Function> findAll() {
		
		List<Function> list = functionDao.find();
		return list;
	}
	/* 
	 *model  Ϊ���ֲ㴫����������
	 *function2 Ϊfunction
	 *2017��2��20������11:24:13
	 */
	@Override
	public void add(Function model) {
		
		Function function2 = model.getFunction();
		if(function2!=null && function2.getId().equals("")){
			model.setFunction(null);
		}
		functionDao.save(model);
	}
	/* 
	 *
	 *2017��2��21������9:22:55
	 */
	@Override
	public List<Function> findMenu() {

		User user = BOSContext.getLoginUser();
		List<Function> list=null;
		if(user.getUsername().equals("chenjiawang")){
			list=functionDao.findAllMenu();
		}else{
			list=functionDao.findMenuByUserid(user.getId());
		}
		return list;
	}

}
