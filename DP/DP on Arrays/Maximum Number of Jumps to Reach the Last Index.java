// LeetCode Medium - 2770



// Approach 1 - Recursion
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public int maxSteps(int i, int[] nums, int n, int target){
        if(i == n-1){
            return 0;
        }
        
        int maxJump = Integer.MIN_VALUE;

        for(int j = i+1; j<n; j++){
            int diff = nums[j] - nums[i];
            
            if(diff >= -target && diff <= target){
                int result = maxSteps(j, nums, n, target);

                if(result != Integer.MIN_VALUE){
                    maxJump = Math.max(maxJump, 1 + result);
                }
            }
        }

        return maxJump;
    }

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;

        int result = maxSteps(0, nums, n, target);
        
        return (result == Integer.MIN_VALUE) ? -1 : result;
    }
}






// Approach 2 - DP (Memoization)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int maxSteps(int i, int[] nums, int n, int target, int[] dp){
        if(i == n-1){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }
        
        int maxJump = Integer.MIN_VALUE;

        for(int j = i+1; j<n; j++){
            long diff = (long) nums[j] - nums[i];
            
            if(diff >= -target && diff <= target){
                int result = maxSteps(j, nums, n, target, dp);

                if(result != Integer.MIN_VALUE){
                    maxJump = Math.max(maxJump, 1 + result);
                }
            }
        }

        return dp[i] = maxJump;
    }

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);

        int result = maxSteps(0, nums, n, target, dp);
        
        return (result == Integer.MIN_VALUE) ? -1 : result;
    }
}






// Approach 3 - DP (Memoization) Slight change in Approach 2
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int maxSteps(int i, int[] nums, int n, int target, int[] dp){
        if(i == n-1){
            return 0;
        }

        if(dp[i] != Integer.MIN_VALUE){
            return dp[i];
        }
        
        int maxJump = Integer.MIN_VALUE;

        for(int j = i+1; j<n; j++){
            if(Math.abs(nums[j] - nums[i]) <= target){
                int result = 1 + maxSteps(j, nums, n, target, dp);
                maxJump = Math.max(maxJump, result);
            }
        }

        return dp[i] = maxJump;
    }

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);

        int result = maxSteps(0, nums, n, target, dp);
        
        return (result < 0) ? -1 : result;
    }
}






// Approach 4 - DP (Bottom Up)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;

        // state : max jump from i to n-1
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);
        dp[n-1] = 0; // base case

        for(int i = n-2; i >= 0; i--){
            for(int j = i+1; j<n; j++){
                if(Math.abs(nums[j] - nums[i]) <= target && dp[j] != Integer.MIN_VALUE){
                    int temp = 1 + dp[j];
                    dp[i] = Math.max(dp[i], temp);
                }
            }
        }

        return dp[0] < 0 ? -1 : dp[0];
    }
}