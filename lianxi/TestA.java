package lianxi;

public class TestA extends TestB{
	private static final int A = 1;
	public TestA(){
		System.out.println("class A");
	}
	
	public static void main(String[] args){
		double[][] points = {
	            {1, 1}, {2, 1}, {1, 2},
	            {2, 2}, {3, 3}, {8, 8},
	            {9, 8}, {8, 9}, {9, 9}};
		System.out.println(points.length);//长度为9，是行数
		TestA a = new TestA();
		System.out.println(a.A);
//		System.out.println(TestA.B);
	}
}

class TestB{
	private static final int A = 222;
	public TestB(){
		System.out.println("class B");
	}
}