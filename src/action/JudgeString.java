package action;

public class JudgeString {
	public static void main(String[] args) {
		boolean condition = false;
		boolean condition4 = true;
		boolean condition2 = false;
		boolean condition3 = false;
		String string = "长途客运";
		String s2 = string.split("，")[0];
		System.out.println(s2);
		String[] size = string.split("，");
		int si = size.length;
		String receive = size[0];
		for(String s:size){
			if (condition4){
			 if(condition){
				 if(condition2) {
					System.out.println(receive);
					 continue;
				 }
			 }else{
					System.out.println(si==1);
					continue;
			 }
				System.out.println(si==1);
				System.out.println(receive);
				continue;
		}
		}
	}

}
