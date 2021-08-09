package codeforces.vp.vp_20210808;

import java.io.*;
import java.util.StringTokenizer;

/**
 * A. Cherry
 * https://codeforces.com/problemset/problem/1554/A
 * */
public class Problem_A {
    public static void main(String[] args) throws FileNotFoundException {
        FastScan sc = new FastScan(System.in);
        //FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/vp/vp_20210808/a_input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];

            long ans = Integer.MIN_VALUE;
            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();

                if(i > 0){
                    ans = Math.max((long)Math.max(a[i], a[i - 1]) * (long)Math.min(a[i], a[i - 1]), ans);
                }
            }
            out.println(ans);
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
