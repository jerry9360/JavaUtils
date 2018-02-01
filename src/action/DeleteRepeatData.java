package action;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class DeleteRepeatData {
	/**
	* 功能：Java读取txt文件的内容 步骤：
	* 1：先获得文件句柄 
	* 2：获得文件句柄当做是输入一个字节码流，需要对这个输入流进行读取
	* 3：读取到输入流后，需要读取生成字节流
	     *4：一行一行的输出。readline()。 备注：需要考虑的是异常情况
	* 
	* @param filePath
	 * @throws IOException 
	*/
	public static void readTxtFile(String filePath) throws IOException {
		try {
			String filePath2 = "D:\\处理后.txt";
			File file2 = new File(filePath2);
			String encoding = "UTF-8";
			File file = new File(filePath);
			System.out.println(file.isFile());
			System.out.println(file.exists());
			if (file.isFile() && file.exists()) { // 判断文件是否存在
			InputStreamReader read = new InputStreamReader(
			new FileInputStream(file), encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			ArrayList<String> ayyarsListText = new ArrayList<String>();
			// 得到文件中的数据
			while ((lineTxt = bufferedReader.readLine()) != null) 
			{
			ayyarsListText.add(lineTxt);
				}
			read.close();
			ArrayList<String> resultList = new ArrayList<String>();
			// 去除文件中的重复数据
			for (String item : ayyarsListText) {
			if (!resultList.contains(item)) {
			resultList.add(item);
			}
		}
		   BufferedWriter bw=new BufferedWriter(new FileWriter(file2));
		   for(int i=0;i<resultList.size();i++){
		       bw.write(resultList.get(i));
		       bw.newLine();
		   }
		   bw.close();
		   System.out.println("成功");
		} else {
			System.out.println("找不到指定的文件");
			}
		}
		 catch (Exception e) {
			 System.out.println("读取文件内容出错");
			 e.printStackTrace();
		}
}
	
	public static void testReadFile() throws IOException {
		String filePath = "D:\\处理前.txt";
		readTxtFile(filePath);
	}
	public static void main(String[] args) throws IOException {
		testReadFile();
	}
}
