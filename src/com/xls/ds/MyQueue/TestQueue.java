package com.xls.ds.MyQueue;

/**
 * @author xuls
 */
public class TestQueue {
	public static void main(String[] args) {
		MyLoopQueue<Integer> loopQueue = new MyLoopQueue<Integer>();
		for (int i = 0; i < 20; i++) {
			loopQueue.enqueue(i);
		}
		System.out.println(loopQueue);
		System.out.println(loopQueue.dequeue());
		System.out.println(loopQueue);
	}
}
