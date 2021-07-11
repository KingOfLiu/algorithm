package leetCode.rolling_hash;


import leetCode_contest.weekly_contest_248.Contest_1923;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 1923. 最长公共子路径
 * 一个国家由 n 个编号为 0 到 n - 1 的城市组成。在这个国家里，每两个 城市之间都有一条道路连接。
 * 总共有 m 个编号为 0 到 m - 1 的朋友想在这个国家旅游。他们每一个人的路径都会包含一些城市。每
 * 条路径都由一个整数数组表示，每个整数数组表示一个朋友按顺序访问过的城市序列。同一个城市在一条路
 * 径中可能 重复 出现，但同一个城市在一条路径中不会连续出现。
 * 给你一个整数 n 和二维数组 paths ，其中 paths[i] 是一个整数数组，表示第 i 个朋友走过的路径，
 * 请你返回 每一个 朋友都走过的 最长公共子路径 的长度，如果不存在公共子路径，请你返回 0 。
 * 一个 子路径 指的是一条路径中连续的城市序列。
 *
 * 示例 1：
 * 输入：n = 5, paths = [[0,1,2,3,4],
 *                      [2,3,4],
 *                      [4,0,1,2,3]]
 * 输出：2
 * 解释：最长公共子路径为 [2,3] 。
 *
 * 示例 2：
 * 输入：n = 3, paths = [[0],[1],[2]]
 * 输出：0
 * 解释：三条路径没有公共子路径。
 *
 * 示例 3：
 * 输入：n = 5, paths = [[0,1,2,3,4],
 *                      [4,3,2,1,0]]
 * 输出：1
 * 解释：最长公共子路径为 [0]，[1]，[2]，[3] 和 [4] 。它们长度都为 1 。
 *
 *
 * 提示：
 *  - 1 <= n <= 10^5
 *  - m == paths.length
 *  - 2 <= m <= 10^5
 *  - sum(paths[i].length) <= 10^5
 *  - 0 <= paths[i][j] < n
 *  - paths[i] 中同一个城市不会连续重复出现。
 * */
public class LeetCode_1923 {
    Logger logger = LoggerFactory.getLogger(getClass());

    public int longestCommonSubpath(int n, int[][] paths) {
        int minLen = Integer.MAX_VALUE;
        for(int[] path : paths){
            minLen = Math.min(minLen, path.length);
        }

        //long start = System.currentTimeMillis();

        int ans = 0;
        int left = 1, right = minLen;
        while(left <= right){
            int midLen = left + (right - left) / 2;
            if(check(paths, midLen)){
                ans = midLen;
                left = midLen + 1;
            } else {
                right = midLen - 1;
            }
        }

        //long end = System.currentTimeMillis();
        //System.out.println("time:" + (end - start));
        return ans;
    }

    boolean check(int[][] paths, int midLen){
        final int mappingSize = (int) (1e6);

        final long mod0 = (int)(1e9 + 7);
        final long mod1 = (int)(1e9 + 9);

        int minBase = (int)(1e6), maxBase = (int)(1e7);
        //int base0 = getRandom(minBase, maxBase), base1 = getRandom(minBase, maxBase);
        //int base0 = 1333331, base1 = 1333337;
        int base0 = 10, base1 = 10;

        long pow0 = 1, pow1 = 1;
        for(int i = 1; i <= midLen; i++){
            pow0 = pow0 * base0 % mod0;
            pow1 = pow1 * base1 % mod1;
        }

        int m = paths.length;
        Map<Long, ArrayList<Integer>> hashSet0 = new HashMap<Long, ArrayList<Integer>>();
        Map<Long, ArrayList<Integer>> hashSet1 = new HashMap<Long, ArrayList<Integer>>();
        int cnt = 0;
        for(int i = 0; i < m; i++){
            int curLen = paths[i].length;
            long hash0 = 0, hash1 = 0;

            for(int j = 0; j < midLen; j++){
                long curVal = paths[i][j];
                hash0 = (hash0 * base0 % mod0 + curVal % mod0) % mod0;
                hash1 = (hash1 * base1 % mod1 + curVal % mod1) % mod1;
            }

            ArrayList<Integer> set0 = hashSet0.getOrDefault(hash0, new ArrayList<Integer>());
            if(set0.size() > 0 && set0.get(set0.size() - 1) != i){
                set0.add(i);
            } else if(set0.isEmpty()){
                set0.add(i);
            }
            hashSet0.put(hash0, set0);

            ArrayList<Integer> set1 = hashSet1.getOrDefault(hash1, new ArrayList<Integer>());
            if(set1.size() > 0 && set1.get(set1.size() - 1) != i){
                set1.add(i);
            } else if(set1.isEmpty()){
                set1.add(i);
            }
            hashSet1.put(hash1, set1);

            if(set0.size() == m && set1.size() == m){
                return true;
            }

            for(int j = midLen; j < curLen; j++){
                long curVal = paths[i][j];
                long prevVal = paths[i][j - midLen];

                long newHashBase0 = hash0 * base0 ;
                long newHash0 = (newHashBase0 + curVal), oldHash0 = (prevVal * pow0 % mod0);
                hash0 = (newHash0 - oldHash0) % mod0;

                //logger.debug("newHashBase0 = {}, newHash0 = {}, oldHash0 = {}, hash0 = {}", new Object[]{newHashBase0, newHash0, oldHash0, hash0});

                long newHashBase1 = hash1 * base1 ;
                long newHash1 = (newHashBase1 + curVal), oldHash1 =  (prevVal * pow1 % mod1);
                hash1 = (newHash1 - oldHash1) % mod1;

                ArrayList<Integer> newSet0 = hashSet0.getOrDefault(hash0, new ArrayList<Integer>());
                if(newSet0.size() > 0 && newSet0.get(newSet0.size() - 1) != i){
                    newSet0.add(i);
                } else if(newSet0.isEmpty()){
                    newSet0.add(i);
                }
                hashSet0.put(hash0, newSet0);

                ArrayList<Integer> newSet1 = hashSet1.getOrDefault(hash1, new ArrayList<Integer>());
                if(newSet1.size() > 0 && newSet1.get(newSet1.size() - 1) != i){
                    newSet1.add(i);
                } else if(newSet1.isEmpty()){
                    newSet1.add(i);
                }
                hashSet1.put(hash1, newSet1);

                if(newSet0.size() == m && newSet1.size() == m){
                    return true;
                }
            }
        }
        //logger.debug("cnt = {}", cnt);
        return false;
    }

    public static void main(String[] args){
        /*int n = 5;
        int[][] path = {
                {Integer.MAX_VALUE - 0, Integer.MAX_VALUE - 1 , Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 5},
                {Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 3, Integer.MAX_VALUE - 4},
                {Integer.MAX_VALUE - 4, Integer.MAX_VALUE - 0, Integer.MAX_VALUE - 1, Integer.MAX_VALUE - 2, Integer.MAX_VALUE - 3}
        };*/
        int n = 5;
        int[][] path = {
                {0,1,2,3,4},{2,3,4},{4,0,1,2,3}
        };

        LeetCode_1923 solution = new LeetCode_1923();
        int ans = solution.longestCommonSubpath(n, path);
        System.out.println(ans);
        /*long start = System.currentTimeMillis();

        for(int i = 0; i < (int)1e5; i++){
            LeetCode_1923 solution = new LeetCode_1923();
            int ans = solution.longestCommonSubpath(n, path);
            //System.out.println(ans);

        }

        long end = System.currentTimeMillis();
        System.out.println("time : " + (end - start));*/
    }
}
