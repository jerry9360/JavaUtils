package action;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class JudgedocSpider   {
	
	private static final Logger logger = LoggerFactory
			.getLogger(JudgedocSpider.class);

	private static int connectTimeout = 0;
	
	public static void main(String[] args) throws Exception { 
		String url = "http://wenshu.court.gov.cn/List/ListContent";
		Connection con = Jsoup.connect(url);
	    con.header("Accept", "text/html, application/xhtml+xml, */*"); 
	    con.ignoreContentType(true);
		con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
		con.timeout(connectTimeout);
		con.data("Direction", "asc");
        con.data("Index","0");
        con.data("Order", "法院层级");
        con.data("Page", "20");
        con.data("Param","全文检索:重庆长寿建设工程公司,案件名称:重庆长寿建设工程公司");
		con.method(Method.POST);
		Response response = con.execute();
		String body = response.body();
        System.out.println(body); 
	}

}
