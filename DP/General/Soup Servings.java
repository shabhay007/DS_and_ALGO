// LeetCode - 808



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(4^(n/25))
// S.C. - O(n)
class Solution {
    public double getProbabilityOfA(int a, int b){
        if(a <= 0 && b <= 0){
            return 0.5;
        }

        // if we keep this condition first, then the above condition
        // will never run and get returned when a <= 0 without checking 
        // for (a <= 0 && b <= 0)
        if(a <= 0){
            return 1.0;
        }

        if(b <= 0){
            return 0.0;
        }

        double p1 = getProbabilityOfA(a - 100, b);
        double p2 = getProbabilityOfA(a - 75, b-25);
        double p3 = getProbabilityOfA(a - 50, b-50);
        double p4 = getProbabilityOfA(a - 25, b-75);

        return 0.25 * (p1 + p2 + p3 + p4);
    }

    public double soupServings(int n) {
        int a = n;
        int b = n;

        return getProbabilityOfA(a, b);
    }
}





// Approach 2 - Recursion + Memoization (Gives MLE)
// T.C. - O(n * n)
// S.C. - O(n * n)
class Solution {
    public double getProbabilityOfA(int a, int b, double[][] dp){
        if(a <= 0 && b <= 0){
            return 0.5;
        }

        // if we keep this condition first, then the above condition
        // will never run and get returned when a <= 0 without checking 
        // for (a <= 0 && b <= 0)
        if(a <= 0){
            return 1.0;
        }

        if(b <= 0){
            return 0.0;
        }

        if(dp[a][b] != -1){
            return dp[a][b];
        }

        double p1 = getProbabilityOfA(a - 100, b, dp);
        double p2 = getProbabilityOfA(a - 75, b-25, dp);
        double p3 = getProbabilityOfA(a - 50, b-50, dp);
        double p4 = getProbabilityOfA(a - 25, b-75, dp);

        return dp[a][b] = 0.25 * (p1 + p2 + p3 + p4);
    }

    public double soupServings(int n) {
        int a = n;
        int b = n;

        double[][] dp = new double[n+1][n+1];

        for(double row[] : dp){
            Arrays.fill(row, -1);
        }

        return getProbabilityOfA(a, b, dp);
    }
}





// Approach 3 - Recursion + Memoization + cap
// T.C. - O(n * n)
// S.C. - O(n * n)
class Solution {
    public double getProbabilityOfA(int a, int b, double[][] dp){
        if(a <= 0 && b <= 0){
            return 0.5;
        }

        // if we keep this condition first, then the above condition
        // will never run and get returned when a <= 0 without checking 
        // for (a <= 0 && b <= 0)
        if(a <= 0){
            return 1.0;
        }

        if(b <= 0){
            return 0.0;
        }

        if(dp[a][b] != -1){
            return dp[a][b];
        }

        double p1 = getProbabilityOfA(a - 100, b, dp);
        double p2 = getProbabilityOfA(a - 75, b-25, dp);
        double p3 = getProbabilityOfA(a - 50, b-50, dp);
        double p4 = getProbabilityOfA(a - 25, b-75, dp);

        return dp[a][b] = 0.25 * (p1 + p2 + p3 + p4);
    }

    public double soupServings(int n) {
        int a = n;
        int b = n;

        /*
            when n is large, a get emptied first because of the 2nd base case
            because probability ~ 1
        */
        if(n > 5000){
            return 1;
        }

        double[][] dp = new double[n+1][n+1];

        for(double row[] : dp){
            Arrays.fill(row, -1);
        }

        return getProbabilityOfA(a, b, dp);
    }
}