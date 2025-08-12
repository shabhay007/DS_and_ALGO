// LeetCode - 2787



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(2^m); m = total no. of num^x
// S.C. - O(m)
class Solution {
    int mod = (int) 1e9 + 7;

    public int getWays(int curr, int n, int x){
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }

        int currValue = (int) Math.pow(curr, x);

        if(currValue > n){
            return 0;
        }

        int take = getWays(curr+1, n - currValue, x);
        int skip = getWays(curr+1, n, x);

        return (take + skip) % mod;
    }

    public int numberOfWays(int n, int x) {
        return getWays(1, n, x);
    }
}




// Approach 2 - Recursion + Memoization
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    int mod = (int) 1e9 + 7;

    public int getWays(int curr, int n, int x, int[][] dp){
        if(n == 0){
            return 1;
        }

        if(n < 0){
            return 0;
        }

        int currValue = (int) Math.pow(curr, x);

        if(currValue > n){
            return 0;
        }

        if(dp[n][curr] != -1){
            return dp[n][curr];
        }

        int take = getWays(curr+1, n - currValue, x, dp);
        int skip = getWays(curr+1, n, x, dp);

        return dp[n][curr] = (take + skip) % mod;
    }

    public int numberOfWays(int n, int x) {
        int[][] dp = new int[301][301];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getWays(1, n, x, dp);
    }
}