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
}
