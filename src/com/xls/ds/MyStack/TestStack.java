package com.xls.ds.MyStack;

/**
 * @author auas
 */
public class TestStack {
	public static void main(String[] args) {
//		MyArrayStack<Integer> stack = new MyArrayStack<Integer>();
//		for (int i = 0; i < 20; i++) {
//			stack.push(i);
//		}
//		System.out.println(stack);
//		System.out.println(stack.pop());
//		System.out.println(stack);
//		System.out.println(stack.peek());


		MyLinkedStack integerMyArrayStack = new MyLinkedStack<Integer>();
		for (int i = 5; i < 10; i++) {
			integerMyArrayStack.push(i);
			System.out.println(integerMyArrayStack);
		}
	}
}
