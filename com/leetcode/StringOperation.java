package com.leetcode;

import javax.swing.plaf.SliderUI;

public class StringOperation {

	
	/*
	 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
	 */
	public boolean isPalindrome(String s) {
        if(s==null){
        	return true;
        }
		int len = s.length();
        if(len==0){
        	return true;
        }
        int start = 0;
        int end = len-1;
        while(start<end){
        	Character st = s.charAt(start);
        	if(Character.isLetterOrDigit(st)==false){
        		start++;
        		continue;
        	}
        	
        	Character ed = s.charAt(end);        	
        	if(Character.isLetterOrDigit(ed)==false){
        		end--;
        		continue;
        	}
        	st = Character.toLowerCase(st);
        	ed = Character.toLowerCase(ed);
        	if(st.equals(ed)){
        		start++;
        		end--;
        	}else {
				return false;
			}
        }
        return true;
    }
	
}
