package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BTreeOperation {

	
	//Given a binary tree, return the postorder traversal of its nodes' values.
	//Note: Recursive solution is trivial, could you do it iteratively?
	
	//后序遍历，迭代,Recursive solution
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<Integer>();
        postorderTraversal(root, result);
        return result;
    }
	public void postorderTraversal(TreeNode root,List<Integer> result) {
        if(root!=null){
        	postorderTraversal(root.left, result);
        	postorderTraversal(root.right,result);
        	result.add(root.val);        	
        }
    }
	//先序遍历二叉树，栈,iteratively
	//Given a binary tree, return the preorder traversal of its nodes' values
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if(root==null){
        	return result;
        }
		Stack<TreeNode> st = new Stack<TreeNode>();
		
		st.push(root);
		while(!st.empty()){
			TreeNode tmp = st.pop();
			result.add(tmp.val);
			if(tmp.right!=null){
				st.push(tmp.right);
			}
			if(tmp.left!=null){
				st.push(tmp.left);
			}			
		}
		return result;		
    }
	
	
	//Given a binary tree, return the inorder traversal of its nodes' values.
	//中序遍历,非递归
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new LinkedList<Integer>();
		if(root==null){
        	return result;
        }
		Stack<TreeNode> st = new Stack<TreeNode>();
		TreeNode head = root;
		while(head!=null||!st.empty()){
			while(head!=null){
				st.push(head);
				head = head.left;
			}
			
			head = st.pop();
			result.add(head.val);
			head = head.right;
		}
		return result;	
    }
	
	//Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
	//层次遍历
	public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root==null){
			return result;
		}
		//一个队列寸当前层，一个队列寸下一层
		Queue<TreeNode> q1 = new LinkedList<TreeNode>();
		Queue<TreeNode> q2 = new LinkedList<TreeNode>();
		
		q1.offer(root);
		while(true){
			//如果q1为空，即表示树读取完毕
			if(q1.isEmpty()){
				return result;
			}
			List<Integer> le = new LinkedList<Integer>();//save level
			//扫当前层，将节点加入q1，子节点加入q2，值加入le链表
			while(!q1.isEmpty()){
				TreeNode tmp = q1.poll();
				if(tmp.left!=null){
					q2.offer(tmp.left);
				}
				if(tmp.right!=null){
					q2.offer(tmp.right);
				}
				le.add(tmp.val);			
			}
			//将当前层结果存入result
			result.add(le);
			//交换q1,，q2
			Queue<TreeNode> t = q1;
			q1 = q2;
			q2 = t;
		}
    }
	
	//Given a binary tree, return the bottom-up level order traversal of its nodes' values.
	//自底向上层次遍历
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
        //主要实现list逆置
		List<List<Integer>> result = levelOrder(root);
		if(result==null){
			return result;
		}
		int len = result.size();
		
		//结果为null或size=1，不用逆置
		if( len==1){
			return result;
		}
		
		//逆置链表
		for(int i=0,j=len-1;i<j;i++,j--){
			List<Integer> tmp = result.get(i);
			result.set(i, result.get(j));
			result.set(j, tmp);
		}
		return result;
    }
	
	/*
	 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
  [15,7]
]

OJ's Binary Tree Serialization:
The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

Here's an example:
   1
  / \
 2   3
    /
   4
    \
     5
The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
 
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		if(root==null){
			return result;
		}
		//一个队列寸当前层，一个队列存下一层
		LinkedList<TreeNode> q1 = new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2 = new LinkedList<TreeNode>();
		boolean l2r = false;//读取顺序，true为从左向右
		q1.offer(root);
		while(true){
			//如果q1为空，即表示树读取完毕
			if(q1.isEmpty()){
				return result;
			}
			List<Integer> le = new LinkedList<Integer>();//save level
			//扫当前层，将节点加入q1，子节点加入q2，值加入le链表
			while(!q1.isEmpty()){
				TreeNode tmp = q1.poll();
				if(l2r){					
					if(tmp.right!=null){
						q2.addFirst(tmp.right);
					}
					if(tmp.left!=null){
						q2.addFirst(tmp.left);
					}				
					
				}else {					
					if(tmp.left!=null){
						q2.addFirst(tmp.left);
					}
					if(tmp.right!=null){
						q2.addFirst(tmp.right);
					}
				}				
				le.add(tmp.val);
			}
			//将当前层结果存入result
			result.add(le);			
			//交换q1,，q2
			LinkedList<TreeNode> t = q1;
			q1 = q2;
			q2 = t;
			l2r = !l2r;
		}
	}
	
	/*
	 * Recover Binary Search Tree
	 * Recover Binary Search Tree

Two elements of a binary search tree (BST) are swapped by mistake.
Recover the tree without changing its structure.
Note:
A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

中序遍历找到两个指针及其前驱
	 */
	
	TreeNode first;
	TreeNode second;
	TreeNode pre;
	void inorder(TreeNode root){
		if(root==null){
			return;
		}
		inorder(root.left);
		if(pre == null){
			pre = root;
		}else {
			if(pre.val>root.val){
				if(first == null){
					first = pre;
				}
				second = root;
			}
			pre = root;
		}
		inorder(root.right);
		
	}
	public void recoverTree(TreeNode root) {
	     first = null;
	     second = null;
	     pre = null;
	     inorder(root);
	     int tmp = first.val;
	     first.val = second.val;
	     second.val = tmp;
	}
	
}
