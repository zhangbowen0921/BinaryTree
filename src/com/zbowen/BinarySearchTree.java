package com.zbowen;

import java.awt.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;

import com.zbowen.printer.BinaryTreeInfo;

@SuppressWarnings("all")
public class BinarySearchTree<E> implements IBinaryTree<E>, BinaryTreeInfo {

	private Node<E> root;
	private int size;
	private Comparator<E> comparator;
	
	private static class Node<E> {
		
		E element;
		Node<E> left;
		Node<E> right;
		Node<E> parent;
		
		public Node(E element, Node<E> parent) {
			super();
			this.element = element;
			this.parent = parent;
		}
		
	}
	
	public BinarySearchTree() {
		this(null);
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public void clear() {
		
	}
	
	public void preorder(Node<E> root) {
		if (root==null) return;
		ArrayList<Node<E>> list = new ArrayList<Node<E>>();
		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node<E> pop = stack.pop();
			if (pop.right!=null) {
				stack.add(pop.right);
			}
			if (pop.left!=null) {
				stack.add(pop.left);
			}
		}
	}
	

	@Override
	public void add(E element) {
		if (element==null) new  IllegalArgumentException("element must not be null");
		if (root==null) root = new Node<E>(element, null);
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		while(node!=null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp<0) {
				node = node.left;
			}else if(cmp>0) {
				node = node.right;
			}else {
				return;
			}
		}
		Node<E> newNode = new Node<E>(element, parent);
		if (cmp<0) {
			parent.left = new Node<E>(element, parent);
		}else {
			parent.right = new Node<E>(element, parent);
		}
		size++;
	}
	
	@SuppressWarnings("unchecked")
	private int compare(E e1, E e2) {
		if (comparator!=null) {
			comparator.compare(e1, e2);
		}
		return ((Comparable<E>)e1).compareTo(e2);
	}

	@Override
	public void remove(E element) {
		
	}

	@Override
	public boolean contains(E element) {
		return false;
	}

	@Override
	public Object root() {
		return root;
	}

	
	@Override
	public Object left(Object node) {
		return ((Node<E>) node).left;
	}

	@Override
	public Object right(Object node) {
		return ((Node<E>) node).right;
	}

	@Override
	public Object string(Object node) {
		return ((Node<E>) node).element;
	}

	
	
}
