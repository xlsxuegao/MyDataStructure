package com.xls.ds.Heap;

import com.xls.ds.Main;

import java.util.Random;

/**
 * @author xuls
 */
public class TestHeap {
	public static void main(String[] args) {
		int n = 1000000;
		MyMaxHeap<Integer> myMaxHeap = new MyMaxHeap<Integer>();
		Random random = new Random();
//		int[] test = {1,2,3,4,5,6,7,8,9};
//		for (int i=0;i<test.length;i++){
//			myMaxHeap.add(test[i]);
//		}
		for (int i = 0; i < n; i++) {
			myMaxHeap.add(random.nextInt(Integer.MAX_VALUE));
		}
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = myMaxHeap.extractMax();
		}
		for (int i = 1; i < n; i++) {
			if (arr[i - 1] < arr[i]) {
				throw new IllegalArgumentException("Error");
			}
		}
		System.out.println("test MaxHeap completed");
	}
}
