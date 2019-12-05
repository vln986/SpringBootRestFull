package com.interview.programs;

public class SwapStrings {
	public static void main(String args[]) {
		String s1 = "Laxminarayana";
		String s2 = "Swetha Harshini";
		
		System.out.println("Before Swapping :");
		System.out.println("s1 = "+s1);
		System.out.println("s2 = "+s2);
		String s3 = s1 + s2;
		s2 = s3.substring(0, s1.length());
		s1 = s3.substring(s1.length());
		
		System.out.println("After Swapping :");
		System.out.println("s1 = "+s1);
		System.out.println("s2 = "+s2);
	}

}
