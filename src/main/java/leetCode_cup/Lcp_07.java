package leetCode_cup;

import java.util.ArrayList;
import java.util.List;

/**
 * LCP 07. 传递信息
 * 小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：
 *      1. 有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
 *      2. 每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系
 *      是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
 *      3. 每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
 * 给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。
 * 返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，
 * 返回 0。
 *
 * 示例 1：
 * 输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3
 * 输出：3
 * 解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案，
 * 分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。
 *
 * 示例 2：
 * 输入：n = 3, relation = [[0,2],[2,1]], k = 2
 * 输出：0
 * 解释：信息不能从小 A 处经过 2 轮传递到编号 2
 *
 *
 * 限制：
 *  - 2 <= n <= 10
 *  - 1 <= k <= 5
 *  - 1 <= relation.length <= 90, 且 relation[i].length == 2
 *  - 0 <= relation[i][0],relation[i][1] < n
 *      且 relation[i][0] != relation[i][1]
 * */
public class Lcp_07 {
    List[] graph;
    int ans = 0;
    public int numWays(int n, int[][] relation, int k) {
        // 建图
        graph = new List[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList();
        }

        for(int[] rel : relation){
            int e0 = rel[0], e1 = rel[1];
            graph[e0].add(e1);
        }

        dfs(0, 0, k, n - 1);
        return ans;
    }

    void dfs(int edge, int level, int k, int t){
        if(level == k){
            if(edge == t) {
                ans++;
            }
            return;
        }

        List<Integer> edges = graph[edge];
        for(int nextEdge: edges){
            dfs(nextEdge, level + 1, k, t);
        }
    }

    public static void main(String[] args){
        /*int n = 5;
        int[][] relation = {{0,2},{2,1},{3,4},{2,3},{1,4},{2,0},{0,4}};
        int k = 3;*/
        int n = 3;
        int[][] relation = {{0,1},{0,2},{2,1},{1,2},{1,0},{2,0}};
        int k = 5;
        Lcp_07 solution = new Lcp_07();
        int ans = solution.numWays(n, relation, k);
        System.out.println(ans);
    }
}
