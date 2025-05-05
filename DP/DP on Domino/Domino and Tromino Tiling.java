// LeetCode - 790


// For Formula -> Refer. codestorywithMIK

// Approach 1 - Recursion (Gives TLE)
// T.C. - O(2^n); as two sub-problems -> solve(n-1) & solve(n-3)
// S.C. - O(n)
class Solution {
    public int solve(int n, int mod){
        if(n == 1 || n == 2){
            return n;
        }

        if(n == 3){
            return 5;
        }

        return ((2*solve(n-1, mod)) % mod + (solve(n-3, mod) % mod)) % mod; 
    }

    public int numTilings(int n) {
        int mod = (int) 1e9 + 7;

        return solve(n, mod);
    }
}




// Approach 2 - Recursion + Memoization
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int solve(int n, int dp[], int mod){
        if(n == 1 || n == 2){
            return n;
        }

        if(n == 3){
            return 5;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        return dp[n] = ((2*solve(n-1, dp, mod)) % mod + (solve(n-3, dp, mod) % mod)) % mod; 
    }

    public int numTilings(int n) {
        int mod = (int) 1e9 + 7;
        int dp[] = new int[n+1];
        Arrays.fill(dp, -1);

        return solve(n, dp, mod);
    }
}





// Approach 3 - Bottom Up
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int numTilings(int n) {
        if(n == 1 || n == 2){
            return n;
        }

        if(n == 3){
            return 5;
        }
        
        int mod = (int) 1e9 + 7;
        int dp[] = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 5;

        for(int i = 4; i<=n; i++){
            dp[i] = ((2*dp[i-1]) % mod + dp[i-3] % mod) % mod;
        }

        return dp[n];
    }
}