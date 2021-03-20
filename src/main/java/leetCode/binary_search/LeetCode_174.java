package leetCode.binary_search;

/**
 * 174. 地下城游戏
 * 一些恶魔抓住了公主（P）并将她关在了地下城的右下角。地下城是由 M x N 个房间组成的二维网格。我们英勇的骑士（K）最初被安置在左上角的房间里，他必须穿过地下城并通过对抗恶魔来拯救公主。
 *
 * 骑士的初始健康点数为一个正整数。如果他的健康点数在某一时刻降至 0 或以下，他会立即死亡。
 *
 * 有些房间由恶魔守卫，因此骑士在进入这些房间时会失去健康点数（若房间里的值为负整数，则表示骑士将损失健康点数）；其他房间要么是空的（房间里的值为 0），要么包含增加骑士健康点数的魔法球（若房间里的值为正整数，则表示骑士将增加健康点数）。
 *
 * 为了尽快到达公主，骑士决定每次只向右或向下移动一步。
 *
 *  
 *
 * 编写一个函数来计算确保骑士能够拯救到公主所需的最低初始健康点数。
 *
 * 例如，考虑到如下布局的地下城，如果骑士遵循最佳路径 右 -> 右 -> 下 -> 下，则骑士的初始健康点数至少为 7。
 *
 * 说明:
 *
 * 骑士的健康点数没有上限。
 * 任何房间都可能对骑士的健康点数造成威胁，也可能增加骑士的健康点数，包括骑士进入的左上角房间以及公主被监禁的右下角房间。
 * */
public class LeetCode_174 {
    public int calculateMinimumHP(int[][] dungeon) {
        int left = 1, right = Integer.MAX_VALUE;
        while(left < right){
            int initHp = left + (right - left) / 2;
            if(isReachable(dungeon, initHp)){
                right = initHp;
            } else {
                left = initHp + 1;
            }
        }
        return left;
    }

    private boolean isReachable(int[][] dungeon, int initialValue){
        int n = dungeon.length, m = dungeon[0].length;
        int[][] matrix = new int[n][m];
        matrix[0][0] = dungeon[0][0] + initialValue;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0){
                    matrix[0][0] = dungeon[0][0] + initialValue;
                    continue;
                }

                if(j > 0 && matrix[i][j - 1] > 0){
                    matrix[i][j] = Math.max(matrix[i][j], matrix[i][j - 1] + dungeon[i][j]);
                }

                if(i > 0 && matrix[i - 1][j] > 0){
                    matrix[i][j] = Math.max(matrix[i][j], matrix[i - 1][j] + dungeon[i][j]);
                }
            }
        }

        System.out.print(initialValue + " = ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();

        return matrix[n - 1][m - 1] > 0;
    }

    public static void main(String[] args){
        int[][] dungeon = {{-2,-3,3}, {-5,-10,1}, {10,30,-5}};
        LeetCode_174 solution = new LeetCode_174();
        int ans = solution.calculateMinimumHP(dungeon);
        System.out.println(ans);
    }
}
