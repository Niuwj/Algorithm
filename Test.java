import java.io.File;

/**
 * 
 * @author nwj
 * 列出D盘中的所有文件夹
 *
 */

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//地址不区分大小写
		File root=new File("E:/ppt模版");
		File[] files=root.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				System.out.println(file.getAbsolutePath());
			}
		}
	}

}
