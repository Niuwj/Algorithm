package com.leetcode;


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
	
	/*
	 * Write a function to find the longest common prefix string amongst an array of strings.
	 */
	public String longestCommonPrefix(String[] strs) {
        if(strs==null || strs.length==0){
        	return "";
        }
        //纵向比较
        for(int i=0; i<strs[0].length();i++){
        	Character c0 = strs[0].charAt(i);
        	int j =1;
        	while(j<strs.length){
//        		System.out.println(strs[j].length());
        		//注意可能strs[1]的长度小于strs[0]导致strs[j].charAt(i)越界
        		if(strs[j].length()-1<i || !c0.equals(strs[j].charAt(i))){
//        			System.out.println("i="+i);
        			return strs[0].substring(0, i);
        		}
//        		System.out.println("j="+j);
        		j++;        		
        	}
        }
        return strs[0];
    }
	
	/*
	 * Validate if a given string is numeric.

Some examples:
"0" => true
" 0.1 " => true
"abc" => false
"1 a" => false
"2e10" => true
Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
	 */
	public boolean isNumber(String s) {		
		//正则方法
		if(s.trim().isEmpty()){  
            return false;  
        }  
        String regex = "[-+]?(\\d+\\.?|\\.\\d+)\\d*(e[-+]?\\d+)?";  
        if(s.trim().matches(regex)){  
            return true;  
        }else{  
            return false;  
        }  
        
    }
	
	/*
	 * Length of Last Word
	 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Note: A word is defined as a character sequence consists of non-space characters only.

For example, 
Given s = "Hello World",
return 5.
	 */
	public int lengthOfLastWord(String s) {
        int len = s.length();
        if(len==0){
        	return 0;
        }
        int result = 0;
        for(int i=len-1; i>=0; i--){
        	Character c = s.charAt(i);
        	if(!c.equals(' ')){
        		result++;
        	}else {
				if(result>0){
					return result;
				}
			}
        }
        return result;        
    }
}
