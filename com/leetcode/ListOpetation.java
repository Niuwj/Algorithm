package com.leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javax.print.attribute.standard.MediaSize.Engineering;


public class ListOpetation {
	
	
	/*删除倒数第n个节点
	 * remove Nth node From End of List
	 * Given a linked list, remove the nth node from the end of list and return its head.
For example,
   Given linked list: 1->2->3->4->5, and n = 2.
   After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:
Given n will always be valid.
Try to do this in one pass.
	 * 
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
        //思路：1，三个指针，快指针quick，待删除节点前边的指针preremove；
		//2，让快指针比慢指针先走n步，慢指针再出发，当快指针为null时，慢指针的下个节点即为待删除节点
		ListNode quick = head;
		ListNode preremove = new ListNode(0);
		preremove.next = head;
		
		if(head == null){
			return null;
		}
		
		if(n<1){
			return head;
		}
		
		int k = n;
		while(k!=0 && quick != null){
			k--;
			quick = quick.next;
		}
		
		//如果只有m个节点，且n>m，返回head
		if(k>0 && quick==null){
			return head;
		}
		
		//如果只有m个节点，且n==m，删除首节点，返回head
		if(k==0 && quick == null){
			head= head.next;
			return head;
		}
		//如果有m>1个节点，n<m，删除倒数第k个，使用quick.next判断循环，就将慢指针指向了待删除节点的前驱
		while(quick!=null){
			quick = quick.next;
			preremove = preremove.next;
		}
		preremove.next = preremove.next.next;
		return head;
		
    }
	
	
	/*
	 * 旋转链表
	 * Given a list, rotate the list to the right by k places, where k is non-negative.
For example:
Given 1->2->3->4->5->NULL and k = 2,
return 4->5->1->2->3->NULL.

ps:链表向右循环移动3次
Input:	{1,2}, 3
Output:	{1,2}
Expected:	{2,1}
	 */
	public ListNode rotateRight(ListNode head, int n) {
        //思路：1.记录链表长度，找到倒数第n%m个节点rotate，及其前驱节点p
		//2.设定链表最后一个节点的next为head，pre的next为null
		//3.返回rotate
		
		if(head == null){
        	return null;
        }
        if(n == 0){
        	return head;
        }
        //count记录链表长度
        int len = 0;
        ListNode p = head;
		while(p!=null){
			len++;
			p = p.next;
		}
		
		//得到rotate节点前驱，如{1,2,3},n=2,得到的k=0，指针指向第一个节点，即值为2的节点的前驱
		int k;
		if(n%len==0){
			return head; 
		}else {
			k = len - (n%len)-1;
		}		
		p = head;
		while( k > 0){
			p = p.next;
			k--;
		}
		//得到rotate节点
		ListNode rotate = p.next;
		if(rotate==null){
			return head;
		}
		//找到链尾并改变链尾指向，p的指向，返回rotate
		while(rotate.next!=null){
			rotate = rotate.next;
		}
		//将链尾指向原来的头指针
		rotate.next = head;
		//将rotate重新指向旋转节点
		rotate = p.next;
		//将旋转节点的前驱作为链尾
		p.next = null;
		return rotate;
    }
	
	
	
/*
 * 问题：
在O(N lgK) 时间内合并K个有序链表， 这里N指的是K个链表中所有的元素个数。
分析：
这是一道非常经典的面试题，在很多大公司的面试题中，此题频繁出现。这题也是算法导论的作业题。
这题的思路如下：
1） 在每一个链表中取出第一个值，然后把它们放在一个大小为K的数组里，然后把这个数组当成heap，然后把该堆建成最小堆。此步骤的时间复杂度为O（K）
2 ）取出堆中的最小值（也是数组的第一个值），然后把该最小值所处的链表的下一个值放在数组的第一个位置。如果链表中有一个已经为空（元素已经都被取出），则改变heap的大小。然后，执行MIN-HEAPIFY操作，此步骤的时间复杂度为O(lg K).
3 ） 不断的重复步骤二，直到所有的链表都为空。

ps：java的PriorityQueue就是一种堆heap结构
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
	public ListNode mergeKLists(List<ListNode> lists) {
        int size = lists.size();
        ListNode head = new ListNode(-1);
        ListNode tail = head;
        if(lists == null ||size==0){
        	return null;
        }
        Comparator<ListNode> mycmp = new Comparator<ListNode>(){
        	public int compare(ListNode l1,ListNode l2){
        		if(l1.val>l2.val)
        			return 1;
        		else if (l1.val<l2.val) {
					return -1;
				}else {
					return 0;
				}
        	}
        };
        
        PriorityQueue<ListNode> heap = new PriorityQueue<ListNode>(size, mycmp);
        
        for(ListNode n:lists){
        	if(n!=null){
        		heap.add(n);
        	}
        }
        while(!heap.isEmpty()){
        	ListNode tmp = heap.poll();        	
        	if(tmp.next!=null){
        		heap.add(tmp.next);
        	}
        	tail.next = tmp;
        	tail = tail.next;
        }
        
        return head.next;        
    }
	/*
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 * 
	 * 归并排序的基本思想是：找到链表的middle节点，然后递归对前半部分和后半部分分别进行归并排序，最后对两个以排好序的链表进行Merge。
	 * 
	 */
	public ListNode sortList(ListNode head) {
		if(head==null || head.next==null){
			return head;
		}
		
		//计算出总数
		int len = 0;
		ListNode q = head;
		while( q!=null ){
			len++;
			q = q.next;
		}
		
		//分为两部分
		int mid = len/2;
		ListNode p = new ListNode(-1);
		p.next = head;
		ListNode d1 = head;
		ListNode d2 = null;
		while( mid!=0 ){
			mid--;
			p = p.next;
		}
		d2 = p.next;
		p.next = null;
		
		//递归归并d1,p
		d1 = sortList(d1);
		d2 = sortList(d2);
		
		//合并两个有序链表
		ListNode result = mergeSort(d1,d2);
		return result;
    }
	public ListNode mergeSort(ListNode l1, ListNode l2){
		if(l1 == null){
        	return l2;
        }else if(l2 == null){
        	return l1;
        }
		
		ListNode pre = new ListNode(-1);
		ListNode end = pre;
		
		while(l1!=null && l2!=null){
			if(l1.val<l2.val){
				end.next = l1;
				l1 = l1.next;
			}else {
				end.next = l2;
				l2 = l2.next;
			}
			end = end.next;
			end.next = null;
		}
		
		if(l1!=null){
			end.next = l1;
		}
		if(l2!=null){
			end.next = l2;
		}
		return pre.next;
	}
	
	
}
