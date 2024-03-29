package codeforces.problemset.segment_tree;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * link: https://codeforces.com/problemset/problem/380/C
 */
public class Div_1_223_C {
    static class Node {
        int a, b, num;
        int left, right;

        public String toString(){
            return "left = " + left + " right = " + right + " a = " + a + " b = " + b + " num = " + num;
        }
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
                nodes[treeIndex].left = left;
                nodes[treeIndex].right = right;
                nodes[treeIndex].a = (ch == '(' ? 1 : 0);
                nodes[treeIndex].b = (ch == ')' ? 1 : 0);
                return ;
            }

            nodes[treeIndex] = new Node();
            nodes[treeIndex].left = left;
            nodes[treeIndex].right = right;

            int leftIndex = leftChild(treeIndex);
            int rightIndex = rightChild(treeIndex);

            int mid = left + (right - left) / 2;
            buildSegmentTree(leftIndex, left, mid);
            buildSegmentTree(rightIndex, mid + 1, right);

            int x = Math.min(nodes[leftIndex].a, nodes[rightIndex].b);
            nodes[treeIndex].num = x + nodes[leftIndex].num + nodes[rightIndex].num;
            nodes[treeIndex].a = nodes[leftIndex].a + nodes[rightIndex].a - x;
            nodes[treeIndex].b = nodes[leftIndex].b + nodes[rightIndex].b - x;
        }

        Node query(int treeIndex, int l, int r, int left, int right){
            // 区间完全包含的条件
            if(l == left && r == right){
                return nodes[treeIndex];
            }

            int mid = l + (r - l) / 2;
            int leftIndex = leftChild(treeIndex), rightIndex = rightChild(treeIndex);
            if(left > mid){
                 return query(rightIndex, mid + 1, r, left, right);
            } else if(right <= mid){
                return query(leftIndex, l, mid, left, right);
            } else {
                Node leftNode = query(leftIndex, l, mid, left, mid);
                Node rightNode = query(rightIndex, mid + 1, r, mid + 1, right);

                Node result = new Node();
                int x = Math.min(leftNode.a, rightNode.b);
                result.num += x;

                result.a += rightNode.a;
                result.b += rightNode.b;
                result.num += rightNode.num;

                result.a += leftNode.a;
                result.b += leftNode.b;
                result.num += leftNode.num;

                result.a -= x;
                result.b -= x;

                return result;
            }
        }

        private int leftChild(int index){
            return 2 * index + 1;
        }

        private int rightChild(int index){
            return 2 * index + 2;
        }
    }

    public static void main(String[] args) throws IOException {
        Scan sc = new Scan(System.in);
        String words = sc.next();
        SegmentTree st = new SegmentTree(words);

        int m = Integer.parseInt(sc.next()), n = words.length();

        StringBuilder print=new StringBuilder();
        for(int i = 0; i < m; i++){
            int left=sc.nextInt();
            int right=sc.nextInt();

            int ans = st.query(0, 0, n - 1,left - 1, right - 1).num * 2;
            print.append(ans).append("\n");
        }
        System.out.print(print.toString());
    }

    static class Scan {
        StringTokenizer st;
        BufferedReader br;

        public Scan(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) {
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }
}
