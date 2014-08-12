package crazy.chapter7;

import java.util.Objects;

public class ObjectsTest{
	static ObjectsTest objectsTest;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Objects.hashCode(objectsTest));
		System.out.println(Objects.toString(objectsTest));
		System.out.println(Objects.requireNonNull(objectsTest, "not null"));
	}

}
