package com.xls.ds.LinkedList;

import com.xls.ds.LinkedList.MyLinkedList;

/**
 * @author xuls
 */
public class TestLinkedList {
	public static void main(String[] args) {
		MyLinkedList<Integer> integerMyLinkedList = new MyLinkedList<Integer>();
		for (int i = 0; i < 5; i++) {
			integerMyLinkedList.addFirst(i);
			System.out.println(integerMyLinkedList);
		}
		integerMyLinkedList.add(2, 66);
		System.out.println(integerMyLinkedList);

		integerMyLinkedList.remove(2);
		System.out.println(integerMyLinkedList);
		integerMyLinkedList.removeFirst();
		integerMyLinkedList.removeLast();
		System.out.println(integerMyLinkedList);
	}
}
