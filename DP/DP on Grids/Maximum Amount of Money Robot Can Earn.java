// LeetCode Medium - 3418



// Approach 1 - Recursion
// T.C. - O(2^(m+n))
// S.C. - O(m+n)
class Solution {
    public int solve(int i, int j, int[][] coins, int neutralise, int m, int n){
        if(i >= m || j >= n){
            return Integer.MIN_VALUE;
        }

        if( i == m-1 && j == n-1){
            if( coins[i][j] < 0 && neutralise > 0){
                return Math.max(0, coins[i][j]);
            }

            return coins[i][j];
        }

        int currCoins = coins[i][j];

        if(currCoins < 0 && neutralise > 0){
            // in case of neutralizing
            int right1 = solve(i, j+1, coins, neutralise - 1, m, n);
            int down1 = solve(i+1, j, coins, neutralise - 1, m, n);
            int final1 = Math.max(right1, down1);
            
            
            // in case of not neutralizing
            int right2 = solve(i, j+1, coins, neutralise, m, n);
            int down2 = solve(i+1, j, coins, neutralise, m, n);
            int final2 = coins[i][j] + Math.max(right2, down2);

            return Math.max(final1, final2);
        }
        
        // rest of the cases
        int right = solve(i, j+1, coins, neutralise, m, n);
        int down = solve(i+1, j, coins, neutralise, m, n);

        int best = Math.max(right, down);

        if(best == Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return currCoins + best;
    }

    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        return solve(0, 0, coins, 2, m, n);
    }
}





// Approach 2 - DP (Memoization)
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public int solve(int i, int j, int[][] coins, int neutralise, int m, int n, int[][][] dp){
        if(i >= m || j >= n){
            return Integer.MIN_VALUE;
        }

        if(dp[i][j][neutralise] != Integer.MIN_VALUE){
            return dp[i][j][neutralise];
        }

        if( i == m-1 && j == n-1){
            if( coins[i][j] < 0 && neutralise > 0){
                return dp[i][j][neutralise] =  Math.max(0, coins[i][j]);
            }

            return dp[i][j][neutralise] =  coins[i][j];
        }

        int currCoins = coins[i][j];

        if(currCoins < 0 && neutralise > 0){
            // in case of neutralizing
            int right1 = solve(i, j+1, coins, neutralise - 1, m, n, dp);
            int down1 = solve(i+1, j, coins, neutralise - 1, m, n, dp);
            int final1 = Math.max(right1, down1);
            int best1 = (final1 == Integer.MIN_VALUE) ? Integer.MIN_VALUE : final1;
            
            
            // in case of not neutralizing
            int right2 = solve(i, j+1, coins, neutralise, m, n, dp);
            int down2 = solve(i+1, j, coins, neutralise, m, n, dp);
            int final2 = coins[i][j] + Math.max(right2, down2);
            int best2 = (final2 == Integer.MIN_VALUE) ? Integer.MIN_VALUE : final2;

            return dp[i][j][neutralise] = Math.max(best1, best2);
        }
        
        // rest of the cases
        int right = solve(i, j+1, coins, neutralise, m, n, dp);
        int down = solve(i+1, j, coins, neutralise, m, n, dp);

        int best = Math.max(right, down);

        if(best == Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }

        return dp[i][j][neutralise] = currCoins + best;
    }

    public int maximumAmount(int[][] coins) {
        int m = coins.length;
        int n = coins[0].length;

        int[][][] dp = new int[m+1][n+1][3];

        for(int[][] mat : dp){
            for(int[] row : mat){
                Arrays.fill(row, Integer.MIN_VALUE);
            }
        }

        return solve(0, 0, coins, 2, m, n, dp);
    }
}







// Approach 3 - DP (Memoization - Optimised)
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int m, n;
    int[][][] dp;

    int solve(int[][] coins, int i, int j, int neu) {

        // Out of bounds
        if (i >= m || j >= n) return Integer.MIN_VALUE;

        // Base case
        if (i == m - 1 && j == n - 1) {
            if (coins[i][j] < 0 && neu > 0) return 0;
            return coins[i][j];
        }

        // Memo
        if (dp[i][j][neu] != Integer.MIN_VALUE) {
            return dp[i][j][neu];
        }

        int best = Integer.MIN_VALUE;

        // Move Down
        int down = solve(coins, i + 1, j, neu);
        if (down != Integer.MIN_VALUE) {
            best = Math.max(best, coins[i][j] + down);
        }

        // Move Right
        int right = solve(coins, i, j + 1, neu);
        if (right != Integer.MIN_VALUE) {
            best = Math.max(best, coins[i][j] + right);
        }

        // Neutralize (skip negative)
        if (coins[i][j] < 0 && neu > 0) {
            int downSkip = solve(coins, i + 1, j, neu - 1);
            int rightSkip = solve(coins, i, j + 1, neu - 1);

            int skipBest = Math.max(downSkip, rightSkip);
            if (skipBest != Integer.MIN_VALUE) {
                best = Math.max(best, skipBest);
            }
        }

        return dp[i][j][neu] = best;
    }

    public int maximumAmount(int[][] coins) {
        m = coins.length;
        n = coins[0].length;

        dp = new int[m][n][3];

        // Initialize with MIN_VALUE
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < 3; k++) {
                    dp[i][j][k] = Integer.MIN_VALUE;
                }
            }
        }

        return solve(coins, 0, 0, 2);
    }
}