package com.xls.ds.AVLTree;


import com.xls.ds.MyMap.IMap;

import java.util.ArrayList;

/**
 * @author xuls
 */
public class AVLTree<K extends Comparable<K>, V> implements IMap<K, V> {
	private class Node {
		public Node left, right;
		public K key;
		public V value;
		public int height;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			height = 1;
		}
	}

	private Node root;
	private int size;

	public AVLTree() {
		root = null;
		size = 0;
	}

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
	}

	private int getHeight(Node node) {
		if (node == null) {
			return 0;
		}
		return node.height;
	}

	private int getBalanceFactor(Node node) {
		if (node == null) {
			return 0;
		}
		return getHeight(node.left) - getHeight(node.right);
	}

	private Node add(Node node, K key, V value) {
		if (node == null) {
			size++;
			return new Node(key, value);
		}
		if (key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, value);
		} else {
			node.value = value;
		}
		//添加新节点后 更新height
		node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

		//计算平衡因子
		int balanceFactor = getBalanceFactor(node);
		if (Math.abs(balanceFactor) > 1) {
			System.out.println("unBalance:" + balanceFactor);
		}
		//更新平衡
		//此时 node节点的平衡因子大于1 并且新加入的节点在node.left的左侧
		//LL
		if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
			//右旋转
			return rightRotate(node);
		}
		//RR
		if (balanceFactor < -1 && getBalanceFactor(node.right) >= 0) {
			//左旋转
			return leftRotate(node);
		}
		//LR
		if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}
		//RL
		if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}
		return node;
	}

	//对节点y进行向右旋转，返回旋转后的新节点x
	//      y                           x
	//     / \    向右旋转(y)          /   \
	//    x  T4 --------------》     z      y
	//   / \                        / \    / \
	//  z   T3                    T1 T2   T3 T4
	// / \
	//T1 T2
	private Node rightRotate(Node y) {
		Node x = y.left;
		Node T3 = x.right;
		//右旋转操作
		x.right = y;
		y.right = T3;
		//更新height值  先更新yheight值 在更新x的height
		y.height = Math.max(getHeight(y.left), getHeight(y.right));
		x.height = Math.max(getHeight(x.left), getHeight(x.right));
		return x;
	}

	private Node leftRotate(Node y) {
		Node x = y.right;
		Node T3 = x.left;
		//左旋转操作
		x.left = y;
		y.right = T3;
		//更新height
		y.height = Math.max(getHeight(y.left), getHeight(y.right));
		x.height = Math.max(getHeight(x.left), getHeight(x.right));
		return x;
	}

	@Override
	public V remove(K key) {
		Node node = getNode(root, key);
		if (node != null) {
			root = remove(root, key);
			return node.value;
		}

		return null;
	}

	private Node minimum(Node node) {
		if (node.left == null) {
			return node;
		}
		return minimum(node.left);
	}

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

	//删除已node为根的二分搜索树中值为e节点，递归算法
	//返回删除节点后新的二分搜索树中的根
	private Node remove(Node node, K key) {
		if (node == null) {
			return null;
		}
		Node retNode;
		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			retNode = node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			retNode = node;
		} else {
			//待删除节点左子树为空
			if (node.left == null) {
				Node rightNode = node.right;
				node.right = null;
				size--;
				retNode = rightNode;
			}
			//待删除节点右子树为空
			else if (node.right == null) {
				Node leftNode = node.left;
				node.left = null;
				size--;
				retNode = leftNode;
			}
			//待删除节点左右子树都不为空
			//找到比待删除节点打的最小节点，即待删除节点右子树的最小节点
			//用这个节点顶替待删除节点的位置
			else {
				Node successor = minimum(node.right);
				successor.right = remove(node.right, successor.key);
				successor.left = node.left;
				node.left = node.right = null;
				retNode = successor;
			}

		}
		if (retNode == null) {
			return null;
		}

		retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
		int balanceFactor = getBalanceFactor(retNode);
		//LL
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
			//右旋转
			return rightRotate(retNode);
		}
		//RR
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) >= 0) {
			//左旋转
			return leftRotate(retNode);
		}
		//LR
		if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
			node.left = leftRotate(retNode.left);
			return rightRotate(retNode);
		}
		//RL
		if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
			node.right = rightRotate(retNode.right);
			return leftRotate(retNode);
		}
		return retNode;
	}

	@Override
	public boolean contains(K key) {
		return getNode(root, key) != null;
	}

	//返回以node为根节点的二分搜索树中，key所在的结点
	private Node getNode(Node node, K key) {
		if (node == null) {
			return null;
		}

		if (key.compareTo(node.key) < 0) {
			return getNode(node.left, key);
		} else if (key.compareTo(node.key) > 0) {
			return getNode(node.right, key);
		} else {
			return node;
		}
	}

	@Override
	public V get(K key) {
		Node node = getNode(root, key);
		return node == null ? null : node.value;
	}

	@Override
	public void set(K key, V newValue) {
		Node node = getNode(root, key);
		if (node == null)
			throw new IllegalArgumentException(key + "doesn't exist!");
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	//判断是否是一个二分搜索树
	public boolean isBST() {
		ArrayList<K> keys = new ArrayList<>();
		inOrder(root, keys);
		for (int i = 1; i < keys.size(); i++) {
			if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
				return false;
			}
		}
		return true;
	}

	//中序遍历
	private void inOrder(Node node, ArrayList<K> keys) {
		if (node == null) {
			return;
		}
		inOrder(node.left, keys);
		keys.add(node.key);
		inOrder(node.right, keys);
	}

	//判断是否是平衡二叉树
	public boolean isBalanced() {
		return isBalanced(root);
	}

	private boolean isBalanced(Node node) {
		if (node == null) {
			return true;
		}
		int balanceFactor = getBalanceFactor(node);
		if (Math.abs(balanceFactor) > 1) {
			return false;
		}
		return isBalanced(node.left) && isBalanced(node.right);
	}
}