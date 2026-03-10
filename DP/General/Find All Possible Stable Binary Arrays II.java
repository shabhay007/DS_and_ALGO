// LeetCode Hard - 3130 (Extension of 3129)



// Approach 1 - DP (Tabulation - Gives TLE)
// T.C. - O(one * zero * limit)
// S.C. - O(one * zero)
class Solution {
    int mod = (int) 1e9 + 7;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero+1][one+1][2];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;

        for(int zerosLeft = 0; zerosLeft <= zero; zerosLeft++){
            for(int onesLeft = 0; onesLeft <= one; onesLeft++){
                if(zerosLeft == 0 && onesLeft == 0){
                    continue;
                }

                // start with 0
                int result = 0;
                for(int len = 1; len <= Math.min(zerosLeft, limit); len++){
                    result = (result + dp[zerosLeft - len][onesLeft][0]) % mod;
                }
                dp[zerosLeft][onesLeft][1] = result;

                // start with 1
                result = 0;
                for(int len = 1; len <= Math.min(onesLeft, limit); len++){
                    result = (result + dp[zerosLeft][onesLeft - len][1]) % mod;
                }
                dp[zerosLeft][onesLeft][0] = result;
            }
        }

        int startWithZero = dp[zero][one][0];
        int startWithOne = dp[zero][one][1];

        return (startWithZero + startWithOne) % mod;
    }
}






// Approach 2 - DP (Tabulation) + Prefix Sum concept
// T.C. - O(one * zero)
// S.C. - O(one * zero)
class Solution {
    int mod = (int) 1e9 + 7;

    public int numberOfStableArrays(int zero, int one, int limit) {
        int[][][] dp = new int[zero+1][one+1][2];
        
        for(int i = 0; i<=Math.min(zero, limit); i++){
            dp[i][0][0] = 1;
        }

        for(int j = 0; j<=Math.min(one, limit); j++){
            dp[0][j][1] = 1;
        }

        for(int i = 0; i<=zero; i++){
            for(int j = 0; j<=one; j++){
                if(i == 0 || j == 0){
                    continue;
                }
                
                dp[i][j][1] = (dp[i][j-1][0] + dp[i][j-1][1]) % mod;

                if(j-1 >= limit){
                    // can be -ve, so adding mod
                    dp[i][j][1] = (dp[i][j][1] - dp[i][j-1-limit][0] + mod) % mod;
                }

                dp[i][j][0] = (dp[i-1][j][0] + dp[i-1][j][1]) % mod;

                if(i-1 >= limit){
                    dp[i][j][0] = (dp[i][j][0] - dp[i-1-limit][j][1] + mod) % mod;
                }
            }
        }

        int startWithZero = dp[zero][one][0];
        int startWithOne = dp[zero][one][1];

        return (startWithZero + startWithOne) % mod;
    }
}