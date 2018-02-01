package action;

import java.util.UUID;

/**
 *
 *@Author chenlw
 *@Date 2018年1月19日 上午9:33:21
 *@Version1.0
 *
 */

public class GenerateRandom {

	public static void main(String[] args) {
		for(int i=0;i<1000;i++){
			System.out.println(UUID.randomUUID().toString().replace("-",""));
		}
	}
}


