package action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestSpider {
//	private static SimpleDateFormat sdf = new SimpleDateFormat(
//			"yyyy-MM-dd HH:mm:ss");
	public static void main(String[] args) throws ParseException {
      for (int i = 0;i < 100000;i++){
        String s = UUID.randomUUID().toString().replaceAll("-", "").trim();

        System.out.println(s);
        }
	}
//		String regex = "(?<!\\d)\\d{3}(?!\\d)";     //线路号3位
//		String regex = "^[\u4e00-\u9fa5|WJ]{1}[A-Z0-9]{6}$";
//		String[] data = {"测试线路331"};
//		TestSpider m = new TestSpider();
//		for(String s:data){
//			System.out.println(s+":"+m.pattern(s, regex));
//		}
//		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		String d  ="2017-04-02 16:30:31";
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		   Date date = null;
//		   try {
//		    date = sdf.parse(d);
//		    System.out.println(date);
//		   } catch (ParseException e) {
//		    e.printStackTrace();
//		   }
//		System.out.println(sdf.format(date));
//    }
	/**
	 * 正则匹配
	 * @param data
	 * @param regex
	 * @return
	 */
	private Boolean pattern(String data,String regex){
		boolean flag = false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(data);
		while(m.find()){
			flag = true;
			break;
		}
		return flag;
	}
	
 
		
//		
//		
//	    try (final WebClient webClient = new WebClient()) {
//	        final HtmlPage page = webClient.getPage("http://weixin.sogou.com/weixin?type=2&query=%E6%88%91%E4%BB%AC%E5%9C%A8%E4%B8%8A%E5%B8%82%E5%89%8D%2C%E4%BD%93%E9%AA%8C%E4%BA%86%E6%B1%9F%E6%B7%AE%E6%9C%80%E6%96%B0%E6%AC%BE%E7%91%9E%E9%A3%8ES212S3&ie=utf8&s_from=input&_sug_=y&_sug_type_=&w=01019900&sut=897&sst0=1488780480077&lkt=1%2C1488780479973%2C1488780479973");
//	        DomNodeList<DomElement> inputs = page.getElementsByTagName("input");
//	        List<DomElement> nodes = null;
//			final Iterator nodesIterator = (Iterator) nodes.iterator();
//	        // now iterate
//	    }
	    
	    
//	}	 
	
	//	String url = "http://weixin.sogou.com/weixin?type=2&query=%E6%88%91%E4%BB%AC%E5%9C%A8%E4%B8%8A%E5%B8%82%E5%89%8D%2C%E4%BD%93%E9%AA%8C%E4%BA%86%E6%B1%9F%E6%B7%AE%E6%9C%80%E6%96%B0%E6%AC%BE%E7%91%9E%E9%A3%8ES212S3&ie=utf8&s_from=input&_sug_=y&_sug_type_=&w=01019900&sut=897&sst0=1488780480077&lkt=1%2C1488780479973%2C1488780479973";
//	String web_urlsource = "http://www.baidu.com";
//	HtmlPage page = webClient.getPage("www.baidu.com");
//	
//	InputStream is = targetPage.getWebResponse().getContentAsStream()
	
//	
//	public void homePage() throws Exception {
//	    try (final WebClient webClient = new WebClient()) {
//	        final HtmlPage page = webClient.getPage("http://htmlunit.sourceforge.net");
//	        Assert.assertEquals("HtmlUnit - Welcome to HtmlUnit", page.getTitleText());
//
//	        final String pageAsXml = page.asXml();
//	        Assert.assertTrue(pageAsXml.contains("<body class=\"composite\">"));
//
//	        final String pageAsText = page.asText();
//	        Assert.assertTrue(pageAsText.contains("Support for the HTTP and HTTPS protocols"));
//	    }		
//	}
		
		
		
			
//			 String string = "共有62282条记录，当前第1/3115页";
//			 
//			TestSpider test = new TestSpider();
//			String str="fsdfasdfasd中标 out sth";
//			if(str.indexOf("招标")!=-1){ //等于-1表示这个字符串中没有o这个字符
//				System.out.println("招标");
//			}else if(str.indexOf("中标")!=-1){
//				System.out.println("中标");
//			}
			
			
			
			
			
		}

												 
