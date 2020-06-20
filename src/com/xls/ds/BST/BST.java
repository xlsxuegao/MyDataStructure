package com.xls.ds.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author xuls
 * 二分搜索树 Binary Search Tree
 */
//二分搜索树并不是所有类型都能存储，只能存储可以比较的类型 即实现了Comparable接口的类型
public class BST<E extends Comparable<E>> {

	private class Node {
		public E e;
		public Node left, right;

		public Node(E e) {
			this.e = e;
			left = null;
			right = null;
		}
	}

	//成员变量
	//根节点
	private Node root;
	private int size;

	//构造函数
	public BST() {
		root = null;
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	//此搜索二叉树 不包含等于的情况
	//与节点比较 小于的放置在左边，大于的放置在右边
	//若想包含等于的情况 ，就规定小于等于放置左边 或 大于等于放置右边
	//向搜索二叉树中添加元素
	public void add(E e) {
	/*	if (root==null){
			root=new Node(e);
			size++;
		}else {
			add(root,e);
		}*/
		root = add(root, e);
	}

	//采用递归算法，向搜索二叉树添加元素
	//改进 ：返回插入元素的根节点
	private Node add(Node node, E e) {
		//compareTo与参数比较等于返回0 大于返回1 小于返回-1；
		//第一种方法 理解方便 代码量大
		/*if (e.compareTo(node.e)==0){
			return;
		}else if (e.compareTo(node.e)<0 && node.left==null){
			node.left = new Node(e);
			size++;
			return;//记得return回去
		}else if (e.compareTo(node.e)>0 && node.right==null){
			node.right =new Node(e);
			size++;
			return;
		}
		if (e.compareTo(node.e)<0){
			add(node.left,e);
		}else if (e.compareTo(node.e)>0){
			add(node.right,e);
		}*/

		//第二种实现方式
		if (node == null) {
			size++;
			return new Node(e);
		}

		if (e.compareTo(node.e) < 0) {
			node.left = add(node.left, e);
		} else if (e.compareTo(node.e) > 0) {
			node.right = add(node.right, e);
		}

		return node;
	}

	//二分搜索树中是否包含元素E e
	public boolean contains(E e) {
		return contains(root, e);
	}

	private boolean contains(Node node, E e) {
		//递归终止条件
		if (node == null) {
			return false;
		}
		if (e.compareTo(node.e) == 0) {
			return true;
		} else if (e.compareTo(node.e) < 0) {
			return contains(node.left, e);
		} else {
			return contains(node.right, e);
		}
	}

	//遍历操作就是把所有节点都访问一遍
	//在线性结构下，遍历是极其容易的
	//前序遍历：先访问root节点，在访问左子树，然后是右子树
	public void preOrder() {
		preOrder(root);
	}

	private void preOrder(Node node) {
		//递归终止条件
		if (node == null) {
			return;
		}
		System.out.println(node.e);
		preOrder(node.left);
		preOrder(node.right);
	}

	//前序遍历非递归写法,使用栈数据结构实现
	public void preOrderNR() {
		if (this.isEmpty()) {
			System.out.println("树为空");
			return;
		}
		Stack<Node> nodes = new Stack<Node>();
		nodes.push(root);
		while (!nodes.isEmpty()) {
			Node node = nodes.pop();
			System.out.println(node.e);
			if (node.right != null) {
				nodes.push(node.right);
			}
			if (node.left != null) {
				nodes.push(node.left);
			}
		}
	}

	//二分搜索树的中序遍历：先遍历左子树-->root节点-->遍历右子树
	//中序遍历结果是从小到大的排列顺序结果
	public void inOrder() {
		inOrder(root);
	}

	private void inOrder(Node node) {
		//先写遍历终止条件
		if (node == null) {
			return;
		}

		inOrder(node.left);
		System.out.println(node.e);
		inOrder(node.right);
	}

	//二分搜索树后序遍历：先遍历左子树-->遍历右子树-->root根节点
	//典型应用：释放内存
	public void postOrder() {
		postOrder(root);
	}

	private void postOrder(Node node) {
		//先写递归终止条件
		if (node == null) {
			return;
		}
		postOrder(node.left);
		postOrder(node.right);
		System.out.println(node.e);
	}

	//层序遍历，也就是广度优先遍历
	//最短路径？
	public void levelOrder() {
		Queue<Node> nodes = new LinkedList<Node>();
		nodes.add(root);
		while (!nodes.isEmpty()) {
			Node cur = nodes.remove();
			System.out.println(cur.e);
			if (cur.left != null) {
				nodes.add(cur.left);
			}
			if (cur.right != null) {
				nodes.add(cur.right);
			}
		}

	}

	//寻找二分搜索树中的最小元素
	public E minimum() {
		if (size == 0) {
			throw new IllegalArgumentException("BST is empty");
		}
		return minimum(root).e;
	}

	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

	//删除二分搜索树中最小的元素
	public E removeMin() {
		E e = minimum();
		root = removeMin(root);
		return e;
	}

	//删除掉以node为根的二分搜索树中的最小节点
	//返回删除节点后新的二分搜索树的根
	//有点难  好好理解一下
	private Node removeMin(Node node) {
		if (node.left == null) {
			Node rightNode = node.right;
			node.right = null;
			size--;
			return rightNode;
		}
		node.left = removeMin(node.left);
		return node;
	}

	//寻找二分搜索树中最大的元素
	public E maximum() {
		if (size == 0)
			throw new IllegalArgumentException("BST is empty");
		return maximum(root).e;
	}

	private Node maximum(Node node) {
		if (node.right == null) {
			return node;
		}
		return maximum(node.right);
	}

	//移除二分搜索树中最大的元素
	public E removeMax() {
		E ret = maximum();
		root = removeMax(root);
		return ret;
	}

	//删除掉以node为根的二分搜索树的最大节点
	//返回删除节点后新的二分搜索树的根
	private Node removeMax(Node node) {
		if (node.right == null) {
			Node leftNode = node.left;
			node.left = null;
			size--;
			return leftNode;
		}
		node.right = removeMax(node.right);
		return node;
	}

	public void remove(E e) {
		root = remove(root, e);
	}

	//删除已node为根的二分搜索树中值为e节点，递归算法
	//返回删除节点后新的二分搜索树中的根
	private Node remove(Node node, E e) {
		if (node == null) {
			return null;
		}

		if (e.compareTo(node.e) < 0) {
			node.left = remove(node.left, e);
			return node;
		} else if (e.compareTo(node.e) > 0) {
			node.right = remove(node.right, e);
			return node;
		} else {
			//待删除节点左子树为空
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				return rightNode;
			}
			//待删除节点右子树为空
			if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				return leftNode;
			}
			//待删除节点左右子树都不为空
			//找到比待删除节点打的最小节点，即待删除节点右子树的最小节点
			//用这个节点顶替待删除节点的位置
			Node successor = minimum(node.right);
			successor.right = removeMin(node.right);
			successor.left = node.left;
			node.left = node.right = null;
			return successor;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		generateBSTString(root, 0, builder);
		return builder.toString();
	}

	private void generateBSTString(Node node, int depth, StringBuilder builder) {
		if (node == null) {
			builder.append(generateDepthString(depth) + "null\n");
			return;
		}
		builder.append(generateDepthString(depth) + node.e + "\n");
		generateBSTString(node.left, depth + 1, builder);
		generateBSTString(node.right, depth + 1, builder);
	}

	private String generateDepthString(int depth) {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			res.append("--");
		}
		return res.toString();
	}
}
