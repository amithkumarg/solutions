package com.oss.shop.assignment;

import java.util.function.Predicate;

public class LoggablePredicate {
	static MLog log = new MLog();
	
	@FunctionalInterface
	interface LPredicate<T> extends Predicate<T>{
		default boolean testAndLog(T t, String prefix){
			boolean result = this.test(t);
			if(!result){
				log.warn(prefix + " " + t);
			}
			return result;
		}
	}
	
	static LPredicate<String> notNull = input -> (input != null);
	static LPredicate<String> notEmpty = input -> (input.trim().length() > 0);	

	public static void main(String[] args) {		
		System.out.println("Case1: " + isInputValid(""));
		System.out.println("Case2: " + isInputValid(null));
		System.out.println("Case3: " + isInputValid("hello"));
	}
	
	public static boolean isInputValid(String input){		
	    return notNull.testAndLog(input, "NULL Check Failed") && notEmpty.testAndLog(input, "EMPTY Check Failed");
	}
}

/**
 * Just a mock log class
 *
 */
class MLog {
	void warn(String str) {
		System.out.println(str);
	}
}
	

