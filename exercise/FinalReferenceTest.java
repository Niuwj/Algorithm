package exercise;

import java.util.Arrays;

public class FinalReferenceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s1 = "hello world";
//		String str1 = "hello ";
//		String str2="world";
		//宏替换
		final String str1 = "hello ";
		final String str2="world";
		String s2=str1+str2;
		System.out.println(s1 == s2);
		
		
		final int[] iArr = {5,6,12,0,10,9};
		System.out.println(Arrays.toString(iArr));
		//可对数组元素进行排序
		Arrays.sort(iArr);
		System.out.println(Arrays.toString(iArr));
		//对数组元素复制，合法
		iArr[2]=-9;
		System.out.println(Arrays.toString(iArr));
		//对iArr重新复制，非法
		//iArr = null;
		final Person person=new Person(20);
		//final修饰引用类型变量p
		person.setAge(22);
		System.out.println(person.getAge());
		//对p重新复制，非法
		//person = null;
	}

}

class Person{
	private int age;
	public Person(){};
	public Person(int age){
		this.age=age;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public final void test(){}
	
}
class Sub extends Person{
	//final修饰的方法不能被子类重写，但可以被重载
	//public void test(){}
	public void test(int a){
	}
}