package exercise;

public class BetterPrinter implements Output{
	private String[] printData = new String[MAX_CACHE_LINE];
	private int datanum = 0;
	
	@Override
	public void out() {
		// TODO Auto-generated method stub
		while(datanum > 0){
			System.out.println("高速打印机正在打印："+printData[0]);
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
