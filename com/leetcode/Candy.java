package com.leetcode;

public class Candy {
	
	/*
	 * There are N children standing in a line. Each child is assigned a rating value.

You are giving candies to these children subjected to the following requirements:

Each child must have at least one candy.
Children with a higher rating get more candies than their neighbors.
What is the minimum candies you must give?
	 */
	public int candy(int[] ratings) {
        int len = ratings.length;
        int sum = 0;
        int[] lc = new int[len];
		int[] rc = new int[len];
		for(int i=0;i<len;i++){
			rc[i]=1;
			lc[i]=1;
		}
		
		for(int i=1;i<len;i++){
			if(ratings[i]>ratings[i-1]){
				lc[i] = lc[i-1]+1;
			}
		}
		
		for(int i=len-2;i>=0;i--){
			if(ratings[i]>ratings[i+1]){
				rc[i] = rc[i+1]+1;
			}
		}
		
		for(int i=0;i<len;i++){
			sum += Math.max(lc[i], rc[i]);
		}
		
		return sum;
		
    }
}
