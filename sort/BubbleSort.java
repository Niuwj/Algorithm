package sort;

public class BubbleSort {
	private int[] a;
	public BubbleSort(int[] a) {
		// TODO Auto-generated constructor stub
		this.a=a;
	}
	
	public void bubble_sort(){
		//将数组a中排列成从小到大的有序的整数序列
		for(int i=a.length-1;i>0;i--){
			for(int j=0;j<i;j++){
				if(a[j]>a[j+1]){
					System.out.println("change"+a[j]+"  "+a[j+1]);
					int temp;
					temp=a[j+1];
					a[j+1]=a[j];
					a[j]=temp;					
				}
			}
		}
	}
	public void show(){
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}
}
