package codeforces.vp.vp_20210808;

import java.io.*;
import java.util.StringTokenizer;

/**
 * B. Phoenix and Puzzle
 * */
public class Problem_B {
    public static void main(String[] args) throws FileNotFoundException {
        FastScan sc = new FastScan(System.in);
        //FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/vp/vp_20210808/b_input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();
        while(t-- > 0){
            int n = sc.nextInt();
            boolean ans = false;
            for(int i = 1; i <= 30; i++){
                int mask = 1 << i;
                if(n == mask){
                    ans = true;
                    break;
                }
            }

            if(!ans && n != 1 && (n % 2) == 0){
                for(int i = 1; i <= 30; i++){
                    int mask = 1 << i;
                    if(n / mask <= 1){
                        break;
                    }

                    if(n % mask == 0 && check(n / mask)){
                        ans = true;
                        break;
                    }
                }
            }
            out.println(ans ? "YES" : "NO");
        }

        out.flush();
        out.close();
    }

    private static boolean check(long num){
        if(num <= 1){
            return false;
        }

        long left = 1, right = (long) Math.sqrt(num);
        while(left <= right){
            long mid = (left + right) / 2;
            if(mid * mid <= num){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        //System.out.println(left + " # " + right);
        return (right * right) == num;
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
