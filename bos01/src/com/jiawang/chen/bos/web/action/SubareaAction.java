/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.FileUtils;

/**
 *<p>����: SubareaAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��17�� ����9:29:01
 *@�汾 
 */
@Controller
@Scope("prototype")
public class SubareaAction  extends BaseAction<Subarea>{

	private static final Logger logger = Logger.getLogger(SubareaAction.class);
	
	/**
	 * ���
	 * 
	 *@ʱ�� 2017��2��17�� ����9:41:34
	 */
	public String add(){
		logger.info("����������ӣ�");
		subareaService.add(model);
		logger.info("���������ɣ�");
		return "list";
	}
	
	
	/**
	 * 
	 * �޸�
	 *@ʱ�� 2017��2��17�� ����9:41:47
	 */
	public String edit(){
		logger.info("���������޸ģ�");
		subareaService.edit(model);
		logger.info("�����޸���ɣ�");
		return "list";
	}
	
	
	/**
	 * ɾ��
	 * 
	 *@ʱ�� 2017��2��17�� ����9:42:32
	 */
	public String delete(){
		logger.info("��������ɾ����");
		subareaService.delete(model);
		logger.info("����ɾ����ɣ�");
		return "list";
	}
	
	
	/**
	 *��ҳ
	 * 
	 *@ʱ�� 2017��2��17�� ����9:43:35
	 */
	public String pageQuery()throws Exception{
				logger.info("������ҳ���ڽ���.......");
				//�ڲ�ѯ֮ǰ ��װ����
				DetachedCriteria detachedCriteria2 = pageBean.getDetachedCriteria();
				String addresskey = model.getAddresskey();
				Region region = model.getRegion();
				if(StringUtils.isNotBlank(addresskey)){
					//���յ�ַ�ؼ���ģ����ѯ
					detachedCriteria2.add(Restrictions.like("addressKey", addresskey));
				}
				if(region!=null){
					//�������������ڶ�������ѯ
					detachedCriteria2.createAlias("region", "r");
					String province = region.getProvince();
					String city = region.getCity();
					String district = region.getDistrict();
					if(StringUtils.isNotBlank(province)){
						//����ʡ����ģ����ѯ
						detachedCriteria2.add(Restrictions.like("r.province","%"+province+"%"));
					}
					if(StringUtils.isNotBlank(city)){
						//�����н���ģ����ѯ
						detachedCriteria2.add(Restrictions.like("r.city","%"+city+"%"));
					}
					if(StringUtils.isNotBlank(district)){
						//����������ģ����ѯ
						detachedCriteria2.add(Restrictions.like("r.district","%"+district+"%"));
					}
				}
				subareaService.pageQuery(pageBean);
				String[] executes=new String[]{"detachedCriteria","currentpage","pageSize","decidedzone","subareas","decidedzones"};
				//String[] executes=new String[]{"detachedCriteria","currentpage","pageSize","decidedzones","subareas","decidedzone"};
				this.WritePageBean2Json(pageBean, executes);
				logger.info("������ҳ���");
				return NONE;
	}
	
	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��17�� ����10:05:23
	 */
	public String exportXls() throws IOException{
		logger.info("�����������ڽ���...............");
		List<Subarea> list = subareaService.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet("��������");
		
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("�������");
		headRow.createCell(1).setCellValue("������");
		headRow.createCell(2).setCellValue("��ַ�ؼ���");
		headRow.createCell(3).setCellValue("ʡ����");
		
		for(Subarea subarea:list){
			//�������´���һ��
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			
			Region region = subarea.getRegion();
			
			dataRow.createCell(3).setCellValue(region.getName());
			}
			String filename="��������.xls";
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			//һ��������ͷ
			ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
			String contentType = ServletActionContext.getServletContext().getMimeType(filename);
			ServletActionContext.getResponse().setContentType(contentType);
			ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
			workbook.write(out);
			return NONE;
	}
	
	
	public String listajax() throws Exception{
		List<Subarea> list= subareaService.findListNotAssociation();
		String[] excludes=new String[]{"decidedzone","region"};
		this.WriteList2Json(list, excludes);
		return NONE;
	}
}
