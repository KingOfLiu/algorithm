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
        int count;
        TrieNode[] child = new TrieNode[2];
    }

    TrieNode root = new TrieNode();

    private void insert(int num){
        TrieNode node = root;
        int highBit = 15;
        for(int i = highBit; i >= 0; i--){
            int index = (num >>> i) & 1;
            if(node.child[index] == null){
                node.child[index] = new TrieNode();
            }
            node = node.child[index];
            node.count++;
        }
    }

    private int smaller(int num, int low){
        TrieNode node = root;

        int cnt = 0;
        int highBit = 15;
        for(int i = highBit; i >= 0; i--){
            if(node == null){
                return cnt;
            }
            int indexNum = (num >>> i) & 1;
            int indexLow = (low >>> i) & 1;

            if(indexLow == 1){
                if(node.child[indexNum] != null){
                    cnt += node.child[indexNum].count;
                }
                node = node.child[1 - indexNum];
            } else {
                node = node.child[indexNum];
            }
        }

        return cnt;
    }

    public int countPairs(int[] nums, int low, int high) {
        int ans = 0;
        for(int num : nums){
            ans += smaller(num, high + 1) -  smaller(num, low);
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