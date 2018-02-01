package action;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetDate {
	
	public static String DEFAULT_FORMAT = "yyyy-MM-dd"; 
	
	  public static void main(String[] args){
//		  for(int i = 1951;i < 1960;i++){  
//	            System.out.println(formatDate(getYearFirst(i)));  
//	            System.out.println(formatDate(getYearLast(i)));  
//	        }  
	          
	        System.out.println(formatDate(getCurrYearFirst()));  
	        System.out.println(formatDate(getCurrYearLast()));  
		  
		  
		  Calendar cal = Calendar.getInstance();  
		  int year = 2017;
		  int month = 8;
	        //设置年份  
	        cal.set(Calendar.YEAR,year);  
	        //设置月份  
	        cal.set(Calendar.MONTH, month-1);  
	        //获取某月最大天数  
	        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        //设置日历中月份的最大天数  
	        cal.set(Calendar.DAY_OF_MONTH, lastDay);  
	        //格式化日期  
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
	        String lastDayOfMonth = sdf.format(cal.getTime()); 
	        
	        Calendar calendar = Calendar.getInstance();
	        Date date = new Date(System.currentTimeMillis());
//	        当前日期
	        calendar.setTime(date);
//	        calendar.add(Calendar.WEEK_OF_YEAR, -1);
	        calendar.add(Calendar.YEAR, -1);
//	        一年前的今天
	        date = calendar.getTime();
	        System.out.println(date);
	        Calendar currWeek = Calendar.getInstance();
	        currWeek.set(Calendar.DAY_OF_MONTH,currWeek.get(Calendar.DAY_OF_MONTH)+7);
	        Date dateWeek=currWeek.getTime();
	        System.out.println(dateWeek);
//	        推迟一个月示例：  半年
	        Calendar currMonth = Calendar.getInstance();
	        currMonth.set(Calendar.MONTH,currMonth.get(Calendar.MONTH)+1);
	        Date dateMonth=currMonth.getTime();
//	        推迟一年示例：
	        Calendar currYear = Calendar.getInstance();
	        currYear.set(Calendar.YEAR,currYear.get(Calendar.YEAR)+1);
	        Date dateYear=currYear.getTime();
	        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        Calendar c = Calendar.getInstance();
	         
	        //过去七天
	        c.setTime(new Date());
	        c.add(Calendar.DATE, - 7);
	        Date d = c.getTime();
	        String day = format.format(d);
	        System.out.println("过去七天："+day);
	         
	        //过去一月
	        c.setTime(new Date());
	        c.add(Calendar.MONTH, -1);
	        Date m = c.getTime();
	        String mon = format.format(m);
	        System.out.println("过去一个月："+mon);
	         
	        //过去三个月
	        c.setTime(new Date());
	        c.add(Calendar.MONTH, -3);
	        Date m3 = c.getTime();
	        String mon3 = format.format(m3);
	        System.out.println("过去三个月："+mon3);
	        
	        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
	        Date dataYear = new Date();
	        String formatDate = sdf1.format(dataYear);
	        System.out.println("格式化后的年份为:" + formatDate);
	        //过去一年
	        c.setTime(new Date());
	        c.add(Calendar.YEAR, -1);
	        Date y = c.getTime();
	        String year1 = sdf.format(y);
	        System.out.println("过去一年："+year1);
	        
	       
	    }
	 
	  /** 
	     * 格式化日期 
	     * @param date 日期对象 
	     * @return String 日期字符串 
	     */  
	    public static String formatDate(Date date){  
	        SimpleDateFormat f = new SimpleDateFormat(DEFAULT_FORMAT);  
	        String sDate = f.format(date);  
	        return sDate;  
	    }  
	      
	    /** 
	     * 获取当年的第一天 
	     * @param year 
	     * @return 
	     */  
	    public static Date getCurrYearFirst(){  
	        Calendar currCal=Calendar.getInstance();    
	        int currentYear = currCal.get(Calendar.YEAR);  
	        return getYearFirst(currentYear);  
	    }  
	      
	    /** 
	     * 获取当年的最后一天 
	     * @param year 
	     * @return 
	     */  
	    public static Date getCurrYearLast(){  
	        Calendar currCal=Calendar.getInstance();    
	        int currentYear = currCal.get(Calendar.YEAR);  
	        return getYearLast(currentYear);  
	    }  
	      
	    /** 
	     * 获取某年第一天日期 
	     * @param year 年份 
	     * @return Date 
	     */  
	    public static Date getYearFirst(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        Date currYearFirst = calendar.getTime();  
	        return currYearFirst;  
	    }  
	      
	    /** 
	     * 获取某年最后一天日期 
	     * @param year 年份 
	     * @return Date 
	     */  
	    public static Date getYearLast(int year){  
	        Calendar calendar = Calendar.getInstance();  
	        calendar.clear();  
	        calendar.set(Calendar.YEAR, year);  
	        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
	        Date currYearLast = calendar.getTime();  
	          
	        return currYearLast;  
	    }  
	}
