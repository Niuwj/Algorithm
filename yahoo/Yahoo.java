package yahoo;

public class Yahoo {

	/**
	 * @param args
	 * @author nwj
	 * 2014.9.21
	 * 雅虎笔试题 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		int[] A = {1,2,3,4,5};
//		show(A);
//		int[] B = jiecheng(A);
//		show(B);
//		int[][] m = {{1,2,3,4},{5,7,8,9},{6,10,11,12},{10,14,15,16}};
		Yahoo y = new Yahoo();
//		System.out.println(y.searchMatrix(m, 15));
		
//		A a = new B();
//		a.go();
//		
//		C c = new D();
//		System.out.println("--------------");
//		c = new C();
//		System.out.println("---------------");
//		D d = new D();
		int[] a = {3,4,5,3,4,5,3,4,4,5,5};
		System.out.println(y.twoInFour(a));
	}
	
	//1.B[i]=A[1]*...A[i]*A[i+1]*...A[n]
	//黄志的两遍扫描法
	//我用的是动态规划的方法
	public static int[] jiecheng(int[] A){
		int len = A.length;
		int[] B = new int[len];
		//扫描前半段
		int  tmp = 1;
		for(int i=1; i<len; i++){
			tmp *= A[i-1];
			B[i] = tmp; 		
		}
		B[0] = 1;
		tmp = 1;
		//扫描后半段
		for(int i=len-2; i>=0; i--){
			tmp *= A[i+1];
			B[i] *= tmp;
		}
		return B;	
	}
	
	//show array
	public static void show(int[] A){
		for(int i=0; i<A.length;i++){
			System.out.print(A[i]+" ");
		}
		System.out.println();
	}
	
	
	//矩阵搜索
	//我的思路是：取a[i][i]，然后分别进行,行二分查找，列二分查找
	public boolean searchMatrix(int[][] m, int target){
		int r = m.length;
		int c = m[0].length;
		int min = r<c?r:c;
		for(int i=0; i<min; i++){
			if(m[i][i]==target){
				return true;
			}else {
				if(i<r-1){
					if(binarySearchRow(m, r, c, i, target)){
						return true;
					}else {
						if(i<c-1){
							if(binarySearchCol(m, r, c, i, target)){
								return true;
							}
						}
					}
				}else {
					if(i<c-1){
						if(binarySearchCol(m, r, c, i, target)){
							return true;
						}
					}
				}
			}
		}		
		return false;
	}
	public boolean binarySearchRow(int[][] m, int r, int c, int k, int target){
		
		int low = k+1;
		int high = c;
		while(low!=high){
			int mid = (low+high)/2;
			int val = m[k][mid];
			if(val==target){
				return true;
			}else if (val<target) {
				low = mid+1;
			}else {
				high = mid;
			}
		}		
		return false;
	}
	public boolean binarySearchCol(int[][] m, int r, int c, int k, int target){
		
		int low = k+1;
		int high = r;
		while(low!=high){
			int mid = (low+high)/2;
			int val = m[mid][k];
			if(val==target){
				return true;
			}else if (val<target) {
				low = mid+1;
			}else {
				high = mid;
			}
		}		
		return false;
	}
	/*
	 * 一个整数列，除了一个数字重复2次，所有其他数字都重复4次。O(n)时间O(1)空间找到那个只出现2次的。
	 * e.g.
	 * 5, 6, 1123,5, 5, 6, 6, 7, 6, 7, 7, 1123, 5,7
	 *  输出 1123； 
	 *  
	 *  解法很巧妙：以二进制位统计，用四个数字表示统计结果
	 */
	public int twoInFour(int[] a){
		//用t[i]记录传入的数（如32位）出现i次的位，例如：t[3]，值为1的比特位，说明该位1出现的次数模4后值为3（出现3k次），否则为0。
		//4k+2即返回record[2]；4k+3即返回record[3]
		int[] record = new int[4];
		record[0] = 0B11111111111111111111111111111111;
		record[1] = 0B00000000000000000000000000000000;
		record[2] = 0B00000000000000000000000000000000;
		record[3] = 0B00000000000000000000000000000000;
		for(int i=0;i<a.length;i++){
			int t = record[3];
			record[3] = (record[3]& ~a[i]) | (record[2]&a[i]);
			record[2] = (record[2]& ~a[i]) | (record[1]&a[i]);
			record[1] = (record[1]& ~a[i]) | (record[0]&a[i]);
			record[0] = (record[0]& ~a[i]) | (t&a[i]);
		}
		return record[2];
	}
	
}
