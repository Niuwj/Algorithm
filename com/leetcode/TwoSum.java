package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of integers, find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.

You may assume that each input would have exactly one solution.

Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2

 * @author nwj
 *
 */


public class TwoSum {
	
	
	/*
	 * 思路1：对于每一个元素，遍历寻找另一个元素使得和为target,时间复杂度O(n^2)。
	 * 思路2：先排序（注意记录数组下标！！！因为要求返回的是原数组下标！！），然后首尾两个指针向中间靠拢，两指针所指元素大于target，移动尾指针，小于target移动头指针，直至找到结果或者两个指针相遇。时间复杂度O(nlogn)。此方法可推广值3Sum，4Sum等，有待整理。
	 * 思路3：利用hashmap，将每个元素值作为key，数组索引作为value存入hashmap，然后遍历数组元素，在hashmap中寻找与之和为target的元素。 时间复杂度O(n)，空间复杂度O(n)。
	 * */
	public int[] twoSum(int[] numbers, int target) {
		int[] result = new int[2];
        for(int i=0;i<numbers.length-1;i++){
        	for(int j=i+1; j<numbers.length;j++){
        		if(numbers[i]+numbers[j] == target){
        			result[0]=i+1;
        			result[1]=j+1;
        		}
        	}
        }
        return result;
    }
	
	public int[] twoSumMap(int[] numbers, int target) {
		int[] result = new int[2];
		int len = numbers.length;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for(int i=0; i<len;i++){
			map.put(numbers[i], i);
		}
		for(int i=0 ; i<len; i++){
			int val = target - numbers[i];
			if(map.containsKey(val)){
				int index = map.get(val);
				if(index != i){
					if(i<index){
						result[0] = i+1;
						result[1] = index+1;
					}else{
						result[0] = index+1;
						result[1] = i+1;
					}
					return result;
				}
			}
		}
		return result;
	}
	
	public static void main(String[] args){
		int[] numbers = {4,2,4,1,6,20,3,8};
		int target = 10;
		TwoSum tSum = new TwoSum();
		int[] result = new int[2];
		result = tSum.twoSumMap(numbers, target);
		System.out.println(result[0]+"  "+result[1]);
	}
}
