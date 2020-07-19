package com.xls.ds.Tire;

/**
 * @author xuls
 */
public class Test {
	public static void main(String[] args) {
		Trie trie = new Trie();
		trie.add("dog");
		trie.add("cat");
		trie.add("panda");
		trie.add("pan");
		System.out.println("dog:" + trie.contains("dog"));
		System.out.println("cat:" + trie.contains("cat"));
		System.out.println("panda:" + trie.contains("panda"));
		System.out.println("pan:" + trie.contains("pan"));
		System.out.println("pand:" + trie.contains("pand"));
	}
}
