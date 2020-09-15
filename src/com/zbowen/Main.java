package com.zbowen;

import com.zbowen.printer.BinaryTrees;

public class Main {

	public static void main(String[] args) {
		int[] arr = {7, 4, 9, 2, 5, 8, 11, 3};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		for (int i = 0; i < arr.length; i++) {
			tree.add(arr[i]);
		}
		
		BinaryTrees.print(tree);
	}
	
}
