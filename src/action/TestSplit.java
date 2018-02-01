package action;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TestSplit {
	
	//solr 日期格式:
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");  
	//开始日期到结束日期
	String time = "["+sdf.format(new Date())+" TO "+sdf.format(new Date())+"]";
	
	public static void main(String[] args) {
		String key = "";
		String keyword = "重庆+北京";
		String[] str = keyword.split("\\+");
		for(String s:str){
			key = key+"content:*"+s+"*,";
		}
		 System.out.println(key);
		
	}

	 

}
