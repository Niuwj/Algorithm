package com.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

查询：
	根据键值查询hashmap，若命中，则返回节点，否则返回null。
	命中时，从双向链表中删除命中的节点，将其重新插入到表头。
	所有操作的复杂度均为O(1)。
插入：
	将新的节点关联到Hashmap
	如果Cache满了，删除双向链表的尾节点，同时删除Hashmap对应的记录
	将新的节点插入到双向链表中头部
更新：
	和查询相似
删除：
	从双向链表和Hashmap中同时删除对应的记录。
 */

public class LRUCache {
    
    CacheNode head;
	CacheNode tail;
	int len;
	//哈希表快速查找
	Map<Integer, CacheNode> mp = new HashMap<Integer, CacheNode>();
    int capacity; 
    public LRUCache(int capacity) {
        this.capacity = capacity;
        mp.clear();
        head = null;
        tail = null;
    }
    
    public int get(int key) {
    	CacheNode tmp = mp.get(key);
        if(mp.isEmpty()||tmp == null){
//        	System.out.println(key+" not found!");
        	return -1;
        }else {
        	move2head(tmp);
        	return tmp.value;
		}
    }
    
    public void set(int key, int value) {
    	CacheNode find = mp.get(key);
    	//找到则更新可以的value值，否则插入
    	if(find!=null){
    		move2head(find);
//    		System.out.println("set is not full     "+find.value);
    		find.value = value;
    	}else {
    		//如果容量满了，则删除尾节点，先删除map，再删除链表尾
        	if(mp.size()==capacity){
//        		System.out.println("delete key"+tail.key);
        		mp.remove(tail.key);
//        		System.out.println("map size "+mp.size());

        		deletetail();        		   		
        	}
            //创建cache节点，添加到map
//        	System.out.println("value"+value);
        	CacheNode tmp = new CacheNode(key, value);
        	mp.put(key, tmp);
//        	System.out.println("set is full"+key+"  "+tmp.value);
        	insert2head(tmp);
		}            	
    }
    
    
    
    //将原有节点插入链表头
    public void move2head(CacheNode node){
    	if(head==node){
//    		System.out.println("move2head   "+node.value);
    		return;
    	}
    	if(tail==node){
    	    tail = node.prev;
    	    tail.next = null;
    	}else {
    	    node.prev.next = node.next;
    	    node.next.prev = node.prev;
    	}
    	head.prev = node;
    	node.next = head;
    	node.prev = null;
    	head = node;
//    	System.out.println(head.value);
    	
    }
    
    //将新节点插入链表头
    public void insert2head(CacheNode node){
    	if(head==null&&tail==null){
    		head = node;
    		tail = node;
    		return;
    	}
    	head.prev = node;
    	node.next = head;
    	head = node;
    }
    
    
    
    //删除链表尾节点
    public void deletetail(){
    	if(head == tail){
    	    head = null;
    	    tail = null;
//    	    System.out.println("delete head and tail");
    	    return;
    	}
    	tail = tail.prev;
    	tail.next = null;
    }
}

class CacheNode {
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