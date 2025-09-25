// LeetCode - 120




// Approach 1 - Recursion + Memoization
// T.C. - O(n^2)
// S.C. - O(n^2)

class Solution {
    public int getMinPathSum(int row, int col, List<List<Integer>> grid, int m, int[][] dp){
        if(row == m-1){
            return grid.get(row).get(col);
        }

        if(dp[row][col] != Integer.MAX_VALUE){
            return dp[row][col];
        }

        int path1 =  getMinPathSum(row+1, col, grid, m, dp);
        int path2 = getMinPathSum(row+1, col+1, grid, m, dp);

        return dp[row][col] = grid.get(row).get(col) + Math.min(path1, path2);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();

        // taking it as square matrix
        int[][] dp = new int[m][m];

        for(int[] row : dp){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        return getMinPathSum(0, 0, triangle, m, dp);
    }
}





// Approach 2 - Bottom Up
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // state : min path sum from row, col to bottom row of triangle
        int[][] dp = new int[n][n];

        // copying the elements
        for(int i = 0; i<n; i++){
            for(int j = 0; j<=i; j++){
                dp[i][j] = triangle.get(i).get(j);
            }
        }

        for(int row = n-2; row >= 0; row--){
            for(int col = 0; col <= row; col++){
                dp[row][col] = dp[row][col] + Math.min(dp[row+1][col], dp[row+1][col+1]);
            }
        }

        // best min answer will be stored in dp[0][0]
        return dp[0][0];
    }
}





// Approach 3 - Bottom Up + Space Optimasation
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // only initialize the last row, it will work as row+1
        int[] dp = new int[n];

        // copying the elements
        for(int i = 0; i<n; i++){
            dp[i] = triangle.get(n-1).get(i);
        }

        for(int row = n-2; row >= 0; row--){
            for(int col = 0; col <= row; col++){
                dp[col] = triangle.get(row).get(col) + Math.min(dp[col], dp[col+1]);
            }
        }

        // best min answer will be stored in dp[0][0]
        return dp[0];
    }
}




// Approach 4 - Bottom Up with reverse direction (top to bottom)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // only initialize the last row, it will work as row+1
        int[] dp = new int[n];

        dp[0] = triangle.get(0).get(0);

        for(int row = 1; row < n; row++){

            // go right -> left to avoid overwriting previous values
            for(int col = row; col >= 0; col--){
                if(col == 0){
                    dp[col] = dp[col] + triangle.get(row).get(col);
                }
                else if(row == col){
                    dp[col] = dp[col - 1] + triangle.get(row).get(col);
                }
                else{
                    dp[col] = Math.min(dp[col], dp[col - 1]) + triangle.get(row).get(col);
                }
            }
        }

        int minPathSum = dp[0];
        
        for(int val : dp){
            minPathSum = Math.min(minPathSum, val);
        }

        return minPathSum;
    }
}