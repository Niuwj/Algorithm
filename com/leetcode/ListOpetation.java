package com.leetcode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;



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
	
	//Given a sorted linked list, delete all duplicates such that each element appear only once.
	public ListNode deleteDuplicates(ListNode head) {
        if(head==null || head.next == null){
        	return head;
        }
        
        //p找到一个节点，q找到第一个与该节点值不相等的节点
        ListNode p = head;
        ListNode q = head.next;
        
        while(q!=null){
        	if( p.val == q.val){
        		q = q.next;
        	}else {
				p.next = q;
				q = q.next;
				p = p.next;
			}       	
        }
        if(q==null){
			p.next = null;
		}
        
        return head;        
    }
	
	//Given a sorted linked list, delete all nodes that have duplicate numbers, 
	//leaving only distinct numbers from the original list.
	public ListNode deleteDuplicates2(ListNode head) {
        if(head==null || head.next==null){
        	return head;
        }
        //有可能会删除第一个节点，所以head需要先保护起来     
        ListNode pre = new ListNode(-1);
        pre.next =head;        
        ListNode p = head;
        head = pre;
        while(p.next!=null){
        	boolean flag = false;        	       	
        	
        	while(p.next!=null && p.val==p.next.val ){
        		flag = true;
        		p = p.next;
        	}        	
        	if(flag){
        		pre.next = p.next;
        		p = p.next;
        	}else {
				pre = p;
				p = p.next;
			}
        	if(p==null){
    			return head.next;
    		}
        }
        return head.next;
    }
	
	
	//Given a singly linked list where elements are sorted in ascending order, 
	//convert it to a height balanced BST.
	//解法：这题的关键是能找出当前链表的中间节点，然后再递归左右的子链表，开始的时候程序先计算链表总长，然后传入两个前后索引指针，最后每次递归找出中间节点即可
	public TreeNode sortedListToBST(ListNode head) {
        if(head==null){
			return null;
		}
		ListNode p = head;
		int len = 0;
		while(p!=null){
			len++;
			p = p.next;
		}
		TreeNode root = l2bst(head,1,len);
		return root;
    }
	
	public TreeNode l2bst(ListNode head, int st, int ed){
		if(st>ed){
			return null;
		}
		int mid = (st+ed)/2;
		ListNode m = head;
		for(int i=st;i<mid;i++){
			m = m.next;
		}
		TreeNode parent = new TreeNode(m.val);
		if(st!=ed){
			TreeNode lt = l2bst(head, st, mid-1);
			TreeNode rt = l2bst(m.next,mid+1 ,ed);			
			parent.left = lt;
			parent.right = rt;
		}
		return parent;
	}
	
	//Sort a linked list using insertion sort.
	public ListNode insertionSortList(ListNode head) {
        if(head==null){
        	return head;
        }
        
        ListNode p = new ListNode(-1);
        p.next = head;
        //避免头结点的特殊情况
        head = p;
        //p是待插入点的前驱，从头到尾走一遍链表
        ListNode q;
        while(p.next!=null){
        	
        	//q找插入位置
        	q = head;
        	//flag用来标识是p是否移动，如果找到插入点并插入，p不动；否则q后移
        	boolean flag = false;
        	while(q!=p){
        		if(q.next.val > p.next.val){
        			ListNode temp = p.next;
        			p.next = p.next.next;
        			temp.next = q.next;
        			q.next = temp;
        			flag = true;
        			break;
        		}else {
					q = q.next;
				}
        	}
        	if(!flag){
        		p = p.next;
        	}
        }
        return head.next;
    }
	
	/*Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
You should preserve the original relative order of the nodes in each of the two partitions.
For example,
Given 1->4->3->2->5->2 and x = 3,
return 1->2->2->4->3->5.
*/
	
	public ListNode partition(ListNode head, int x) {
        if(head == null || head.next == null){
        	return head;
        }
        
        //两个指针，一个less指向的是小于x的,ltail指向链尾，另一个great指向的是大于x的，gtail指向链尾
        ListNode p = new ListNode(0);
        p.next = head;
        
        ListNode less = new ListNode(-1);
        ListNode ltail = less;
        ListNode great = new ListNode(-1);
        ListNode gtail = great;
        while(p.next!=null){
        	ListNode tmp = p.next;
        	p.next = p.next.next;
    		tmp.next = null;        	
        	if(tmp.val<x){
        		ltail.next = tmp;
        		ltail = ltail.next; 
        	}else {
				gtail.next = tmp;
				gtail = gtail.next;
			}
        }
        if(less.next==null){
        	return great.next;
        }
        ltail.next= great.next;
        return less.next;        
    }
	
	//反转/逆置单链表
	public ListNode reverseList(ListNode head){
		//result是逆置后的头结点
		ListNode result = null;
		//p指向当前节点，pre指向当前节点的前驱
		ListNode p = head;
		ListNode pre = null;
		while(p!=null){
			ListNode next = p.next;
			if(next==null){
				result = p;
			}
			p.next = pre;
			pre = p;
			p = next;					
		}
		return result;
	}
	
	/*Reverse a linked list from position m to n. Do it in-place and in one-pass.
For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,
return 1->4->3->2->5->NULL.
Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.*/
	
	public ListNode reverseBetween(ListNode head, int m, int n) {
        //找到第m个节点pm的前驱prem，pn指向第n个节点
		if(m==n){
            return head;
        }
		//ph保护头结点，如果m==1，头结点将指向pn
        ListNode ph = new ListNode(-1);
		ph.next = head;
		ListNode prem = new ListNode(-2);
		prem.next = head;
		ListNode pm=null,pn=null;
		//指针p遍历链表
		ListNode p = new ListNode(-3);
		p.next = head;
		int count = 0;
		while(p!=null){
			//找到pm后继续找pn
			if(count==m-1){
				prem = p;
			}
			//找到pn后跳出
			if(count==n){
				pn = p;
				break;
			}
			p = p.next;
			count++;
		}
		
		pm = prem.next;
		
		//p在此指向pn的next
		p = pn.next;
		
		//reverse从pm到pn的节点
		
		//p指向当前节点，pre指向当前节点的前驱
		ListNode rp = pm;
		//此处pre初始化为p，即pn的next，这样链表的m之后的就连起来了，相当于在反转后pm的next即指向了p		
		ListNode pre = p;
		while(rp!=p){
			ListNode next = rp.next;
			rp.next = pre;
			if(next == p){
			    break;
			}
			pre = rp;
			rp = next;	
		}
		prem.next = rp;
		//pm.next = p;
		if(m==1){
			ph.next = pn;
		}
		return ph.next;
    }
	
	//k个节点一组，逆序
	public ListNode reverseKGroup(ListNode head, int k) {
        if(head==null){
        	return null;
        }
        ListNode p = new ListNode(-1);
        p.next = head;
        head = p;
    	ListNode q = p;
        while(true){
            int i = 0;
        	while(q!=null && i<k){
        		q = q.next;
        		i++;
        	}
        	
        	if(q==null){
        		return head.next;
        	}else {        		
				//循环左移
				while(p.next!=q){
					ListNode pnext,qnext;
					pnext = p.next;
					qnext = q.next;
					p.next = p.next.next;
					q.next = pnext;
					pnext.next = qnext;					
				}
			}
        	for(int j=0; j<k; j++){
        		p = p.next;
        	}
        	q = p;
        }
    }
	
	/*
	 * Given a singly linked list L: L0→L1→…→Ln-1→Ln,
reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

You must do this in-place without altering the nodes' values.
	 */
	//算法：1.用快慢指针找到中间节点mid；2，将mid之后的链表逆序；3.合并前后两个链表
	public void reorderList(ListNode head) {
		
		if(head ==null || head.next==null || head.next.next==null){
        	return;
        }
        
        //1.找到中间节点mid，快慢指针
        ListNode p = head;
        ListNode quick = head;
        while(quick.next!=null&&quick.next.next!=null){
        	quick = quick.next.next;
        	p = p.next;
        }
        
        //此时p指向的是中间节点
        //2逆序p之后的链表
        quick = p.next;
        p.next = null;
        //System.out.println(quick.val);
        ListNode pre = null;
        while(quick!=null){
        	ListNode next = quick.next;
        	if(next ==null){
        		quick.next = pre;
        		break;
        	}
        	quick.next = pre;
        	pre = quick;
        	quick = next;
        }
        //quick.print();
        //此时quick指向的是逆序后的后半部分链表
        //3.合并head和quick
        
        p = head;
        while(p!=null && quick!=null){
        	ListNode next = quick.next;
        	quick.next = p.next;
        	p.next = quick;
        	quick = next;
        	p = p.next.next;
        }        
    }
	
	/*
	 * Copy List with Random Pointer
	 * A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
思路一：将A节点和复制的节点A'存入hashmap
思路二：分三步：1.将复制的节点插入被复制节点之后，A->A'->B->B'->C->C'...；2.复制random指针；3.分拆两个链表
	 */
	public RandomListNode copyRandomList(RandomListNode head) {
        if(head==null){
        	return null;
        }
		RandomListNode p = head;
        while(p!=null){
        	RandomListNode newNode = new RandomListNode(p.label);
        	newNode.next = p.next;
        	p.next = newNode;
        	p = newNode.next;
        }
		
        p = head;
        while(p!=null){
        	if(p.random!=null){
        		p.next.random = p.random.next;
        	}
        	p = p.next.next;
        }
        p = head;
        RandomListNode result = head.next;
        RandomListNode copy = result;
        while(copy.next!=null){
        	p.next = copy.next;
        	copy.next = copy.next.next;
        	p = p.next;
        	copy = copy.next;
        }
        p.next = null;
        return result;        
    }
}
