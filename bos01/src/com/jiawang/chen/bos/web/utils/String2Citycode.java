/**
 * 
 */
package com.jiawang.chen.bos.web.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *<p>����: String2Citycode </p>
 *<p>���������ɳ��м��� �ͳ����� </p>
 *<p>company:</p>
 * @����  �¼���
 * @ʱ��  2017��2��16�� ����12:36:37
 *@�汾 
 */
public class String2Citycode {

	public String string2Citycode(String city){
		
		city=city.substring(0, city.length()-1);
		String[] cityPinyin = PinYin4jUtils.stringToPinyin(city);
		String citycode = StringUtils.join(cityPinyin, "");
		return citycode;
	}
	
	/**
	 * 
	 * 
	 *@ʱ�� 2017��2��16�� ����12:44:46
	 */
	public String string2Shortcode(String city,String province,String district){
		city=city.substring(0, city.length()-1);
		province=province.substring(0, province.length()-1);
		district=district.substring(0, district.length()-1);
		String info=province+city+district;
		String[] infoPinyin = PinYin4jUtils.getHeadByString(info);
		String shortcode = StringUtils.join(infoPinyin, "");
		return shortcode;
	}
}
