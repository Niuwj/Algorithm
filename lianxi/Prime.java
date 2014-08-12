package lianxi;

import java.lang.Math;

public class Prime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char c = '好';
		System.out.println(c);
		Prime prime = new Prime();
		prime.isPrime(99);
	}
	
	public boolean isPrime(int n){
		boolean flag = false;
		int i = 2;
		while(n%i !=0 && i*1.0 <Math.sqrt(n)){
			i++;
		}
		if(i*1.0 > Math.sqrt(n)){
			System.out.println(n+" 是素数");
			flag = true;
		} else {
			System.out.println(n+" 不是素数");
		}
		
		return flag;
	}

}
