// LeetCode Medium - 3573



// Approach 1 - Recursion + Memoization
// T.C. - O(n*k)
// S.C. - O(n*k)
class Solution {
    public long maxProfit(int i, int k, int stockCase, int[] prices, int n, long[][][] dp){
        if(i >= n){
            if(stockCase == 0) return 0; // transaction completed

            // in case of unfinished transaction, return invalid value
            return Long.MIN_VALUE/2;
        }

        if(dp[i][k][stockCase] != Long.MIN_VALUE){
            return dp[i][k][stockCase];
        }

        // passing 0 for case as we haven't started transaction
        long skip = maxProfit(i+1, k, stockCase, prices, n, dp);

        long take = 0;

        if(k > 0){
            if(stockCase == 1){
                // already bought, have to sell first
                // and after selling, can start fresh transac. so passing 0 for case
                take = prices[i] + maxProfit(i+1, k-1, 0, prices, n, dp);
            }
            else if(stockCase == 2){
                // short sold, have to buy first
                // after buying, can start fresh transac, so case 0
                take = -prices[i] + maxProfit(i+1, k-1, 0, prices, n, dp);
            }
            else{
                // here buying, have to sell first, so passing case 1
                long buy = -prices[i] + maxProfit(i+1, k, 1, prices, n, dp);

                // here short selling, have to buy first, so passing case 2
                long shortSell = prices[i] + maxProfit(i+1, k, 2, prices, n, dp);

                take = Math.max(buy, shortSell);
            }
        }

        return dp[i][k][stockCase] = Math.max(skip, take);
    }

    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;

        long[][][] dp = new long[n+1][k+1][3];

        for(long[][] grid : dp){
            for(long[] row : grid){
                Arrays.fill(row, Long.MIN_VALUE);
            }
        }

        // case 0 -> fresh transaction
        // case 1 -> if bought then, have to sell first
        // case 2 -> already sold then, have to buy first -> short sell
        return maxProfit(0, k, 0, prices, n, dp);
    }
}





// Approach 2 - Bottom Up
// T.C. - O(n*k)
// S.C. - O(n*k)
class Solution {
    public long maximumProfit(int[] prices, int K) {
        int n = prices.length;

        // case 0 -> fresh transaction
        // case 1 -> if bought then, have to sell first
        // case 2 -> already sold then, have to buy first -> short sell

        long[][][] dp = new long[n+1][K+1][3];

        // for any k
        for(int k = 0; k<=K; k++){
            dp[n][k][0] = 0;
            dp[n][k][1] = Long.MIN_VALUE/2;
            dp[n][k][2] = Long.MIN_VALUE/2;
        }

        for(int i = n-1; i >= 0; i--){
            for(int k = 0; k<=K; k++){
                // take
                // CASE 0 : no open transaction
                dp[i][k][0] = dp[i + 1][k][0]; // do nothing

                if(k > 0){
                    long buy = -prices[i] + dp[i+1][k][1];
                    long sell = prices[i] + dp[i+1][k][2];
                    dp[i][k][0] = Math.max(dp[i][k][0], Math.max(buy, sell));
                }

                // CASE 1 : sell
                dp[i][k][1] = dp[i + 1][k][1];

                if(k > 0){
                    dp[i][k][1] = Math.max(dp[i][k][1], prices[i] + dp[i+1][k-1][0]);
                }

                // CASE 2 : short sell
                dp[i][k][2] = dp[i + 1][k][2];

                if(k > 0){
                    dp[i][k][2] = Math.max(dp[i][k][2], -prices[i] + dp[i+1][k-1][0]);
                }
            }
        }

        return dp[0][K][0];
    }
}