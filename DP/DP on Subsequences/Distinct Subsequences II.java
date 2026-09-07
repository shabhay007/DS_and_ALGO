// LeetCode Hard - 940



// Approach 1 - DP (Memoization)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int n;
    int mod = (int) 1e9 + 7;
    int[] dp;

    public int solve(int n, int[] prev){
        if(n == 0){
            return 1;
        }

        if(dp[n] != -1){
            return dp[n];
        }

        int total = (int) (2L * solve(n-1, prev)) % mod;

        if(prev[n] != 0){
            int duplicates = solve(prev[n] - 1, prev);

            // adding mod to escape from -ve
            total = (total - duplicates + mod) % mod;
        }

        return dp[n] = total;
    }

    public int distinctSubseqII(String s) {
        n = s.length();
        int[] prev = new int[n+1];
        int[] lastSeen = new int[26];

        for(int i = 1; i<=n; i++){
            int idx = s.charAt(i-1) - 'a';
            prev[i] = lastSeen[idx];
            lastSeen[idx] = i;
        }

        dp = new int[n+1];
        Arrays.fill(dp, -1);

        return (solve(n, prev) - 1 + mod) % mod; // removing empty string ""
    }
}





// Approach 2 - DP (Bottom Up)
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int distinctSubseqII(String s) {
        int n = s.length();
        int mod = (int) 1e9 + 7;
        
        // prev[n] = last time when we saw this nth character (1-based indexing)
        int[] prev = new int[n+1];
        int[] lastSeen = new int[26];

        for(int i = 1; i<=n; i++){
            int idx = s.charAt(i-1) - 'a';
            prev[i] = lastSeen[idx];
            lastSeen[idx] = i;
        }

        int[] dp = new int[n+1];
        dp[0] = 1;

        for(int i = 1; i<=n; i++){
            int total = (int) (2L * dp[i-1]) % mod;

            if(prev[i] != 0){
                int dup = dp[prev[i] - 1];
                total = (total - dup + mod) % mod;
            }

            dp[i] = total;
        }

        return (dp[n] - 1 + mod) % mod; // removing empty string ""
    }
}