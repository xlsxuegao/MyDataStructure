package com.xls.ds.SegmentTree;

/**
 * @author xuls
 * 线段树 ，也叫区间树
 */
public class SegmentTree<E> {
	private Merger<E> merger;
	private E[] tree;
	private E[] data;

	public SegmentTree(E[] arr, Merger<E> merger) {
		this.merger = merger;
		data = (E[]) new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			data[i] = arr[i];
		}
		tree = (E[]) new Object[arr.length * 4];
		buildSegmentTree(0, 0, arr.length - 1);
	}

	//在treeIndex的位置创建表示区间[left...right]的线段树
	private void buildSegmentTree(int treeIndex, int left, int right) {

		//递归结束条件
		while (left == right) {
			tree[treeIndex] = data[left];
			return;
		}

		int treeLeftIndex = getLeftChild(treeIndex);
		int treeRightTree = getRightChild(treeIndex);
		int mid = left + (right - left) / 2;

		buildSegmentTree(treeLeftIndex, left, mid);
		buildSegmentTree(treeRightTree, mid + 1, right);

		tree[treeIndex] = merger.merge(tree[treeLeftIndex], tree[treeRightTree]);
	}

	public int getSize() {
		return data.length;
	}

	public E get(int index) {
		if (index < 0 || index >= data.length) {
			throw new IllegalArgumentException("index is Illegal");
		}
		return data[index];
	}

	private int getLeftChild(int index) {
		return 2 * index + 1;
	}

	private int getRightChild(int index) {
		return 2 * index + 2;
	}

	public E query(int queryL, int queryR) {
		if (queryL < 0 || queryL >= data.length ||
				queryR < 0 || queryR >= data.length || queryL > queryR) {
			throw new IllegalArgumentException("queryL and queryR is illegal");
		}
		return query(0, 0, data.length - 1, queryL, queryR);
	}

	//查询以treeIndex为节点的[l,r]中范围了[queryL,queryR]的值
	private E query(int treeIndex, int l, int r, int queryL, int queryR) {
		if (l == queryL && r == queryR)
			return tree[treeIndex];

		int mid = l + (r - l) / 2;
		int treeIndexLeft = getLeftChild(treeIndex);
		int treeIndexRight = getRightChild(treeIndex);

		if (queryL >= mid + 1) {
			return query(treeIndexRight, mid + 1, r, queryL, queryR);
		} else if (queryR <= mid) {
			return query(treeIndexLeft, 0, mid, queryL, queryR);
		}
		E treeLeftRes = query(treeIndexLeft, l, mid, queryL, mid);
		E treeRightRes = query(treeIndexRight, mid + 1, r, mid + 1, queryR);
		return merger.merge(treeLeftRes, treeRightRes);

	}

	public void set(int index, E e) {
		if (index < 0 || index >= data.length) {
			throw new IllegalArgumentException("index is illegal");
		}
		set(0, 0, data.length - 1, index, e);
	}

	//以treeIndex为节点更新index的值为e
	private void set(int treeIndex, int l, int r, int index, E e) {
		if (l == r) {
			tree[treeIndex] = e;
			return;
		}

		int mid = l + (r - l) / 2;
		int treeLeftChild = getLeftChild(treeIndex);
		int treeRightChild = getRightChild(treeIndex);

		if (index >= mid) {
			set(treeRightChild, mid + 1, r, index, e);
		} else {
			set(treeLeftChild, l, mid, index, e);
		}
		//叶子节点发生改变之后，父节点也要更新
		tree[treeIndex] = merger.merge(tree[treeLeftChild], tree[treeRightChild]);
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		res.append("[");
		for (int i = 0; i < tree.length; i++) {
			if (tree[i] != null) {
				res.append(tree[i]);
			} else {
				res.append("null");
			}
			if (i == tree.length - 1) {
				res.append("]");
			} else {
				res.append("，");
			}
		}
		return res.toString();
	}
}
