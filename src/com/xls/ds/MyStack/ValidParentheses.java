package com.xls.ds.MyStack;

import java.util.Stack;

/**
 * @author auas
 * leeCode 20题 有效的括号
 */
public class ValidParentheses {
	public static void main(String[] args) {
		String s1 = "{[()]}";
		String s2 = "[{]}";
		System.out.println(isValid(s1));
		System.out.println(isValid(s2));

	}

	public static boolean isValid(String s) {
		Stack<Character> stack = new Stack<Character>();
		for (int i = 0; i < s.length(); i++) {
			char res = s.charAt(i);
			if ('(' == res || '{' == res || '[' == res) {
				stack.push(res);
			} else {
				if (stack.isEmpty()) {
					return false;
				}
				char top = stack.pop();
				if (res == ')' && top != '(') {
					return false;
				}
				if (res == ']' && top != '[') {
					return false;
				}
				if (res == '}' && top != '{') {
					return false;
				}
			}

		}
		return stack.isEmpty();
	}
}
