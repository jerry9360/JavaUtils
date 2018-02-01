package action;

public class TestSpil {
	public static void main(String[] args) {
		String key = null;
		String keyword = "重庆+北京";
		String[] str = keyword.split("\\+");
		for(String s:str){
			key = key+"content:*"+s+"*";
		}
		
	}

}
