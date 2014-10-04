package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BTreeOperation {

	/*
	 * Binary Tree Maximum Path Sum 
	 * Given a binary tree, find the maximum path sum.

The path may start and end at any node in the tree.

For example:
Given the below binary tree,

       1
      / \
     2   3
Return 6.
	 */
	public int maxPathSum(TreeNode root) {
        
    }
	
	
	
	
	
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
	//阿里二面问到广度优先遍历
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
	
	
	/*
	 *same tree
	 *Given two binary trees, write a function to check if they are equal or not.

Two binary trees are considered equal if they are structurally identical and the nodes have the same value 

递归比较
	 */
	public boolean isSameTree(TreeNode p, TreeNode q) {
        
        if(p!=null){
        	if(q!=null){
        		if(p.val==q.val){
                	if(!isSameTree(p.left, q.left)){
                		return false;
                	}
                	if(!isSameTree(p.right, q.right)){
                		return false;
                	}
                	return true;
                }else {
        			return false;
        		}
        	}else {
				return false;
			}
        }else {
			if(q==null){
				return true;
			}else {
				return false;
			}
		}
        /*简略的代码
        
        //终止条件
        if(p==null && q==null){
        	return true;
        }
        //剪枝
        if(p==null || q==null){
        	return false;
        }
        //三方合并
        return p.val==q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        */
    }
	
	/*
	 * Symmetric Tree 对称树
	 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
But the following is not:
    1
   / \
  2   2
   \   \
   3    3
Note:
Bonus points if you could solve it both recursively and iteratively.
	 */
	
	public boolean isSymmetric(TreeNode root) {
		if(root==null){
			return true;
		}
		return isSymTree(root.left, root.right);
    }
	boolean isSymTree(TreeNode p, TreeNode q){
		if(p!=null){
        	if(q!=null){
        		if(p.val==q.val){
                	if(!isSameTree(p.left, q.right)){
                		return false;
                	}
                	if(!isSameTree(p.right, q.left)){
                		return false;
                	}
                	return true;
                }else {
        			return false;
        		}
        	}else {
				return false;
			}
        }else {
			if(q==null){
				return true;
			}else {
				return false;
			}
		}
	}
	
	//迭代版本
	public boolean isSm(TreeNode p, TreeNode q){
		LinkedList<TreeNode> st = new LinkedList<TreeNode>();
		st.push(p);
		st.push(q);
		while(!st.isEmpty()){
			TreeNode tp = st.pop();
			TreeNode tq = st.pop();
			if(tp==null && tq==null){
				continue;
			}
			if(tp==null || tq==null){
				return false;
			}
			if(tp.val != tq.val){
				return false;
			}
			//修改压入顺序，可判断是same或者symmetric
			st.push(tp.left);
			st.push(tq.right);
			st.push(tp.right);
			st.push(tq.left);			
		}
		return true;
	}
	/*
	 * Balanced Binary Tree
	 * Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.
	
	 *如果是平衡二叉树，返回height，否则返回-1
	 */
	
	 public boolean isBalanced(TreeNode root) {
		 return balancedHeight(root)>=0;
		 
	 }
	 public int balancedHeight(TreeNode root){
		 if(root==null){
			 return 0;
		 }
		 int left = balancedHeight(root.left);
		 int right = balancedHeight(root.right);
		 if(left<0 || right<0 || Math.abs(left-right)>1){
			 return -1;
		 }
		 
		 return Math.max(left, right)+1;
	 }
	 
	 /*
	  * Minimum Depth of Binary Tree 
	  * Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
递归！！
	  */
	 public int minDepth(TreeNode root) {
		 if(root==null){
			 return 0;
		 }
		 if(root.left==null && root.right==null){
			 return 1;
		 }else if (root.left==null) {
			return minDepth(root.right)+1;
		 }else if (root.right==null) {
			return minDepth(root.left)+1;
		 }else {
			return Math.min(minDepth(root.left),minDepth(root.right))+1;
		 }		 
	 }
	 
	 /*
	  * Maximum Depth of Binary Tree 
	  * Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
	  */
	 public int maxDepth(TreeNode root) {
		 if(root==null){
			 return 0;
		 }
		 if(root.left==null && root.right==null){
			 return 1;
		 }else if (root.left==null) {
			return maxDepth(root.right)+1;
		 }else if (root.right==null) {
			return maxDepth(root.left)+1;
		 }else {
			return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
		 }
	 }
	 
	 /*
	  * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
	  */
	 public boolean hasPathSum(TreeNode root, int sum){
		 if(root==null){
			 return false;
		 }
		 
		 if(root.left==null && root.right==null){
			 return sum==root.val;
		 }
		 return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
	 }
	 
	 /*
	  * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]
	  */
	 
	 /*public List<List<Integer>> result = new LinkedList<List<Integer>>();
	 public LinkedList<Integer> path = new LinkedList<>();
	 public List<List<Integer>> pathSum(TreeNode root, int sum) {
		 path.clear();
		 result.clear();
		 if(root==null){
			 return result;
		 }	 
		 
		 findPath(root, sum);
		 return result;
	 }
	 
	 public boolean findPath(TreeNode root,int gap){
		 if(root==null){
			 return false;
		 }
		 if(root.left==null && root.right==null && gap==root.val){
			 path.add(root.val);
			 result.add(path);
			 return true;
		 }
		 path.add(root.val);
		 
		 if(findPath(root.left, gap-root.val)==false && findPath(root.right, gap-root.val)==false){
			 path.removeLast();
			 return false;
		 }else {
			 ///path.removeLast();
			 return true;
		 }
		 
		
	 }*/
	 
	 
	 List<List<Integer>> result = new LinkedList<List<Integer>>();
	 LinkedList<Integer> path = new LinkedList<Integer>();
	 public List<List<Integer>> pathSum(TreeNode root, int sum) {
		 path.clear();
		 result.clear();
		 if(root==null){
			 return result;
		 }	 
		 
		 findPath(root, sum);
		 return result;
	 }
	 
	 public void findPath(TreeNode root,int gap){
		 if(root.left==null && root.right==null && gap==root.val){
			 path.add(root.val);
			 //因为path是全局变量，所以要新建一个链表复制此时的path，将新建的lsit加入root的队列
			 LinkedList<Integer> tmp = new LinkedList<Integer>();
			 for(int i=0;i<path.size();i++){
				 tmp.add(path.get(i));
			 }
			 result.add(tmp);
			 return;
		 }
		 path.add(root.val);
		 if(root.left!=null){
			 findPath(root.left, gap-root.val);
			 path.removeLast();
		 }
		 if(root.right!=null){
			 findPath(root.right, gap-root.val);
			 path.removeLast();
		 }
	 }
	 
}
