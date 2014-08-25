package com.leetcode;


//cache块的结构是双向链表，可以在O(1)时间内，调整到链表头，删除节点，添加，都是O(1)

public class CacheNode {
	int value;
	int key;
	CacheNode prev = null;
	CacheNode next = null;
	public CacheNode(int key, int value, CacheNode p, CacheNode n){
		this.key = key;
		this.value = value;
		this.prev = p;
		this.next = n;
	}
	public CacheNode(int key, int value){
		this.key = key;
		this.value = value;
	}
}
