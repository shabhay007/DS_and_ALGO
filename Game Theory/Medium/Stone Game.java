// LeetCode - 877



// Approach 1 - DP (Memoization)
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    int n;

    public int solve(int i, int j, int[] piles, int[][] dp){
        if(i > j){
            return 0;
        }

        if(i == j){
            return piles[i];
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int aliceI = piles[i] + Math.min(solve(i+2, j, piles, dp), solve(i+1, j-1, piles, dp));
        int aliceJ = piles[j] + Math.min(solve(i+1, j-1, piles, dp), solve(i, j-2, piles, dp));

        return dp[i][j] = Math.max(aliceI, aliceJ);
    }

    public boolean stoneGame(int[] piles) {
        n = piles.length;
        int totalStones = Arrays.stream(piles).sum();

        int[][] dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        int aliceStones = solve(0, n-1, piles, dp);
        int bobStones = totalStones - aliceStones;

        return aliceStones >= bobStones;
    }
}






// Approach 2 - Observation
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public boolean stoneGame(int[] piles) {
        /*
            Since alice starts first she can choose all the even indices and accumulate stones or all the odd indices
            So, she will choose in a way so that she will win.
        */
        return true;
    }
}