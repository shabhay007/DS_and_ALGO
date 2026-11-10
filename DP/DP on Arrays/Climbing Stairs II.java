// LeetCode Medium - 3693



// Approach 1 - Recursion
// T.C. - O(3^n)
// S.C. - O(n)
class Solution {
    public int solve(int i, int n, int[] costs){
        if(i == n){
            return 0;
        }

        // (i + 1 - i) * (i + 1 - i) -> 1^2
        // (i + 2 - i) * (i + 2 - i) -> 2^2
        // (i + 3 - i) * (i + 3 - i) -> 3^2
        int step1 = Integer.MAX_VALUE;
        int step2 = Integer.MAX_VALUE;
        int step3 = Integer.MAX_VALUE;

        if(i+1 <= n){
            // -1 for 1 based indexing in costs
            step1 = costs[i+1-1] + 1 + solve(i+1, n, costs);
        }

        if(i+2 <= n){
            step2 = costs[i+2-1] + 4 + solve(i+2, n, costs);
        }
        
        if(i+3 <= n){
            step3 = costs[i+3-1] + 9 + solve(i+3, n, costs);
        }

        return Math.min(step1, Math.min(step2, step3));
    }

    public int climbStairs(int n, int[] costs) {
        return solve(0, n, costs);
    }
}





// Approach 2 - DP (Memoization)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int solve(int i, int n, int[] costs, int[] dp){
        if(i == n){
            return 0;
        }

        if(dp[i] != Integer.MAX_VALUE){
            return dp[i];
        }

        // (i + 1 - i) * (i + 1 - i) -> 1^2
        // (i + 2 - i) * (i + 2 - i) -> 2^2
        // (i + 3 - i) * (i + 3 - i) -> 3^2
        int step1 = Integer.MAX_VALUE;
        int step2 = Integer.MAX_VALUE;
        int step3 = Integer.MAX_VALUE;

        if(i+1 <= n){
            // -1 for 1 based indexing in costs
            step1 = costs[i+1-1] + 1 + solve(i+1, n, costs, dp);
        }

        if(i+2 <= n){
            step2 = costs[i+2-1] + 4 + solve(i+2, n, costs, dp);
        }
        
        if(i+3 <= n){
            step3 = costs[i+3-1] + 9 + solve(i+3, n, costs, dp);
        }

        return dp[i] = Math.min(step1, Math.min(step2, step3));
    }

    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);

        return solve(0, n, costs, dp);
    }
}