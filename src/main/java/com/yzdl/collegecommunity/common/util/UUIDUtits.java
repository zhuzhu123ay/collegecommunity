package com.yzdl.collegecommunity.common.util;


public class UUIDUtits {
	public static int getUUID(int min, int max){
		 int randNum = min + (int)(Math.random() * ((max - min) + 1));
		    return randNum;
	}
	/*public static void main(String[] args) {
		System.out.println(getUUID(1,999999));
	}*/
}
