package action;

import java.io.File;
import java.util.ArrayList;

public class Weixin {
 
		public static void main(String[] args) {
			
			
			File pathToBinary = new File("C:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
//	        FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//	        FirefoxProfile firefoxProfile = new FirefoxProfile();
//	        FirefoxDriver driver = new FirefoxDriver(ffBinary,firefoxProfile);


//	        driver.get("http://cq.qq.com/baoliao/detail.htm?294064");

	        ArrayList list = new ArrayList();
	        list.add("http://www.sina.com.cn");
	        list.add("http://www.sohu.com");
	        list.add("http://www.163.com");
	        list.add("http://www.qq.com");

	        long start,end;

	        for(int i=0;i<list.size();i++){
	            start = System.currentTimeMillis();
//	            driver.get(list.get(i).toString());
	            end = System.currentTimeMillis();
	            System.out.println(list.get(i).toString() + ":" + (end - start));
	        }

//	        driver.close();
}

}