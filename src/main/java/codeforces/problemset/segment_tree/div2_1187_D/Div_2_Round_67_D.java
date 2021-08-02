package codeforces.problemset.segment_tree.div2_1187_D;

import java.io.*;
import java.util.StringTokenizer;

/**
 * D. Subarray Sorting
 * link: https://codeforces.com/contest/1187/problem/D
 * */
public class Div_2_Round_67_D {
    static class FastScan {
        StringTokenizer st;
        BufferedReader br;

        public FastScan(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public FastScan(String debugInputFileName) throws FileNotFoundException {
            br = new BufferedReader(new FileReader(debugInputFileName));
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (Exception e) { }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastScan sc = new FastScan("E:/3.study/2.git_workspace/algorithm/src/main/java/codeforces/problemset/segment_tree/div2_1187_D/input_01.txt");
        int t = sc.nextInt();

        StringBuffer output = new StringBuffer();
        while(t-- > 0){
            int n =  sc.nextInt();
            int[] a = new int[n];
            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();
            }

            int[] b = new int[n];
            for(int i = 0; i < n; i++){
                b[i] = sc.nextInt();
            }

            String ans = solve(a, b, n);
            output.append(ans).append("\n");
        }

        PrintWriter pw = new PrintWriter(System.out);
        pw.print(output);
    }

    static class SegmentTree {
        static class SegmentNode {
            int minNum;
            int left, right;

            @Override
            public String toString() {
                StringBuffer nodeOutput = new StringBuffer();
                nodeOutput.append("[left = ").append(left)
                        .append(" - right = ").append(right)
                        .append("] minNum = ").append(minNum).append(" ");
                return nodeOutput.toString();
            }
        }

        SegmentNode[] nodes;
        int[] num;

        SegmentTree(int[] num){
            this.nodes = new SegmentNode[num.length * 4];
            this.num = num;
            build(0, 0, num.length - 1);
        }

        void build(int treeIndex, int left, int right){
            if(left == right){
                nodes[treeIndex] = new SegmentNode();
                nodes[treeIndex].minNum = num[left];
                nodes[treeIndex].left = left;
                nodes[treeIndex].right = right;
                return;
            }

            int mid = (left + right) / 2;

            int treeLeftIndex = 2 * treeIndex + 1;
            int treeRightIndex = 2 * treeIndex + 2;

            build(treeLeftIndex, left, mid);
            build(treeRightIndex, mid + 1, right);

            nodes[treeIndex] = new SegmentNode();
            nodes[treeIndex].left = left;
            nodes[treeIndex].right = right;
            nodes[treeIndex].minNum = Math.min(nodes[treeLeftIndex].minNum, nodes[treeRightIndex].minNum);
        }

        @Override
        public String toString(){
            StringBuffer output = new StringBuffer();
            for(SegmentNode node : nodes){
                output.append(node).append("\n");
            }

            return output.toString();
        }
    }

    static String solve(int[] a, int[] b, int n){
        // 1.构建线段树
        SegmentTree segmentTree = new SegmentTree(a);
        System.out.println(segmentTree);

        return "YES";
    }
}