package com.interview.programs;

import java.util.Scanner;

public class PrintPrimeNumbers {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter number to print prime number up to :");
		
		int limit = sc.nextInt();
		sc.close();
		String primeNumbers = "";
		
	    for(int i = 0;i<=limit;i++) {
	    	int count = 0;
	    	for(int j = i;j>=1;j--) {
	    		if(i % j ==0) {
	    			count = count +1;
	    		}
	    	}
	    	if(count == 2) {
	    		primeNumbers = primeNumbers + i + ", ";
	    	}
	    }
	    System.out.println("PrimeNumbers from 2 to "+limit+ " : "+primeNumbers.substring(0, primeNumbers.length()-2));
	}

}
