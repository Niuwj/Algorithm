package exercise;

public class Exercise {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char ch=Character.toUpperCase('g');
		System.out.println(ch);
		String string="web舒服sfh";
		System.out.println(string.charAt(5));
		System.out.println(string.length());
		String string2=" boo:foofL:fha s  ";
		String[] ss=string2.split("o");
		for(int i=0;i<ss.length;i++){
			System.out.println(i+ss[i]);
		}
		System.out.println(string2.trim());
		
		//常量池constant pool
		String s1="疯狂java讲义";
		String s2="疯狂";
		String s3="java";
		String s4="讲义";
		String s5="疯狂"+"java"+"讲义";
		String s6="疯狂java"+"讲义";
		String s7=s1+s2+s3;
		//通过调试可看出其指向的是同一内存地址
		System.out.println(s1==s5);
		System.out.println(s1==s6);
		System.out.println(s1==s7);
		Integer inta=2;
		Integer intb=2;
		System.out.println(inta==intb);
		
		System.out.println(System.identityHashCode(intb)+"   "+System.identityHashCode(inta));
	}
}
