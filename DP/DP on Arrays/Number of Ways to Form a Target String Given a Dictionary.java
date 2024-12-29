// LeetCode Hard - 1639


// Recursion + Memoization
// T.C. - O(n*k + k * m); as we are traversing all elements in dp array once; k = target.length()
// S.C. - O(k * m)
class Solution {
    int m;
    int MOD = (int) 1e9 + 7;
    public int solve(int targetIdx, int dictIdx, String target, int[][] freq, int[][] dp){
        if(targetIdx == target.length()){
            return 1;
        }

        if(dictIdx == m){
            return 0;
        }

        if(dp[targetIdx][dictIdx] != -1){
            return dp[targetIdx][dictIdx];
        }

        int chIdx = target.charAt(targetIdx) - 'a';
        int take = (int) ((long) freq[chIdx][dictIdx] * solve(targetIdx+1, dictIdx+1, target, freq, dp)%MOD);
        int notTake = solve(targetIdx, dictIdx+1, target, freq, dp)%MOD;

        return dp[targetIdx][dictIdx] = (take + notTake)%MOD;
    }

    public int numWays(String[] words, String target) {
        int n = words.length;
        m = words[0].length();

        int[][] frequency = new int[26][m];
        for(int i = 0; i<n; i++){ // O(n*m)
            for(int j = 0; j<m; j++){
                char ch = words[i].charAt(j);
                frequency[ch - 'a'][j]++;
            }
        }

        // for(int col = 0; col<m; col++){
        //     for(String word : words){
        //         char ch = word.charAt(col);
        //         frequency[ch - 'a'][col]++;
        //     }
        // }

        int[][] dp = new int[target.length()][m+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, 0, target, frequency, dp);
    }
}



// Bottom-Up / Tabulation
// T.C. - O(n*k + k * m); as we are traversing all elements in dp array once; k = target.length()
// S.C. - O(k * m)
class Solution {
    public int numWays(String[] words, String target) {
        int n = words.length;
        int m = target.length();
        int k = words[0].length();
        int MOD = (int) 1e9+7;

        long[][] freq = new long[26][k];
        for(int i = 0; i<n; i++){
            for(int j = 0; j<k; j++){
                char ch = words[i].charAt(j);
                freq[ch - 'a'][j]++;
            }
        }

        // [i][j] ith letter in target, jth letter in word
        // number of ways form [0:i] in target, using [0:j] letter in words
        // add all result from 0 to j to save the time that we sum it again
        long[][] dp = new long[m+1][k+1];
        dp[0][0] = 1;

        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=k; j++){
                if(j < k){ // notTake
                    dp[i][j+1] = (dp[i][j+1] + dp[i][j]) % MOD;
                }

                if(i < m && j < k){ // take
                    dp[i+1][j+1] = (dp[i+1][j+1] + dp[i][j] * freq[target.charAt(i) - 'a'][j]) % MOD;
                }
            }
        }

        return (int) dp[m][k];
    }
}