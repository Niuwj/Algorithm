
public class MeituanBishi {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] N = {0,2};
		System.out.println(hasI(N));
	}
	
	//查找升序非重复数组N中是否存在N[i]=i的元素
	//分析：二分查找，N[mid]>mid，high=mid;N[mid]<mid,low=mid+1
	public static int hasI(int[] N){
		int len = N.length;
		int low = 0;
		int high = len;
		while(low<high){
			int mid = (low+high)/2;
			if(N[mid]==mid){
				return mid;
			}else if (N[mid]<mid) {
				low = mid+1;
			}else {
				high = mid;
			}
		}
		return -1;
	}
	

}
