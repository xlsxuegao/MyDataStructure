package com.xls.ds.MyStack;

/**
 * @author auas
 */
public class TestStack {
	public static void main(String[] args) {
		MyArrayStack<Integer> stack = new MyArrayStack<Integer>();
		for (int i = 0; i < 20; i++) {
			stack.push(i);
		}
		System.out.println(stack);
		System.out.println(stack.pop());
		System.out.println(stack);
		System.out.println(stack.peek());
	}
}
