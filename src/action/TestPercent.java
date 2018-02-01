package action;

import java.text.NumberFormat;

public class TestPercent {
	public static void main(String[] args) {
		Object num1 = 99;
		Object num2 = 199;
//		int num1 = 7;
//		int num2 = 9;
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(0);
		String result = numberFormat.format((float) Integer.parseInt(num1.toString()) / (float) Integer.parseInt(num2.toString()) * 100);
		System.out.println("num1和num2的百分比为:" + result + "%");
	}
	
}
