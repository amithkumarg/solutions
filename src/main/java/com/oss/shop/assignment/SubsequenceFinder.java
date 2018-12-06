package com.oss.shop.assignment;

import java.util.regex.Pattern;

public class SubsequenceFinder {

	public static void main(String[] args) {
		System.out.println(findSubsequence("hackerrank", "hereiamstackerrank")); //true
		System.out.println(findSubsequence("hackerrank", "heeiamstackerank")); //false
		System.out.println(findSubsequence("hackerrank", "hackerworld")); //false
		System.out.println(findSubsequence("hackerrank", "hackerhellorankworld")); //true
		System.out.println(findSubsequence("hackerrank", "xyzhackerrank")); //true
		System.out.println(findSubsequence("hackerrank", "xyzhachackerrank")); //true
	}
	
	public static boolean findSubsequence(String subject, String toCheck){
		String wildCard = ".*";
		StringBuilder pattern = new StringBuilder(wildCard);
		for(char c : subject.toCharArray()){
			pattern.append(Pattern.quote(String.valueOf(c))).append(wildCard) ;
		}
		return toCheck.matches(pattern.toString());		
	}
}
