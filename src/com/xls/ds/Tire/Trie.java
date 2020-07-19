package com.xls.ds.Tire;

import java.util.TreeMap;

/**
 * @author xuls
 * 字典树
 */
public class Trie {

	private class Node {
		public boolean isWord;
		public TreeMap<Character, Node> next;

		public Node(boolean isWord) {
			this.isWord = isWord;
			next = new TreeMap<>();
		}

		public Node() {
			this(false);
		}
	}

	private Node root;
	private int size;

	public Trie() {
		root = new Node();
		size = 0;
	}

	public int getSize() {
		return size;
	}

	//向trie中添加一个单词 word
	public void add(String word) {
		/*Node curr = root;

		for (int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if (curr.next.get(c)==null){
				curr.next.put(c,new Node());
			}
			curr = curr.next.get(c);
		}
		if (!curr.isWord){
			curr.isWord=true;
			size++;
		}*/
		//递归实现add
		add(root, word, 0);
	}

	private void add(Node node, String word, int index) {
		if (index == word.length()) {
			if (!node.isWord) {
				size++;
				node.isWord = true;
			}
			return;
		}
		char c = word.charAt(index);
		if (node.next.get(c) == null) {
			node.next.put(c, new Node());
		}
		node = node.next.get(word.charAt(index));
		add(node, word, index + 1);
	}

	//查询单词在word中是否存在trie中
	public boolean contains(String word) {
	/*	Node cur = root;
		for (int i=0;i<word.length();i++){
			char c = word.charAt(i);
			if (cur.next.get(c)==null){
				return false;
			}
			cur = cur.next.get(c);
		}
		return cur.isWord;*/
		//递归实现
		return contains(root, word, 0);
	}

	//查询单词是否存在递归实现
	private boolean contains(Node node, String word, int index) {
		if (index == word.length()) {
			return node.isWord;
		}
		char c = word.charAt(index);
		if (node.next.get(c) == null) {
			return false;
		}
		return contains(node.next.get(c), word, index + 1);
	}

	//查询是否在Trie中单词以prefix为前缀
	public boolean isPrefix(String prefix) {
		Node cur = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (cur.next.get(c) == null) {
				return false;
			}
			cur = cur.next.get(c);
		}
		return true;
	}

}
