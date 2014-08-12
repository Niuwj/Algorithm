package exercise;

public class OutputFactory {

	/**
	 * @param args
	 */
	
	//简单工厂的设计模式
	//面向接口编程
	public Output getOutput(){
		//return new Printer();
		return new BetterPrinter();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OutputFactory oFactory = new OutputFactory();
		Output ot = oFactory.getOutput();
		Computer computer = new Computer(ot);
		computer.keyin("hello");
		computer.keyin("world");
		computer.print();
	}

}
