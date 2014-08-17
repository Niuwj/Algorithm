package com.leetcode;

import java.util.Stack;

/**
 * aluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. Each operand may be an integer or another expression.
Some examples:
  ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
  ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 * @author nwj
 *
 */

public class EvalRPN {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] tokens = new String[]{"2", "1", "+", "3", "*"};
		int result = evalRPN(tokens);
		System.out.println(result);
	}
	public static int evalRPN(String[] tokens) {
		int result = 0;
		String operators = "+-*/";
		int size = tokens.length;
		Stack<String> stack = new Stack<String>();
		for( int i = 0; i<size; i++){
			String temp = tokens[i];
			if(!operators.contains(temp)){
				stack.push(temp);
			}else{
				int b = Integer.valueOf(stack.pop());
				int a = Integer.valueOf(stack.pop());
				int index = operators.indexOf(temp);
				switch (index) {
				case 0:
					stack.push(String.valueOf(a+b));
					break;
				case 1:
					stack.push(String.valueOf(a-b));
					break;
				case 2:
					stack.push(String.valueOf(a*b));
					break;
				case 3:
					stack.push(String.valueOf(a/b));
					break;
				default:
					break;
				}
			}
		}
		result = Integer.valueOf(stack.pop());
		return result;
    }
}
