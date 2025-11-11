// LeetCode - 474



// Approach 1 - Recursion (Brute Force - Gives TLE)
// T.C. - O(2^n * len); len = avg length of strings
// S.C. - O(strs.length)
class Solution {
    public int[] getCount(String str){
        int zero = 0;
        int one = 0;

        for(char ch : str.toCharArray()){
            if(ch == '0'){
                zero++;
            }
            else{
                one++;
            }
        }

        return new int[]{zero, one};
    }

    public int getMaxLength(int i, String[] strs, int m, int n, int zeroCount, int oneCount){
        if(i >= strs.length){
            return 0;
        }

        int take = 0;
        int[] curr = getCount(strs[i]);
        
        if((zeroCount + curr[0] <= m) && (oneCount + curr[1] <= n)){
            take = 1 + getMaxLength(i+1, strs, m, n, zeroCount + curr[0], oneCount + curr[1]);
        }

        int skip = getMaxLength(i+1, strs, m, n, zeroCount, oneCount);

        return Math.max(take, skip);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int zeroCount = 0;
        int oneCount = 0;

        return getMaxLength(0, strs, m, n, zeroCount, oneCount);
    }
}






// Approach 2 - Recursion + Memoization
// T.C. - O(L * m * n * len); L = length(strs), len = avg length of strings
// S.C. - O(L * m * n)
class Solution {
    public int[] getCount(String str){
        int zero = 0;
        int one = 0;

        for(char ch : str.toCharArray()){
            if(ch == '0'){
                zero++;
            }
            else{
                one++;
            }
        }

        return new int[]{zero, one};
    }

    public int getMaxLength(int i, String[] strs, int m, int n, int zeroCount, int oneCount, int[][][] dp){
        if(i >= strs.length){
            return 0;
        }

        if(dp[i][zeroCount][oneCount] != -1){
            return dp[i][zeroCount][oneCount];
        }

        int take = 0;
        int[] curr = getCount(strs[i]);
        
        if((zeroCount + curr[0] <= m) && (oneCount + curr[1] <= n)){
            take = 1 + getMaxLength(i+1, strs, m, n, zeroCount + curr[0], oneCount + curr[1], dp);
        }

        int skip = getMaxLength(i+1, strs, m, n, zeroCount, oneCount, dp);

        return dp[i][zeroCount][oneCount] = Math.max(take, skip);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        
        for(int[][] twoD : dp){
            for(int[] oneD : twoD){
                Arrays.fill(oneD, -1);
            }
        }

        int zeroCount = 0;
        int oneCount = 0;

        return getMaxLength(0, strs, m, n, zeroCount, oneCount, dp);
    }
}






// Approach 3 - Recursion + Memoization + Precomputation of strings
// T.C. - O(L * m * n * len); L = length(strs), len = avg length of strings
// S.C. - O(L * m * n)
class Solution {
    public int getMaxLength(int i, List<int[]> list, int m, int n, int[][][] dp){
        if(i >= list.size() || (m == 0 && n == 0)){
            return 0;
        }

        if(dp[i][m][n] != -1){
            return dp[i][m][n];
        }

        int take = 0;
        
        if((list.get(i)[0] <= m) && (list.get(i)[1] <= n)){
            take = 1 + getMaxLength(i+1, list, m - list.get(i)[0], n - list.get(i)[1], dp);
        }

        int skip = getMaxLength(i+1, list, m, n, dp);

        return dp[i][m][n] = Math.max(take, skip);
    }

    public int findMaxForm(String[] strs, int m, int n) {
        int L = strs.length;
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i<L; i++){
            int zero = 0;
            int one = 0;

            for(char ch : strs[i].toCharArray()){
                if(ch == '0'){
                    zero++;
                }
                else{
                    one++;
                }
            }

            list.add(new int[]{zero, one});
        }

        int[][][] dp = new int[strs.length + 1][m + 1][n + 1];
        
        for(int[][] twoD : dp){
            for(int[] oneD : twoD){
                Arrays.fill(oneD, -1);
            }
        }

        return getMaxLength(0, list, m, n, dp);
    }
}