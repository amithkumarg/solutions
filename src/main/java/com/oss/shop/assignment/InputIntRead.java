package com.oss.shop.assignment;

import java.util.Scanner;

public class InputIntRead {
	
	public static void main(String[] args){
		System.out.println("UserNumber entered:" + userNumber());
	}
	
	public static int userNumber(){

		try (Scanner scanner = new Scanner(System.in)) {
		    String msg = "Please, enter a positive integer:";
		    int n = 0;

		    while(n <= 0){	            
	            System.out.println(msg);
	            if(scanner.hasNextInt()){
	            	n = scanner.nextInt();
	            } else {
	            	scanner.next();
	            }
	        }

		    return n;
		}
    }
}	
