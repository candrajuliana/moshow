package com.bigjava.jsf;

public class Util {
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	
	public static String toUnitString(long bw) {
		if(bw>1073741824) {
			return String.format("%.2fM", bw/1073741824);
		}
		if(bw>1048576) {
			return String.format("%.2fM", bw/1048576);
		}
		if(bw>1024) {
			return String.format("%.2fK", bw/1024);
		}
		
		return String.format("%d", bw);
	}
	
	public static String toUnitString(int bw) {
		if(bw>1073741824) {
			return String.format("%.2fM", bw/1073741824);
		}
		if(bw>1048576) {
			return String.format("%.2fM", bw/1048576);
		}
		if(bw>1024) {
			return String.format("%.2fK", bw/1024);
		}
		
		return String.format("%d", bw);
	}
}
