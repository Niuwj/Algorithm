package exercise;

interface Product{
	int getProduceTime();
}

public class Printer implements Output,Product{

	/**
	 * @param args
	 */
	private String[] printData = new String[MAX_CACHE_LINE];
	private int datanum = 0;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Output ot = new Printer();
		ot.getData("java web tutorial");
		ot.getData("crazy java");
		ot.out();
		ot.getData("machine learning");
		ot.getData("data mining");
		ot.out();
	}

	@Override
	public int getProduceTime() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public void out() {
		// TODO Auto-generated method stub
		while(datanum > 0){
			System.out.println("打印机打印："+printData[0]);
			System.arraycopy(printData,1, printData, 0, --datanum);
		}
	}

	@Override
	public void getData(String msg) {
		// TODO Auto-generated method stub
		if(datanum > MAX_CACHE_LINE){
			System.out.println("输出队列已满，添加失败");
		}else {
			printData[datanum] = msg;
			datanum++;
		}
	}

}
