package action;

 
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;


//编写一个程序，在F盘下创建一个“Java学习”的文件夹，并在文件夹中一次性创建200个
//“智递科技Android实训课程之JavaSE培训_视频教程001.wmv”视频文件，文件名末尾的序号从001—200，
//再编写一个工具类，将所有视频文件的名字修改为“JavaSE视频教程001.wmv”序号保持不变。


public class CreateFilePackage {
		public void rename(String ysl,String oldName,String newName){
		File fa=new File(ysl);
		File[] f1=fa.listFiles();
		int count=0;
		for (File ff : f1) {
		String oldname=ff.getName();
		String newname=oldname.replace(oldName, newName);
		String newPath=fa.getAbsolutePath()+"\\"+newname;
		File newpath=new File(newPath);
		boolean b=ff.renameTo(newpath);
			if(b){
			System.out.println("改名成功");
			count++;
		}else{
			System.out.println("改名失败");
			}
		}
		System.out.println("一共有"+fa.length()+"个文件，修改了"+count+"份文件");
}

public static void main(String[] args) throws IOException{
	for(int i = 6; i < 21; i++){
		String str = "F:\\121\\day"+ i;
	File file=new File(str);
	boolean b=file.exists();
	if(b){
		System.out.println("文件夹已创建");
	}else{
		file.mkdirs();
	 }
	}
	CreateFilePackage fd=new CreateFilePackage();
	fd.chuangjian();
	fd.rename("F:\\java学习", "智递科技Android实训课程之JavaSE培训_视频教程", "JavaSE视频教程");
	}
	public void chuangjian() throws IOException{
		DecimalFormat df=new DecimalFormat("000");
		File file=new File("F:\\java学习");
		boolean b=file.exists();
		if(b){
			System.out.println("文件夹已创建");
		}else{
			file.mkdirs();
     }
		boolean button=true;
		while(button){
		for(int i=1;i<=200;i++){
		File f=new File(file.getAbsolutePath()+"\\"+"智递科技Android实训课程之JavaSE培训_视频教程"+df.format(i)+".wmv");
		boolean c=f.createNewFile();
		if(c){
		System.out.println("文件创建成功");
		}else{
		System.out.println("文件创建失败");
		}
		
		if(i==200){
		button=false;
			  }
		   }
		}
      }
}