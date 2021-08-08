package codeforces.vp.vp_20210808;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * C. Penalty
 * */
public class Problem_C {
    public static void main(String[] args) throws FileNotFoundException {
        //FastScan sc = new FastScan(System.in);
        FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/vp/vp_20210808/c_input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();

        while(t-- > 0){
            String s = sc.next();
            out.println(solve(s));
        }

        out.flush();
        out.close();
    }

    static int solve(String s){
        int[] a = new int[1 << s.length()];
        Arrays.fill(a, -1);
        dfs(s, 0, 0, a);

        int ans = 10;
        for(int i = 0; i < a.length; i++){
            if(a[i] != -1){
                int[] c = check(i);
                int aS = c[0];
                int bS = c[1];

                int aOver = c[2];
                int bOver = c[3];

                int abs = Math.abs(aS - bS);
                System.out.println(i + " = " + Integer.toBinaryString(i) + " = " + a[i] + " odd = " + c[0] + " even = " + c[1] + " abs = " + abs + " # aOver = " + aOver + " # bOver = " + bOver);

                if(Math.abs(aS - bS) > aOver && Math.abs(aS - bS) > bOver){
                    ans = Math.min(ans, a[i]);
                }
            }
        }

        return ans;
    }

    static int[] check(int mask){
        // 当前能获得的最高位
        int maxBit = -1;

        // 奇数的得分
        int oddBitCnt = 0;

        // 偶数的得分
        int evenBitCnt = 0;
        for(int i = 0; i <= 10; i++){
            int curMask = 1 << i;
            if((curMask & mask) == curMask){
                if(i % 2 != 0){
                    oddBitCnt++;
                } else {
                    evenBitCnt++;
                }
                maxBit = Math.max(maxBit, i + 1);
            }
        }

        int overOddBitCnt = -1, overEvenBitCnt = -1;
        if(maxBit % 2 == 0){
            // 当最高位是偶数时，剩余的回合数为(10 - 最高位)/2
            overOddBitCnt = overEvenBitCnt = (10 - maxBit) / 2;
        } else {
            // 否则当最高位为奇数时，剩余的回合数，奇数位的肯定要少于偶数位
            int over = 10 - maxBit;
            overOddBitCnt = over / 2;
            overEvenBitCnt = over / 2 + 1;
        }
        return new int[]{oddBitCnt, evenBitCnt, overOddBitCnt, overEvenBitCnt};
    }

    static void dfs(String s, int i, int mask, int[] a){
        if(i == s.length()){
            return ;
        }

        int curMask = mask;
        curMask <<= 1;

        char ch = s.charAt(i);
        if(ch == '?'){
            dfs(s, i + 1, curMask ^ 0, a);
            a[curMask ^ 0] = (i + 1);

            dfs(s, i + 1, curMask ^ 1, a);
            a[curMask ^ 1] = (i + 1);
        } else {
            int num = s.charAt(i) - '0';
            dfs(s, i + 1, curMask ^ num, a);
            a[curMask ^ num] = (i + 1);
        }
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