package com.leetcode;
/*
 * 判断一个数字是否是回文
 * 方法：将数字逆转，再与原数字比较
 */
public class IntParlindrome {
	public boolean isPalindrome(int x) {
        int r = reverseInt(x);
        if(x<0){
            return false;
        }
        if(r==x){
            return true;
        }else{
            return false;
        }
        
    }
	public int reverseInt(int x){
		boolean flag = false;
		int rev = 0;
		if(x<0){
			flag = true;
			x = -x;
		}
		
		while(x>0){
			rev = rev*10+x%10;
			x/=x;
		}
		if(flag){
			rev = -rev;
		}
		return rev;
	}
	
}
