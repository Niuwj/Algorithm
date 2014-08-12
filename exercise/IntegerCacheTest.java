package exercise;

public class IntegerCacheTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//生成新的Integer对象
		Integer int1 = new Integer(6);
		//生成新的Integer对象，并缓存该对象，valueof方法用了缓存池的策略，但是只缓存了-128~127
		Integer int2 = Integer.valueOf(6);
		Integer int3 = Integer.valueOf(6);
		//输出false
		System.out.println(int1 == int2);
		//输出true
		System.out.println(int2 == int3);
		Integer int4 = Integer.valueOf(128);
		Integer int5 = Integer.valueOf(128);
		//输出false
		System.out.println(int4 == int5);
		
	}

}
