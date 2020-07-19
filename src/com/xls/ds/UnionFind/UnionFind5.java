package com.xls.ds.UnionFind;

/**
 * @author xuls
 * 基于rank的优化，rank 树的层数,再加上路径压缩进行优化
 */
public class UnionFind5 implements UF {
	private int[] parent;
	private int[] rand;

	public UnionFind5(int size) {
		parent = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rand[i] = 1;
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
		if (rand[pRoot] < rand[qRoot]) {
			parent[pRoot] = qRoot;
		} else if (rand[qRoot] < rand[pRoot]) {
			parent[qRoot] = pRoot;
		} else {
			parent[qRoot] = pRoot;
			rand[pRoot] += 1;
		}
	}

	private int find(int p) {
		if (p < 0 || p >= parent.length)
			throw new IllegalArgumentException("p is out of bounds");
		while (p != parent[p]) {
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;
	}
}
