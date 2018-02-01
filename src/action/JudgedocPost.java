package action;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.message.BasicNameValuePair;

public class JudgedocPost {
	
	public static String URL_LOGIN = "http://wenshu.court.gov.cn/List/ListContent";
	
	public static String cookieVal = "";
	
	public static void main(String[] args) {
		try {
			JudgedocPost.Post(URL_LOGIN,"Direction=asc&Index=&order=法院层级&page=20&Param=全文检索:重庆长寿建设工程公司,案件名称:重庆长寿建设工程公司","utf-8",false,"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
//	params.add(new BasicNameValuePair("Direction", "asc"));
//	params.add(new BasicNameValuePair("Index", index + ""));
//	params.add(new BasicNameValuePair("Order", "法院层级"));
//	params.add(new BasicNameValuePair("Page", "20"));
////	全文检索:重庆长寿建设工程公司,案件名称:重庆长寿建设工程公司
//	params.add(new BasicNameValuePair("Param", "全文检索:" + unitName+","+"案件名称::" + unitName));
//	
	public static  void Post(String url_post,String str_param_body,String charset,boolean b_flag,String cookies) throws IOException   {  
        // Post请求的url，与get不同的是不需要带参数  
        URL postUrl = new URL(url_post);  
        // 打开连接  
        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();  
        // Output to the connection. Default is  
        // false, set to true because post  
        // method must write something to the  
        // connection  
        // 设置是否向connection输出，因为这个是post请求，参数要放在  
        // http正文内，因此需要设为true  
        if("" != cookies){  
            connection.setRequestProperty("Cookie", cookies);  
        }  
  
        connection.setDoOutput(true);  
        // Read from the connection. Default is true.  
        connection.setDoInput(true);  
        // Set the post method. Default is GET  
        connection.setRequestMethod("POST");  
        // Post cannot use caches  
        // Post 请求不能使用缓存  
        connection.setUseCaches(false);  
        // This method takes effects to  
        // every instances of this class.  
        // URLConnection.setFollowRedirects是static函数，作用于所有的URLConnection对象。  
        // connection.setFollowRedirects(true);  
  
        // This methods only  
        // takes effacts to this  
        // instance.  
        // URLConnection.setInstanceFollowRedirects是成员函数，仅作用于当前函数  
        connection.setInstanceFollowRedirects(b_flag);  
        // Set the content type to urlencoded,  
        // because we will write  
        // some URL-encoded content to the  
        // connection. Settings above must be set before connect!  
        // 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的  
        // 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode  
        // 进行编码  
        connection.setRequestProperty("Content-Type",  
                "application/x-www-form-urlencoded");  
        // 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，  
        // 要注意的是connection.getOutputStream会隐含的进行connect。  
        connection.connect();  
        DataOutputStream out = new DataOutputStream(connection  
                .getOutputStream());  
        // The URL-encoded contend  
        // 正文，正文内容其实跟get的URL中'?'后的参数字符串一致  
        //    String content = "userName=" + URLEncoder.encode("console", "utf-8");  
        //    content = content + "&password=" + URLEncoder.encode("12345678", "utf-8");  
  
        System.out.println("http param body = [" + str_param_body + "]");
        // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写道流里面  
        out.writeBytes(str_param_body);  
  
        out.flush();  
  
        // 取得cookie，相当于记录了身份，供下次访问时使用  
        //    cookieVal = connection.getHeaderField("Set-Cookie").split(";")[0]  
        cookieVal = connection.getHeaderField("Set-Cookie"); 
		System.out.println("get cookieVal = [" + cookieVal  + "]");  
  
        out.close(); // flush and close  
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),charset));  
        String line;  
        System.out.println("Contents of post request:");  
        while ((line = reader.readLine()) != null)  {  
            System.out.println(line);  
        }  
        System.out.println(line); 
        reader.close();  
        connection.disconnect();  
    }  

}
