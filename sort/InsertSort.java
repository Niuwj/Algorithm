package sort;

public class InsertSort {
	private int[] a;
	public InsertSort(int[] a) {
		// TODO Auto-generated constructor stub
		this.a=a;
	}
	
	public void sort(){
		int temp;
		for(int i=1;i<a.length;i++){
			temp=a[i];
			int j=i-1;
			while(j>=0 && a[j]>temp){
				a[j+1]=a[j];
				j--;
			}
			a[j+1]=temp;
		}
	}
	
	
	public void show(){
		for(int i=0;i<a.length;i++){
			System.out.println(a[i]);
		}
	}
}
