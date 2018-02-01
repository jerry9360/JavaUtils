package action;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * 重命名类
 * @author Chen_lw
 */
public class RenameImg {
	

	public static void main(String[] args) {
		RenameImg replacementChain = new RenameImg();
	        replacementChain.addRegulation("【我是一个好长好长的前缀哦】~~~~~", "").addRegulation("（我是萌萌的小尾巴）", "");
//	        replacementChain.multiRename("D:\\iphone 6s 2017-07-14\\iTools Photos", replacementChain);
	        replacementChain.multiRename("F:\\测试文件夹", replacementChain);
	}
	

	 
	/**
	 * 重命名规则类
	 * @author jack
	 */
 
	    private Map<String,String> map;
	 
	 
	    public RenameImg() {
	        this.map = new HashMap<String, String>();
	    }
	 
	    public Map<String, String> getMap() {
	        return map;
	    }
	 
	    // 添加新的替换规则(字符串替换)
	    public RenameImg  addRegulation(String oldStr , String newStr){
	        this.map.put(oldStr, newStr);
	        return this;
	    }
	 
	 
	 
	
	
	 
	    /**
	     * 批量重命名
	     * @param path
	     * @param replacementChain
	     */
	    public void multiRename(String path,RenameImg replacementChain){
	        File file = new File(path);
	        boolean isDirectory = file.isDirectory();
	 
	        /** 如果不是文件夹，就返回* */
	        if(!isDirectory){
	            System.out.println(path + "不是一个文件夹！");
	            return;
	        }
	 
	        String[] files = file.list();
	        File f = null;
	        String filename = "";
	        String oldFileName = ""; //之前的名字
	        /** 循环遍历所有文件* */
	        int length = files.length;
	        for(String fileName : files){
//	        	int a = (int)(Math.random()*(9999-1000+1))+1000;
	            oldFileName = fileName;
	            int index = oldFileName.indexOf("_");
//	            String rename = "IMG_"+Integer.toString(a)+".jpg";
//	            Map<String, String> map = replacementChain.getMap();
//	            for (Entry<String, String> entry : map.entrySet()) {
//	                fileName = fileName.replace(entry.getKey(), entry.getValue());
//	            }
	            String rename = oldFileName.substring(index+1,oldFileName.length());
	            f = new File(path + "\\" + oldFileName); //输出地址和原路径保持一致
	            f.renameTo(new File(path + "\\" +  rename));
	        }
	        System.out.println("恭喜，批量重命名成功！");
	    }
	 
	    
	}


