import sort.BubbleSort;
import sort.InsertSort;
public class Main {

	/**
	 * @param args
	 */
	private int[] a={2,1,5,3,0,4,6};
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a={2,1,5,3,0,4,6};
//		BubbleSort bs=new BubbleSort(a);
//		bs.bubble_sort();
//		bs.show();
		InsertSort iSort=new InsertSort(a);
		iSort.sort();
		iSort.show();
		short x=128;
		byte b=(byte)x;
		System.out.println(b);
	}
}
