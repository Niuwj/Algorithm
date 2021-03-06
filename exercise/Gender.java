package exercise;

interface GenderDec {
	void info();
}

public enum Gender implements GenderDec{
	MALE("男"){
		public void info(){
			System.out.println("这个枚举值代表男性");
		}
	},
	FEMALE("女"){
		public void info(){
			System.out.println("这个枚举值代表女性");
		}
	};
	private final String name;
	private Gender(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
