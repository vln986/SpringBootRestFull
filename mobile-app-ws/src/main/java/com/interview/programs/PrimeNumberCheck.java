package com.interview.programs;

import java.util.Scanner;

public class PrimeNumberCheck {
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter Number");
		int flag = 0;
		int n = sc.nextInt();
		sc.close();
		if(n == 0 || n == 1) {
			System.out.println("Given Number "+n+ " is not a prime number");
		}else {
			for(int i = 2;i<=n/2;i++) {
				if(n % i == 0) {
					System.out.println("Given Number "+n+ " is not a prime number");
					flag = 1;
					break;
				}
			}
			if(flag == 0) {
				System.out.println("Given Number "+n+ " is a prime number");
			}
		}
	}

}
