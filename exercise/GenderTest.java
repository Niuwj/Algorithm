package exercise;

public class GenderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gender gender = Enum.valueOf(Gender.class, "MALE");
		System.out.println(gender.getName());
		gender.info();
	}

}
