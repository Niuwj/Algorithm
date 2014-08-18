package com.leetcode;
/*
 * Given a linked list, determine if it has a cycle in it.
 * 快慢指针
 */


public class LinkedListCircle {
	public boolean hasCycle(ListNode head) {
        if(head == null){
        	return false;
        }
		ListNode p = head;
        ListNode q = head;        
        while(q.next != null && q.next.next!= null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q){
        		return true;
        	}
        }
        return false;		
    }
	//找到交点
	/*
	 * Given a linked list, return the node where the cycle begins. 
	 * If there is no cycle, return null.
	 * Follow up:
Can you solve it without using extra space?
	 */
	public ListNode detectCycle(ListNode head) {
		if(head == null){
        	return null;
        }
		ListNode p = head;
        ListNode q = head;
        int loop = 0;
        while(q.next != null && q.next.next!= null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q){
        		//求环长
        		while(q.next != p){
        			loop++;
        			q = q.next;
        		}
        		System.out.println(loop);
        		break;
        	}
        }
        if(loop == 0){
        	return null;
        }else{
        	p = head;
            q = head;
            //让q先走loop步
            while(loop!=0){
            	q = q.next;
            	loop--;
            }
            //当q.next == p时，p即为环的起点
            while(q.next != p){
            	p = p.next;
            	q = q.next;
            }
            return p;
        } 
    }
}
