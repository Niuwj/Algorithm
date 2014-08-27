package com.leetcode;

public class ReverseInt {
	//Reverse digits of an integer.
	public int reverse(int x) {
        int rx = 0;
        boolean flag = false;
        if(x<0){
        	x = -x;
        	flag = true;
        }
		while(x>0){
			rx = rx*10+(x%10);
			x/=10;
		}
		if(flag){
			rx = -rx;
		}
		return rx;
    }
}
