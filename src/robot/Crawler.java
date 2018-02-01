/**
* author: Bill Wang
* data: 2015-08-07
*/

package robot;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler implements Runnable {

	private final int count = 4102907;
	private final String url = "http://video.qupeiyin.cn/index.php?m=home&c=show&a=share&id=";
	private String name;

	public Crawler() {

	}

	public Crawler(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		
		int step = Integer.parseInt(Thread.currentThread().getName());
		
		for (int i = this.count-step; i >= 30000+step; i-=32) {

			String url2;
			String content = "";
			url2 = this.url + i;
			try {
				content = this.getInfo(this.getOneHtml(url2),url2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (content != null) {
				// System.out.println(content);
				content += "\r\n";
				this.fileIO("d:\\qupeiyin.txt", content);
			}

			System.out.println("线程"+Thread.currentThread().getName() + "：正在处理 id 为" + i + " 的网页");

		}
	}

	// 爬下静态网页
	public String getOneHtml(String htmlurl) throws IOException {
		URL url;
		String temp;
		StringBuffer sb = new StringBuffer();
		try {
			url = new URL(htmlurl);
			BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));// 读取网页全部内容
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (MalformedURLException me) {
			System.out.println("你输入的URL格式有问题！请仔细输入");
			me.getMessage();
			throw me;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

		// System.out.println(sb.toString()); //成功爬下了静态网页
		return sb.toString();
	}

	// 通过通配符匹配
	public String getInfo(String s,String url) {
		String regex;
		String res = "";

		// 通配符
		regex = "This is bill";
		Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		Matcher ma = pa.matcher(s);

		if (ma.find()) {
			// 成功找到

			regex = "http://cdn.qupeiyin.cn/.*?/.*?.mp4";
			pa = Pattern.compile(regex, Pattern.CANON_EQ);
			ma = pa.matcher(s);

			if (ma.find())
				res += (ma.group());

			return url+" "+res;
		}

		else
			return null;

		// test 多线程
		/*
		 * regex = "http://cdn.qupeiyin.cn/.*?/.*?.mp4"; Pattern pa =
		 * Pattern.compile(regex, Pattern.CANON_EQ); Matcher ma = pa.matcher(s);
		 * 
		 * if (ma.find()) res += (ma.group());
		 * 
		 * return res;
		 */

	}

	public void fileIO(String fileName, String content) {
		FileWriter writer = null;
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			writer = new FileWriter(fileName, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String args[]) throws IOException {

		Crawler crawler = new Crawler();

		/*
		 * new Thread(crawler, "线程1").start(); new Thread(crawler,
		 * "线程2").start(); new Thread(crawler, "线程3").start();
		 */

		// 批量产生线程
		for (int i = 0; i < 32; i++) {
			new Thread(crawler, i + "").start();
		}

	}
}
