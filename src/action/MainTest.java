package action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainTest {  
	  
	  public static void main(String[] args) {  
	    List<Object> list = new ArrayList<Object>();  
//	    userList.add(new User("Lucy", 19));  
//	    userList.add(new User("Jack", 19));  
//	    userList.add(new User("Jim", 19));  
//	    userList.add(new User("James", 19));  
//	    userList.add(new User("Herry", 19));  
//	    userList.add(new User("Luccy", 19));  
//	    userList.add(new User("James", 18));  
//	    userList.add(new User("Herry", 20));  
		 Object array = "巡游出租车";
		 list.add(array);
//		 巡游出租车   网约出租车   铁路旅客运输   水路运输市场    汽车租赁    其它  驾培  航道    公路养护   公路施工   公共交通  高速公路  道路货物运输  长途客运
		 array = "铁路旅客运输  ";
		 list.add(array);
		 array = "网约出租车";
		 list.add(array);
		 array = "公路养护";
		 list.add(array);
		 array = "公路施工";
		 list.add(array);
		 array = " 公共交通";
		 list.add(array);
		 array = " 汽车租赁";
		 list.add(array);
		 array = "其它";
		 list.add(array);
		 array = " 驾培";
		 list.add(array);
	    
	    
	    
	  
	    Collections.sort(list, new Comparator<Object>() {  
	  
	      public int compare(User user1, User user2) {  
	        int compareName = user1.getName().compareTo(user2.getName());  
	        if (compareName == 0) {  
	          return (user1.getAge() == user2.getAge() ? 0 : (user1.getAge() > user2.getAge() ? 1 : -1));  
	        }  
	        return compareName;  
	      }

		@Override
		public int compare(Object o1, Object o2) {
			int compareName = ((String) o1).compareTo((String) o2);  
	        return compareName;
		}  
	    });  
	  
	    for (Object object : list) {  
	      System.out.println(object + "\t\t");  
	    }  
	  }  
	  
	  private static class User {  
	  
	    private String name;  
	    private int  age;  
	  
	    public User(String name, int age){  
	      this.name = name;  
	      this.age = age;  
	    }  
	  
	    public String getName() {  
	      return name;  
	    }  
	  
	    public int getAge() {  
	      return age;  
	    }  
	  }  
	}