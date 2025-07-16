// LeetCode Medium - 3201



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public int getMaxLength(int i, int prevIdx, int prevMod, int[] nums){
        if(i >= nums.length){
            return 0;
        }

        int take = 0;

        if(prevIdx == -1 || prevMod == -1 || (nums[i] + nums[prevIdx]) % 2 == prevMod){
            int newMod = (prevIdx == -1) ? -1 : (nums[i] + nums[prevIdx]) % 2;
            take = 1 + getMaxLength(i+1, i, newMod, nums);
        }

        int skip = getMaxLength(i+1, prevIdx, prevMod, nums);

        return Math.max(take, skip);
    }

    public int maximumLength(int[] nums) {
        return getMaxLength(0, -1, -1, nums);
    }
}





// Approach 2 - Recursion + Memoization (Gives MLE)
// T.C. - O(n*n*3)
// S.C. - O(n*n*3)
class Solution {
    public int getMaxLength(int i, int prevIdx, int prevMod, int[] nums, int[][][] dp){
        if(i >= nums.length){
            return 0;
        }

        if(prevIdx != -1 && prevMod != -1 && dp[i][prevIdx][prevMod] != -1){
            return dp[i][prevIdx][prevMod];
        }

        int take = 0;

        if(prevIdx == -1 || prevMod == -1 || (nums[i] + nums[prevIdx]) % 2 == prevMod){
            int newMod = (prevIdx == -1) ? -1 : (nums[i] + nums[prevIdx]) % 2;
            take = 1 + getMaxLength(i+1, i, newMod, nums, dp);
        }

        int skip = getMaxLength(i+1, prevIdx, prevMod, nums, dp);

        if(prevIdx != -1 && prevMod != -1){
            dp[i][prevIdx][prevMod] = Math.max(take, skip);
        }

        return Math.max(take, skip);
    }

    public int maximumLength(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n+1][n+1][3];

        for(int[][] level1 : dp){
            for(int[] row : level1){
                Arrays.fill(row, -1);
            }
        }

        return getMaxLength(0, -1, -1, nums, dp);
    }
}





// Approach 3 - Parity Checking
// T.C. - O(2n)
// S.C. - O(1)
class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int odd = 0;
        int even = 0;

        for(int i = 0; i<n; i++){
            if(nums[i] % 2 == 0){
                even++;
            }
            else{
                odd++;
            }
        }

        int prev = nums[0] % 2;
        int count = 1;

        for(int i = 1; i<n; i++){
            if(nums[i] % 2 != prev){
                count++;
                prev = nums[i] % 2;
            }
        }

        return Math.max(count, Math.max(odd, even));
    }
}





// Approach 4 - Slight improvement in Approach 3
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int maximumLength(int[] nums) {
        int n = nums.length;
        int odd = 0;
        int even = 0;
        int parity = nums[0] % 2;
        int count = 1;

        for(int i = 0; i<n; i++){
            if(nums[i] % 2 == 0){
                even++;
            }
            else{
                odd++;
            }

            if(i >= 1 && nums[i] % 2 != parity){
                count++;
                parity = nums[i] % 2;
            }
        }

        return Math.max(count, Math.max(odd, even));
    }
}