package com.xls.ds.UnionFind;

/**
 * @author xuls
 * 基于size的优化，size 树的节点数
 */
public class UnionFind3 implements UF {
	private int[] parent;
	private int[] sz;

	public UnionFind3(int size) {
		parent = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			sz[i] = 1;
		}
	}

	@Override
	public int getSize() {
		return parent.length;
	}

	@Override
	public boolean isConnected(int p, int q) {
		return find(p) == find(q);
	}

	@Override
	public void unionElements(int p, int q) {
		int pRoot = find(p);
		int qRoot = find(q);
		if (pRoot == qRoot) {
			return;
		}
		if (sz[pRoot] < sz[qRoot]) {
			parent[pRoot] = qRoot;
			sz[qRoot] += sz[qRoot];
		} else {
			parent[qRoot] = pRoot;
			sz[pRoot] += sz[qRoot];
		}
	}

	private int find(int p) {
		if (p < 0 || p >= parent.length)
			throw new IllegalArgumentException("p is out of bounds");
		while (p != parent[p]) {
			p = parent[p];
		}
		return p;
	}
}
