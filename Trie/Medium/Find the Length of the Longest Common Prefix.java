// LeetCode - 3043



// Approach 1 - Recursion
// T.C. - O(2^(m+n))
// S.C. - O(m+n)
class Solution {
    public int getLengthOfCommonPrefix(int n1, int n2){
        String s1 = String.valueOf(n1);
        String s2 = String.valueOf(n2);

        int len = Math.min(s1.length(), s2.length());
        int common = 0;

        for(int i = 0; i<len; i++){
            if(s1.charAt(i) == s2.charAt(i)){
                common++;
            }
            else{
                break;
            }
        }

        return common;
    }

    public int solve(int i, int j, int[] arr1, int m, int[] arr2, int n){
        if(i >= m || j >= n){
            return 0;
        }

        int currLength = getLengthOfCommonPrefix(arr1[i], arr2[j]);

        int pair1 = solve(i, j+1, arr1, m, arr2, n);
        int pair2 = solve(i+1, j, arr1, m, arr2, n);

        return Math.max(currLength, Math.max(pair1, pair2));
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;

        return solve(0, 0, arr1, m, arr2, n);
    }
}






// Approach 2 - DP (Memoization)
// T.C. - O(m*n)
// S.C. - O(m*n)
class Solution {
    public int getLengthOfCommonPrefix(int n1, int n2){
        String s1 = String.valueOf(n1);
        String s2 = String.valueOf(n2);

        int len = Math.min(s1.length(), s2.length());
        int common = 0;

        for(int i = 0; i<len; i++){
            if(s1.charAt(i) == s2.charAt(i)){
                common++;
            }
            else{
                break;
            }
        }

        return common;
    }

    public int solve(int i, int j, int[] arr1, int m, int[] arr2, int n, int[][] dp){
        if(i >= m || j >= n){
            return 0;
        }

        if(dp[i][j] != -1){
            return dp[i][j];
        }

        int currLength = getLengthOfCommonPrefix(arr1[i], arr2[j]);

        int pair1 = solve(i, j+1, arr1, m, arr2, n, dp);
        int pair2 = solve(i+1, j, arr1, m, arr2, n, dp);

        return dp[i][j] = Math.max(currLength, Math.max(pair1, pair2));
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        int m = arr1.length;
        int n = arr2.length;

        int[][] dp = new int[m][n];
        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        return solve(0, 0, arr1, m, arr2, n, dp);
    }
}





// Approach 3 - Set + Maths
// T.C. - O(mlog10D + nlog10D); D = avg. no. of digits
// S.C. - O(mlog10D)
class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        // Storing all the prefixes in Set
        Set<Integer> set = new HashSet<>();
        for(int num : arr1){
            while(!set.contains(num) && num > 0){
                set.add(num);
                num /= 10;
            }
        }

        // now processing
        int result = 0;
        for(int num : arr2){
            while(!set.contains(num) && num > 0){
                num /= 10;
            }

            //  Math.log10(num) + 1 = gives the remaining length
            if(num > 0){
                result = Math.max(result, (int) Math.log10(num) + 1);
            }
        }

        return result;
    }
}