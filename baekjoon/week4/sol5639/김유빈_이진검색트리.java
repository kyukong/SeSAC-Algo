package com.example.java.baekjoon.week4.sol5639;

import java.io.*;

public class Main {

    private static class Node {

        int num;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int nowNum = Integer.parseInt(br.readLine());

        // 1. 결과 -> 전위 순회 트리
        // 첫번째 값 Node 생성
        Node root = new Node(nowNum);

        Node parent = root;
        while (true) {
            String input = br.readLine();
            if (input == null || input.trim().isEmpty()) {
                break;
            }
            nowNum = Integer.parseInt(input);

            // 노드 연결
            while (true) {
                if (nowNum < parent.num) {
                    if (parent.left == null) {
                        parent.left = new Node(nowNum);
                        break;
                    } else {
                        parent = parent.left;
                    }
                } else if (nowNum > parent.num) {
                    if (parent.right == null) {
                        parent.right = new Node(nowNum);
                        break;
                    } else {
                        parent = parent.right;
                    }
                }
            }

            parent = root;
        }

        // 2. 트리 -> 후위 순회 결과
        postOrder(root);
    }

    private static void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        System.out.println(node.num);
    }
}
