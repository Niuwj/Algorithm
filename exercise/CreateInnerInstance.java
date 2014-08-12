package exercise;

class Out{
	class In{
		public In(String msg){
			System.out.println(msg);
		}
	}
}

public class CreateInnerInstance {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Out.In in = new Out().new In("hello");
	}

}

class SubClass extends Out.In{
	public SubClass(Out out){
		//通过传入的Out对象，显示调用In构造器
		out.super("hello");
	}
}