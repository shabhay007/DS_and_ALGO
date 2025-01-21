// LeetCode Medium -2017


// Approach 1 - Prefix Sum
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public long rowSum(int[][] grid){
        long sum = 0;

        for(int i = 0; i < grid[0].length; i++){
            sum += grid[0][i];
        }

        return sum;
    }

    public long gridGame(int[][] grid) {
        int n = grid[0].length;
        long firstRowRemainingSum = rowSum(grid);
        long secondRowRemainingSum = 0;
        long minPointsForRobot2 = Long.MAX_VALUE;

        for(int robot1Col = 0; robot1Col < n; robot1Col++){
            firstRowRemainingSum -= grid[0][robot1Col];

            // robot 2 will try to do best after robot 1 has taken the above strategy
            long bestByRobot2 = Math.max(firstRowRemainingSum, secondRowRemainingSum);

            minPointsForRobot2 = Math.min(minPointsForRobot2, bestByRobot2);

            // available points in row 2
            secondRowRemainingSum += grid[1][robot1Col];
        }

        return minPointsForRobot2;
    }
}