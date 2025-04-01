// LeetCode Medium - 2140


// Approach 1 - Recursion (Gives TLE)
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    public long maxPoints(int i, int[][] questions, int n){
        if(i >= n){
            return 0;
        }

        // if solved the curr question
        // skipping next questions[i][1] number of questions
        long solved = questions[i][0] + maxPoints(i + questions[i][1] + 1, questions, n);

        // if skipped the curr question
        long skipped = maxPoints(i + 1, questions, n);

        return Math.max(solved, skipped);
    }
    
    public long mostPoints(int[][] questions) {
        int n = questions.length;

        return maxPoints(0, questions, n);
    }
}




// Approach 2 - Recursion + Memoization
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public long maxPoints(int i, int[][] questions, int n, long[] dp){
        if(i >= n){
            return 0;
        }

        if(dp[i] != -1){
            return dp[i];
        }

        // if solved the curr question
        // skipping next questions[i][1] number of questions
        long solved = questions[i][0] + maxPoints(i + questions[i][1] + 1, questions, n, dp);

        // if skipped the curr question
        long skipped = maxPoints(i + 1, questions, n, dp);

        return dp[i] = Math.max(solved, skipped);
    }
    
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        long[] dp = new long[n];
        Arrays.fill(dp, -1);

        return maxPoints(0, questions, n, dp);
    }
}




// Approach 3 - Bottom-up
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public long mostPoints(int[][] q) {
        int n = q.length;

        // state definition :- 
        // dp[i] = max. points earned from i - (n-1)
        long[] dp = new long[n+1];

        // since curr question points are also dependent on future questions
        // so, we have to traverse right to left in order to remove this dependency
        for(int i = n-1; i >= 0; i--){
            long solved = q[i][0] + (((i + q[i][1] + 1) < n) ? dp[i + q[i][1] + 1] : 0);
            long skipped = dp[i+1];

            dp[i] = Math.max(solved, skipped);
        }

        return dp[0];
    }
}