package exercise;

public class EnumTest {

	public void judge(SeasonEnum seasonEnum){
		switch (seasonEnum) {
		case SPRING:
			System.out.println("when spring comes, flowers blossom.");
			break;
		case SUMMER:
			System.out.println("when summer comes, it's swimming time");
			break;
		case FALL:
			System.out.println("when fall comes, the air is fresh.");
			break;
		case WINTER:
			System.out.println("when winter comes, the groud is white");
			break;
		default:
			break;
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(SeasonEnum seasonEnum:SeasonEnum.values()){
			System.out.println(seasonEnum);
		}
		System.out.println(SeasonEnum.SPRING.compareTo(SeasonEnum.FALL));
		System.out.println(SeasonEnum.SPRING.toString()+SeasonEnum.SPRING.ordinal());
		new EnumTest().judge(SeasonEnum.SUMMER);
	}

}
