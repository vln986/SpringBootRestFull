package com.interview.programs;

import java.util.ArrayList;
import java.util.List;

public class RemoveDupArrayList {
	
	public static void main(String args[]) {
		
		List<String> list = new ArrayList<String>();
		list.add("Amit Jain");
		list.add("Amit");
		list.add("Manju");
		list.add("manju");
		list.add("laxman");
		list.add("laxman");
		list.add("laxminarayana");
		list.add("laxminarayana");
		
		System.out.println("list = "+list);
		System.out.println("list size = "+list.size());
		for(int i = 0;i<=list.size()-1;i++) {
			for(int j = i+1;j<=list.size()-1;j++) {
				if(list.get(i).equals(list.get(j))){
					list.remove(j);
					j--;
					break;
				}
			}
		}
		System.out.println("After Removing Duplicates "+list);
		System.out.println("size = "+list.size());
	}

}
