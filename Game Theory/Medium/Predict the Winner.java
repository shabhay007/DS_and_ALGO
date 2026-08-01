// LeetCode - 486



// Approach 1 - Recursion + Game Strategy
// T.C. - O(2^n)
// S.C. - O(n)


/*
    Game Strategy / Optimal Game Strategy (OGS) / Min - Max Game Strategy

    -> You play optimally and your opponent plays optimally as well

    -> When it's your turn - Do your best i.e. take max
    -> When it's your opponents turn - Expect the worst from results i.e. take min
*/
class Solution {
    int n;

    public int winner(int i, int j, int[] nums){
        if(i > j){
            return 0;
        }

        if(i == j){
            return nums[i];
        }

        /*
            If you took i, opp. range -> (i+1, j) OR if you took j, 
            opp. range -> (i, j-1);

            Similarly, if your opp. took i from (i+1, j), 
            your range -> (i+2, j) OR (i+1, j-1) in case he chooses j.

            And if he took i from (i, j-1), your range -> (i+1, j-1) OR (i, j-2) in case he chooses j.
        */
        int p1I = nums[i] + Math.min(winner(i+2, j, nums), winner(i+1, j-1, nums));
        int p1J = nums[j] + Math.min(winner(i+1, j-1, nums), winner(i, j-2, nums));

        return Math.max(p1I, p1J);
    }

    public boolean predictTheWinner(int[] nums) {
        this.n = nums.length;
        int totalScore = Arrays.stream(nums).sum();

        int p1Score = winner(0, n-1, nums);
        int p2Score = totalScore - p1Score;

        return p1Score >= p2Score;
    }
}






// Approach 2 - DP (Memoization) + Game Strategy
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    int n;

    public int winner(int i, int j, int[] nums, int[][] dp){
        if(i > j){
            return 0;
        }

        if(i == j){
            return nums[i];
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int p1I = nums[i] + Math.min(winner(i+2, j, nums, dp), winner(i+1, j-1, nums, dp));
        int p1J = nums[j] + Math.min(winner(i+1, j-1, nums, dp), winner(i, j-2, nums, dp));

        return dp[i][j] = Math.max(p1I, p1J);
    }

    public boolean predictTheWinner(int[] nums) {
        this.n = nums.length;
        int totalScore = Arrays.stream(nums).sum();

        int[][] dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        int p1Score = winner(0, n-1, nums, dp);
        int p2Score = totalScore - p1Score;

        return p1Score >= p2Score;
    }
}






// Approach 3 - Recursion + Game Strategy
// T.C. - O(2^n)
// S.C. - O(n)
class Solution {
    int n;

    public int winner(int i, int j, int[] nums){
        if(i > j){
            return 0;
        }

        if(i == j){
            return nums[i];
        }

        int p1I = nums[i] - winner(i+1, j, nums);
        int p1J = nums[j] - winner(i, j-1, nums);

        return Math.max(p1I, p1J);
    }

    public boolean predictTheWinner(int[] nums) {
        this.n = nums.length;

        int p1Score = winner(0, n-1, nums);

        return p1Score >= 0;
    }
}






// Approach 4 - DP (Memoization) + Game Strategy (Finding diff. bet. p1 and p2)
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    int n;

    public int winner(int i, int j, int[] nums, int[][] dp){
        if(i > j){
            return 0;
        }

        if(i == j){
            return nums[i];
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int p1I = nums[i] - winner(i+1, j, nums, dp);
        int p1J = nums[j] - winner(i, j-1, nums, dp);

        return dp[i][j] = Math.max(p1I, p1J);
    }

    public boolean predictTheWinner(int[] nums) {
        this.n = nums.length;

        int[][] dp = new int[n][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        int p1Score = winner(0, n-1, nums, dp);

        return p1Score >= 0;
    }
}