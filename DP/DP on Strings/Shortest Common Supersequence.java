// LeetCode Hard - 1092 (Printing SCS)


// Approach 1 - Bottom Up
// T.C. - O(m.n + (m + n))
// S.C. - O(m.n + (m + n)); in worst case StringBuilder's length will be m + n;
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m+1][n+1];

        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=n; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = i+j;
                }
                else if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        // finding the SCS
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                sb.append(str1.charAt(i-1));

                // diagonal movement
                i--;
                j--;
            }
            else if(dp[i-1][j] < dp[i][j-1]){
                sb.append(str1.charAt(i-1));

                // movement towards min length (row decreases)
                i--;
            }
            else{
                sb.append(str2.charAt(j-1));

                // movement towards min length (col decreases)
                j--;
            }
        }

        // str1 has some characters left
        while(i > 0){
            sb.append(str1.charAt(i-1));
            i--;
        }

        // str2 has some characters left
        while(j > 0){
            sb.append(str2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }
}





// Approach 2 - Using Longest Common Subsequence
// T.C. - O(m.n + 2(m + n))
// S.C. - O(m.n + (m + n)); in worst case StringBuilder's length will be m + n;
class Solution {
    public int[][] longestCommonSubsequence(String str1, String str2){
        int m = str1.length();
        int n = str2.length();
        
        int[][] dp = new int[m+1][n+1];

        // filling 1st row, when str1 is empty
        for(int i = 0; i <= n; i++){
            dp[0][i] = 0;
        }

        // filling 1st col, when str2 is empty
        for(int i = 0; i <= m; i++){
            dp[i][0] = 0;
        }

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(str1.charAt(i-1) == str2.charAt(j-1)){
                    dp[i][j] = 1 + dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        return dp;
    }

    public String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        // step 1 : filling the DP table using LCS
        int[][] dp = longestCommonSubsequence(str1, str2);

        // step 2 : finding the SCS
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();

        while(i > 0 && j > 0){
            if(str1.charAt(i-1) == str2.charAt(j-1)){
                sb.append(str1.charAt(i-1));

                // diagonal movement
                i--;
                j--;
            }
            else if(dp[i-1][j] > dp[i][j-1]){ // change in the condition here
                sb.append(str1.charAt(i-1));

                // movement towards min length (row decreases)
                i--;
            }
            else{
                sb.append(str2.charAt(j-1));

                // movement towards min length (col decreases)
                j--;
            }
        }

        // str1 has some characters left
        while(i > 0){
            sb.append(str1.charAt(i-1));
            i--;
        }

        // str2 has some characters left
        while(j > 0){
            sb.append(str2.charAt(j-1));
            j--;
        }

        return sb.reverse().toString();
    }
}