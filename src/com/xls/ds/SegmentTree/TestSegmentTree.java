package com.xls.ds.SegmentTree;

/**
 * @author xuls
 */
public class TestSegmentTree {
	public static void main(String[] args) {
		Integer[] nums = {0, 5, -1, 9, -3, 5, 4};
		SegmentTree<Integer> segmentTree = new SegmentTree<Integer>(nums, (a, b) -> a + b);
		System.out.println(segmentTree);
		System.out.println(segmentTree.query(1, 3));
	}

}
