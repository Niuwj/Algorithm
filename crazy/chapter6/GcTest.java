package crazy.chapter6;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GcTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for(int i=0; i<4; i++){
			new GcTest();
			Runtime.getRuntime().gc();
		}
		Map map = Collections.synchronizedMap(new HashMap());
	}
	//永远不要调用该方法，finalize方法应交给gc调用
	public void finalize(){
		System.out.println("系统正在清理GcTest对象的资源！");
	}

}
