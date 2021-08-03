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
        // FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/problemset/segment_tree/div2_1187_D/input_01.txt");
        FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
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
        pw.flush();
        pw.close();
    }

    static class SegmentTree {
        static class SegmentNode {
            int minNum;
            int idx;
            int left, right;

            @Override
            public String toString() {
                StringBuffer nodeOutput = new StringBuffer();
                nodeOutput.append("index = ").append(idx).append(" ");
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
                nodes[treeIndex].idx = left;
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

        SegmentNode query(int treeIndex, int left, int right, int l, int r){
            int mid = (left + right) / 2;
            if(left == l && right == r){
                return nodes[treeIndex];
            }
            // 查询右区间，条件：如果查询区间的左边界大于中间节点，查询右区间
            else if(l > mid){
                return query(treeIndex * 2 + 2, mid + 1, right,l, r);
            }
            // 查询左区间，条件：如果查询区间的右边界小于等于中间节点，查询左区间
            else if(r <= mid){
                return query(treeIndex * 2 + 1, left, mid, l, r);
            } else {
                SegmentNode leftNode = query(treeIndex * 2 + 1, left, mid, l, mid);
                SegmentNode rightNode = query(treeIndex * 2 + 2, mid + 1, right, mid + 1, r);
                if(leftNode.minNum <= rightNode.minNum){
                    return leftNode;
                }

                return rightNode;
            }
        }

        void update(int treeIndex, int left, int right, int idx, int newNum){
            int mid = (left + right) / 2;
            if(left == right){
                nodes[treeIndex].minNum = newNum;
                return ;
            }

            // i > mid 更新右区间
            if(idx > mid){
                update(treeIndex * 2 + 2, mid + 1, right, idx, newNum);
            }
            // i <= mid 更新左区间
            else if(idx <= mid){
                update(treeIndex * 2 + 1, left, mid, idx, newNum);
            }

            // 这里处理回溯
            SegmentNode leftNode = nodes[treeIndex * 2 + 1];
            SegmentNode rightNode = nodes[treeIndex * 2 + 2];
            nodes[treeIndex].minNum = Math.min(leftNode.minNum, rightNode.minNum);
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

        for(int i = 0; i < n; i++){
            if(a[i] != b[i]){
                // 2.1 find i to n - 1 minNum
                SegmentTree.SegmentNode tMin = segmentTree.query(0, 0, n - 1, i, n - 1);
                System.out.println(tMin);

                // 2.2 update

            }
        }
        return "YES";
    }
}