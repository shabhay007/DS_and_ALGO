// LeetCode - 3882



// Approach 1 - Recursion
// T.C. - O(2^(m+n))
// S.C. - O(m + n)
class Solution {
    int m;
    int n;
    int minXor = 1023;

    public void solve(int i, int j, int[][] grid, int currXor){
        if(i >= m || j >= n){
            return;
        }

        currXor ^= grid[i][j];

        if(i == m-1 && j == n-1){
            minXor = Math.min(minXor, currXor);
            return;
        }

        solve(i, j+1, grid, currXor); // right
        solve(i+1, j, grid, currXor); // down
    }

    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        solve(0, 0, grid, 0);

        return minXor;
    }
}





// Approach 2 - DP (Memoization)
// T.C. - O(m * n * 1024)
// S.C. - O(m * n * 1024)
class Solution {
    int m;
    int n;
    int minXor = Integer.MAX_VALUE;
    boolean[][][] dp;

    public void solve(int i, int j, int[][] grid, int currXor){
        if(i >= m || j >= n){
            return;
        }

        currXor ^= grid[i][j];

        if(dp[i][j][currXor]){
            return;
        }

        dp[i][j][currXor] = true; // visited

        if(i == m-1 && j == n-1){
            minXor = Math.min(minXor, currXor);
            return;
        }

        solve(i, j+1, grid, currXor); // right
        solve(i+1, j, grid, currXor); // down
    }

    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;
        dp = new boolean[m][n][1024];

        solve(0, 0, grid, 0);

        return minXor;
    }
}