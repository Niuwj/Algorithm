package baidu;

public class Bishi {

	
	public static void main(String[] args) {
		int[] arr = {1,4,3,5,6,7,8};
		System.out.println(LIS(arr));

	}
	
	
	/*最长非递减子序列
	 * 题：求一个一维数组arr[i]中的最长非递减子序列的长度，如在序列1，-1，2，-3，4，-5，6，-7中，最长递增子序列长度为4，可以是1，2，4，6，也可以是-1，2，4，6。
	 * 
	 *方法一：DP

像LCS一样，从后向前分析，很容易想到，第i个元素之前的最长递增子序列的长度要么是1（单独成一个序列），要么就是第i-1个元素之前的最长递增子序列加1，可以有状态方程：

LIS[i] = max{1,LIS[k]+1}，其中，对于任意的k<=i-1，arr[i] > arr[k]，这样arr[i]才能在arr[k]的基础上构成一个新的递增子序列。

 
 这个方法也最容易想到也是最传统的解决方案，对于该方法和LIS，有以下两点说明：

由LIS可以衍生出来最长非递减子序列，最长递减子序列，道理是一样的。
对于输出序列，也是可以再申请一数组pre[i]记录子序列中array[i]的前驱，道理跟本节的实现也是一样的
方法二：排序+LCS
用quicksort + LCS，这个思路还是很巧妙的，因为LIS是单调递增的性质，所以任意一个LIS一定跟排序后的序列有LCS，并且就是LIS本身。
	 */
	
	
	public static int LIS(int[] arr){
		int len = arr.length;
		int[] dp = new int[len];
		int max = 0;
		for(int i=0; i<len; i++){
			dp[i] = 1;
			for(int j=0; j<i; j++){
				if(arr[i] > arr[j] && dp[i]<dp[j]+1){
					dp[i] = dp[j] + 1;
					if(dp[i] > max){
						max = dp[i];
					}
				}
			}
		}
		return max;
	}
	
	
	
}
