package com.xls.ds.MyArray;

import javax.sound.midi.Soundbank;

/**
 * @author auas
 */
public class TestOfArray {
	public static void main(String[] args) {
		MyArray<Integer> myArray = new MyArray();
		//测试addLast
		for (int i = 0; i < 20; i++) {
			myArray.addLast(i);
		}
		System.out.println(myArray);

		//测试插入元素
		myArray.add(3, 15);
		System.out.println(myArray);

		//测试插入第一个元素
		myArray.addFirst(66);
		System.out.println(myArray);

		//测试超过数组大小
//		myArray.add(15,1);
		//测试删除数据
		myArray.remove(6);
		System.out.println(myArray);
	}
}
