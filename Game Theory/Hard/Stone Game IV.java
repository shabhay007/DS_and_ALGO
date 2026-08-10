// LeetCode - 1510



// Approach 1 - DP (Memoization)
// T.C. - O(n * m); m = sqrt(n)
// S.C. - O(m)
class Solution {
    public boolean isPerfectSquare(int num){
        int sqrt = (int) Math.sqrt(num);

        return (sqrt * sqrt == num);
    }

    public boolean solve(int n, List<Integer> list, int m, Boolean[] dp){
        if(n == 0){
            return false;
        }

        if(dp[n] != null){
            return dp[n];
        }

        int stones = 0;
        for(int i = 0; i<m; i++){
            int num = n - list.get(i);

            if(num < 0){
                break;
            }

            if(!solve(n - list.get(i), list, m, dp)){
                return dp[n] = true;
            }
        }

        return dp[n] = false;
    }

    public boolean winnerSquareGame(int n) {
        List<Integer> list = new ArrayList<>();

        for(int i = 1; i*i <= n; i++){
            list.add(i*i);
        }

        int m = list.size();

        Boolean[] dp = new Boolean[n+1];

        return solve(n, list, m, dp);
    }
}