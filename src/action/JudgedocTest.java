package action;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JudgedocTest {
	
	private static final Logger logger = LoggerFactory
			.getLogger(JudgedocTest.class);
	
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

    private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
    private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
    
	public static JudgedocTest getJudgeDoc(JSONObject jsonObject,String name,String id,String industry) {
//		JudgeDoc judgeDoc = new JudgeDoc();
		String url_source = null;
		String url = null;
		String html = null;
		url_source = "http://wenshu.court.gov.cn/";
		String iname = name;
		String corp_id = id;
		String Industry =industry;
		try {
			String hidcasetype = jsonObject.getString("案件类型");
//			logger.info("hidcasetype="+hidcasetype);
			String courtName = jsonObject.getString("法院名称");
//			logger.info("courtName="+courtName);
			String caseCode = jsonObject.getString("案号");
//			logger.info("caseCode="+caseCode);
			String filename = jsonObject.getString("文书ID");
//			logger.info("filename="+filename);
			url = "http://wenshu.court.gov.cn/content/content?DocID="+filename+"&KeyWord="+iname;
			url_source = "http://wenshu.court.gov.cn/CreateContentJS/CreateContentJS.aspx?DocID="+filename+"";
			html = getHTML(url_source);
			logger.info(url_source);
//			judgeDoc.setContent(html);
			Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
	        Matcher m_html = p_html.matcher(html);  
	        html = m_html.replaceAll(""); // 过滤html标签  
	        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
	        Matcher m_space = p_space.matcher(html);  
	        html = m_space.replaceAll(""); // 过滤空格回车标签  
		   html=html.replace("$(function(){varjsonHtmlData=\"","").replaceAll("\\\\\"", "'"); 
		   int index = html.indexOf("varjsonData=eval") ;
		   html = html.substring(0,index); //取index之前的值
		   JSONObject myJsonObject = new JSONObject(html);//将字符串转换成jsonObject对象
		    //结构化
//			judgeDoc.setHidcasetype(hidcasetype);
//			judgeDoc.setCorp_name(iname);
//			judgeDoc.setCorp_id(corp_id);
//			judgeDoc.setIndustry(Industry);
//			judgeDoc.setHidcaseinfo(myJsonObject.getString("Html"));
//			judgeDoc.setHidcasename(myJsonObject.getString("Title"));
//			judgeDoc.setHidcasenumber(caseCode);
//			judgeDoc.setHidcourt(courtName);
//			judgeDoc.setReleasetime(myJsonObject.getString("PubDate"));
//			judgeDoc.setFilename(filename);
		} catch (Exception e) {
			logger.error("getJudgeDoc error", e);
		}
//		return judgeDoc;
		return null;
	}
	

	public static String getRespondJson() {
		int index = 0;
		String unitName ="重庆长寿建设工程公司";
		String result = null;
		String url = "http://wenshu.court.gov.cn/List/ListContent";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost post = new HttpPost(url);
			post.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:43.0) Gecko/20100101 Firefox/43.0");
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Direction", "asc"));
			params.add(new BasicNameValuePair("Index", index + ""));
			params.add(new BasicNameValuePair("Order", "法院层级"));
			params.add(new BasicNameValuePair("Page", "20"));
//			全文检索:重庆长寿建设工程公司,案件名称:重庆长寿建设工程公司
			params.add(new BasicNameValuePair("Param", "全文检索:" + unitName+","+"案件名称::" + unitName));
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

	public static void main(String[] args) {
		getRespondJson();
	}

	
	
}
