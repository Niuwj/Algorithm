package com.leetcode;

public class MergeTwoSortedList {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
        	return l2;
        }else if(l2 == null){
        	return l1;
        }
        ListNode merge = null;
        if(l1.val < l2.val){
        	merge = l1;
        	merge.next = mergeTwoLists(l1.next, l2);
        }else {
			merge = l2;
			merge.next = mergeTwoLists(l1, l2.next);
		}
        return merge;
    }
	//非递归方法
	public ListNode merge(ListNode l1, ListNode l2 ){
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
