package binarytree;

/*
 * Binary Tree implementation. Properties of a binary tree are the following
 * 1. Every node with in binary tree must have atmost 2 child nodes
 * 2. The left child node must be <= in value comparted to the parent node and the right node must be > in value 
 * 
 * Deleting a node with 2 children will be done by replacing the node to be deleted with the node which is largest in value in its left subtree
 *  
 */ 

import java.lang.StringBuilder;

public class BinaryTree{
	private Node root;

	private class Node{
		private int value;
		private Node leftChild;
		private Node rightChild;
		private Node parent;

		public Node(int value, Node parent){
			this.value = value;
			this.parent = parent;
		}

		public void addChild(Node node){
			if(node.value <= this.value){
				createLeftChild(node);
			}
			else{
				createRightChild(node);
			}
			node.parent = this;
		}

		public void removeChild(Node node){
			if(this.leftChild == node){
				this.leftChild = null;
			} else if(this.rightChild == node){
				this.rightChild = null;
			}
		}

		public boolean haveChildren(){
			return this.leftChild != null || this.rightChild != null;
		}

		public boolean hasBothChildren(){
			return this.leftChild != null && this.rightChild != null;
		}

		public boolean hasParent(){
			return this.parent != null;
		}

		private void createLeftChild(Node node){
			this.leftChild = node;
		}

		private void createRightChild(Node node){
			this.rightChild = node;
		}

		public Node successor(){
			return null;
		}

		public String toString(){
			StringBuilder sb = new StringBuilder("Value");
			sb.append(" = ").append(this.value);
			if(this.parent != null){
				sb.append("; Parent = ").append(this.parent.value);
			}
			if(this.leftChild != null){
				sb.append("; Left Child = ").append(this.leftChild.value);
			}
			if(this.rightChild != null){
				sb.append("; Right Child = ").append(this.rightChild.value);
			}
			return sb.toString();
		}
	}

	public BinaryTree(){
	}

	public void insert(int value){
		Node n = createNode(value);
		if(this.root == null){
			this.root = n;
			return;
		} 

		Node parent = findParentNode(value);
		if(parent == null){
			throw new RuntimeException("Couldn't find the parent. findParentNode method is buggy");
		}
		parent.addChild(n);

	}

	/* if there are multiple nodes with the same value, deletes the left most */
	public void delete(int value){
		Node n = search(value);
		if(n == null){
			return;
		}

		if(!n.haveChildren()){
			n.parent.removeChild(n);
			return;
		}

		Node s = n.successor();
		replace(n, s);
	}

	private void replace(Node n, Node s){
	}

	/* if there are multiple nodes with the same value, returns the left most */
	public Node search(int value){
		Node n = root;
		while(n != null){
			if(value == n.value){
				if(n.leftChild == null || n.leftChild.value != value){
					return n;
				}
				n = n.leftChild;
			} else if(value < n.value){
				n = n.leftChild;
			} else{
				n = n.rightChild;
			}
		}

		return null;
	}

	public void dump(){
		dumpTree(root);
	}

	private void dumpTree(Node node){
		if(node == null){
			return;
		}

		dumpTree(node.leftChild);
		System.out.println(node);
		dumpTree(node.rightChild);
	}

	private Node createNode(int value){
		return new Node(value, null);
	}

	private Node findParentNode(int value){
		Node n = this.root;
		while(n != null){
			if(value <= n.value){
				if(n.leftChild == null){
					return n;
				}
				n = n.leftChild;
			} else{
				if(n.rightChild == null){
					return n;
				}
				n = n.rightChild;
			}
		}

		return null;
		
	}

	public static void main(String[] args){
		BinaryTree bt = new BinaryTree();
		bt.insert(2);
		bt.insert(2);
		bt.insert(3);
		bt.dump();

		System.out.println(bt.search(2).toString());
	}
}
