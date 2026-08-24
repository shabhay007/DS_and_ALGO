// LeetCode - 1872



// Approach 1 - DP (Memoization)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int n;

    public int solve(int i, int[] prefixSum, int[] dp){
        if(i == n-1){
            return prefixSum[n-1];
        }

        if(dp[i] != -1){
            return dp[i];
        }

        int take = prefixSum[i] - solve(i+1, prefixSum, dp);
        int skip = solve(i+1, prefixSum, dp);

        return dp[i] = Math.max(take, skip);
    }

    public int stoneGameVIII(int[] stones) {
        n = stones.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];

        for(int i = 1; i<n; i++){
            prefixSum[i] = prefixSum[i-1] + stones[i];
        }

        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        // x > 1 i.e. 0, 1 (2 elements to take min., so we have to start from idx 1)
        return solve(1, prefixSum, dp);
    }
}







// Approach 2 - DP (Bottom Up)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int n;

    public int stoneGameVIII(int[] stones) {
        n = stones.length;
        int[] prefixSum = new int[n];
        prefixSum[0] = stones[0];

        for(int i = 1; i<n; i++){
            prefixSum[i] = prefixSum[i-1] + stones[i];
        }

        int[] dp = new int[n];
        
        // base case : if(i == n-1) return prefixSum[n-1]
        dp[n-1] = prefixSum[n-1];

        for(int i = n-2; i >= 0; i--){
            int take = prefixSum[i] - dp[i+1];
            int skip = dp[i+1];

            dp[i] = Math.max(take, skip);
        }

        return dp[1];
    }
}