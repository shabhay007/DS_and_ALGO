// LeetCode - 1140



// Approach - DP (Memoization)
// T.C. - O(n^3)
// S.C. - O(n^2)
class Solution {
    int n;

    public int solve(int person, int i, int M, int[] piles, int[][][] dp){
        if(i >= n){
            return 0;
        }

        if(dp[person][i][M] != -1){
            return dp[person][i][M];
        }

        int stones = 0;
        int result = (person == 0) ? -1 : Integer.MAX_VALUE;

        for(int x = 1; x <= Math.min(2 * M, n-i); x++){
            stones += piles[i+x-1]; // -1 for zero based indexing
            
            if(person == 0){
                result = Math.max(result, stones + solve(1, i+x, Math.max(M, x), piles, dp));
            }
            else{
                result = Math.min(result, solve(0, i+x, Math.max(M, x), piles, dp));
            }
        }

        return dp[person][i][M] = result;
    }

    public int stoneGameII(int[] piles) {
        n = piles.length;

        int[][][] dp = new int[2][n][n+1];
        for(int[][] grid : dp){
            for(int[] row : grid){
                Arrays.fill(row, -1);
            }
        }

        // 0 -> alice, 1 -> bob
        return solve(0, 0, 1, piles, dp);
    }
}