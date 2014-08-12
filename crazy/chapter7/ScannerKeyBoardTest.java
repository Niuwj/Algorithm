package crazy.chapter7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerKeyBoardTest {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new File("src/crazy/chapter7/ScannerKeyBoardTest.java"));
		//sc.useDelimiter("\n");
		//System.out.println(sc.delimiter());
		while(sc.hasNextLine()){
		//while(sc.hasNextLine()){
			//System.out.println("键盘输入的内容是："+sc.next());
			System.out.println(sc.nextLine());
		}
		sc.close();
		
	}

}
