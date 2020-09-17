package com.zbowen;

import com.zbowen.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		int[] arr = {2, 7, 18, 1, 5, 17, 6, 11};
		BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		for (int i = 0; i < arr.length; i++) {
			tree.add(arr[i]);
		}
		
		tree.add(16);
		
		BinaryTrees.print(tree);
		tree.remove(2);
		tree.remove(1);
		tree.remove(11);
		System.out.println("\n");
		BinaryTrees.print(tree);
		
	}
	
}
