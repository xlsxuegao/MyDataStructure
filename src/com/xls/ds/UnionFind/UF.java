package com.xls.ds.UnionFind;

/**
 * @author xuls
 * 并查集
 */
public interface UF {
	int getSize();

	boolean isConnected(int p, int q);

	void unionElements(int p, int q);
}
