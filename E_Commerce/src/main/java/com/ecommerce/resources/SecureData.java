package com.ecommerce.resources;

public class SecureData {
	
	public static String encrypt(String s) {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<s.length();i++) {
			sb.append((char)(s.charAt(i)+3));
		}
		return sb.toString();
	}
	public static String decrypt(String s) {
		StringBuffer sb=new StringBuffer();
		for(int i=0;i<s.length();i++) {
			sb.append((char)(s.charAt(i)-3));
		}
		return sb.toString();
	}
}
