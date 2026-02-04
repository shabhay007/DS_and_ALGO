// LeetCode Hard - 3640


// Approach 1 - Recursion (Gives TLE)
// T.C : O(n)
// S.C : O(n)
class Solution {
    public long solve(int i, int trend, int[] nums, int n){
        // base case
        if(i == n){
            if(trend == 3){
                return 0; // no for elements can be found for trionic array
            }
            else{
                return Long.MIN_VALUE/2; // invalid value for wrong sub-array/path
            }
        }

        long take = Long.MIN_VALUE/2;
        long skip = Long.MIN_VALUE/2;

        // we can skip only if trend = 0
        if(trend == 0){
            skip = solve(i+1, trend, nums, n);
        }

        // we are at trend 3 and we can finish the trionic sub-array here
        if(trend == 3){
            take = nums[i]; // finishing it here
        }

        if(i+1 < n){
            int curr = nums[i];
            int next = nums[i+1];

            if(trend == 0 && next > curr){
                take = Math.max(take, curr + solve(i+1, 1, nums, n));
            }
            else if(trend == 1){
                if(next > curr){ // increasing
                    take = Math.max(take, curr + solve(i+1, 1, nums, n));
                }
                else if(next < curr){ // trend 2 -> decreasing
                    take = Math.max(take, curr + solve(i+1, 2, nums, n));
                }
            }
            else if(trend == 2){
                if(next < curr){ // decreasing
                    take = Math.max(take, curr + solve(i+1, 2, nums, n));
                }
                else if(next > curr){ // trend 3 -> increasing
                    take = Math.max(take, curr + solve(i+1, 3, nums, n));
                }
            }
            else if(trend == 3 && next > curr){
                take = Math.max(take, curr + solve(i+1, 3, nums, n));
            }
        }

        return Math.max(take, skip);
    }

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;

        return solve(0, 0, nums, n); // index, trend
    }
}





// Approach 2 - (Recursion + Memoization)
// T.C : O(n)
// S.C : O(n)
class Solution {
    public long solve(int i, int trend, int[] nums, int n, long[][] dp){
        // base case
        if(i == n){
            if(trend == 3){
                return 0; // no for elements can be found for trionic array
            }
            else{
                return Long.MIN_VALUE/2; // invalid value for wrong sub-array/path
            }
        }

        if(dp[i][trend] != Long.MIN_VALUE){
            return dp[i][trend];
        }

        long take = Long.MIN_VALUE/2;
        long skip = Long.MIN_VALUE/2;

        // we can skip only if trend = 0
        if(trend == 0){
            skip = solve(i+1, trend, nums, n, dp);
        }

        // we are at trend 3 and we can finish the trionic sub-array here
        if(trend == 3){
            take = nums[i]; // finishing it here
        }

        if(i+1 < n){
            int curr = nums[i];
            int next = nums[i+1];

            if(trend == 0 && next > curr){
                take = Math.max(take, curr + solve(i+1, 1, nums, n, dp));
            }
            else if(trend == 1){
                if(next > curr){ // increasing
                    take = Math.max(take, curr + solve(i+1, 1, nums, n, dp));
                }
                else if(next < curr){ // trend 2 -> decreasing
                    take = Math.max(take, curr + solve(i+1, 2, nums, n, dp));
                }
            }
            else if(trend == 2){
                if(next < curr){ // decreasing
                    take = Math.max(take, curr + solve(i+1, 2, nums, n, dp));
                }
                else if(next > curr){ // trend 3 -> increasing
                    take = Math.max(take, curr + solve(i+1, 3, nums, n, dp));
                }
            }
            else if(trend == 3 && next > curr){
                take = Math.max(take, curr + solve(i+1, 3, nums, n, dp));
            }
        }

        return dp[i][trend] = Math.max(take, skip);
    }

    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n][4]; // trend -> {0, 1, 2, 3}

        for(long[] row : dp){
            Arrays.fill(row, Long.MIN_VALUE);
        }

        return solve(0, 0, nums, n, dp); // index, trend
    }
}