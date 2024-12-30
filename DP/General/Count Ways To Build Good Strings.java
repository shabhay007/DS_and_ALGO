// LeetCode Medium - 2466


// if we use only recursion then it will take O(2^high)
// Recursion + Memoization
// T.C. - O(high); 
// S.C. - O(high)
class Solution {
    // length of the string will be same as of index
    public long solve(int length, int low, int high, int zero, int one, long[] dp){
        long count = 0;
        if(length >= low && length <= high){
            count++;
        }

        if(length > high){
            return 0;
        }

        if(dp[length] != -1){
            return dp[length];
        }    
               
        long takeZero = solve(length+zero, low, high, zero, one, dp);
        long takeOne = solve(length+one, low, high, zero, one, dp);

        return dp[length] = (count + takeZero + takeOne) % (long) (1e9+7);
    }

    public int countGoodStrings(int low, int high, int zero, int one) {
        long dp[] = new long[high+1];
        Arrays.fill(dp, -1);

        return (int) solve(0, low, high, zero, one, dp);
    }
}




// Bottom-Up
// T.C. - O(high)
// S.C. - O(high)
class Solution {
    public int countGoodStrings(int low, int high, int zero, int one) {
        int MOD = (int) 1e9 + 7;

        // dp[n] = no of good strings of length n;
        int dp[] = new int[high+1];
        dp[0] = 1;

        for(int n = 1; n<=high; n++){
            if(n-zero >= 0){
                dp[n] = (dp[n] % MOD + dp[n - zero] % MOD) % MOD;
            }

            if(n-one >= 0){
                dp[n] = (dp[n] % MOD + dp[n - one] % MOD) % MOD;
            }
        }

        int totalGoodStrings = 0;
        for(int i = low; i<=high; i++){
            totalGoodStrings = (totalGoodStrings % MOD + dp[i] % MOD) % MOD;
        }

        return totalGoodStrings;
    }
}