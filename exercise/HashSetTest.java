package exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
class R
{
	int count;
	public R(int count)
	{
		this.count = count;
	}
	public String toString()
	{
		return "R[count:" + count + "]";
	}
	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if (obj != null && obj.getClass() == R.class)
		{
			R r = (R)obj;
			if (r.count == this.count)
			{
				return true;
			}
		}
		return false;
	}
	public int hashCode()
	{
		return this.count;
	}
}
public class HashSetTest
{
	public static void main(String[] args) 
	{
//		HashSet hs = new HashSet();
//		hs.add(new R(5));
//		hs.add(new R(-3));
//		hs.add(new R(9));
//		hs.add(new R(-2));
//		//打印HashSet集合，集合元素没有重复
//		System.out.println(hs);
//		//取出第一个元素
//		Iterator it = hs.iterator();
//		R first = (R)it.next();
//		//为第一个元素的count实例变量赋值
//		first.count = -3;     //①
//		//再次输出HashSet集合，集合元素有重复元素
//		System.out.println(hs);
//		//删除count为-3的R对象
//		hs.remove(new R(-3));   //
//		//可以看到被删除了一个R元素
//		System.out.println(hs);
//		//输出false
//		System.out.println("hs是否包含count为-3的R对象？"
//			+ hs.contains(new R(-3)));
//		//输出false
//		System.out.println("hs是否包含count为5的R对象？"
//			+ hs.contains(new R(5)));
		
		String[] books = {
				"疯狂Java讲义",
				"轻量级Java EE企业应用实战"
			};
			List bookList = new ArrayList();
			for (int i = 0; i < books.length ; i++ )
			{
				bookList.add(books[i]);
			}
			ListIterator lit = bookList.listIterator();
			while (lit.hasNext())
			{
				System.out.println(lit.next());
				lit.add("-------分隔符-------");
			}
//			System.out.println("=======下面开始反向迭代=======");
//			while(lit.hasPrevious())
//			{
//				System.out.println(lit.previous());
//			}
		
	}
	
	
}
