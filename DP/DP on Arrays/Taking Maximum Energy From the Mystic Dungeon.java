// LeetCode - 3147



// Approach 1 - Brute Force (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(1)
class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxPower = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            int newPosition = i;
            int power = 0;

            while(newPosition < n){
                power += energy[newPosition];
                newPosition += k;
            }

            maxPower = Math.max(maxPower, power);
        }

        return maxPower;
    }
}





// Approach 2 - Recursion (Gives TLE)
// T.C. - O(n^2)
// S.C. - O(n)
class Solution {
    public int solve(int i, int[] energy, int k, int n){
        if(i >= n){
            return 0;
        }

        return energy[i] + solve(i+k, energy, k, n);
    }

    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxPower = Integer.MIN_VALUE;

        for(int i = 0; i<n; i++){
            maxPower = Math.max(maxPower, solve(i, energy, k, n));
        }

        return maxPower;
    }
}





// Approach 3 - Memoization
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int solve(int i, int[] energy, int k, int n, int[] dp){
        if(i >= n){
            return 0;
        }

        if(dp[i] != Integer.MIN_VALUE){
            return dp[i];
        }

        return dp[i] = energy[i] + solve(i+k, energy, k, n, dp);
    }

    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxPower = Integer.MIN_VALUE;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MIN_VALUE);

        for(int i = 0; i<n; i++){
            maxPower = Math.max(maxPower, solve(i, energy, k, n, dp));
        }

        return maxPower;
    }
}






// Approach 4 - Tabulation
// T.C. - O(n)
// S.C. - O(n)
class Solution {
    public int maximumEnergy(int[] energy, int k) {
        int n = energy.length;
        int maxPower = Integer.MIN_VALUE;

        int[] dp = new int[n];

        for(int i = n-1; i >= 0; i--){
            dp[i] = ((i+k < n) ? dp[i+k] : 0) + energy[i];
        }

        // finding the max element in the dp array
        for(int max : dp){
            maxPower = Math.max(maxPower, max);
        }

        return maxPower;
    }
}