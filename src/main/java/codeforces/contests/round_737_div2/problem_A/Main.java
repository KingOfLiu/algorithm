package codeforces.contests.round_737_div2.problem_A;

import java.io.*;
import java.util.StringTokenizer;

/**
 * A. Ezzat and Two Subsequences
 * https://codeforces.com/contest/1557/problem/A
 * */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //FastScan sc = new FastScan(System.in);
        FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/contests/round_737_div2/problem_A/a_input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            long[] a = new long[n];
            for(int i = 0; i < n; i++){
                a[i] = sc.nextLong();
            }

            double[] p = new double[n];
            p[0] = a[0];
            for(int i = 1; i < n; i++){
                p[i] = p[i - 1] + (double) a[i];
            }

            double ans = -1 * Long.MIN_VALUE;
            for(int i = 0; i < n - 1; i++){
                double p1 = p[i], p2 = p[n - 1] - p1;
                int s1 = i + 1, s2 = n - 1 - i;

                double val = p1 / s1 + p2 / s2;
                ans = Math.max(ans, val);
            }
            out.println(String.format("%.9f", ans));
        }

        out.flush();
        out.close();
    }

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

        public long nextLong(){
            return Long.parseLong(next());
        }
    }
}
