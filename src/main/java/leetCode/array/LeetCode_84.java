package leetCode.array;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 *  柱状图中最大的矩形
 *  给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 *
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * */
public class LeetCode_84 {
    public int largestRectangleArea(int[] height){
        int len = height.length;
        if(len == 0){
            return 0;
        }

        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> stack = new ArrayDeque<Integer>();
        for(int i = 0; i < len; i++){
            while(!stack.isEmpty() && height[stack.peek()] >= height[i]){
                stack.pop();
            }
            left[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }
        stack.clear();

        for(int i = len - 1; i >= 0; i--){
            while(!stack.isEmpty() && height[stack.peek()] >= height[i]){
                stack.pop();
            }
            right[i] = (stack.isEmpty() ? -1 : stack.peek());
            stack.push(i);
        }

        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < len; i++){
            ans = Math.max(ans, (right[i] - left[i] - 1) * height[i]);
        }
        return ans;
    }

    public static void main(String[] args){
        //int[] heights = {2,1,5,6,2,3};
        int[] heights = {6,7,5,2,4,5,9,3};
        LeetCode_84 solution = new LeetCode_84();
        int ans = solution.largestRectangleArea(heights);
        System.out.println(ans);
    }
}
