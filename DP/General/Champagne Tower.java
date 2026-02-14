// LeetCode Medium - 799



// Approach 1 - Recursion + Memoization
// T.C. - O(101 * 101)
// S.C. - O(101 * 101)
class Solution {
    public double solve(int poured, int i, int j, double[][] dp){
        if(i < 0 || j < 00 || i < j){
            return 0.0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        if(i == 0 && j == 0){ // reached the top glass
            return poured;
        }

        double upperLeft = (solve(poured, i-1, j-1, dp) - 1)/2.0;
        double upperRight = (solve(poured, i-1, j, dp) - 1)/2.0;

        if(upperLeft < 0){
            upperLeft = 0.0;
        }

        if(upperRight < 0){
            upperRight = 0.0;
        }

        return dp[i][j] = upperLeft + upperRight;
    }

    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];

        for(double[] row : dp){
            Arrays.fill(row, -1);
        }

        return Math.min(1.0, solve(poured, query_row, query_glass, dp));
    }
}





// Approach 2 - Bottom-Up
// T.C. - O(101 * 101)
// S.C. - O(101 * 101)
class Solution {
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = (double) poured;

        for(int row = 0; row <= query_row; row++){
            for(int col = 0; col < row+1; col++){
                double extra = (dp[row][col] - 1)/2.0;

                if(extra > 0){
                    dp[row+1][col] += extra;
                    dp[row+1][col+1] += extra;
                }
            }
        }

        return Math.min(1.0, dp[query_row][query_glass]);
    }
}