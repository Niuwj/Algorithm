package baidu;

public class Bishi {

	
	public static void main(String[] args) {
		int[] arr = {6,7,8,1,2,3,5,};
		System.out.println(LIS(arr));

	}
	
	
	/*最长非递减子序列
	 * 题：求一个一维数组arr[i]中的最长非递减子序列的长度，如在序列1，-1，2，-3，4，-5，6，-7中，最长递增子序列长度为4，可以是1，2，4，6，也可以是-1，2，4，6。
	 * 
	 * http://www.ahathinking.com/archives/117.html#more-117
	 *方法一：DP

像LCS一样，从后向前分析，很容易想到，第i个元素之前的最长递增子序列的长度要么是1（单独成一个序列），要么就是第i-1个元素之前的最长递增子序列加1，可以有状态方程：

LIS[i] = max{1,LIS[k]+1}，其中，对于任意的k<=i-1，arr[i] > arr[k]，这样arr[i]才能在arr[k]的基础上构成一个新的递增子序列。

 
 这个方法也最容易想到也是最传统的解决方案，对于该方法和LIS，有以下两点说明：

由LIS可以衍生出来最长非递减子序列，最长递减子序列，道理是一样的。
对于输出序列，也是可以再申请一数组pre[i]记录子序列中array[i]的前驱，道理跟本节的实现也是一样的
方法二：排序+LCS
用quicksort + LCS，这个思路还是很巧妙的，因为LIS是单调递增的性质，所以任意一个LIS一定跟排序后的序列有LCS，并且就是LIS本身。

方法三：DP+二分查找

《编程之美》对于这个方法有提到，不过它的讲解我看得比较难受，好长时间才明白，涉及到的数组也比较多，除了源数据数组，有LIS[i]和MaxV[LIS[i]]，后来看了大牛Felix的讲解，我才忽然发现编程之美中的这个数组MaxV[LIS[i]]在记录信息上其实是饶了弯的，因为我们在寻找某一长度子序列所对应的最大元素最小值时，完全没必要通过LIS[i]去定位，即没必要与数据arr[i]挂钩，直接将MaxV[i]的下标作为LIS的长度，来记录最小值就可以了（表达能力太次，囧。。。），一句话，就是不需要LIS[i]这个数组了，只用MaxV[i]即可达到效果，而且原理容易理解，代码表达也比较直观、简单。

下面说说原理：

目的：我们期望在前i个元素中的所有长度为len的递增子序列中找到这样一个序列，它的最大元素比arr[i+1]小，而且长度要尽量的长，如此，我们只需记录len长度的递增子序列中最大元素的最小值就能使得将来的递增子序列尽量地长。

方法：维护一个数组MaxV[i]，记录长度为i的递增子序列中最大元素的最小值，并对于数组中的每个元素考察其是哪个子序列的最大元素，二分更新MaxV数组，最终i的值便是最长递增子序列的长度。这个方法真是太巧妙了，妙不可言。


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
