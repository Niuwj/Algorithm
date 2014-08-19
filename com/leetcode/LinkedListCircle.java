package com.leetcode;

import java.util.HashSet;
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
	//找到起点
	/*
	 * Given a linked list, return the node where the cycle begins. 
	 * If there is no cycle, return null.
	 * Follow up:
Can you solve it without using extra space?
	 */
	
	//不求环长，根据   s=la + x; 2s = la + x +k*loop
	// 得出  la = k*loop -x;所以当两指针相遇后，让慢指针从头走，快指针从相遇点开始，均一次一步，再次相遇点即为环起始点
	public ListNode detectCycle(ListNode head) {
		if(head == null){
        	return null;
        }		
		ListNode p = head;
        ListNode q = head;
        boolean tag = false;
        while(q.next != null && q.next.next!= null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q){
        		tag = true;
        		p = head;
        		while(p!=q){        			
        			p = p.next;
        			q = q.next;
        		}
        		break;
        	}
        }
        if(tag){
        	return p;
        }else {
			return null;
		}
    }
	
	//求环长
	public int getCycleLen(ListNode head) {
		if(head == null){
        	return 0;
        }		
		ListNode p = head;
        ListNode q = head;
        int loop = 0;
        boolean tag = false;
        while(q.next != null && q.next.next!= null){
        	p = p.next;
        	q = q.next.next;
        	if(p == q){
        		//有环
        		tag = true;
        		//求环长
        		while(q.next != p){
        			loop++;
        			q = q.next;
        		}
        		System.out.println(loop);
        		break;
        	}
        }
        //注意，环长节点个数是loop+1，三个节点组成的环的环长是3，此时loop=2
        return loop;
    }
	
	
	
	//用哈希表存储节点地址
	public ListNode detectC( ListNode head){
		HashSet<ListNode> nodes = new HashSet<ListNode>();
	    ListNode current = head;

	    while(current != null){
	        if(nodes.contains(current))
	            return current;
	        nodes.add(current);
	        current = current.next;
	    }

	    return null;
	}
	
	
}
