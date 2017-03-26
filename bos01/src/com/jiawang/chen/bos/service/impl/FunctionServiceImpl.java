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
 *<p>标题: FunctionServiceImpl </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Service
@Transactional
public class FunctionServiceImpl implements IFunctionService{

	@Resource
	private IFunctionDao functionDao;
	
	@Override
	public void pageQuery(PageBean pageBean) {
		functionDao.pageQuery(pageBean);
		
	}

	@Override
	public List<Function> findAll() {
		
		List<Function> list = functionDao.find();
		return list;
	}
	/* 
	 *model  为表现层传过来的数据
	 *function2 Ϊfunction
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
