package com.zbowen;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

import com.zbowen.printer.BinaryTreeInfo;

@SuppressWarnings("all")
public class BinarySearchTree<E> implements IBinaryTree<E>, BinaryTreeInfo {

	private Node<E> root;
	private int size;
	private Comparator<E> comparator;

	public Node<E> getRoot() {
		return root;
	}

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

		public boolean isLeaf() {
			return this.left == null && this.right == null;
		}

		public boolean hasTwoChildren() {
			return this.left != null && this.right != null;
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
		return size == 0;
	}

	@Override
	public void clear() {
		size = 0;
		root = null;
	}
	
	@Override
	public boolean contains(E element) {
		return node(element)!=null;
	}


	/**
	 * 前序遍历
	 * 
	 * @param root 根节点
	 */
	public void preorderTraversal(Node<E> root) {
		ArrayList<Node<E>> list = new ArrayList<Node<E>>();
		Stack<Node<E>> stack = new Stack<Node<E>>();
		stack.add(root);
		while (!stack.isEmpty()) {
			Node<E> pop = stack.pop();
			if (pop.right != null) {
				stack.add(pop.right);
			}
			if (pop.left != null) {
				stack.add(pop.left);
			}
		}
	}

	public void preorder(Node<E> root) {
		if (root == null)
			return;
		System.out.println(root.element);
		preorder(root.left);
		preorder(root.right);
	}

	/**
	 * 中序遍历
	 * 
	 * @param root
	 */
	public void infixorderTraversal(Node<E> root) {
		Stack<Node<E>> stack = new Stack<Node<E>>();
		Node<E> node = root;
		while (node != null | !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			Node<E> pop = stack.pop();
			System.out.println(pop.element);
			if (pop.right != null) {
				node = pop.right;
			}
		}
	}

	/**
	 * 后序遍历
	 * 
	 * @param root 根节点
	 */
	public void epilogueTraversal(Node<E> root) {
		Stack<Node<E>> stack = new Stack<>();
		LinkedList<Node<E>> list = new LinkedList<>();
		Node<E> node = root;
		while (node != null || !stack.isEmpty()) {
			while (node != null) {
				stack.push(node);
				node = node.left;
			}
			Node<E> peek = stack.peek();
			if (peek.right != null) {
				boolean contains = list.contains(peek);
				if (contains) {
					Node<E> pop = stack.pop();
					System.out.println(pop.element);
				} else {
					list.add(peek);
					node = peek.right;
				}
			} else {
				Node<E> pop = stack.pop();
				System.out.println(pop.element);
			}

		}
	}

	/**
	 * 层序遍历
	 * 
	 * @param root 根节点
	 */
	public void sequenceTraversal(Node<E> root) {
		if (root == null)
			return;
		LinkedList<Node<E>> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<E> pop = queue.pop();
			System.out.print(pop.element + ", ");
			if (pop.left != null) {
				queue.add(pop.left);
			}
			if (pop.right != null) {
				queue.add(pop.right);
			}
		}
	}

	@Override
	public void add(E element) {
		if (element == null)
			new IllegalArgumentException("element must not be null");
		if (root == null)
			root = new Node<E>(element, null);
		Node<E> node = root;
		Node<E> parent = null;
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			parent = node;
			if (cmp < 0) {
				node = node.left;
			} else if (cmp > 0) {
				node = node.right;
			} else {
				return;
			}
		}

		if (cmp < 0) {
			parent.left = new Node<E>(element, parent);
		} else {
			parent.right = new Node<E>(element, parent);
		}
		size++;
	}

	@Override
	public void remove(E element) {
		Node<E> node = node(element);
		remove(node);
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
	public Object string(Object obj) {
		Node<E> node = (Node<E>) obj;
		return node.element+"_p("+(node.parent==null?"null":node.parent.element)+")";
	}

	@SuppressWarnings("unchecked")
	private int compare(E e1, E e2) {
		if (comparator != null)
			return comparator.compare(e1, e2);
		return ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * 根据元素获取节点
	 * 
	 * @param element
	 * @return
	 */
	private Node<E> node(E element) {
		if (root == null)
			return root;
		Node<E> node = root;
		while (node != null) {
			int compare = compare(element, node.element);
			if (compare > 0) {
				node = node.right;
			} else if (compare < 0) {
				node = node.left;
			} else {
				return node;
			}
		}
		return node;
	}

	/**
	 * 获取某个节点的前驱节点
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> precursorNode(Node<E> node) {
		if (node == null)
			return null;
		if (node.left != null) {
			// 该节点左节点都不等于空 那么前驱节点只能出现在该节点的左子树中
			node = node.left;
			while (node != null) {
				if (node.right == null) {
					return node;
				}
				node = node.right;
			}
		}
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		return node.parent;
	}

	/**
	 * 获取某个节点的后继节点
	 * 
	 * @param node
	 * @return
	 */
	private Node<E> successorNode(Node<E> node) {
		if (node == null)
			return null;
		if (node.left != null) {
			// 该节点右节点都不等于空 那么后继节点只能出现在该节点的右子树中
			node = node.right;
			while (node != null) {
				if (node.left == null) {
					return node;
				}
				node = node.left;
			}
		}
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;
	}

	/***
	 * 删除某个节点
	 * 
	 * @param node
	 */
	private void remove(Node<E> node) {
		if (node == null) return;
		
		if (node.hasTwoChildren()) {
			Node<E> precursor = precursorNode(node);
			node.element = precursor.element;
			node = precursor;
		}

		Node<E> child = node.left != null ? node.left : node.right;
		if (child != null) {
			child.parent = node.parent;
			if (node.parent == null) { // 说明node是度为1点节点并且是根节点
				root = child;
			} else if (node.parent.left == node) {
				node.parent.left = child;
			} else {
				node.parent.right = child;
			}
		} else if (node.parent == null) { // 说明node是叶子节点 也是根节点
			root = null;
		} else { // 说明node是叶子节点 不是根节点
			if (node.parent.left == node) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
		}
		
		size--;
	}

}
