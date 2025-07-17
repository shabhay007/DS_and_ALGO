// LeetCode Medium - 3202



// Approach 1 - Recursion
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public int LIS(int i, int prev, int mod, int[] nums, int n, int k){
        if(i >= n){
            return 0;
        }

        int take = 0;

        if(prev == -1 || mod == -1 || (nums[prev] + nums[i]) % k == mod){
            int newMod = (prev == -1) ? -1 : (nums[prev] + nums[i]) % k;
            take = 1 + LIS(i+1, i, newMod, nums, n, k);
        }

        int skip = LIS(i+1, prev, mod, nums, n, k);

        return Math.max(take, skip);
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        return LIS(0, -1, -1, nums, n, k);
    }
}





// Approach 2 - Recursion + Memoization
// T.C. - O(n.n)
// S.C. - O(n.n)
class Solution {
    public int LIS(int i, int prev, int mod, int[] nums, int n, int k, int[][][] dp){
        if(i >= n){
            return 0;
        }

        if(prev != -1 && mod != -1 && dp[i][prev][mod] != -1){
            return dp[i][prev][mod];
        }

        int take = 0;

        if(prev == -1 || mod == -1 || (nums[prev] + nums[i]) % k == mod){
            int newMod = (prev == -1) ? -1 : (nums[prev] + nums[i]) % k;
            take = 1 + LIS(i+1, i, newMod, nums, n, k, dp);
        }

        int skip = LIS(i+1, prev, mod, nums, n, k, dp);

        if(prev != -1 && mod != -1){
            dp[i][prev][mod] = Math.max(take, skip);
        }

        return Math.max(take, skip);
    }

    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][][] dp = new int[n+1][n+1][k+1];

        for(int[][] grid : dp){
            for(int[] row : grid){
                Arrays.fill(row, -1);
            }
        }

        return LIS(0, -1, -1, nums, n, k, dp);
    }
}




// Approach 3 - Bottoms up
// T.C. - O(n.n)
// S.C. - O(n.n)
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;
        int[][] dp = new int[n+1][k+1];
        int max = 0;

        for(int i = 1; i<n; i++){
            for(int j = 0; j<i; j++){
                int mod = (nums[i] + nums[j]) % k;

                dp[i][mod] = Math.max(dp[i][mod], dp[j][mod] + 1);
                max = Math.max(max, dp[i][mod]);
            }
        }

        return max + 1;
    }
}




// Approach 4 - Bottoms up (Slight change in Approach 3)
// T.C. - O(n.n)
// S.C. - O(n.n)
class Solution {
    public int maximumLength(int[] nums, int k) {
        int n = nums.length;

        int[][] dp = new int[k+1][n+1];

        // At least one element is always a valid subsequence
        for(int[] row : dp){
            Arrays.fill(row, 1);
        }

        int max = 1; // At least one element is always a valid subsequence

        for(int i = 1; i<n; i++){
            for(int j = 0; j<i; j++){
                int mod = (nums[i] + nums[j]) % k;

                dp[mod][i] = Math.max(dp[mod][i], dp[mod][j] + 1);
                max = Math.max(max, dp[mod][i]);
            }
        }

        return max;
    }
}