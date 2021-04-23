package leetCode.greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1801. 积压订单中的订单总数
 * */
public class LeetCode_1801 {
    public int getNumberOfBacklogOrders(int[][] orders) {
        // 采购的积压订单
        PriorityQueue<int[]> buyPq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                return p2[0] - p1[0];
            }
        });

        // 销售的积压订单
        PriorityQueue<int[]> sellPq = new PriorityQueue<int[]>(new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                return p1[0] - p2[0];
            }
        });

        int n = orders.length;
        for(int i = 0; i < n; i++){
            int[] curOrder = orders[i];
            // type = 0 采购订单 buy 找价格最低的销售订单，且该销售订单的价格小于等于当前订单价格
            // type = 1 销售订单 sell 找价格最高的采购订单，且该采购订单价格大于等于当前订单价格
            int type = curOrder[2];
            // 采购订单，需要从积压的销售订单中找到价格小于等于当前订单的价格的订单
            if(type == 0){
                if(sellPq.isEmpty()){
                    buyPq.offer(curOrder);
                } else {
                    if(sellPq.peek()[0] > curOrder[0]){
                        buyPq.offer(curOrder);
                        continue;
                    }

                    int curAmount = curOrder[1];
                    while(!sellPq.isEmpty() && sellPq.peek()[0] <= curOrder[0] && curAmount > 0){
                        if(curAmount > sellPq.peek()[1]){
                            curAmount -= sellPq.peek()[1];
                            sellPq.poll();
                        } else {
                            sellPq.peek()[1] -= curAmount;
                            curAmount = 0;
                            if(sellPq.peek()[1] <= 0){
                                sellPq.poll();
                            }
                        }
                    }

                    if(curAmount > 0){
                        curOrder[1] = curAmount;
                        buyPq.offer(curOrder);
                    }
                }
            } else {
                // 销售订单 查找积压订单中价格最高的采购订单，如果采购订单价格大于等于当前销售订单的价格，
                // 执行两笔订单，否则将销售订单放入到积压订单中
                if(buyPq.isEmpty()){
                    sellPq.offer(curOrder);
                } else {
                    if(curOrder[0] > buyPq.peek()[0]){
                        sellPq.offer(curOrder);
                        continue;
                    }

                    int curAmount = curOrder[1];
                    while(!buyPq.isEmpty() && buyPq.peek()[0] >= curOrder[0] && curAmount > 0){
                        if(curAmount > buyPq.peek()[1]){
                            curAmount -= buyPq.peek()[1];
                            buyPq.poll();
                        } else {
                            buyPq.peek()[1] -= curAmount;
                            curAmount = 0;
                            if(buyPq.peek()[1] <= 0){
                                buyPq.poll();
                            }
                        }
                    }

                    if(curAmount > 0){
                        curOrder[1] = curAmount;
                        sellPq.offer(curOrder);
                    }
                }
            }
        }

        long ans = 0;
        int mod = (int) 1e9 + 7;
        while(!buyPq.isEmpty()){
            int curAmount = buyPq.poll()[1];
            ans += curAmount;
        }

        while(!sellPq.isEmpty()){
            int curAmount = sellPq.poll()[1];
            ans += curAmount;
        }
        return (int)(ans % mod);
    }

    public static void main(String[] args){
        int[][] orders = {{20,5,1}, {5,2,1}, {30,3,0}, {45,8,1}, {75,6,1}, {60,3,0}};
        //int[][] orders = {{10,5,0},{15,2,1},{25,1,1},{30,4,0}};
        //int[][] orders = {{7,1000000000,1},{15,3,0},{5,999999995,0},{5,1,1}};
        //int[][] orders = {{19,28,0},{9,4,1},{25,15,1}};
        LeetCode_1801 solution = new LeetCode_1801();
        int ans = solution.getNumberOfBacklogOrders(orders);
        System.out.println(ans);
    }
}