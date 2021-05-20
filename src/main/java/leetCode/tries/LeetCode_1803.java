package leetCode.tries;

/**
 * 1803. 统计异或值在范围内的数对有多少
 * 给你一个整数数组 nums （下标 从 0 开始 计数）以及两个整数：low 和 high ，
 * 请返回 漂亮数对 的数目。
 * 漂亮数对 是一个形如 (i, j) 的数对，
 * 其中 0 <= i < j < nums.length 且 low <= (nums[i] XOR nums[j]) <= high 。
 *
 * 示例 1：
 * 输入：nums = [1,4,2,7], low = 2, high = 6
 * 输出：6
 * 解释：所有漂亮数对 (i, j) 列出如下：
 *     - (0, 1): nums[0] XOR nums[1] = 5
 *     - (0, 2): nums[0] XOR nums[2] = 3
 *     - (0, 3): nums[0] XOR nums[3] = 6
 *     - (1, 2): nums[1] XOR nums[2] = 6
 *     - (1, 3): nums[1] XOR nums[3] = 3
 *     - (2, 3): nums[2] XOR nums[3] = 5
 *
 * 示例 2：
 * 输入：nums = [9,8,4,2,1], low = 5, high = 14
 * 输出：8
 * 解释：所有漂亮数对 (i, j) 列出如下：
 * ​​​​​    - (0, 2): nums[0] XOR nums[2] = 13
 *     - (0, 3): nums[0] XOR nums[3] = 11
 *     - (0, 4): nums[0] XOR nums[4] = 8
 *     - (1, 2): nums[1] XOR nums[2] = 12
 *     - (1, 3): nums[1] XOR nums[3] = 10
 *     - (1, 4): nums[1] XOR nums[4] = 9
 *     - (2, 3): nums[2] XOR nums[3] = 6
 *     - (2, 4): nums[2] XOR nums[4] = 5
 *
 * 提示：
 *  1 <= nums.length <= 2 * 10^4
 *  1 <= nums[i] <= 2 * 10^4
 *  1 <= low <= high <= 2 * 10^4
 * */
public class LeetCode_1803 {
    class TrieNode{
        TrieNode[] child = new TrieNode[2];
        int cnt = 0;
    }

    TrieNode root = new TrieNode();

    private void insert(int num){
        TrieNode node = root;
        for(int i = 15; i >= 0; i--){
            int bit = (num >> i) & 1;
            if(node.child[bit] == null){
                node.child[bit] = new TrieNode();
            }
            node = node.child[bit];
            node.cnt++;
        }
    }

    private int search(int num, int limit){
        TrieNode node = root;
        int count = 0;
        for(int i = 15; i >= 0; i--){
            if(node == null){
                return count;
            }

            int bitNum = (num >> i ) & 1;
            int bitLimit = (limit >> i) & 1;
            if(bitLimit == 1){
                if(node.child[bitNum] != null){
                    count += node.child[bitNum].cnt;
                }
                node = node.child[1 - bitNum];
            } else {
                node = node.child[bitNum];
            }
        }

        return count;
    }

    public int countPairs(int[] nums, int low, int high) {
        int ans = 0;
        for(int num : nums){
            ans += search(num, high + 1) - search(num, low);
            insert(num);
        }
        return ans;
    }

    public static void main(String[] args){
        int[] nums = {9,8,4,2,1};
        int low = 5, high = 14;
        LeetCode_1803 solution = new LeetCode_1803();
        int ans = solution.countPairs(nums, low, high);
        System.out.println(ans);
    }
}