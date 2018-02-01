package action;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.BloomFactory;
import redis.JedisPoolUtils;
import redis.clients.jedis.Jedis;



public class WeiXinDataSpider  {

	private static final Logger logger = LoggerFactory.getLogger(WeiXinDataSpider.class);

	private static String web_url = "http://weixin.sogou.com/weixin?type=2&ie=utf8&_sug_=n&_sug_type_=1&query=";
	
	private static BloomFactory bf = BloomFactory.getInstance();
	
	private static String getHTML(String url) {
		String html = null;
		try{
			Document doc = Jsoup.connect(url)
					.userAgent("Mozilla")
					.timeout(30000)
					.get();
			html = doc.html();
		}catch(Exception e) {
		}
		return html;
	}
	
	/**
	 * 获取总页数
	 * @return
	 */
	public static int getPageNum(String html) {
			int num = 0;
				Document doc=null;
				doc = Jsoup.parse(html);
				List<Element> pageString = doc.select("div.mun");
				if(pageString.size() ==0){
					return num=-1;
				}
				String string = pageString.toString();
				int loc = string.indexOf("结果");
				String newStr = string.substring(0,loc);
				String regEx="[^0-9]";   
				Pattern p = Pattern.compile(regEx);   
				Matcher m = p.matcher(newStr);   
				String b=m.replaceAll("").trim();
				num = Integer.parseInt(b);
				num = num / 10;
				int remainder = num % 10;
				if(remainder!=0){
					num++;
				}
			return num;
	}
	
	
	public static void spiderWeiXinData(String name){
		int page =1;
		int  pages = 11;
		String url = null;
		String html = null;
		 url = web_url +name;
		 html = getHTML(url);
		 int pageNum = getPageNum(html);
		 if (pageNum== -1){
			 pages=2;
		 }else if (pageNum<11){
			 pages=pageNum;
		 }
		try{
			for(int i=1;i<pages;i++){
				logger.info("--WeiXinDataSpider--第--"+i+"--页数据----");
				if (i % 2 == 0){
					Thread.sleep(4000);
				}
				String url_source=null;
				page=i;
				url_source = url+"&page="+page;
//				logger.info(url_source);
				html = getHTML(url_source);
				Document doc = null;
				try{
					doc = Jsoup.parse(html);
				}catch(Exception e){
					continue;
				}
				List<Element> eles = doc.select("div.news-box").select("li").select("h3");
				List<Element> element =  doc.select("div.news-box").select("li").select("div.txt-box").select("div.s-p");	  
				String curl = null;
				String h = null;
				for(int j=0;j<eles.size();j++){
					int order = j;
					order++;
					logger.info("----第--"+order+"--条数据----");
					if (j % 2 == 0){
						Thread.sleep(2000);
					}
					try{
						curl = eles.get(j).select("a").attr("href");
						//作者
//						String issueman = element.get(j).select("a").text();
						 h = getHTML(curl);
						 try{
								doc = Jsoup.parse(h);
							}catch(Exception e){
								continue;
							}
						 List<Element> hrefs = doc.select("div.rich_media_content").select("p").select("img[data-s]");
						 for(int number = 0; number < hrefs.size();number++){
							 String href = hrefs.get(number).attr("data-src");
							 if(bf.contains(href)){
									continue;
								}else{
									bf.add(href);
								}
							 imgD(href,name);
							 Thread.sleep(2000);
							// redis缓存已爬取得网页
							Jedis jedis = JedisPoolUtils.getInstance().getJedis();
							jedis.sadd("TuWen", href);
							JedisPoolUtils.getInstance().returnRes(jedis);
						 }
//						try{
////							通过详情页面获取数据title  content  issuetime
//							news = ContentExtractor.getNewsByHtml(h);
//						}catch(Exception e){
//							continue;
//						}
					}catch(Exception e){
						continue;
					}
				}
				continue;}
		}catch(Exception e){
			logger.error("爬取失败！");
		}
	}
	
	private static void imgD(String href,String name) throws Exception{
    	String s = UUID.randomUUID().toString().replaceAll("-", "").trim();
        //new一个URL对象  
//        URL url = new URL("http://img.hexun.com/2011-06-21/130726386.jpg");  
    	URL url = new URL(href);
    	//打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        String address = "c:/img/"+name;
        File imageFile = new File(address);  
        if (!imageFile.exists()){
        	imageFile.mkdirs();
        }
        String fileName = address + "/"+s+".jpg";  
    	Img.createFile(fileName);
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(fileName);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  
	}

	public static void main(String[] args) {
		//初始化连接池
		Jedis jedis = JedisPoolUtils.getInstance().getJedis();
		//获取数据库存在的URL
		Set<String> urls = jedis.smembers("TuWen");
		for(String u:urls){
			if(!bf.contains(u)){
				bf.add(u);
			}
		}
		//归还链接?
		JedisPoolUtils.getInstance().returnRes(jedis);
//		"aisiruming521","ns8765432","ns76543","ns7654","gaogenmeizi","yiqiri","meinvxiu6","rosimmn","taaabb","mmtupian","chemo233","mntpgq","jingleren","mnspxiu",
		String[] nameList = {"mssxye","aiqinggs","jfss222","mnsy04","yfyy68","t123cc","myugirls","ns8765432","yishu879","zy65433","meinvlian1","meinvmeng22","xiaohuag18","mntupian","kanxiaoshimei"};
		for(String name:nameList){
			logger.info("-------"+name+"------");
			spiderWeiXinData(name); 
		}
		
	}
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();  
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }  
	    
	    public static boolean createFile(String destFileName) {  
	        File file = new File(destFileName);  
	        if(file.exists()) {  
	            System.out.println("创建单个文件"  + "失败，目标文件已存在！");  
	            return false;  
	        }  
	        if (destFileName.endsWith(File.separator)) {  
	            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
	            return false;  
	        }  
	        //判断目标文件所在的目录是否存在  
	        if(!file.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!file.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  
	                return false;  
	            }  
	        }  
	        //创建目标文件  
	        try {  
	            if (file.createNewFile()) {  
//	                System.out.println("创建单个文件" + destFileName + "成功！");  
	                return true;  
	            } else {  
	                System.out.println("创建单个文件" + destFileName + "失败！");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
	            return false;  
	        }  
	    } 
	    /*** 
	     * 下载图片 
	     *  
	     * @param listImgSrc 
	     */  
	    private void Download(String url) {  
	        try {  
//	            for (String url : listImgSrc) {  
	                String imageName = url.substring(url.lastIndexOf("/") + 1, url.length());  
	                URL uri = new URL(url);  
	                InputStream in = uri.openStream();  
	                FileOutputStream fo = new FileOutputStream(new File(imageName));  
	                byte[] buf = new byte[1024];  
	                int length = 0;  
	                System.out.println("开始下载:" + url);  
	                while ((length = in.read(buf, 0, buf.length)) != -1) {  
	                    fo.write(buf, 0, length);  
	                }  
	                in.close();  
	                fo.close();  
	                System.out.println(imageName + "下载完成");  
//	            }  
	        } catch (Exception e) {  
	            System.out.println("下载失败");  
	        }  
	    } 
	
}