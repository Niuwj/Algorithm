package designPattern;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		Hello hello = new StaticProxy(new HelloImpl());
//		System.out.println(hello.greet("nwj"));
		Hello hello = (Hello)new DynamicProxy().bind(new HelloImpl());
		String re = hello.greet("nwj");
		System.out.println(re);
	}

}
