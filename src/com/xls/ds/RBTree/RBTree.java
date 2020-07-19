package com.xls.ds.RBTree;

import com.xls.ds.MyMap.IMap;

/**
 * @author xuls
 */
public class RBTree<K extends Comparable<K>, V> implements IMap<K, V> {
	private static final boolean RED = true;
	private static final boolean BLACK = false;

	private class Node {
		public Node left, right;
		public K key;
		public V value;
		public boolean color;

		public Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
			color = RED;
		}
	}

	private Node root;
	private int size;

	public RBTree() {
		root = null;
		size = 0;
	}

	//         node                        x
	//        /   \      左旋转           /  \
	//       T1   x     ---------》     node T3
	//           / \                   / \
	//          T2  T3               T1  T2
	private Node leftRotate(Node node) {
		Node x = node.right;
		//左旋转
		node.right = x.left;
		x.left = node;
		//改变颜色
		x.color = node.color;
		node.color = RED;
		return x;
	}

	//颜色翻转
	private void flipColors(Node node) {
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
	}

	//右旋转
	private Node rightRotate(Node node) {
		Node x = node.right;
		//右旋转
		node.left = x.right;
		x.right = node;
		//改变颜色
		x.color = node.color;
		node.color = RED;
		return x;
	}

	@Override
	public void add(K key, V value) {
		root = add(root, key, value);
		root.color = BLACK;//最终根节点为黑色
	}

	private boolean isRed(Node node) {
		if (node == null) {
			return BLACK;
		}
		if (node.color == RED) {
			return RED;
		} else {
			return BLACK;
		}
	}

	private Node add(Node node, K key, V value) {
		if (node == null) {
			size++;
			return new Node(key, value);//默认插入红色节点
		}
		if (key.compareTo(node.key) < 0) {
			node.left = add(node.left, key, value);
		} else if (key.compareTo(node.key) > 0) {
			node.right = add(node.right, key, value);
		} else {
			node.value = value;
		}
		if (isRed(node.right) && !isRed(node.left)) {
			node = leftRotate(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rightRotate(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColors(node);
		}
		return node;
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

		if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			return node;
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
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
}
