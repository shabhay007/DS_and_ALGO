// LeetCode 1563



// Approach 1 - DP (Memoization)
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    public int solve(int i, int j, int sum, int[] stoneValue, int[][] dp){
        if(i >= j){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int leftSum = 0;
        int rightSum = 0;
        int result = 0;
        for(int k = i; k < j; k++){
            leftSum += stoneValue[k];
            rightSum = sum - leftSum;

            if(leftSum < rightSum){
                result = Math.max(result, leftSum + solve(i, k, leftSum, stoneValue, dp));
            }
            else if(leftSum > rightSum){
                result = Math.max(result, rightSum + solve(k+1, j, rightSum, stoneValue, dp));
            }
            else{
                result = Math.max(result, Math.max(
                    (leftSum + solve(i, k, leftSum, stoneValue, dp)),
                    (rightSum + solve(k+1, j, rightSum, stoneValue, dp))
                ));
            }
        }

        return dp[i][j] = result;
    }

    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int totalSum = Arrays.stream(stoneValue).sum();

        int[][] dp = new int[n+1][n+1];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, n-1, totalSum, stoneValue, dp);
    }
}