// LeetCode Medium - 3665



// Approach 1 - DP (Memoization)
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m;
    int n;
    int mod = (int) 1e9 + 7;
    int left = 0; // Arrived from left to curr cell (it was moving right)
    int up = 1; // Arrived from up to curr cell (it was moving down)

    public int solve(int i, int j, int[][] grid, int cameFrom, int[][][] dp){
        if(i >= m || j >= n){
            return 0;
        }

        if(i == m-1 && j == n-1){
            return 1; // found one path
        }

        if(dp[i][j][cameFrom] != -1){
            return dp[i][j][cameFrom];
        }

        // grid[i][j] = 0;
        int total = 0;
        total = (total + solve(i, j+1, grid, left, dp)) % mod;
        total = (total + solve(i+1, j, grid, up, dp)) % mod;

        if(grid[i][j] == 1){
            if(cameFrom == left){ // came from left, have to deflect it to down
                return dp[i][j][cameFrom] = solve(i+1, j, grid, up, dp); // going to down from up
            }
            else{
                return dp[i][j][cameFrom] = solve(i,j+1, grid, left, dp); // going to right from left
            }
        }

        return dp[i][j][cameFrom] = total % mod;
    }

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][][] dp = new int[m][n][2];

        for(int[][] mat : dp){
            for(int[] row : mat){
                Arrays.fill(row, -1);
            }
        }

        return solve(0, 0, grid, 0, dp);
    }
}







// Approach 2 - DP (Bottom Up)
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m;
    int n;
    int mod = (int) 1e9 + 7;
    int left = 0; // Arrived from left to curr cell (it was moving right)
    int up = 1; // Arrived from up to curr cell (it was moving down)

    public int uniquePaths(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        // dp[row][col][LEFT] = ways to reach this cell by entering from the left
        // dp[row][col][UP]   = ways to reach this cell by entering from above
        int[][][] dp = new int[m][n][2];
        dp[0][0][left] = 1;

        // filling 0th row
        for(int col = 1; col < n; col++){
            dp[0][col][left] = 1;

            // if a cell has 1, we'll never be able to move right
            if(grid[0][col] == 1){
                break;
            }
        }

        // filling 0th col
        for(int row = 1; row < m; row++){
            dp[row][0][up] = 1;

            // if a cell has 1, we'll never be able to move down
            if(grid[row][0] == 1){
                break;
            }
        }

        // filling the rest
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {

                // Entering from the left neighbour (row, col-1)
                if (grid[row][col - 1] == 1)
                    dp[row][col][left] = dp[row][col - 1][up]; // mirror: only UP-entrants turn right
                else
                    dp[row][col][left] = (dp[row][col - 1][left] + dp[row][col - 1][up]) % mod;

                // Entering from the top neighbour (row-1, col)
                if (grid[row - 1][col] == 1)
                    dp[row][col][up] = dp[row - 1][col][left]; // mirror: only left-entrants turn down
                else
                    dp[row][col][up] = (dp[row - 1][col][left] + dp[row - 1][col][up]) % mod;
            }
        }

        return (dp[m - 1][n - 1][left] + dp[m - 1][n - 1][up]) % mod;
    }
}