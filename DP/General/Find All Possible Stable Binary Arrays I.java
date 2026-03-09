// LeetCode Medium - 3129



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(L^(z+o)); -> L = limit
// S.C. - O(zero + one)
class Solution {
    int mod = (int) 1e9 + 7;

    public int solve(int zero, int one, boolean wasPrevOne, int limit) {
        if (zero == 0 && one == 0) {
            return 1;
        }

        int result = 0;

        if (wasPrevOne) { // have to use zero
            for (int len = 1; len <= Math.min(zero, limit); len++) {
                // sending false to use one on next
                result = (result + solve(zero - len, one, false, limit)) % mod;
            }
        } else {
            for (int len = 1; len <= Math.min(one, limit); len++) {
                // sending true to use zero on the next
                result = (result + solve(zero, one - len, true, limit)) % mod;
            }
        }

        return result;
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        int startWithZero = solve(zero, one, false, limit);
        int startWithOne = solve(zero, one, true, limit);

        return (startWithZero + startWithOne) % mod;
    }
}






// Approach 2 - Recursion + Memoization
// T.C. - O(zero * one)
// S.C. - O(zero * one)
class Solution {
    int mod = (int) 1e9 + 7;

    public int solve(int zero, int one, int wasPrevOne, int limit, int[][][] dp) {
        if (zero == 0 && one == 0) {
            return 1;
        }

        if(dp[zero][one][wasPrevOne] != -1){
            return dp[zero][one][wasPrevOne];
        }

        int result = 0;

        if (wasPrevOne == 1) { // have to use zero
            for (int len = 1; len <= Math.min(zero, limit); len++) {
                // sending false to use one on next
                result = (result + solve(zero - len, one, 0, limit, dp)) % mod;
            }
        } else {
            for (int len = 1; len <= Math.min(one, limit); len++) {
                // sending true to use zero on the next
                result = (result + solve(zero, one - len, 1, limit, dp)) % mod;
            }
        }

        return dp[zero][one][wasPrevOne] = result;
    }

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero+1][one+1][2];

        for(int[][] mat : dp){
            for(int[] row : mat){
                Arrays.fill(row, -1);
            }
        }

        int startWithZero = solve(zero, one, 0, limit, dp);
        int startWithOne = solve(zero, one, 1, limit, dp);

        return (startWithZero + startWithOne) % mod;
    }
}






// Approach 3 - Tabulation
// T.C. - O(zero * one)
// S.C. - O(zero * one)
class Solution {
    int mod = (int) 1e9 + 7;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[one+1][zero+1][2];
        dp[0][0][1] = 1;
        dp[0][0][0] = 1;

        for(int onesLeft = 0; onesLeft <= one; onesLeft++){
            for(int zerosLeft = 0; zerosLeft <= zero; zerosLeft++){
                if(onesLeft == 0 && zerosLeft == 0){
                    continue;
                }

                int result = 0;

                for(int len = 1; len <= Math.min(zerosLeft, limit); len++){
                    result = (result + dp[onesLeft][zerosLeft - len][0]) % mod;
                }
                dp[onesLeft][zerosLeft][1] = result;

                result = 0;
                for(int len = 1; len <= Math.min(onesLeft, limit); len++){
                    result = (result + dp[onesLeft - len][zerosLeft][1]) % mod;
                }
                dp[onesLeft][zerosLeft][0] = result;
            }
        }

        int startWithZero = dp[one][zero][0];
        int startWithOne = dp[one][zero][1];

        return (startWithZero + startWithOne) % mod;
    }
}