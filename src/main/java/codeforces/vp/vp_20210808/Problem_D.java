package codeforces.vp.vp_20210808;

import java.io.*;
import java.util.*;

/**
 *
 * */
public class Problem_D {
    public static void main(String[] args) throws FileNotFoundException {
        FastScan sc = new FastScan(System.in);
        //FastScan sc = new FastScan("/Users/zhuhuina/IdeaProjects/algorithm/src/main/java/codeforces/vp/vp_20210808/d_input_01.txt");
        //FastScan sc = new FastScan("E:\\3.study\\2.git_workspace\\algorithm\\src\\main\\java\\codeforces\\problemset\\segment_tree\\div2_1187_D\\input_01.txt");
        PrintWriter out = new PrintWriter(System.out);

        int t = sc.nextInt();

        while(t-- > 0){
            int n = sc.nextInt();
            int[] a = new int[n];
            TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
            for(int i = 0; i < n; i++){
                a[i] = sc.nextInt();

                int cnt = map.getOrDefault(a[i], 0);
                map.put(a[i], ++cnt);
            }

            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o2[1] - o1[1];
                }
            });
            Set<Integer> keySet = map.keySet();
            Iterator<Integer> it = keySet.iterator();
            while(it.hasNext()){
                Integer k = it.next();
                Integer v = map.get(k);

                pq.offer(new int[]{k, v});
            }

            while(pq.size() > 1){
                int[] p1 = pq.poll();
                int[] p2 = pq.poll();

                p1[1] --;
                p2[1] --;

                if(p1[1] > 0){
                    pq.offer(p1);
                }

                if(p2[1] > 0){
                    pq.offer(p2);
                }
            }
            out.println(pq.isEmpty() ? 0 : pq.peek()[1]);
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
