// LeetCode Medium - 1594



// Approach 1 - Recursion (TLE)
// T.C. - O(2^(m+n))
// S.C. - O(m+n)
class Solution {
    public long[] getMaxPathProduct(int i, int j, int[][] grid, int m, int n){
        if(i == m-1 && j == n-1){
            return new long[]{grid[i][j], grid[i][j]};
        }

        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;

        if(i+1 < m){
            long[] down = getMaxPathProduct(i+1, j, grid, m, n);
            maxVal = Math.max(maxVal, Math.max(down[0] * grid[i][j], down[1] * grid[i][j]));
            minVal = Math.min(minVal, Math.min(down[0] * grid[i][j], down[1] * grid[i][j]));
        }

        if(j+1 < n){
            long[] right = getMaxPathProduct(i, j+1, grid, m, n);
            maxVal = Math.max(maxVal, Math.max(right[0] * grid[i][j], right[1] * grid[i][j]));
            minVal = Math.min(minVal, Math.min(right[0] * grid[i][j], right[1] * grid[i][j]));
        }

        return new long[]{maxVal, minVal};
    }

    public int maxProductPath(int[][] grid) {
        int mod = (int) 1e9 + 7;
        int m = grid.length;
        int n = grid[0].length;

        long[] result = getMaxPathProduct(0, 0, grid, m, n);

        return (result[0] < 0) ? -1 : (int) (result[0] % mod);
    }
}





// Approach 2 - Recursion + Memoization
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public long[] getMaxPathProduct(int i, int j, int[][] grid, int m, int n, Long[][][] dp){
        if(i == m-1 && j == n-1){
            return new long[]{grid[i][j], grid[i][j]};
        }

        if(dp[i][j][0] != null){
            return new long[]{dp[i][j][0], dp[i][j][1]};
        }

        long maxVal = Long.MIN_VALUE;
        long minVal = Long.MAX_VALUE;

        if(i+1 < m){
            long[] down = getMaxPathProduct(i+1, j, grid, m, n, dp);
            long x = down[0] * grid[i][j];
            long y = down[1] * grid[i][j];

            maxVal = Math.max(maxVal, Math.max(x, y));
            minVal = Math.min(minVal, Math.min(x, y));
        }

        if(j+1 < n){
            long[] right = getMaxPathProduct(i, j+1, grid, m, n, dp);
            long x = right[0] * grid[i][j];
            long y = right[1] * grid[i][j];

            maxVal = Math.max(maxVal, Math.max(x, y));
            minVal = Math.min(minVal, Math.min(x, y));
        }

        dp[i][j][0] = maxVal;
        dp[i][j][1] = minVal;

        return new long[]{maxVal, minVal};
    }

    public int maxProductPath(int[][] grid) {
        int mod = (int) 1e9 + 7;
        int m = grid.length;
        int n = grid[0].length;

        Long[][][] dp = new Long[m][n][2];
        for(Long[][] mat : dp){
            for(Long[] row : mat){
                Arrays.fill(row, null);
            }
        }


        long[] result = getMaxPathProduct(0, 0, grid, m, n, dp);

        return (result[0] < 0) ? -1 : (int) (result[0] % mod);
    }
}





// Approach 3 - Bottom-Up DP
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public int maxProductPath(int[][] grid) {
        int mod = (int) 1e9 + 7;
        int m = grid.length;
        int n = grid[0].length;

        // max & min product from (0, 0) to (i, j)
        Long[][][] dp = new Long[m][n][2];
        dp[0][0][0] = (long) grid[0][0];
        dp[0][0][1] = (long) grid[0][0];

        // filling base case (1st row)
        for(int i = 1; i<n; i++){
            dp[0][i][0] = dp[0][i-1][0] * grid[0][i];
            dp[0][i][1] = dp[0][i-1][1] * grid[0][i];
        }

        // filling base case (1st col)
        for(int i = 1; i<m; i++){
            dp[i][0][0] = dp[i-1][0][0] * grid[i][0];
            dp[i][0][1] = dp[i-1][0][1] * grid[i][0];
        }
        
        for(int i = 1; i<m; i++){
            for(int j = 1; j<n; j++){
                long upMax = dp[i-1][j][0];
                long upMin = dp[i-1][j][1];

                long leftMax = dp[i][j-1][0];
                long leftMin = dp[i][j-1][1];

                long maxVal = Long.MIN_VALUE;
                long minVal = Long.MAX_VALUE;

                long x = grid[i][j];
                long y = grid[i][j];
                maxVal = Math.max(maxVal, Math.max(upMax * x, upMin * y));
                minVal = Math.min(minVal, Math.min(upMax * x, upMin * y));

                maxVal = Math.max(maxVal, Math.max(leftMax * x, leftMin * y));
                minVal = Math.min(minVal, Math.min(leftMax * x, leftMin * y));

                dp[i][j][0] = maxVal;
                dp[i][j][1] = minVal;
            }
        }

        return (dp[m-1][n-1][0] < 0) ? -1 : (int) (dp[m-1][n-1][0] % mod);
    }
}