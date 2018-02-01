package action;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;  
public class CqgcjySpider {
	
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		  /** 
	     * 使用httpclient进行http通信 
	     *  
	     * Get请求 
	     */  
	    
	  String urlStr = "http://www.cpcb.com.cn/Front.aspx/Zbgs/2";
	        System.out.println(urlStr);  
	  
	        // 创建HttpClient对象  
	        HttpClient client = HttpClients.createDefault();  
	  
	        // 创建GET请求（在构造器中传入URL字符串即可）  
	        HttpGet get = new HttpGet(urlStr);  
	  
	        // 调用HttpClient对象的execute方法获得响应  
	        HttpResponse response = client.execute(get);  
	  
	        // 调用HttpResponse对象的getEntity方法得到响应实体  
	        HttpEntity httpEntity = response.getEntity();  
	  
	        // 使用EntityUtils工具类得到响应的字符串表示  
	        String result = EntityUtils.toString(httpEntity, "utf-8");  
	        System.out.println(result);  
	    
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//public static void main(String[] args) {
//		PrintWriter out = null;
//	    BufferedReader in = null;
//	    String url = "http://www.cpcb.com.cn/Front.aspx/Gcjy/2";
//	    String result = null;
//	    try {
//	        URL realUrl = new URL(url);
//	        // 打开和URL之间的连接
//	        URLConnection conn = realUrl.openConnection();
//	        // 设置通用的请求属性
////	        conn.setRequestProperty("FTNO", "");
////	        conn.setRequestProperty("FProjectName", "");
//	        conn.setRequestProperty("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
//	        // 发送POST请求必须设置如下两行
//	        conn.setDoOutput(true);
//	        conn.setDoInput(true);
//	        // 获取URLConnection对象对应的输出流
//	        out = new PrintWriter(conn.getOutputStream());
//	        // 发送请求参数
//	        out.print("FTNO=&FProjectName=");
//	        // flush输出流的缓冲
//	        out.flush();
//	        // 定义BufferedReader输入流来读取URL的响应
//	        in = new BufferedReader(
//	                new InputStreamReader(conn.getInputStream()));
//	        String line;
//	        while ((line = in.readLine()) != null) {
//	            result += line;
////	            System.out.println(result);
//	        }
//	        System.out.println(result);
//	        
//	    } catch (Exception e) {
//	        System.out.println("发送 POST 请求出现异常！"+e);
//	        e.printStackTrace();
//	    }
//	    //使用finally块来关闭输出流、输入流
//	    finally{
//	        try{
//	            if(out!=null){
//	                out.close();
//	            }
//	            if(in!=null){
//	                in.close();
//	            }
//	        }
//	        catch(IOException ex){
//	            ex.printStackTrace();
//	        }
//	    }
//	}  
	
}
   

