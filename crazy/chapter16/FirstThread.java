package crazy.chapter16;
public class FirstThread extends Thread
{
	private int i ;

	public void run()
	{
		for ( ; i < 100 ; i++ )
		{

			System.out.println(this.getName() +  " " + i);
		}
	}
	public static void main(String[] args) 
	{
		for (int i = 0; i < 100;  i++)
		{
			// ����Thread��currentThread������ȡ��ǰ�߳�
			System.out.println(Thread.currentThread().getName()
				+  " " + i);
			if (i == 20)
			{
				// ��������������һ���߳�
				new FirstThread().start();
				// �������������ڶ����߳�
				new FirstThread().start();
			}
		}
	}
}

