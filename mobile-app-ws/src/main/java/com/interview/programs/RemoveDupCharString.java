package com.interview.programs;

public class RemoveDupCharString {
	
	public static void main(String args[]) {
		String str = "LaxminarayanaVeggalamVv";
		String s = "";
		System.out.println("Before removing DuplicateChars :"+str);
		for(int i = 0;i<=str.length()-1;i++) {
			Character c = str.charAt(i);
			if(s.indexOf(c) < 0) {
				s = s+c;
			}
		}
		System.out.println("After removing duplicate chars : "+s);
	}

}
