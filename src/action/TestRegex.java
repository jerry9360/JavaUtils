package action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
	public static void main(String[] args) {
		String regex = "(?<!\\d)\\d{3}(?!\\d)";
		String[] data = { "测试线路331", "线4567路", "测试线路376,已告知", "测试线路86531",
				"测试线路3311231,已告知", "234234324", "测试线路33qw11" };
		
		TestRegex m = new TestRegex();
		for (String s : data) {
			System.out.println(s + ":" + m.pattern(s, regex));
		}
	 
	}
	
	private Boolean pattern(String data, String regex) {
		List<String> lineNumber = new ArrayList<String>();
		boolean flag = false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(data);
		while (m.find()) {
			String t = m.group().trim();
			System.out.println(t);
			if (t.length() > 0) {
				lineNumber.add(t);
			}
		}
		System.out.println(lineNumber);
		return flag;
	}
	
	

}
