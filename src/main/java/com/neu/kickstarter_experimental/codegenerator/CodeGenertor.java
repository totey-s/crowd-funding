package com.neu.kickstarter_experimental.codegenerator;

import java.security.SecureRandom;
import java.util.Random;

public class CodeGenertor {

	private static char[] CHARSET_AZ_09 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
	
	public static String randomString(int length) {
	    Random random = new SecureRandom();
	    char[] result = new char[length];
	    for (int i = 0; i < result.length; i++) {
	        // picks a random index out of character set > random character
	        int randomCharIndex = random.nextInt(CHARSET_AZ_09.length);
	        result[i] = CHARSET_AZ_09[randomCharIndex];
	    }
	    return new String(result);
	}
	
//	public static void main(String[] args) {
//		System.out.println(CodeGenertor.randomString(6));
//	}
}
