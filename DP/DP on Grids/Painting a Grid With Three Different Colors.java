// LeetCode Hard - 1931



// Approach 1 - Recursion (Gives TLE)
// T.C. - O(exponential); O((3 * 2(m-1))^n * m)
// S.C. - O(n)
class Solution {
    static int mod = (int) 1e9 + 7;

    public void generateColumnStates(String curr, char prevCh, int rowsRemaining, int m, List<String> colStates){
        if(rowsRemaining == 0){
            colStates.add(curr);
            return;
        }

        for(char ch : new char[]{'R', 'G', 'B'}){
            if(prevCh == ch){
                continue;
            }

            generateColumnStates(curr + ch, ch, rowsRemaining-1, m, colStates);
        }
    }

    public int solve(int remainCols, int prevStateIdx, List<String> colStates, int m){
        if(remainCols == 0){ // we have filled all cols
            return 1;
        }

        int ways = 0;
        String prevState = colStates.get(prevStateIdx);

        for(int i = 0; i<colStates.size(); i++){
            String currState = colStates.get(i);
            boolean isValid = true;

            for(int j = 0; j<m; j++){ // iterating on curr and prev states
                if(prevState.charAt(j) == currState.charAt(j)){
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                ways = (ways + solve(remainCols - 1, i, colStates, m)) % mod;
            }
        }

        return ways;
    }

    public int colorTheGrid(int m, int n) {
        List<String> colStates = new ArrayList<>();

        // step 1 - Generate column states
        generateColumnStates("", '#', m, m, colStates);

        // step 2
        int result = 0;

        for(int i = 0; i<colStates.size(); i++){
            result = (result + solve(n-1, i, colStates, m)) % mod;
        }

        return result;
    }
}






// Approach 2 - Recursion + Memoization
//T.C : O(n * S * S * m), where S = total states i.e. 3 * 2^m-1
//S.C : O((n * S) + (S * m)) where n * S is because of memoization array t, and S * m is for storing columnStates
class Solution {
    static int mod = (int) 1e9 + 7;

    // Recursively generate all valid column colorings of height 'm'
    // such that no two vertically adjacent cells have the same color
    public void generateColumnStates(String curr, char prevCh, int rowsRemaining, int m, List<String> colStates){
        if(rowsRemaining == 0){
            colStates.add(curr);
            return;
        }

        for(char ch : new char[]{'R', 'G', 'B'}){
            if(prevCh == ch){
                continue;
            }

            generateColumnStates(curr + ch, ch, rowsRemaining-1, m, colStates);
        }
    }

    public int solve(int remainCols, int prevStateIdx, List<String> colStates, int m, int[][] dp){
        if(remainCols == 0){ // we have filled all cols
            return 1;
        }

        if(dp[remainCols][prevStateIdx] != -1){
            return dp[remainCols][prevStateIdx];
        }

        int ways = 0;
        String prevState = colStates.get(prevStateIdx);

        for(int i = 0; i<colStates.size(); i++){
            String currState = colStates.get(i);
            boolean isValid = true;

            for(int j = 0; j<m; j++){ // iterating on curr and prev states
                if(prevState.charAt(j) == currState.charAt(j)){
                    isValid = false;
                    break;
                }
            }

            if(isValid){
                ways = (ways + solve(remainCols - 1, i, colStates, m, dp)) % mod;
            }
        }

        return dp[remainCols][prevStateIdx] = ways;
    }

    public int colorTheGrid(int m, int n) {
        List<String> colStates = new ArrayList<>();

        // step 1 - Generate column states
        generateColumnStates("", '#', m, m, colStates);

        // step 2
        int result = 0;
        int[][] dp = new int[n+1][colStates.size() + 1];

        for(int[] row : dp){
            Arrays.fill(row, -1);
        }

        for(int i = 0; i<colStates.size(); i++){
            result = (result + solve(n-1, i, colStates, m, dp)) % mod;
        }

        return result;
    }
}