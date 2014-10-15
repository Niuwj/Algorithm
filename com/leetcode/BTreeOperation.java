package com.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class BTreeOperation {
	
	
	
	
	/*
	 * Sum Root to Leaf Numbers
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

For example,

    1
   / \
  2   3
The root-to-leaf path 1->2 represents the number 12.
The root-to-leaf path 1->3 represents the number 13.

Return the sum = 12 + 13 = 25.


	 */
	
	public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
	
	public int dfs(TreeNode root, int sum){
		if(root==null){
			return 0;
		}
		if(root.left==null && root.right==null){
			return sum*10 + root.val;
		}
		return dfs(root.left, sum*10+root.val)+dfs(root.right, sum*10+root.val);		
	}
	
	
	
	
	/*
	 * Populating Next Right Pointers in Each Node 
	 * Given a binary tree

    struct TreeLinkNode {
      TreeLinkNode *left;
      TreeLinkNode *right;
      TreeLinkNode *next;
    }
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Note:

You may only use constant extra space.
You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
For example,
Given the following perfect binary tree,
         1
       /  \
      2    3
     / \  / \
    4  5  6  7
After calling your function, the tree should look like:
         1 -> NULL
       /  \
      2 -> 3 -> NULL
     / \  / \
    4->5->6->7 -> NULL
	 */
	public void connect(TreeLinkNode root) {
        
    }
	
	/*
	 * 搜狐笔试题：二叉树叶子节点到叶子节点的最长路径大小：
	 * 1.求左子树深度dl，右子树深度dr
	 * 2.dl+dr
	 * 3.如果根节点的右子树为空则转为求root.left的longestWay，
	 */
	//遍历二叉树，计算每个节点的longestNodeWay与max相比
	public int longestWay(TreeNode root)
	{
		int max = longestNodeWay(root);
		TreeNode tmp = root;
		while(tmp!=null){
			int lway = longestNodeWay(root.left);
//			System.out.println(lway);
			int rway = longestNodeWay(root.right);
//			System.out.println(rway);
			if(max>lway && max>rway){
				return max;
			}
			if(lway>rway){
				max = lway;
				tmp = tmp.left;
				continue;
			}else if (lway<rway) {
				max = rway;
				tmp = tmp.right;
				continue;
			}else {				
				return lway;
			}
		}		
		return max;
	}
	
	public int longestNodeWay(TreeNode root){
		if(root==null){
			return -1;
		}
		if(root.left==null){
			return longestWay(root.right);
		}
		if(root.right == null){
			return longestWay(root.left);
		}
		int dl = dfs(root.left);
		int dr = dfs(root.right);
		return dl+dr;
	}
	
	int dfs(TreeNode root){
		if(root==null){
			return 0;
		}
		int l = dfs(root.left);
		int r = dfs(root.right);
		
		return Math.max(l, r)+1;		
	}
	
	
	
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
	int max_sum = Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		dfsMPS(root);
		return max_sum;
    }
	
	public int dfsMPS(TreeNode root){
		if(root==null){
			return 0;
		}
		int sum = root.val;
		int l = dfsMPS(root.left);
		int r = dfsMPS(root.right);
		if(l>0){
			sum += l;
		}
		if(r>0){
			sum += r;
		}
		max_sum = Math.max(max_sum, sum);
		return Math.max(l, r)>0 ? Math.max(l, r)+root.val : root.val;
	}
	
	
	/*
	 * Flatten Binary Tree to Linked List
	 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
   
Hints:
If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.	
 */
	public void flatten(TreeNode root) {        
		if(root==null){
			return;
		}
		flatten(root.left);
		flatten(root.right);
		
		if(root.left==null){
			return;
		}		
		
		//寻找左子树最右节点，将左子树插入root和root.right之间
		TreeNode p = root.left;
		while(p.right!=null){
			p= p.right;
		}
		p.right = root.right;		
		root.right = root.left;
		root.left = null;		
    }
	
	
	/*
	 * Construct Binary Tree from Inorder and Postorder Traversal 
	 * Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

分析：
1. Find the last node in the postorder vector, which is the root of the current tree.
2. Find the position of root node in the inorder vector, which divide the inorder vector into 2 sub tree inorder vectors. Left part is the left sub-tree, right part is the right sub-tree.
3. Do 1 and 2 for the right and left sub-tree, respectively.

	 */
	public TreeNode buildTreeInPost(int[] inorder, int[] postorder) {
        int lin = inorder.length;
        int lpo = postorder.length;
        if(lpo==0){
        	return null;
        }else {
			return partInPost(inorder, postorder, 0, lin-1, lpo-1);
		}
    }
	
	public TreeNode partInPost(int[] inorder, int[] postorder, int istart, int iend, int pend){
		if(istart>iend){
			return null;
		}
		TreeNode root = new TreeNode(postorder[pend]);
		int mid = 0;
		for(int i=istart;i<=iend;i++){
			if(inorder[i]==root.val){
				mid = i;
				break;
			}
		}
		root.right = partInPost(inorder, postorder, mid+1, iend, pend-1);
		root.left = partInPost(inorder, postorder, istart, mid-1, pend-1-iend+mid);
		return root;
	}
	
	/*
	 * Construct Binary Tree from Preorder and Inorder Traversal 
	 * Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.
	 */
	public TreeNode buildTreePreIn(int[] preorder, int[] inorder) {
		int lin = inorder.length;
        int lpre = preorder.length;
        if(lpre==0){
        	return null;
        }else {
			return partPreIn(preorder, inorder, 0, 0, lin-1);
		}
    }
	
	public TreeNode partPreIn(int[] preorder, int[] inorder, int pstart, int istart, int iend){
		if(istart>iend){
			return null;
		}
		int mid = 0;
		TreeNode root = new TreeNode(preorder[pstart]);
		for(int i=istart;i<=iend;i++){
			if(inorder[i]==root.val){
				mid = i;
				break;
			}
		}
		root.left = partPreIn(preorder, inorder, pstart+1, istart, mid-1);
		root.right = partPreIn(preorder, inorder, pstart+mid-istart+1, mid+1, iend);
		return root;		
	}
	
		
	/*
	 * Given a binary tree, return the postorder traversal of its nodes' values.
	 * Note: Recursive solution is trivial, could you do it iteratively?
	 */	
	
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
	
	/*
	 * Given a binary tree, return the preorder traversal of its nodes' values
	 */
	//先序遍历二叉树，栈,iteratively
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
	
	
	/*
	 * Given a binary tree, return the inorder traversal of its nodes' values.
	 */
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
