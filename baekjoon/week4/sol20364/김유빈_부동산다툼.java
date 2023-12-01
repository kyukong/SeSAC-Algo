package com.example.java.baekjoon.week4.sol20364;

import java.util.*;
import java.io.*;

public class Main {

    private static int n;
    private static Node root = new Node(1);
    private static int[] dp;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 트리 생성
        n = Integer.parseInt(st.nextToken());
        dp = new int[n + 1];
        int q = Integer.parseInt(st.nextToken());

        create(root);

        // 오리 이동
        int duck;
        List<Integer> nums;
        int _duck;
        for (int i = 0; i < q; i++) {
            duck = Integer.parseInt(br.readLine());
            nums = new ArrayList<>();
            _duck = duck;
            while (true) {
                nums.add(_duck);
                _duck = _duck / 2;
                if (_duck < 1) {
                    Collections.sort(nums);
                    break;
                }
            }

            boolean check = false;
            for (int num : nums) {
                if (dp[num] == 1) {
                    sb.append(num).append("\n");
                    check = true;
                    break;
                }
            }

            if (!check) {
                sb.append(0).append("\n");
                dp[duck] = 1;
            }
        }
        System.out.println(sb);
    }

    private static void create(Node node) {
        if (node.num * 2 <= n) {
            node.left = new Node(node.num * 2);
            create(node.left);
        }
        if (node.num * 2 + 1 <= n) {
            node.right = new Node(node.num * 2 + 1);
            create(node.right);
        }
    }
}
