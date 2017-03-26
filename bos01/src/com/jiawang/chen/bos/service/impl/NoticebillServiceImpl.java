/**
 * 
 */
package com.jiawang.chen.bos.service.impl;

import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.jiawang.chen.bos.crm.CustomerService;
import com.jiawang.chen.bos.dao.IDecidedzoneDao;
import com.jiawang.chen.bos.dao.INoticebillDao;
import com.jiawang.chen.bos.dao.IWorkbillDao;
import com.jiawang.chen.bos.entity.Decidedzone;
import com.jiawang.chen.bos.entity.Noticebill;
import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.entity.Workbill;
import com.jiawang.chen.bos.service.INoticebillService;

/**
 *<p>����: NoticebillServiceImpl </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��20�� ����9:19:02
 *@�汾 
 */
@Service
@Transactional
public class NoticebillServiceImpl implements INoticebillService{

	@Resource
	private INoticebillDao noticebillDao;
	@Resource
	private CustomerService customerService;
	@Resource
	private IDecidedzoneDao decidedzoneDao;
	@Resource
	private IWorkbillDao workbillDao;
	/* 
	 *����ҵ��֪ͨ���������Զ��ֵ�
	 *2017��2��20������9:19:34
	 */
	@Override
	public void save(Noticebill noticebill) {
		noticebillDao.save(noticebill);
		//��ȡȡ����ַ
		String pickaddress = noticebill.getPickaddress();
		//����ȡ����ַ��ѯ����id---��crm�����ѯ 
		String dId = customerService.findDecidedzoneIdByPickaddress(pickaddress);
		if(dId != null){
					//��ѯ������id�������Զ��ֵ�
					Decidedzone decidedzone = decidedzoneDao.get(dId);
					Staff staff = decidedzone.getStaff();
					noticebill.setStaff(staff);//ҵ��֪ͨ������ƥ�䵽��ȡ��Ա
					noticebill.setOrdertype("�Զ�");//�ֵ�����
					//��ҪΪȡ��Ա����һ������
					Workbill workbill = new Workbill();
					workbill.setAttachbilltimes(0);//׷������
					workbill.setBuildtime(new Timestamp(System.currentTimeMillis()));//����������ʱ��
					workbill.setNoticebill(noticebill);//��������ҵ��֪ͨ��
					workbill.setPickstate("δȡ��");//ȡ��״̬
					workbill.setRemark(noticebill.getRemark());//��ע
					workbill.setStaff(staff);//��������ȡ��Ա
					workbill.setType("�µ�");
					workbillDao.save(workbill);//���湤��
					//���ö���ƽ̨���񣬸�ȡ��Ա���Ͷ���
				}else{
					//û�в�ѯ������id��תΪ�˹��ֵ�
					noticebill.setOrdertype("�˹�");
		}
	}
}
