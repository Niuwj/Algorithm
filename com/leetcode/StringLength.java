package com.leetcode;

//刚才写的。。。。
import java.io.DataInputStream;
import java.io.IOException;

public class StringLength {

  public String search(String text, String query) {
      String max = "";
      for (int i = 0; i < text.length(); i++) {
          for (int j = i; j < text.length(); j++) {
              String sub = text.substring(i, j);
              if ((query.indexOf(sub) != -1) && sub.length() > max.length()) {
                  max = sub;
              }
          }
      }
      return max;
  }

  public static void main(String[] args) {
      String a = "";
      String b = "";
      System.out.println("请输入第一个字符串:");
      a = inputString.getString();
      System.out.println("请输入第二个字符串:");
      b = inputString.getString();
      String output = new StringLength().search(a, b);
      System.out.println("相同的字符串是：" + output + "长度为:" + output.length());
  }
}

class inputString {
  public static String getString() {
      DataInputStream dis = new DataInputStream(System.in);
      String value = null;
      try {
          @SuppressWarnings("deprecation")
          String str = dis.readLine();
          value = str;

      } catch (IOException e) {
          e.printStackTrace();
      }
      return value;
  }
}