package test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	private static List<String> case_reason_list = new ArrayList<String>();
	static {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("CaseReason.txt"));
			
			while (scanner.hasNextLine()) {
				 System.out.println("请输入字符串："); 
				case_reason_list.add(scanner.nextLine());
			       if (scanner.nextLine().equals("exit")) break; 
	                System.out.println(">>>" + scanner.nextLine()); 
				
			}
			for (int i = 0, size = case_reason_list.size(); i < size; i++) {
				for (int j = i + 1; j < size - 1; j++) {
					if (case_reason_list.get(j).length() > case_reason_list
							.get(i).length()) {
						String str = case_reason_list.get(j);
						case_reason_list.set(j, case_reason_list.get(i));
						case_reason_list.set(i, str);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} finally {
			if (scanner != null) {
				scanner.close();
			}
		}
	}

	private boolean setContains(Set<String> set, String str) {
		for (String s : set) {
			if (str.startsWith(s)) {
				return true;
			}
		}
		return false;
	}

	public static String getHTML(String url) {
		String html = null;
		try {
			Document doc = Jsoup.connect(url).userAgent("Mozilla")
					.timeout(15000).get();
			html = doc.html();
		} catch (Exception e) {

		}
		return html;
	}

	private Map<String, String> extract(List<String> list) {

		Map<String, String> map = new LinkedHashMap<String, String>();
		Set<String> set = new HashSet<String>();

		StringBuilder PLAINTIFF = new StringBuilder(""); // 原告
		StringBuilder PARTY = new StringBuilder(""); // 被告
		StringBuilder CASEREASON = new StringBuilder(""); // 案由

		int upper = list.size(); // upper代表取原告被告时,遍历的的上限位置

		boolean flag = true;
		boolean upperflag = true;

		for (int i = 0, size = list.size(); i < size; i++) {

			String str = list.get(i).trim();

			if (str.contains("当事人到庭情况") && upperflag) {
				upperflag = false;
				upper = i;
			}

			if ((str.contains("本院") || str.contains("本案"))
					&& upperflag
					&& (str.contains("判决") || str.contains("裁定")
							|| str.contains("受理") || str.contains("审理") || str
								.contains("合议庭"))) {
				upperflag = false;
				upper = i;
			}

			for (int j = 0, len = case_reason_list.size(); j < len; j++) {
				if (str.contains(case_reason_list.get(j)) && flag) {
					CASEREASON.append(case_reason_list.get(j));
					flag = false;
					break;
				}
			}
			if (!flag && !upperflag) {
				break;
			}
		}

		// System.out.println("upper :" + upper);

		for (int i = 1; i < upper; i++) {
			// start for loop

			String str = list.get(i).trim();
			str = str.replaceAll("、", "，").replaceAll("。", "，")
					.replaceAll(":", "：").replaceAll(",", "，");
			// System.out.println(str);
			// 处理原告 start
			if (str.startsWith("原告") || str.startsWith("公诉机关")
					|| str.startsWith("抗诉机关") || str.startsWith("上诉人")
					|| str.startsWith("申诉人") || str.startsWith("起诉人")
					|| str.startsWith("再审申请人") || str.startsWith("申请")
					|| str.startsWith("执行申请人") || str.startsWith("申请执行人")
					|| str.startsWith("自诉人")) {
				String regEx = "(（.{0,2}(到|出)庭）)*";
				String regEx1 = "(（.{0,6}名.{0,6}）)*";
				String regEx2 = "(（系.{0,15}）)*";
				String regEx3 = "(原告|(公|抗)诉机关|(上|起|申|自)诉人|再审申请人|(执行)?申请(再审)?人|申请执行人)(（((原|一)审|反诉)?((原|被)告.{0,15}|第三人|起诉人)）)*：?";
				String regEx4 = "(（反诉(原|被)告）)*";

				str = str.replaceAll(regEx, "").replaceAll(regEx1, "")
						.replaceAll(regEx2, "").replaceAll(regEx3, "")
						.replaceAll(regEx4, "");

				if (str.charAt(0) == '，') {
					str = str.substring(1);
				}

				if (str.contains("，")) {
					str = str.substring(0, str.indexOf("，"));
				}
				if (!str.contains("委托") && !str.contains("代理")
						&& !str.contains("执行")) {
					if (!setContains(set, str)) {
						PLAINTIFF.append("、" + str);
						set.add(str);
					}
				}

			}// 处理原告 end

			// 处理被告 start
			if (str.startsWith("被告") || str.startsWith("罪犯")
					|| str.startsWith("被上诉人") || str.startsWith("原公诉机关")
					|| str.startsWith("原审被告人") || str.startsWith("被申")
					|| str.startsWith("被执行人") || str.startsWith("再审被申请人")) {
				String regEx = "(（.{0,2}(到|出)庭）)*";
				String regEx1 = "(（.{0,6}名.{0,6}）)*";
				String regEx2 = "(（系.{0,15}）)*";
				String regEx3 = "((原审)?被告(单位|人)?|罪犯 |被上诉人|原公诉机关|(再审)?被申(请|诉)(人|执行人)|被执行人)(（((原|一)审|反诉)?((原|被)告.{0,15}|第三人)）)*：?";
				String regEx4 = "(（反诉(原|被)告）)*";

				// String regExName =
				// "(.{0,4}省)*(.{0,4}市)*(.{0,4}县)*(.{0,4}区)*.*(行|局|社|院|司|厅|会)(（.*）)*$";

				str = str.replaceAll(regEx, "").replaceAll(regEx1, "")
						.replaceAll(regEx2, "").replaceAll(regEx3, "")
						.replaceAll(regEx4, "");

				if (str.charAt(0) == '，') {
					str = str.substring(1);
				}
				if (str.contains("，")) {
					str = str.substring(0, str.indexOf("，"));
				}
				if (!str.contains("委托") && !str.contains("代理")
						&& !str.contains("执行")) {
					if (!setContains(set, str)) {
						PARTY.append("、" + str);
						set.add(str);
					}
				}

			}// 处理被告 end

		}// end for loop

		if (PLAINTIFF.length() > 0) {
			PLAINTIFF.deleteCharAt(0);
		}
		if (PARTY.length() > 0) {
			PARTY.deleteCharAt(0);
		}

		map.put("PLAINTIFF", PLAINTIFF.toString());
		map.put("PARTY", PARTY.toString());
		map.put("CASEREASON", CASEREASON.toString());

		return map;
	}

	public void test() throws IOException {
		String html = getHTML("http://wenshu.court.gov.cn/CreateContentJS/CreateContentJS.aspx?DocID=d6a12c3c-cdb5-4147-8fc3-a74500f0e6eb");
		Document doc = Jsoup.parse(html);
		Elements divList = doc.select("div");
		List<String> list = new ArrayList<String>();

		for (int i = 1; i < divList.size(); i++) {
			Element div = divList.get(i);
			String divText = div.text();

			char[] charArrayBuffer = divText.toCharArray();

			StringBuilder sb = new StringBuilder();
			// 去掉空格乱码
			for (char c : charArrayBuffer) {
				if (c != 160)
					sb.append(c);
			}
			divText = sb.toString();

			list.add(divText);

		}

		Map<String, String> map = extract(list);

		String PLAINTIFF = map.get("PLAINTIFF");
		String PARTY = map.get("PARTY");
		System.out.println("原告:" + PLAINTIFF);
		System.out.println("被告:" + PARTY);

	}

	public static void main(String[] args) throws IOException {
		new Test().test();
	}

}
