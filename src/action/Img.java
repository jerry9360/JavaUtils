package action;
import java.io.ByteArrayOutputStream;
import java.io.File;  
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;  
import java.net.HttpURLConnection;  
import java.net.URL;
import java.util.UUID;  
public class Img {
	
	    /** 
	     * @param args 
	     */  
	    public static void main(String[] args) throws Exception { 
	    	 
	    	String s = UUID.randomUUID().toString().replaceAll("-", "").trim();
	        //new一个URL对象  
//	        URL url = new URL("http://img.hexun.com/2011-06-21/130726386.jpg");  
	    	URL url = new URL(" ");
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
	        String address = "c:/img";
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
	            System.out.println("创建单个文件"  + "失败，目标文件不能为目录！");  
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
	                System.out.println("创建单个文件:"  + "成功！");  
	                return true;  
	            } else {  
	                System.out.println("创建单个文件:"  + "失败！");  
	                return false;  
	            }  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	            System.out.println("创建单个文件:"  + "失败！" + e.getMessage());  
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

