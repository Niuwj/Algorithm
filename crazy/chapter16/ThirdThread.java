package crazy.chapter16;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class ThirdThread implements Callable<Integer>
{
	// ʵ��call��������Ϊ�߳�ִ����
	public Integer call()
	{
		int i = 0;
		for ( ; i < 50 ; i++ )
		{
			System.out.println(Thread.currentThread().getName()
				+ " 的循环变量i的值：" + i);
		}
		// call()���������з���ֵ
		return i;
	}

	public static void main(String[] args) 
	{
		// ����Callable����
		ThirdThread rt = new ThirdThread();
		ThirdThread rt2 = new ThirdThread();
		// ʹ��FutureTask����װCallable����
		FutureTask<Integer> task = new FutureTask<Integer>(rt);
		FutureTask<Integer> task2 = new FutureTask<Integer>(rt2);
		for (int i = 0 ; i < 30 ; i++)
		{
			System.out.println(Thread.currentThread().getName()
				+ " 的循环变量i的值：" + i);
			if (i == 20)
			{
				// ʵ�ʻ�����Callable�������������������߳�
				new Thread(task , "返回值1").start();
				new Thread(task2,"线程2：").start();
			}
		}
		try
		{
			// ��ȡ�̷߳���ֵ
			System.out.println("子线程1的返回值" + task.get());
			System.out.println("线程2的返回值：" + task2.get());
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}
}

