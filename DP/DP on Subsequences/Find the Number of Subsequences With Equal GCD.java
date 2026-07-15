// LeetCode Hard - 3336



// Approach 1 - Recursion + Memoization
// T.C. - O(n * maxVal * maxVal * log(min(a, b)))
// S.C. - O(n * maxVal * maxVal)
class Solution {
    int mod = (int) 1e9 + 7;

    public int gcd(int num1, int num2){ // O(log(min(num1, num2)))
        while(num2 != 0){
            int temp = num2;
            num2 = num1 % num2;
            num1 = temp;
        }

        return num1;
    }

    public int solve(int i, int[] nums, int first, int second, int n, int[][][] dp){
        if(i >= n){
            boolean isEmpty = (first == 0 && second == 0);
            boolean isEqual = (first == second);

            return (!isEmpty && isEqual) ? 1 : 0;
        }

        if(dp[i][first][second] != -1){
            return dp[i][first][second] % mod;
        }

        int skip = solve(i+1, nums, first, second, n, dp);
        int seq1 = solve(i+1, nums, gcd(first, nums[i]), second, n, dp);
        int seq2 = solve(i+1, nums, first, gcd(second, nums[i]), n, dp);

        return dp[i][first][second] = (int) ((0L + skip + seq1 + seq2) % mod);
    }

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;

        int[][][] dp = new int[n][201][201];
        for(int[][] grid : dp){
            for(int[] row : grid){
                Arrays.fill(row, -1);
            }
        }

        return solve(0, nums, 0, 0, n, dp);
    }
}