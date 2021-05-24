package leetCode.dfs;

import common.ArrayUtil;

import java.util.*;

/**
 * 1766. 互质树
 * 给你一个 n 个节点的树（也就是一个无环连通无向图），节点编号从 0 到 n - 1 ，且恰好有 n - 1 条边，
 * 每个节点有一个值。树的 根节点 为 0 号点。
 *
 * 给你一个整数数组 nums 和一个二维数组 edges 来表示这棵树。nums[i] 表示第 i 个点的值
 * edges[j] = [uj, vj] 表示节点 uj 和节点 vj 在树中有一条边。
 *
 * 当 gcd(x, y) == 1 ，我们称两个数 x 和 y 是 互质的 ，其中 gcd(x, y) 是 x 和 y 的 最大公约数 。
 *
 * 从节点 i 到 根 最短路径上的点都是节点 i 的祖先节点。一个节点 不是 它自己的祖先节点。
 *
 * 请你返回一个大小为 n 的数组 ans ，其中 ans[i]是离节点 i 最近的祖先节点且满足 nums[i] 和 nums[ans[i]] 是 互质的 ，
 * 如果不存在这样的祖先节点，ans[i] 为 -1 。
 *
 *
 * 示例 1：
 * 输入：nums = [2,3,3,2], edges = [[0,1],[1,2],[1,3]]
 * 输出：[-1,0,0,1]
 * 解释：上图中，每个节点的值在括号中表示。
 * - 节点 0 没有互质祖先。
 * - 节点 1 只有一个祖先节点 0 。它们的值是互质的（gcd(2,3) == 1）。
 * - 节点 2 有两个祖先节点，分别是节点 1 和节点 0 。节点 1 的值与它的值不是互质的（gcd(3,3) == 3）但节点 0 的值是互质的(gcd(2,3) == 1)，
 *      所以节点 0 是最近的符合要求的祖先节点。
 * - 节点 3 有两个祖先节点，分别是节点 1 和节点 0 。它与节点 1 互质（gcd(3,2) == 1），所以节点 1 是离它最近的符合要求的祖先节点。
 *
 * 示例 2：
 * 输入：nums = [5,6,10,2,3,6,15], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[2,6]]
 * 输出：[-1,0,-1,0,0,0,-1]
 *
 *
 * 提示：
 *  nums.length == n
 *  1 <= nums[i] <= 50
 *  1 <= n <= 10^5
 *  edges.length == n - 1
 *  edges[j].length == 2
 *  0 <= u_j, v_j < n
 *  u_j != v_j
 * */
public class LeetCode_1766 {
    // graph中记录的每个节点对应的子节点
    Map<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();

    // map中记录1到50所有数字的最大公约数
    Map<Integer, Set<Integer>> map = new HashMap<Integer, Set<Integer>>();
    int[] ans = null;

    public int[] getCoprimes(int[] nums, int[][] edges) {
        int n = nums.length;
        ans = new int[n];
        Arrays.fill(ans, -1);

        for(int i = 0; i <= 50; i++){
            map.put(i, new HashSet<Integer>());
        }

        for(int i = 1; i <= 50; i++){
            for(int j = 1; j <= i; j++){
                if(gcd(i, j) == 1){
                    map.get(i).add(j);
                    map.get(j).add(i);
                }
            }
        }

        for(int[] edge : edges){
            graph.putIfAbsent(edge[0], new ArrayList<Integer>());
            graph.putIfAbsent(edge[1], new ArrayList<Integer>());
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        dfs(nums, 0, -1, new HashMap<Integer, Integer>());
        return ans;
    }

    /**
     * record 存储和nums[u]相关的最大公约数
     * */
    void dfs(int[] nums, int u, int fa, HashMap<Integer, Integer> record){
        Integer recordU = record.getOrDefault(nums[u], null);
        if(null != recordU){
            ans[u] = record.get(nums[u]);
        }

        HashMap<Integer, Integer> newMap = new HashMap<Integer, Integer>(record);
        for(int num : map.get(nums[u])){
            newMap.put(num, u);
        }

        List<Integer> path = graph.getOrDefault(u, null);
        if(null != path){
            for(int v : path){
                if(v != fa){
                    dfs(nums, v, u, newMap);
                }
            }
        }
    }

    private int gcd(int x, int y){
        int result = 0;
        while(y != 0){
            result = x % y;
            x = y;
            y = result;
        }
        return x;
    }

    public static void main(String[] args){
        /*int[] nums = {5,6,10,2,3,6,15};
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{2,6}};*/


        int[] nums = {2,3,3,2};
        int[][] edges = {{0,1},{1,2},{1,3}};

        LeetCode_1766 solution = new LeetCode_1766();
        int[] ans = solution.getCoprimes(nums, edges);
        ArrayUtil.printArray(ans);
    }
}
