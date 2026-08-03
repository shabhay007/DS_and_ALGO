// LeetCode - 1406



// Approach 1 - Recursion + Game Strategy
// T.C. - O(3^n)
// S.C. - O(n)
class Solution {
    int n;

    public int solve(int i, int[] arr){
        if(i >= n){
            return 0;
        }

        // result = diff = alice - bob
        int result = Integer.MIN_VALUE;
        result = Math.max(result, arr[i] - solve(i+1, arr));

        if(i + 1 < n){
            result = Math.max(result, arr[i] + arr[i+1] - solve(i+2, arr));
        }

        if(i + 2 < n){
            result = Math.max(result, arr[i] + arr[i+1] + arr[i+2] - solve(i+3, arr));
        }

        return result;
    }

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;

        int diff = solve(0, stoneValue);

        if(diff == 0){
            return "Tie";
        }

        return (diff > 0) ? "Alice" : "Bob";
    }
}





// Approach 2 - DP (Memoization) + Game Strategy
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int n;

    public int solve(int i, int[] arr, int[] dp){
        if(i >= n){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }

        // result = diff = alice - bob
        int result = Integer.MIN_VALUE;
        result = Math.max(result, arr[i] - solve(i+1, arr, dp));

        if(i + 1 < n){
            result = Math.max(result, arr[i] + arr[i+1] - solve(i+2, arr, dp));
        }

        if(i + 2 < n){
            result = Math.max(result, arr[i] + arr[i+1] + arr[i+2] - solve(i+3, arr, dp));
        }

        return dp[i] = result;
    }

    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        int[] dp = new int[n+1];
        Arrays.fill(dp, -1);

        int diff = solve(0, stoneValue, dp);

        if(diff == 0){
            return "Tie";
        }

        return (diff > 0) ? "Alice" : "Bob";
    }
}






// Approach 3 - DP (Bottom Up) + Game Strategy
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    int n;
    
    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        int[] dp = new int[n+1];

        for(int i = n-1; i >= 0; i--){
            dp[i] = stoneValue[i] - dp[i+1];

            if(i+2 <= n){
                dp[i] = Math.max(dp[i], stoneValue[i]+stoneValue[i+1] - dp[i+2]);
            }

            if(i+3 <= n){
                dp[i] = Math.max(dp[i], stoneValue[i] + stoneValue[i+1] + stoneValue[i+2] - dp[i+3]);
            }
        }

        int diff = dp[0];

        if(diff == 0){
            return "Tie";
        }

        return (diff > 0) ? "Alice" : "Bob";
    }
}







// Approach 4 - DP (Bottom Up + Space Optimization) + Game Strategy
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    int n;
    
    public String stoneGameIII(int[] stoneValue) {
        n = stoneValue.length;
        int a = 0; // i+1
        int b = 0; // i+2
        int c = 0; // i+3

        for(int i = n-1; i >= 0; i--){
            int result = Integer.MIN_VALUE;
            result = stoneValue[i] - a;

            if(i+2 <= n){
                result = Math.max(result, stoneValue[i]+stoneValue[i+1] - b);
            }

            if(i+3 <= n){
                result = Math.max(result, stoneValue[i] + stoneValue[i+1] + stoneValue[i+2] - c);
            }

            c = b;
            b = a;
            a = result;
        }

        int diff = a;

        if(diff == 0){
            return "Tie";
        }

        return (diff > 0) ? "Alice" : "Bob";
    }
}