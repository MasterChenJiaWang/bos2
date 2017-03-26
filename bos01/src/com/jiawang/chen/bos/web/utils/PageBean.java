/**
 * 
 */
package com.jiawang.chen.bos.web.utils;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public class PageBean {
	private int currentPage;//��ǰҳ��
	private int pageSize;//ÿҳ��ʾ����
	private int total=0;//�ܼ�¼��
	private DetachedCriteria detachedCriteria;//���߲�ѯ����
	private List rows;//��ǰҳ��Ҫչʾ�����ݼ���
	/**
	 * @return currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	/**
	 * @param currentPage Ҫ���õ� currentPage
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	/**
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize Ҫ���õ� pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * @return total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total Ҫ���õ� total
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return detachedCriteria
	 */
	public DetachedCriteria getDetachedCriteria() {
		return detachedCriteria;
	}
	/**
	 * @param detachedCriteria Ҫ���õ� detachedCriteria
	 */
	public void setDetachedCriteria(DetachedCriteria detachedCriteria) {
		this.detachedCriteria = detachedCriteria;
	}
	/**
	 * @return rows
	 */
	public List getRows() {
		return rows;
	}
	/**
	 * @param rows Ҫ���õ� rows
	 */
	public void setRows(List rows) {
		this.rows = rows;
	}
	
	
}
