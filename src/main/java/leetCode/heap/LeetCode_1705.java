package leetCode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class LeetCode_1705 {
    class Pair{
        int i;
        int a;
        int d;
        int bd;

        Pair(int i, int a, int d){
            this.i = i;
            this.a = a;
            this.d = d;

            this.bd = (i + d);
        }

        @Override
        public String toString(){
            return "i = " + i + " a = " + a + " d = " + d + " bd = " + bd;
        }
    }

    public int eatenApples(int[] apples, int[] days) {
        /*int n = apples.length;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2){
                return p1.bd - p2.bd;
            }
        });

        int ans = 0;
        for(int i = 0; i < n || pq.size() > 0; i++){
            // 1.移除过期的
            while(!pq.isEmpty()){
                Pair pr = pq.peek();
                if(pr.bd <= i){
                    pq.poll();
                } else {
                    break;
                }
            }

            // 2.添加当天新张出来的
            if(i < n && apples[i] > 0){
                Pair p = new Pair(i, apples[i], days[i]);
                pq.offer(p);
            }

            // 3.吃掉已有的（优先吃掉）
            Pair p = pq.peek();
            if(null != p && p.a > 0){
                ans += 1;
                p.a -= 1;
                if(p.a == 0){
                    pq.poll();
                }
            }
        }

        return ans;*/

        int n = apples.length;
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2){
                return p1.bd - p2.bd;
            }
        });

        for(int i = 0; i < n; i++){
            Pair p = new Pair(i, apples[i], days[i]);
            pq.offer(p);
        }

        int curDay = 0;
        while(!pq.isEmpty()){
            if(pq.peek().bd <= curDay){
                pq.poll();
                continue;
            } else {
                Pair p = pq.peek();
                if(p.a > 0){
                    p.a -= 1;
                    if(p.a == 0){
                        pq.poll();
                    }
                    curDay ++;
                }
            }
        }

        return curDay;
    }

    public static void main(String[] args){
        int[] apples = {2,1,1,4,5};
        int[] days = {10,10,6,4,2};
        /*int[] apples = {3,0,0,0,0,2};
        int[] days = {3,0,0,0,0,2};*/
        /*int[] apples = {1,2,3,5,2};
        int[] days = {3,2,1,4,2};*/
        /*int[] apples = {2,1,10};
        int[] days = {2,10,1};*/
        LeetCode_1705 solution = new LeetCode_1705();
        int ans = solution.eatenApples(apples, days);
        System.out.println(ans);
    }
}
