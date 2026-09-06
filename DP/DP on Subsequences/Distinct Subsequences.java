// LeetCode Hard - 115



// Approach 1 - Backtracking
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    int n;

    public int solve(int i, StringBuilder curr, String s, String t){
        if(curr.length() > t.length()){
            return 0;
        }

        if(curr.toString().equals(t)){
            return 1;
        }

        if(i == n){ // s is exhausted
            return 0;
        }


        char ch = s.charAt(i);
        int take = solve(i+1, curr.append(ch), s, t);
        curr.deleteCharAt(curr.length() - 1); // backtracking

        // skip path
        int skip = solve(i+1, curr, s, t);

        return take + skip;
    }

    public int numDistinct(String s, String t) {
        n = s.length();

        return solve(0, new StringBuilder(), s, t);
    }
}





// Approach 2 - Recursion
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    int n;
    int m;

    public int solve(int i, int j, String s, String t){
        // j will move forward only if char's matches, so if j == m that means
        // we have found one way
        if(j == m){
            return 1;
        }

        if(i == n){
            return 0;
        }

        char chi = s.charAt(i);
        char chj = t.charAt(j);
        int case1 = 0;

        if(chi == chj){
            case1 = solve(i+1, j+1, s, t);
        }

        int case2 = solve(i+1, j, s, t);

        return case1 + case2;
    }

    public int numDistinct(String s, String t) {
        n = s.length();
        m = t.length();

        return solve(0, 0, s, t);
    }
}





// Approach 3 - DP (Memoization)
// T.C. - O(n * m)
// S.C. - O(n * m)
class Solution {
    int n;
    int m;

    public int solve(int i, int j, String s, String t, int[][] dp){
        // j will move forward only if char's matches, so if j == m that means
        // we have found one way
        if(j == m){
            return 1;
        }

        if(i == n){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        char chi = s.charAt(i);
        char chj = t.charAt(j);
        int case1 = 0;

        if(chi == chj){
            case1 = solve(i+1, j+1, s, t, dp);
        }

        int case2 = solve(i+1, j, s, t, dp);

        return dp[i][j] = case1 + case2;
    }

    public int numDistinct(String s, String t) {
        n = s.length();
        m = t.length();

        int[][] dp = new int[n][m];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, 0, s, t, dp);
    }
}






// Approach 4 - DP (Memoization)
// T.C. - O(n * m)
// S.C. - O(n * m)
class Solution {
    public int solve(int n, int m, String s, String t, int[][] dp){
        // j will move forward only if char's matches, so if j == m that means
        // we have found one way
        if(m == 0){
            return 1;
        }

        if(n == 0){
            return 0;
        }

        if(dp[n][m] != -1){
            return dp[n][m];
        }

        char chi = s.charAt(n-1);
        char chj = t.charAt(m-1);
        int case1 = 0;

        if(chi == chj){
            case1 = solve(n-1, m-1, s, t, dp);
        }

        int case2 = solve(n-1, m, s, t, dp);

        return dp[n][m] = case1 + case2;
    }

    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(n, m, s, t, dp);
    }
}






// Approach 5 - DP (Bottom Up)
// T.C. - O(n * m)
// S.C. - O(n * m)
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[][] dp = new int[n+1][m+1];

        // base case 1, if m == 0, return 1
        for(int i = 0; i<n; i++){
            dp[i][0] = 1;
        }

        // base case, n == 0
        // if we take i = 0, dp[0][0] = 0 but dp[][0] must be 1 (see above)
        for(int i = 1; i<=m; i++){
            dp[0][i] = 0;
        }
        
        for(int i = 1; i <= n; i++){
            char chi = s.charAt(i-1);

            for(int j = 1; j <= m; j++){
                char chj = t.charAt(j-1);
                int case1 = 0;

                if(chi == chj){
                    case1 = dp[i-1][j-1];
                }

                int case2 = dp[i-1][j];

                dp[i][j] = case1 + case2;
            }
        }

        return dp[n][m];
    }
}






// Approach 6 - DP (Bottom Up) + Space Optimization
// T.C. - O(n * m)
// S.C. - O(m)
class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();

        int[] curr = new int[m+1];
        int[] prev = new int[m+1];

        // base case
        curr[0] = 1;
        prev[0] = 1;
        
        for(int i = 1; i <= n; i++){
            char chi = s.charAt(i-1);

            for(int j = 1; j <= m; j++){
                char chj = t.charAt(j-1);
                int case1 = 0;

                if(chi == chj){
                    case1 = prev[j-1];
                }

                int case2 = prev[j];

                curr[j] = case1 + case2;
            }

            // Swap current and previous rows
            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }
}