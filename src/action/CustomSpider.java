package action;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CustomSpider {

//	/wps/PA_YZXX/ContentListServlet?currPage=1&channelId=99
			private static final Logger logger = LoggerFactory
			.getLogger(CustomSpider.class);
			public static String getHTML(String url) {
		String html = null;
		try{
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla")
					.timeout(30000)
					.get();
			html = doc.html();
		}catch(Exception e) {
			logger.info("getHTML error",e);
		}
		return html;
	}
	
	
	public static String getRespondJson(int index,String unitName) {
		String result = null;
		String url = "http://www.cqyg.net/wps/portal/Home/yzxx/!ut/p/a1/jZFNboJAGIbP4oIt8ykC0t0UEkXU_hgjzqYBnAIGBsWpFHfeoIsmvUEv0YW3aewtOpKmaRqLnd1Mnuf98r2DCHIRYd4mDj0eZ8xLjnei3fVbA7PZ64JzpQ5bgB1DH4_ssWI2dQHMBAB_HAx1vnOjfPk1wL_mm13ca-sDMbHdaYFtXfYs3RgC2No5f4pIhdQlVEDNin1EwiTzq7pmmPlKJ0Qkp_c0p7n8kIvniPPlhQQSFEUhB6sFl8NsIwdMglNKlK05cn-hR_A7JvZTOchSRILIY4wm1x6PkCtBsCpDCcptWQiGzemjHPG01hx5KUXuYf_0_rw_vO4-Xt5qcXuOXMM4jcRrnGRMpI3QTPyK_qN2y7QAa5PxLbbNJoAmaj8DLNPJxIWFmmwGdNrhql8qWxU3Gp8zsAN_/";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost post = new HttpPost(url);
			post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Direction", "asc"));
			params.add(new BasicNameValuePair("Index", index + ""));
			params.add(new BasicNameValuePair("Order", "法院层级"));
			params.add(new BasicNameValuePair("Page", "20"));
			params.add(new BasicNameValuePair("Param", "全文检索:" + unitName));
			post.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			HttpResponse response = httpclient.execute(post);
			result = EntityUtils.toString(response.getEntity(), "UTF-8");
			result = result.replace("\\", "").replace("n", "");
			int pos = 0;
			pos = result.indexOf("]");
			//网站数据获取失败，让线程休眠10分钟
			if (pos == -1){
				Thread.sleep(600000);
				return null ;
			}
			result = result.substring(1, pos + 1);
		} catch (Exception e) {
			logger.error("getRespondJson error", e);
		}
		return result;
	}
			
			
}
