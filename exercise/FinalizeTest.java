package exercise;

public class FinalizeTest {
	private static FinalizeTest fTest = null;
	public void info(){
		System.out.println("test resource clean finalize method");
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建FinalizeTest对象立即进入可恢复状态
		new FinalizeTest();
		//通知系统进行资源回收
		System.gc();
		//强制垃圾回收机制调用可恢复对象的finalize方法
		//Runtime.getRuntime().runFinalization();
		System.runFinalization();
		fTest.info();
	}
	
	public void finalize(){
		fTest = this;
	}
}
