package leetCode.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1792. 最大平均通过率
 *
 * 示例 1:
 * 输入：classes = [[1,2],[3,5],[2,2]], extraStudents = 2
 * 输出：0.78333
 * 解释：你可以将额外的两个学生都安排到第一个班级，平均通过率为 (3/4 + 3/5 + 2/2) / 3 = 0.78333 。
 *
 * 示例 2：
 * 输入：classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
 * 输出：0.53485
 * */
public class LeetCode_1792 {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        int n = classes.length;

        PriorityQueue<double[]> pq = new PriorityQueue<double[]>(new Comparator<double[]>(){
            @Override
            public int compare(double[] p1, double[] p2){
                /*double avg1 = p1[0] / p1[1];
                double avg2 = p2[0] / p2[1];
                return Double.compare(avg1, avg2);*/
                double x = ((p2[0] + 1) / (p2[1] + 1) - p2[0] / p2[1]);
                double y = ((p1[0] + 1) / (p1[1] + 1) - p1[0] / p1[1]);
                return Double.compare(x, y);
            }
        });

        for(int i = 0; i < n; i++){
            double[] cls = new double[2];
            cls[0] = (double) classes[i][0];
            cls[1] = (double) classes[i][1];

            pq.offer(cls);
        }

        while(extraStudents > 0){
            double[] d = pq.poll();
            d[0] += 1.0;
            d[1] += 1.0;
            pq.offer(d);

            extraStudents -= 1;
        }

        double ans = 0.0;
        while(!pq.isEmpty()){
            double[] d = pq.poll();

            double avg = d[0] / d[1];
            ans += (avg);
        }

        return ans / n;
    }

    public static void main(String[] args){
        int[][] classes = {{1,2},{3,5},{2,2}};
        int extraStudents = 2;
        /*int[][] classes = {{2,4},{3,9},{4,5},{2,10}};
        int extraStudents = 4;*/

        LeetCode_1792 solution = new LeetCode_1792();
        double ans = solution.maxAverageRatio(classes, extraStudents);
        System.out.println(ans);
    }
}
