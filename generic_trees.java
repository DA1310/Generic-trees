package Trees;

import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.soap.Node;

import java.util.LinkedList;

public class generic_trees {
	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;
	private int size;

	public generic_trees() {
		this.root = take_input(new Scanner(System.in), null, -1);
	}

	private Node take_input(Scanner scn, Node parent, int i) {

		if (parent == null) {
			System.out.println(" enter the root member");
		} else {
			System.out.println("enter the data for " + i + "th child of " + parent.data);
		}
		Node child = new Node();

		child.data = scn.nextInt();
		System.out.println(" enter the number of children for " + child.data);
		int children = scn.nextInt();
		this.size++;

		for (int j = 0; j < children; j++) {
			Node des = take_input(scn, child, j);
			child.children.add(des);

		}

		return child;

	}

	public int size() {
		return this.size;
	}

	public boolean isempty() {
		return this.size() == 0;
	}

	public void display() {

		display(this.root);
	}

	private void display(Node parent) {
		// String str = parent.data + " =>";
		// System.out.print(str);
		// for (Node child : parent.children) {
		// System.out.print(child.data + " , ");
		// }
		// System.out.println(".");
		// for (Node child : parent.children) {
		// display(child);
		// }
		//
		// }
		System.out.print(parent.data + " => ");
		for (Node child : parent.children) {
			System.out.print(child.data + " , ");
		}
		System.out.println();
		for (Node child : parent.children) {
			display(child);

		}

	}

	public int size2() {
		return size2(this.root);
	}

	private int size2(Node node) {
		int size = 0;
		for (Node child : node.children) {
			int sizeC = size2(child);
			size += sizeC;
		}
		return 1 + size;

	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {
		int max = node.data;
		for (Node child : node.children) {
			int maxC = max(child);
			if (maxC > max) {
				max = maxC;
			}
		}
		return max;

	}

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {
		int maxHeight = -1;
		for (Node child : node.children) {
			int C_height = height(child);
			if (C_height > maxHeight) {
				maxHeight = C_height;
			}
		}
		return maxHeight + 1;
	}

	public boolean find(int data) {
		return find(data, this.root);
	}

	private boolean find(int data, Node node) {
		if (data == node.data) {
			return true;
		}
		for (Node child : node.children) {
			boolean found = find(data, child);
			if (found)
				return true;

		}
		return false;
	}

	public void mirror() {
		mirror(this.root);
	}

	private void mirror(Node node) {
		for (Node child : node.children) {
			mirror(child);
		}
		int li = 0;
		int ri = node.children.size() - 1;
		while (li < ri) {
			Node lin = node.children.get(li);
			Node rin = node.children.get(ri);
			node.children.set(li, rin);
			node.children.set(ri, lin);

			li++;
			ri--;
		}

	}

	public void linearise() {
		linearise(this.root);
	}

	private Node getTail(Node node) {
		if (node.children.size() == 0) {
			return node;
		} else {
			return getTail(node.children.get(0));

		}

	}

	private void linearise(Node node) {

		for (Node child : node.children) {
			linearise(child);
		}
		while (node.children.size() > 1) {
			Node removed = node.children.remove(1);
			Node tail = getTail(node);
			tail.children.add(removed);
		}
	}

	public void lineariseEfficient() {
		lineariseEfficient(this.root);
	}

	private Node lineariseEfficient(Node node) {

		if (node.children.size() == 0) {
			return node;
		}
		Node tail0 = lineariseEfficient(node.children.get(0));
		while (node.children.size() > 1) {
			Node tail1 = lineariseEfficient(node.children.get(1));

			Node removed = node.children.remove(1);
			tail0.children.add(removed);
			tail0 = tail1;
		}
		return tail0;

	}

	public void preOrder() {
		preOrder(this.root);
	}

	private void preOrder(Node node) {
		System.out.print(node.data + "\t");
		for (Node child : node.children) {
			preOrder(child);
		}
	}

	public void postOrder() {
		postOrder(this.root);
	}

	private void postOrder(Node node) {
		for (Node child : node.children) {
			postOrder(child);
		}
		System.out.print(node.data + "\t");

	}

	public void levelOrder() {
		levelOrder(this.root);
	}

	private void levelOrder(Node node) {
		LinkedList<Node> Queue = new LinkedList<>();
		Queue.addLast(node);
		while (Queue.isEmpty() == false) {
			Node temp = Queue.removeFirst();
			System.out.print(temp.data + "\t");
			for (Node child : temp.children) {
				Queue.addLast(child);
			}
		}

	}

	public void levelOrderLine() {
		levelOrderLine(this.root);
	}

	private void levelOrderLine(Node node) {
		LinkedList<Node> curr = new LinkedList<>();
		LinkedList<Node> next = new LinkedList<>();
		curr.addLast(node);
		while (!curr.isEmpty()) {
			Node parent = curr.removeFirst();
			System.out.print(parent.data + "\t");
			for (Node child : parent.children) {
				next.addLast(child);
			}
			if (curr.size() == 0) {
				System.out.println();
				curr = next;
				next = new LinkedList<>();
			}
		}
	}

	public void levelOrderZigzag() {
		levelOrderZigzag(this.root);
	}

	private void levelOrderZigzag(Node node) {
		LinkedList<Node> curr = new LinkedList<>();
		LinkedList<Node> next = new LinkedList<>();
		curr.addLast(node);
		int count = 0;
		while (!curr.isEmpty()) {
			Node parent = curr.removeFirst();
			System.out.print(parent.data + "\t");
			if (count == 0)
				for (Node child : parent.children) {
					next.addFirst(child);
				}
			else {
				for (int i = parent.children.size() - 1; i >= 0; i--) {
					next.addFirst(parent.children.get(i));
				}
			}
			if (curr.size() == 0) {
				System.out.println();
				curr = next;
				next = new LinkedList<>();
				count++;
			}
		}

	}

	private class preOrderPair {
		Node node;
		boolean self = false;
		int childrensDone = 0;
	}

	public void preOrderIterative2() {
		preOrderPair head = new preOrderPair();
		head.node = this.root;
		LinkedList<preOrderPair> stack = new LinkedList<>();
		stack.addFirst(head);
		while (!stack.isEmpty()) {
			preOrderPair parent = stack.getFirst();
			if (parent.self == false) {
				System.out.print(parent.node.data + "\t");
				parent.self = true;
			}
			if (parent.childrensDone < parent.node.children.size()) {
				preOrderPair child = new preOrderPair();
				child.node = parent.node.children.get(parent.childrensDone);
				stack.addFirst(child);
				parent.childrensDone++;
			} else {

				stack.removeFirst();
			}
		}
	}

	private class heapmover {

		int size = 0;
		int height = -1;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		boolean found = false;
		Node predecessor = null;
		Node successor = null;
		Node JL = null;
		int countLeaf=0;

	}

	public void multisolver(int data) {

		heapmover mover = new heapmover();
		
		multisolver(this.root, 0, mover, data, null);
		System.out.println( " number of leaf "+ mover.countLeaf);
		System.out.println(" size " + mover.size);
		System.out.println(" height " + mover.height);
		System.out.println(" max " + mover.max);
		System.out.println(" min " + mover.min);
		System.out.println(" found " + mover.found);
		System.out.println(" predecessor is " + (mover.predecessor == null ? "null" : mover.predecessor.data));
		System.out.println(" successor is " + (mover.successor == null ? "null" : mover.successor.data));
		System.out.println(" just largest is " + (mover.JL == null ? "null" : mover.JL.data));

	}

	private void multisolver(Node node, int depth, heapmover mover, int data, Node prev) {

		if (mover.found && mover.successor == null) {
			mover.successor = node;

		}
		if (node.data > data) {
			if (mover.JL == null) {
				mover.JL = node;
			}
			if (node.data < mover.JL.data) {
				mover.JL = node;
			}
		}

		if (node.data > mover.max) {
			mover.max = node.data;
		}
		if (node.data < mover.min) {
			mover.min = node.data;
		}
		mover.size++;
		if (data == node.data) {
			mover.found = true;
		}
		if (depth > mover.height) {
			mover.height = depth;
		}
		if (node.data == data) {
			mover.predecessor = prev;
		}
		if(node.children.size()==0) {
			mover.countLeaf++;
		}
		node.data=depth;

		prev = node;
		for (Node child : node.children) {
			multisolver(child, depth + 1, mover, data, node);
		}
	}

	public int K_smallest(int data) {
		return K_smallest(data, this.root);
	}

	// do ques in class work copy 
	private int K_smallest(int k, Node node) {
		int min=Integer.MIN_VALUE;
		for(int i=0 ; i<k ; i++) {
			heapmover mover =new heapmover();
			multisolver(node , 0 , mover,min ,null);
			min = mover.JL.data;
			
		}
		return min;

	}

	public void removeLeaves() {
		removeLeaves(null, this.root, 0);
	}

	private void removeLeaves(Node prev, Node curr, int j) {
		if (curr.children.size() == 0) {
			prev.children.remove(j);
			
		}
		for (int i = 0; i < curr.children.size(); i++) {
			Node child = curr.children.get(i);
			if(child.children.size()==0) {
				i--;
				removeLeaves(curr, child, i+1);
			}
			removeLeaves(curr, child, i);

			
		}
	}
	
	public void PrintAtDepth(int d) {
		PrintAtDepth(d,this.root , new int[1]);
	}

	private void PrintAtDepth(int d,Node node , int[] arr) {
		LinkedList<Node> curr = new LinkedList<>();
		LinkedList<Node> next = new LinkedList<>();
		curr.addLast(node);
		while (!curr.isEmpty()) {
			Node parent = curr.removeFirst();
			if(arr[0]==d)
			{
			System.out.print(parent.data + "\t");
			}
			for (Node child : parent.children) {
				next.addLast(child);
			}
			if (curr.size() == 0) {
				System.out.println();
				curr = next;
				next = new LinkedList<>();
				arr[0]++;
			}
		}
	}

	public boolean isIsomorphic(generic_trees other) {
		return isIsomorphic(this.root,other.root);
	}
	private boolean isIsomorphic(Node thisnode, Node othernode) {
	   
		
		
		if(thisnode.children.size() == othernode.children.size()) {
			for(int i=0 ;i< thisnode.children.size() ; i++ ) {
				boolean isoM = isIsomorphic(thisnode.children.get(i) , othernode.children.get(i));
				if(!isoM) {
					return false ;
				}
				
			}
			
		} else return false;
		return true;
	}
	
	public boolean MirrorIsomorphic() {
		return MirrorIsomorphic(this.root,this.root);
	}
	private boolean MirrorIsomorphic(Node thisnode, Node othernode) {
	   
		
		
		if(thisnode.children.size() == othernode.children.size()) {
			int i;
			
			for( i=0  ;i< thisnode.children.size(); i++ ) {
				boolean isoM = isIsomorphic(thisnode.children.get(i) , othernode.children.get(othernode.children.size()-1-i));
				if(!isoM) {
					return false ;
				}
				
			}
			
		} else return false;
		return true;
	}
	
	


}
