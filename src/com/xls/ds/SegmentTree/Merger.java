package com.xls.ds.SegmentTree;

/**
 * @author xuls
 */
public interface Merger<E> {
	E merge(E a, E b);
}
