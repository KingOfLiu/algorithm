package codeforces.contests.round_735_div2.problem_B;

import java.io.*;
import java.util.StringTokenizer;

/**
 * B. Cobb
 * https://codeforces.com/contest/1554/problem/B
 * */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        //FastScan sc = new FastScan(System.in);
        FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/contests/round_735_div2/problem_B/input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] a = new int[n + 1];
            for(int i = 1; i <= n; i++){
                a[i] = sc.nextInt();
            }

            long ans = Integer.MIN_VALUE;
            int left = Math.max(1, n - 2 * k);
            for(int i = left; i <= n; i++){
                for(int j = i + 1; j <= n; j++){
                    ans = Math.max(ans, (i * j - k * (a[i] | a[j])));
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
    }
}
