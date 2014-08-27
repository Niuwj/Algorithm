package com.leetcode;

public class SingleNumber {
	
	/*
	 * Given an array of integers, every element appears twice except for one. Find that single one.

Note:
Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
	 
	 一个整数和它本身异或之后得到值是0。
	 所以初始化一个值为0的变量，让数组中的所有数与之异或，然后就可以找到这个只出现一次的数
	 */
	public int singleNumber(int[] A) {
        int result = A[0];
        for(int i = 1; i<A.length; i++){
        	result = result ^ A[i];
        }
        return result;
    }
	
	
	/*
	 * Given an array of integers, every element appears  three times except for one. Find that single one.
	 * 因为只能用O(1)的空间，所以技巧是对每一个位的1的个数进行计数。这样唯一的只出现一次的数用到的位将导致计数不是3的倍数。
	 * 最后检查所有计数不是3倍数的位，即可恢复原来的数字。
	 * 
	 * 
	 * 其中ones记录了所有出现了模3余1次的bit，twos记录的是余2的。not_threes是当一个bit出现第3次的时候，把它清掉。 
     * 最后输出ones（如果题目中那个特殊的数出现了1次，当然如果是出现2次的话，应该输出twos 
	 */
	
	public int SingleNumber3(int[] A){
		int once = 0;
		int twice = 0;
		
		for(int i =0; i<A.length; i++){
			twice |= once&A[i];
			once ^= A[i];
			int not_three = ~(once & twice);
			once = not_three & once;
			twice = not_three & twice;
		}
		return once;
	}
}
