/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Staff;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.PageBean;

/**
 *<p>����: RegionAction </p>
 *<p>������ </p>
 *<p>company:</p>
 * @����  �¼���
 *@�汾 
 */
@Controller
@Scope("prototype")
public class RegionAction extends BaseAction<Region> {

	private static final Logger logger = Logger.getLogger(RegionAction.class);
	private File myFile;
	
	/**
	 * @param myFile Ҫ���õ� myFile
	 */
	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}
	
	/**
	 * 
	 * 
	 */
	public String add(){
		logger.info("���ڽ����������-------");
		regionService.save(model);
		logger.info("������ӳɹ�-------");
		return "list";
	}
	
	
	/**
	 * 
	 * �޸�
	 */
	public String edit(){
		logger.info("���ڽ��������޸�-------");
		regionService.update(model);
		logger.info("�����޸ĳɹ�-------");
		return "list";
	}
	
	
	/**
	 * ����
	 * 
	 */
	public String importXls() throws Exception{
		String flag="1";
		logger.info("���ڽ���������-------");
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
			//��õ�һ��sheetҳ
			HSSFSheet sheet = workbook.getSheetAt(0);
			List<Region> list = new ArrayList<>();
			for(Row row:sheet){
				//�õ�����
				int rowNum = row.getRowNum();
				if(rowNum==0){
					//��һ�У������У�����
					continue;
				}
				
				String id = row.getCell(0).getStringCellValue();//id
				String province = row.getCell(1).getStringCellValue();//ʡ��
				String city= row.getCell(2).getStringCellValue();//����
				String district = row.getCell(3).getStringCellValue();//����
				String postcode = row.getCell(4).getStringCellValue();//�ʱ�
				String citycode = string2Citycode(city);
				String shortcode = string2Shortcode(city, province, district);
				Region region = new Region(id, province, city, district, postcode, shortcode, citycode, null);
				list.add(region);
			}
			regionService.saveBacth(list);
		} catch (Exception e) {
			flag="0";
			e.printStackTrace();
		}
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().print(flag);
		logger.info("������ɹ�-------");
		return NONE;

	}
	
	
	/**
	 * ��ҳ
	 * 
	 *@ʱ�� 2017��2��16�� ����1:31:09
	 */
	public String pageQuery( ) throws Exception{
		logger.info("���ڽ��������ҳ-------");
		regionService.pageQuery(pageBean);
		String[] excludes=new String[]{"decidedzones","currentPage","detachedCriteria","pageSize","subareas"};
		WritePageBean2Json(pageBean, excludes);
		logger.info("�����ҳ�ɹ�-------");
		return NONE;
	}
	
	
	//ģ����ѯ����
	private String q;
	
	/**
	 * @return q
	 */
	public String getQ() {
		return q;
	}
	
	/**
	 * @param q Ҫ���õ� q
	 */
	public void setQ(String q) {
		this.q = q;
	}

	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��16�� ����4:55:41
	 */
	public String listajax() throws Exception{
List<Region> list=null;
		
		if(StringUtils.isNotBlank(q)){
			list=regionService.findByQ(q);
		}else{
			list=regionService.findAll();
		}
				
		String[] excludes=new String[]{"subareas"};
		this.WriteList2Json(list,excludes);
		return NONE;
	}
	
	
}
