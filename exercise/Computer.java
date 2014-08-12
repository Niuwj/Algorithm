package exercise;

public class Computer {
	private Output ot;
	public Computer(Output ot){
		this.ot = ot;
	}
	
	public void keyin(String msg){
		ot.getData(msg);
	}
	
	public void print(){
		ot.out();
	}
}
