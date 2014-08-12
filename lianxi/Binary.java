package lianxi;

public class Binary {
	public static void main(String[] args){
		int n = 11;
		int count = 0;
		while(n != 0){
			++count;
			n = (n-1)&n;
		}
		System.out.println(count);
		
	}
}
