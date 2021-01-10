package leetCode.array;

/**
 * 接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * */
public class LeetCode_42 {
    public int trapViolence(int[] height){
        int len = height.length;
        if(len == 0){
            return 0;
        }

        int ans = 0;
        for(int i = 1; i < len; i++){
            int curNums = height[i];
            int maxLeft = 0;
            int maxRight = 0;
            // 向左找当前元素最大值
            for(int j = i; j >= 0; j--){
                maxLeft = Math.max(maxLeft, height[j]);
            }

            for(int k = i; k < len; k++){
                maxRight = Math. max(maxRight, height[k]);
            }

            ans += ((Math.min(maxLeft, maxRight) - curNums));
        }
        return ans;
    }

    public int trapDp(int[] height){
        int len = height.length;
        if(len == 0){
            return 0;
        }

        int[] maxLeft = new int[len];
        int[] maxRight = new int[len];
        maxLeft[0] = height[0];
        maxRight[len - 1] = height[len - 1];
        for(int i = 1; i < len; i++){
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
        }

        for(int i = len - 2; i >= 0; i--){
            maxRight[i] = Math.max(maxRight[i + 1], height[i]);
        }

        int ans = 0;
        for(int i = 1; i < len; i++){
            ans += Math.min(maxLeft[i], maxRight[i]) - height[i];
        }
        return ans;
    }

    public static void main(String[] args){
        //int[] height = {4,2,0,3,2,5};
        //int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] height = {6, 7, 5,2,4,5,9,3};
        LeetCode_42 solution = new LeetCode_42();
        int ans = solution.trapDp(height);
        System.out.println(ans);
    }
}
