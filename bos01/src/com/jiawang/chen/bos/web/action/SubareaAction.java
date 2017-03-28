/**
 * 
 */
package com.jiawang.chen.bos.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jiawang.chen.bos.entity.Region;
import com.jiawang.chen.bos.entity.Subarea;
import com.jiawang.chen.bos.web.action.base.BaseAction;
import com.jiawang.chen.bos.web.utils.FileUtils;
import com.jiawang.chen.bos.web.utils.PinYin4jUtils;
import com.opensymphony.xwork2.ActionContext;

/**
 *<p>标题: SubareaAction </p>
 *<p>描述： </p>
 *<p>company:</p>
 * @作者  陈加望
 *@版本 
 */
@Controller
@Scope("prototype")
public class SubareaAction  extends BaseAction<Subarea>{

	private static final Logger logger = Logger.getLogger(SubareaAction.class);
	
	private String ids;
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**
	 * 添加
	 * 
	 */
	public String add(){
		logger.info("分区正在添加！");
		subareaService.add(model);
		logger.info("分区添加完成！");
		return "list";
	}
	
	
	/**
	 * 
	 * 修改
	 */
	public String edit(){
		logger.info("分区正在修改！");
		subareaService.edit(model);
		logger.info("分区修改完成！");
		return "list";
	}
	
	
	/**
	 * 删除
	 * 
	 */
	public String delete(){
		logger.info("分区正在删除！");
		subareaService.deleteBatch(ids);
		logger.info("分区删除完成！");
		return "list";
	}
	
	
	/**
	 *分页
	 * 
	 */
	public String pageQuery()throws Exception{
				logger.info("分区分页正在进行.......");
				//在查询之前 封装条件
				DetachedCriteria detachedCriteria = pageBean.getDetachedCriteria();
//				detachedCriteria.getExecutableCriteria(session)
				String addresskey = model.getAddresskey();
				Region region = model.getRegion();
				if(StringUtils.isNotBlank(addresskey)){
					//按照地址关键字模糊查询
					detachedCriteria.add(Restrictions.like("addressKey", addresskey));
				}
				if(region!=null){
					//创建别名，用于夺标关联查询
					detachedCriteria.createAlias("region", "r");
					String province = region.getProvince();
					String city = region.getCity();
					String district = region.getDistrict();
					if(StringUtils.isNotBlank(province)){
						//按照省进行模糊查询
						detachedCriteria.add(Restrictions.like("r.province","%"+province+"%"));
					}
					if(StringUtils.isNotBlank(city)){
						//按照市进行模糊查询
						detachedCriteria.add(Restrictions.like("r.city","%"+city+"%"));
					}
					if(StringUtils.isNotBlank(district)){
						//按照区进行模糊查询
						detachedCriteria.add(Restrictions.like("r.district","%"+district+"%"));
					}
				}
				subareaService.pageQuery(pageBean);
				String[] excludes = new String[] { "detachedCriteria", "currentPage",
						"pageSize", "decidedzone", "subareas","decidedzones"};
				//String[] executes=new String[]{"detachedCriteria","currentpage","pageSize","decidedzones","subareas","decidedzone","region"};
				this.WritePageBean2Json(pageBean, excludes);
				logger.info("分区分页完成");
				return NONE;
	}
	
	
	/**
	 * 
	 * 
	 */
	public String exportXls() throws IOException{
		logger.info("导出分区正在进行...............");
		List<Subarea> list = subareaService.findAll();
		
		HSSFWorkbook workbook = new HSSFWorkbook();
		
		HSSFSheet sheet = workbook.createSheet("分区数据");
		
		HSSFRow headRow = sheet.createRow(0);
		headRow.createCell(0).setCellValue("分区编号");
		headRow.createCell(1).setCellValue("区域编号");
		headRow.createCell(2).setCellValue("地址关键字");
		headRow.createCell(3).setCellValue("省市区");
		
		for(Subarea subarea:list){
			//在首行下创建一行
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum()+1);
			dataRow.createCell(0).setCellValue(subarea.getId());
			dataRow.createCell(1).setCellValue(subarea.getRegion().getId());
			dataRow.createCell(2).setCellValue(subarea.getAddresskey());
			
			Region region = subarea.getRegion();
			
			dataRow.createCell(3).setCellValue(region.getName());
			}
			String filename="分区数据.xls";
			String agent = ServletActionContext.getRequest().getHeader("User-Agent");
			filename = FileUtils.encodeDownloadFilename(filename, agent);
			//一个流两个头
			ServletOutputStream out = ServletActionContext.getResponse().getOutputStream();
			String contentType = ServletActionContext.getServletContext().getMimeType(filename);
			ServletActionContext.getResponse().setContentType(contentType);
			ServletActionContext.getResponse().setHeader("content-disposition", "attchment;filename="+filename);
			workbook.write(out);
			return NONE;
	}
	
	
	
//	//接收上传的文件
//		private File myFile;
//
//		public void setMyFile(File myFile) {
//			this.myFile = myFile;
//		}
//	/**
//	 * 区域数据导入功能
//	 */
//	public String importXls() throws FileNotFoundException, IOException{
//		String flag = "1";
//		try{
//			//使用POI解析Excel文件，将数据导入到数据库中
//			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(myFile));
//			HSSFSheet sheet = workbook.getSheetAt(0);
//			List<Subarea> list = new ArrayList<Subarea>();
//			for (Row row : sheet) {
//				int rowNum = row.getRowNum();
//				if(rowNum == 0){
//					continue;
//				}
//				String id = row.getCell(0).getStringCellValue();
//				String decidedzone = row.getCell(1).getStringCellValue();
//				String city = row.getCell(2).getStringCellValue();
//				String district = row.getCell(3).getStringCellValue();
//				String postcode = row.getCell(4).getStringCellValue();
//				Region region = new Region(id, province, city, district, postcode, null, null, null);
//				
//				city = city.substring(0, city.length() - 1);
//				String citycode = PinYin4jUtils.hanziToPinyin(city, "");
//				region.setCitycode(citycode);
//				decidedzone =decidedzone.substring(0, decidedzone.length() - 1);
//				district = district.substring(0, district.length() - 1);
//				String info = province + city + district;//河北石家庄桥西
//				String[] infos = PinYin4jUtils.getHeadByString(info);
//				String shortcode = StringUtils.join(infos, "");
//				region.setShortcode(shortcode);
//				list.add(region);
//			}
//			subareaService.saveBacth(list);
//		}catch (Exception e) {
//			flag = "0";
//		}
//		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
//		ServletActionContext.getResponse().getWriter().print(flag);
//		return NONE;
//	}
	
	public String listajax() throws Exception{
		List<Subarea> list= subareaService.findListNotAssociation();
		String[] excludes=new String[]{"decidedzone","region"};
		this.WriteList2Json(list, excludes);
		return NONE;
	}
}
