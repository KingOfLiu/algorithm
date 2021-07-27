package codeforces.problemset.segment_tree;

import java.io.BufferedInputStream;
import java.util.Scanner;

/**
 * link: https://codeforces.com/problemset/problem/380/C
 */
public class Div_1_223_C {
    static class Node {
        int a, b, num;
    }

    static class SegmentTree {
        Node[] nodes;
        char[] data;
        SegmentTree (String s){
            data = s.toCharArray();
            nodes = new Node[data.length * 4];

            buildSegmentTree(0, 0, data.length - 1);
        }

        void buildSegmentTree(int treeIndex, int left, int right){
            if(left == right){
                char ch = data[left];
                nodes[treeIndex] = new Node();
                nodes[treeIndex].a = (ch == '(' ? 1 : 0);
                nodes[treeIndex].b = (ch == ')' ? 1 : 0);
                return ;
            }

            nodes[treeIndex] = new Node();

            int leftIndex = leftChild(treeIndex);
            int rightIndex = rightChild(treeIndex);

            int mid = left + (right - left) / 2;
            buildSegmentTree(leftIndex, left, mid);
            buildSegmentTree(rightIndex, mid + 1, right);

            Node leftNode = nodes[leftIndex], rightNode = nodes[rightIndex];
            if(leftNode != null && rightNode != null){
                if(leftNode.a != 0 && rightNode.b != 0){
                    nodes[treeIndex].num += 2 * Math.min(leftNode.a, rightNode.b);
                }
            }
        }

        private int leftChild(int index){
            return 2 * index + 1;
        }

        private int rightChild(int index){
            return 2 * index + 2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        String words = sc.next();
        int m = sc.nextInt();
        int[] q = new int[m];
        for(int i = 0; i < m; i++){
            q[i] = sc.nextInt();
        }

        SegmentTree st = new SegmentTree(words);
        System.out.println(st);
    }
}
