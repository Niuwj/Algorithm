package com.leetcode;


/**
 * You are given two linked lists representing two non-negative numbers.
 *  The digits are stored in reverse order and each of their nodes contain a single digit.
 *   Add the two numbers and return it as a linked list.
Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
 * @author nwj
 * 模拟加法：342+465=807
 * 逆序存储：7->0->8
 *
 */


public class AddTwoNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ListNode l1 = new ListNode(2);
		ListNode x2 = new ListNode(4);
		ListNode x3 = new ListNode(3);
		l1.next = x2;
		x2.next = x3;
		
		ListNode l2 = new ListNode(8);
		ListNode y2 = new ListNode(6);
		ListNode y3 = new ListNode(6);
		ListNode y4 = new ListNode(1);
		l2.next = y2;
		y2.next =y3;
		y3.next = y4;
		
		ListNode result = addTwoNumbers(l1,l2);
		result.print();
		

	}
	//两链表相加：长度关系3种情况，l1<l2,l==l2,l1>l2
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode p1 = l1;
		ListNode p2 = l2;
		
		//进位
		int carry = 0;	
		
		//结果链表
		ListNode result = null;
		ListNode p3 = result;
		
		//处理相同长度部分
		while( p1!=null && p2!=null){
			int add = p1.val + p2.val + carry;
			//System.out.println("p1.val "+p1.val+" p2.val"+p2.val);
			if( add < 10 ){
				carry = 0;
			}else {
				carry = 1;
				add -= 10;
			}
			ListNode n = new ListNode(add);
			if(result == null){
				result = n;
				p3 = n;
			}else {
				p3.next = n;
				p3 = n;
			}
			p1 = p1.next;
			p2 = p2.next;
			//System.out.println("p1,p2 "+n.val);
		}
		
		//处理不同长部分
		while( p1!=null ){
			int add = p1.val+carry;
			if( add < 10 ){
				carry = 0;
			}else {
				carry = 1;
				add -= 10;
			}
			ListNode n = new ListNode(add);
			p3.next = n;
			p3 = n;
			p1 = p1.next;
			//System.out.println("p1 "+n.val);
		}
		
		while( p2!=null ){
			int add = p2.val+carry;
			//System.out.println("p2 add "+add);
			if( add <10 ){
				carry = 0;
			}else {
				carry = 1;
				add -= 10;
			}
			ListNode n = new ListNode(add);
			p3.next = n;
			p3 = n;
			p2 = p2.next;
			//System.out.println("p2 "+n.val);
		}
		
		//处理最后可能出现的进位
		if( p1==null && p2==null && carry!=0){
			ListNode node = new ListNode(carry);
			p3.next = node;
			//System.out.println("carry "+node.val);
		}
		return result;
	}
}