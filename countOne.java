
public class countOne {

	/**
	 * 对于任意整数n，返回0到n直接出现‘1’的个数
	 * weight是代表个数百千，最高位的权重
	 * 三部分：
	 * 如：999
	 * 0~899
	 * 900~999
	 * 然后递归 
	 */
	/*
	 * 对于数n，可以把它分成三段，高位段most，当前位cur，低位段least，每一段分别为一个整数。
	 * 对于一个有weight位的数，假设当前位是左数第i位，则设一个临时变量tmp为10的weight-i次方，即比least多一位的最小整数。
	 * 如数123456，为6位数，weight=6，设当前为左起第3位，则i=3，most=12，cur=3，
least=456，tmp=1000。
如果当前位大于1，则从1到n间出现在当前位出现的1的个数是most*tmp+tmp；如果等于1，则是
most*tmp+least+1；如果小于，则为most*tmp。
	 */
	static int count(int n){
		int count = 0;
		//count the 位数 of n
		int weight = (int)Math.log10((double)n)+1;
		int most,cur,least,tmp;
		
		for(int i=0; i<weight; i++){
			tmp = (int)Math.pow(10, weight-i-1);
			most = n/tmp/10;
			cur = (n/tmp)%10;
			least = n%tmp;
			count += most*tmp;
			if(cur>1){
				count += tmp;
			}else if (cur==1) {
				count += least+1;
			}
		}
		return count;
		
	}
	
	
	public static void main(String[] args) {
		System.out.println(count(113));

	}

}
