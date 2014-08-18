package com.leetcode;

public class ListNode {
	int val;
	ListNode next;
	ListNode(int x){
		val = x;
		next = null;
	}
	
	public void print(){
		ListNode p = this;
		while(p!= null){
			if(p.next == null){
				System.out.println(p.val);
				return;
			}
			System.out.print(p.val+" ->");
				p = p.next;
		}
	}
}
