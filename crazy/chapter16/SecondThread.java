package crazy.chapter16;
public class SecondThread implements Runnable
{
	private int i ;
	// run����ͬ�����߳�ִ����
	public void run()
	{
		for ( ; i < 100 ; i++ )
		{
			// ���߳���ʵ��Runnable�ӿ�ʱ��
			// ������ȡ��ǰ�̣߳�ֻ����Thread.currentThread()������
			System.out.println(Thread.currentThread().getName()
				+ "  " + i);
		}
	}
		
	public static void main(String[] args) 
	{
		for (int i = 0; i < 100;  i++)
		{
			System.out.println(Thread.currentThread().getName()
				+ "  " + i);
			if (i == 20)
			{
				SecondThread st = new SecondThread();     // ��
				// ͨ��new Thread(target , name)�����������߳�
				new Thread(st , "新线程1").start();
				new Thread(st , "新线程2").start();
			}
		}
	}
}

