// LeetCode Hard - 2435



// Approach 1 - DFS
// T.C. - O(2^(m+n))
// S.C. - O(m+n) // recursion depth
class Solution {
    int mod = (int) 1e9 + 7;

    public int getPaths(int i, int j, int currRem, int[][] grid, int k, int m, int n){
        if(i == m || j == n){
            return 0;
        }

        if(i == m-1 && j == n-1){
            int rem = (currRem + grid[i][j]) % k;

            return rem == 0 ? 1 : 0;
        }

        int newRem = (currRem + grid[i][j]) % k;

        int right = getPaths(i+1, j, newRem, grid, k, m, n);
        int down =  getPaths(i, j+1, newRem, grid, k, m, n);

        return (right + down) % mod;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        return getPaths(0, 0, 0, grid, k, m, n);
    }
}






// Approach 2 - DP (Memoization)
// T.C. - O(m*n*k)
// S.C. - O(m*n*k)
class Solution {
    int mod = (int) 1e9 + 7;

    public int getPaths(int i, int j, int currRem, int[][] grid, int k, int m, int n, int[][][] dp){
        if(i == m || j == n){
            return 0;
        }

        if(dp[i][j][currRem] != -1){
            return dp[i][j][currRem];
        }

        if(i == m-1 && j == n-1){
            int rem = (currRem + grid[i][j]) % k;

            return rem == 0 ? 1 : 0;
        }

        int newRem = (currRem + grid[i][j]) % k;

        int right = getPaths(i+1, j, newRem, grid, k, m, n, dp);
        int down =  getPaths(i, j+1, newRem, grid, k, m, n, dp);

        return dp[i][j][currRem] = (right + down) % mod;
    }

    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[][][] dp = new int[m+1][n+1][k];

        for(int[][] mat : dp){
            for(int[] row : mat){
                Arrays.fill(row, -1);
            }
        }

        return getPaths(0, 0, 0, grid, k, m, n, dp);
    }
}





// Approach 3 - DP (Bottom Up)
// T.C. - O(m*n*k)
// S.C. - O(m*n*k)
class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int mod = (int) 1e9 + 7;

        int[][][] dp = new int[m+1][n+1][k];

        // base case same as recursion
        for(int rem = 0; rem < k; rem++){
            dp[m-1][n-1][rem] = ((rem + grid[m-1][n-1]) % k == 0) ? 1 : 0;
        }

        for(int i = m-1; i>=0; i--){
            for(int j = n-1; j>=0; j--){
                for(int rem = 0; rem < k; rem++){
                    if(i == m-1 && j == n-1){
                        continue;
                    }

                    int currRem = (rem + grid[i][j]) % k;

                    int right = dp[i+1][j][currRem];
                    int down = dp[i][j+1][currRem];

                    dp[i][j][rem] = (right + down) % mod;
                }
            }
        }

        return dp[0][0][0];
    }
}