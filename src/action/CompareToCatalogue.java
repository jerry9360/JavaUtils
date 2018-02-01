package action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CompareToCatalogue {
	    /**
	     * 
	     * @author chen_lw
	     */
		private static int order = 0;
		
		private static List<String> sourceList = new ArrayList<String>();
		
		private static List<String> targetList = new ArrayList<String>();

		
	    public static void main(String[] args) {
	    	CompareToCatalogue comparetocatalogue = new CompareToCatalogue();
	    	comparetocatalogue.start();
	        
	    }
	    private void start() {
	    	  try {  
	              BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));  
	              System.out.print("请输入一个目录路径格式(C:/*/*):");  
	              String str = strin.readLine();  
	              System.out.println("第一个："+str);  
	              getFileName(str,0);
	              System.out.println("请输入第二目录路径格式(C:/*/*):");  
	              String str2 = strin.readLine();  
	              order = 0;
	              System.out.println("第2个："+str2); 
	              getFileName(str2,1);
	              compareToCatalogue(sourceList,targetList);
	          } catch (IOException e) {  
	              e.printStackTrace();  
	          } 
		}
		private static void compareToCatalogue(List<String> sourceList, List<String> targetList) {
	    	boolean flag  = false;
	    	System.out.println("------------");
			for(String s:sourceList){
				System.out.println("sssss----"+s);
				for(String ts:targetList){
//					System.out.println("ts----"+s);
					if(s.equals(ts)){
//						System.out.println(s+"-------------"+ts);
						continue;
					}
					flag = true;
				}
				if(flag)
				System.out.println(s);
				}	
			}
		public void traverseFolder1(String path) {
	        int fileNum = 0;
	        int folderNum = 0;
	        File file = new File(path);
	        if (file.exists()) {
	            LinkedList<File> list = new LinkedList<File>();
	            File[] files = file.listFiles();
	            for (File file2 : files) {
	                if (file2.isDirectory()) {
	                    System.out.println("文件夹:" + file2.getAbsolutePath());
	                    list.add(file2);
						folderNum++;
	                } else {
	                    System.out.println("文件:" + file2.getAbsolutePath());
	                    fileNum++;
	                }
	            }
	            File temp_file;
	            while (!list.isEmpty()) {
	                temp_file = list.removeFirst();
	                files = temp_file.listFiles();
	                for (File file2 : files) {
	                    if (file2.isDirectory()) {
	                        System.out.println("文件夹:" + file2.getAbsolutePath());
	                        list.add(file2);
	                        folderNum++;
	                    } else {
	                        System.out.println("文件:" + file2.getAbsolutePath());
	                        fileNum++;
	                    }
	                }
	            }
	        } else {
	            System.out.println("文件不存在!");
	        }
	        System.out.println("文件夹共有:" + folderNum + ",文件共有:" + fileNum);

	    }
	    public static void getFileName(String str,int index ) {
	        String path = str; // 路径
	        File f = new File(path);
	        if (!f.exists()) {
	            System.out.println(path + " not exists");
	            return;
	        }
	        File fa[] = f.listFiles();
	        for (int i = 0; i < fa.length; i++) {
	            File fs = fa[i];
	            if (fs.isDirectory()) {
	            	getFileName(str+"/"+fs.getName(),index);
//	                System.out.println(fs.getName() + " [目录]");
	            } else {
//	            	order++;
//	                System.out.println("第"+order+"个文件"+fs.getName());
	                if(index==0)
	                sourceList.add(fs.getName());
	                else
	                	targetList.add(fs.getName());
	            }
	        }
	        
	    }
	 
//	    wzwsconfirm=b1f0a1f0d5f1f2c8c743ae6518be37a2; 
//	    wzwstemplate=MTA=; 
//	    ccpassport=9fb38482fcd0639fe2b42ee459cc5f6a; 
//	    wzwschallenge=-1; 
//	    wzwsvtime=1511252951;
//	    ASP.NET_SessionId=pwictauu5tlobhukrexynqe1; 
//	    vjkl5=2c67a0ffc8dd7501e218d419156d656efe61954c; 
//	    _gscu_2116842793=1125256665m48c20; 
//	    _gscs_2116842793=11252566hfyjzh20|pv:6; 
//	    _gscbrs_2116842793=1; 
//	    Hm_lvt_3f1a54c5a86d62407544d433f6418ef5=1511252566,1511252978,1511254341; 
//	    Hm_lpvt_3f1a54c5a86d62407544d433f6418ef5=151125441
}
