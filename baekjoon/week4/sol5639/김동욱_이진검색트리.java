package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 김동욱_이진검색트리 {
	public static class Node {
		int num;
		Node left, right;

		public Node() {
		}

		public Node(int num) {
			this.num = num;
		}

		public void insert(int num) {
			if (num < this.num) {
				if (this.left == null) {
					this.left = new Node(num);
				} else {
					this.left.insert(num);
				}
			} else {
				if (this.right == null) {
					this.right = new Node(num);
				} else {
					this.right.insert(num);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Node root = new Node(Integer.parseInt(br.readLine()));
		while (true) {
			String temp = br.readLine();
			if (temp == null)
				break;
			int nextNode = Integer.parseInt(temp);
			root.insert(nextNode);
		}
		postOrder(root);
	}

	private static void postOrder(Node node) {
		if (node.left != null)
			postOrder(node.left);
		if (node.right != null)
			postOrder(node.right);
		System.out.println(node.num);
	}
}
