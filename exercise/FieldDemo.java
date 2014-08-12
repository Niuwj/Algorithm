package exercise;

import java.lang.reflect.Field;
import java.util.List;

public class FieldDemo<T> {

	/**
	 * @param args
	 */
	public int id = 1;
	public String name = "TianYa";
	public double[] d;
	public List<Object> lo;
	public T val;
	

	public static void main(String[] args) throws Exception{
		Class<?> c = FieldDemo.class;
//		Field field = c.getField("id");
//		Field field = c.getField("name");
		Field field = c.getField("d");
//		Field field = c.getField("lo");
//		Field field = c.getField("val");
		
		System.out.println("field 类型 " + field.getType());
		System.out.println("field 泛型:" + field.getGenericType());;
	}
}