package action;

import java.io.IOException;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * jsoup 模拟登录
 * @author wxb
 *
 */
public class JsoupLoginVisitor extends LoginVisitor{
	
	private static final Logger logger = LoggerFactory.getLogger(JsoupGetVisitor.class);
	
	private String charset;

	private int connectTimeout = 0;
	
	public JsoupLoginVisitor(){
		
	}
	
	public JsoupLoginVisitor(String charset){
		this.charset = charset;
	}

	@Override
	public Map<String,String> cookie(String url) throws Exception {
		Connection con = Jsoup.connect(url);
	    con.header("Accept", "text/html, application/xhtml+xml, */*"); 
	    con.header("Content-Type", "application/x-www-form-urlencoded");
		con.header("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0))"); 
		con.timeout(this.connectTimeout );
		con.method(Method.GET);
		Response response = con.execute();
		Map<String, String> cookies = response.cookies();
		logger.info("cookie获取成功！");
		return cookies;
	}

	@Override
	public Map<String,String> login(String url, Map<String,String> cookie, String[] keys,
			String[] values) throws Exception {
		return null;
	}
	
	public String cookieVisitor(Map<String, String> cookies,String url){
		String html = null;
		Document doc = null;
		try {
			doc = Jsoup.connect(url).cookies(cookies).timeout(30000).get();
			html = doc.html();
			logger.info(html);
		} catch (IOException e) {
			logger.info(url+"-爬取失败！");
		}
		return html;
	}
	
	public static void main(String[] args) throws Exception {
		JsoupLoginVisitor visitor = new JsoupLoginVisitor();
//		http://www.cpcb.com.cn/Front.aspx/Gcjy
		Map<String, String> cookies = visitor.cookie("http://wenshu.court.gov.cn/list/list/?sorttype=1");
//		for(Entry<String,String> en:cookies.entrySet()){
//			System.out.println(en.getKey()+"-"+en.getValue());
//		}
		String html = visitor.cookieVisitor(cookies,"http://www.cpcb.com.cn/Front.aspx/Gcjy/3");
		System.out.println(html);
	}
}
