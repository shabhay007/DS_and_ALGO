// LeetCode Medium - 712



// Approach 1 - Recursion + Memoization
// T.C. - O(n1 * n2)
// S.C. - O(n1 * n2)
class Solution {
    public int getSum(int i, int j, String s1, String s2, int[][] dp){
        if(i == s1.length()){
            // and j is still not over, return the sum of all char's in s2
            int sumS2 = 0;

            while(j < s2.length()){
                sumS2 += s2.charAt(j);
                j++;
            }

            return sumS2;
        }

        if(j == s2.length()){
            // it means i is still not over, return the sum of all char's in s1
            int sumS1 = 0;

            while(i < s1.length()){
                sumS1 += s1.charAt(i);
                i++;
            }

            return sumS1;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int match = Integer.MAX_VALUE;
        if(s1.charAt(i) == s2.charAt(j)){
            match = getSum(i+1, j+1, s1, s2, dp);
        }

        int skipS1 = (s1.charAt(i)) + getSum(i+1, j, s1, s2, dp);
        int skipS2 = (s2.charAt(j)) + getSum(i, j+1, s1, s2, dp);

        return dp[i][j] = Math.min(match, Math.min(skipS1, skipS2));
    }

    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1+1][n2+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getSum(0, 0, s1, s2, dp);
    }
}





// Approach 2 - Recursion + Memoization (Slight Readable)
// T.C. - O(n1 * n2)
// S.C. - O(n1 * n2)
class Solution {
    public int getSum(int i, int j, String s1, String s2, int[][] dp){
        // terminal base case
        if(i >= s1.length() && j >= s2.length()){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        // base case 1
        if(i >= s1.length()){
            // and j is still not over, return the sum of all char's in s2
            return dp[i][j] = s2.charAt(j) + getSum(i, j+1, s1, s2, dp);
        }
        
        // base case 2
        if(j >= s2.length()){
            // it means i is still not over, return the sum of all char's in s1
            return dp[i][j] = s1.charAt(i) + getSum(i+1, j, s1, s2, dp);
        }

        int match = Integer.MAX_VALUE;
        if(s1.charAt(i) == s2.charAt(j)){
            match = getSum(i+1, j+1, s1, s2, dp);
        }

        int skipS1 = (s1.charAt(i)) + getSum(i+1, j, s1, s2, dp);
        int skipS2 = (s2.charAt(j)) + getSum(i, j+1, s1, s2, dp);

        return dp[i][j] = Math.min(match, Math.min(skipS1, skipS2));
    }

    public int minimumDeleteSum(String s1, String s2) {
        int n1 = s1.length();
        int n2 = s2.length();
        int[][] dp = new int[n1+1][n2+1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return getSum(0, 0, s1, s2, dp);
    }
}