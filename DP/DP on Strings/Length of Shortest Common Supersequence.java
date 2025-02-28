// GFG Medium - Shortest Common Supersequence (Finding Length)


// Approach 1 - Recursion + Memoization (Matching from start)
// T.C. - O(2.m.n)
// S.C. - O(m.n + min(m, n))
class Solution {
    public static int getSCS(int i, int j, String s1, int m, String s2, int n, int[][] dp){
        if(i == m && j == n){
            return 0;
        }
        
        if(i == m){
            return n-j;
        }
        
        if(j == n){
            return m-i;
        }
        
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        if(s1.charAt(i) == s2.charAt(j)){
            return dp[i][j] = 1 + getSCS(i+1, j+1, s1, m, s2, n, dp);
        }
        else{
            int firstCase = 1 + getSCS(i+1, j, s1, m, s2, n, dp);
            int secondCase = 1 + getSCS(i, j+1, s1, m, s2, n, dp);
            
            return dp[i][j] = Math.min(firstCase, secondCase);
        }
    }
    
    // Function to find length of shortest common supersequence of two strings.
    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        int[][] dp = new int[m][n];
        
        for(int row[] : dp){ // O(m*n)
            Arrays.fill(row, -1);
        }
        
        return getSCS(0, 0, s1, m, s2, n, dp);
    }
}





// Approach 2 - Recursion + Memoization (Matching from end)
// T.C. - O(2.m.n)
// S.C. - O(m.n + min(m, n))
class Solution {
    public static int getSCS(String s1, int m, String s2, int n, int[][] dp){
        if(m == 0 || n == 0){
            return m+n;
        }
        
        if(dp[m][n] != -1){
            return dp[m][n];
        }
        
        if(s1.charAt(m-1) == s2.charAt(n-1)){
            return dp[m][n] = 1 + getSCS(s1, m-1, s2, n-1, dp);
        }
        else{
            int firstCase = 1 + getSCS(s1, m-1, s2, n, dp);
            int secondCase = 1 + getSCS(s1, m, s2, n-1, dp);
            
            return dp[m][n] = Math.min(firstCase, secondCase);
        }
    }
    
    // Function to find length of shortest common supersequence of two strings.
    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        for(int row[] : dp){ // O(m*n)
            Arrays.fill(row, -1);
        }
        
        return getSCS(s1, m, s2, n, dp);
    }
}






// Approach 3 - Bottom-Up/Tabulation
// T.C. - O(m.n)
// S.C. - O(m.n)
class Solution {
    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // state: dp[i][j] = SCS of string of length i and string of length j
        int[][] dp = new int[m+1][n+1];
        
        // filling 1st row
        for(int i = 0; i<=n; i++){
            dp[0][i] = i;
        }
        
        // filling 1st col
        for(int j = 0; j<=m; j++){
            dp[j][0] = j;
        }
        
        for(int i = 1; i<m+1; i++){
            for(int j = 1; j<n+1; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = i+j;
                }
                else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }
}







// Approach 4 - Bottom-Up/Tabulation (Slight change in Approach 3)
// T.C. - O(m.n)
// S.C. - O(m.n)
class Solution {
    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        // state: dp[i][j] = SCS of string of length i and string of length j
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 0; i<m+1; i++){
            for(int j = 0; j<n+1; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = i+j;
                }
                else if(s1.charAt(i-1) == s2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[m][n];
    }
}






// Approach 5 - Bottom-Up/Tabulation + Space Optimisation
// T.C. - O(m.n)
// S.C. - O(2n)
class Solution {
    public static int shortestCommonSupersequence(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        
        int[] prev = new int[n+1];
        int[] curr = new int[n+1];
        
        // If s1 is empty, SCS length is length of s2
        for(int i = 0; i<n+1; i++){
            prev[i] = i;
        }
        
        for(int i = 1; i<m+1; i++){
            curr[0] = i; // If s2 is empty, SCS length is length of s1
            
            for(int j = 1; j<n+1; j++){
                if(s1.charAt(i-1) == s2.charAt(j-1)){
                    curr[j] = 1 + prev[j-1];
                }
                else{
                    curr[j] = 1 + Math.min(prev[j], curr[j-1]);
                }
            }
            
            prev = curr.clone();
        }
        
        return prev[n];
    }
}