package action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Removerepeatdata implements  Comparable<List<Object>>   {
	public static void main(String[] args) {
			 List<Object> list=new ArrayList<Object>();
			 list.add("1");
			 list.add("2");
			 list.add("3");
			 list.add("4");
			 list.add("5");
			 list.add("6");
			 list.add("1");
			 list.add("2");
			 list.add("3");
			 list.add("4");
			 list.add("5");
			 list.add("6");
			 List<Object> tempList= new ArrayList<Object>();
			 for(Object i:list){
			  if(!tempList.contains(i)){
			   tempList.add(i);
			  }
			 }
			 for(Object i:tempList){
			  System.out.println(i);
			 }
			 Object[] array = new Object[12];
//			 巡游出租车   网约出租车   铁路旅客运输   水路运输市场    汽车租赁    其它  驾培  航道    公路养护   公路施工   公共交通  高速公路  道路货物运输  长途客运
			 array[0] = "巡游出租车";
			 array[1] = "网约出租车";
			 array[3] = "备案";
			 array[4] = "汽车租赁";
			 array[5] = "网约出租车";
			 array[6] = "备案";
			 array[7] = "汽车租赁";
			 array[8] = "水路运输市场   ";
			 array[9] = "铁路旅客运输 ";
			 array[10] = "水路运输市场   ";
			 array[11] = "铁路旅客运输 ";
//			 Collections.sort(list); 
			 Arrays.sort(array);
			 for (Object person:array) {
		            System.out.println(person);
		        }
	}

	@Override
	public int compareTo(List<Object> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	 
	
	
}
