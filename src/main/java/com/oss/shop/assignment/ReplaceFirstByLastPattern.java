package com.oss.shop.assignment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceFirstByLastPattern {

	public static void main(String[] args) {
		System.out.println(swapAByO("testoyxoaaligatr"));
	}
	
	public static String swapAByO(String input){
		
		//Pattern when a falls before o
		Pattern pattern = Pattern.compile("(a)(.*)(o)");
		Matcher matcher = pattern.matcher(input);
		if(matcher.find()){
			return matcher.replaceAll("$3$2$1");
		}
		
		//Pattern when o falls before a
		pattern = Pattern.compile("(.*)(o)(.*?)(a)");
		matcher = pattern.matcher(input);
		if(matcher.find()){
			return matcher.replaceAll("$1$4$3$2");
		}
		 
		return input;
	}
}
