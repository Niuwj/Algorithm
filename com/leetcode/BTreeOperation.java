package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
}
