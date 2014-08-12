package crazy.chapter6;

public class FinalTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//定义宏变量
		final Integer a = 5;
		final String book = "疯狂java讲义" + 99.0;
		final String book2 = "疯狂java讲义" + String.valueOf(99.0);
		//System.out.println(a.hashCode());
		//System.out.println(a);
		System.out.println(book == "疯狂java讲义99.0");
		System.out.println(book2 == "疯狂java讲义99.0");
	}

}
