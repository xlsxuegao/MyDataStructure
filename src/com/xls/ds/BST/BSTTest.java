package com.xls.ds.BST;

/**
 * @author xuls
 */
public class BSTTest {
	public static void main(String[] args) {
		BST<Integer> bst = new BST<Integer>();
		int[] nums = {5, 6, 7, 8, 9, 11, 2, 3};
		for (int num : nums) {
			bst.add(num);
		}
		/*bst.preOrder();
		System.out.println();
		System.out.println(bst);
		bst.inOrder();
		System.out.println();
		bst.postOrder();*/
		System.out.println("前序遍历递归结果");
		bst.preOrder();
		System.out.println("前序遍历非递归结果");
		bst.preOrderNR();
		System.out.println("层序遍历结果");
		bst.levelOrder();
		System.out.println("寻找二分搜索树中最小的元素");
		System.out.println(bst.minimum());
		System.out.println("寻找二分搜索树中最大的元素");
		System.out.println(bst.maximum());
	}
}
