// LeetCode Hard - 2267



// Approach 1 - Recursion
// T.C. - O(2^n) // exponential -> n = m+n
// S.C. - O(n)
class Solution {
    int m;
    int n;

    public boolean isValid(String str){
        int open = 0;

        for(int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);

            if(ch == '('){
                open++;
            }
            else{
                open--;

                if(open < 0){
                    return false;
                }
            }
        }

        return open == 0;
    }

    public boolean solve(int i, int j, StringBuilder sb, char[][] grid){
        if(i >= m || j >= n){
            return false;
        }

        sb.append(grid[i][j]); // appending once

        if(i == m-1 && j == n-1){
            if(isValid(sb.toString())){
                return true;
            }

            sb.deleteCharAt(sb.length() - 1);
            return false;
        }

        if(solve(i, j+1, sb, grid)){
            return true;
        }
        
        if(solve(i+1, j, sb, grid)){
            return true;
        }

        sb.deleteCharAt(sb.length() - 1); // removing that character, when not valid

        return false;
    }

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        return solve(0, 0, new StringBuilder(), grid);
    }
}






// Approach 2 - Recursion
// T.C. - O(2^n) // exponential -> n = m+n
// S.C. - O(n)
class Solution {
    int m;
    int n;

    public boolean solve(int i, int j, char[][] grid, int open){
        if(i >= m || j >= n){
            return false;
        }

        // validating parentheses
        if(grid[i][j] == '('){
            open += 1;
        }
        else{
            open -= 1;
        }

        if(open < 0){
            return false;
        }

        if(i == m-1 && j == n-1){
            if(open == 0){
                return true;
            }

            return false;
        }

        if(solve(i, j+1, grid, open)){
            return true;
        }
        
        if(solve(i+1, j, grid, open)){
            return true;
        }

        return false;
    }

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        return solve(0, 0, grid, 0);
    }
}





// Approach 3 - DP (Memoization)
// T.C. - O(m * n * (m+n))
// S.C. - O(m * n * (m+n))
class Solution {
    int m;
    int n;

    public boolean solve(int i, int j, char[][] grid, int open, Boolean[][][] dp){
        if(i >= m || j >= n){
            return false;
        }

        // validating parentheses
        if(grid[i][j] == '('){
            open += 1;
        }
        else{
            open -= 1;
        }

        if(open < 0){
            return false;
        }

        if(dp[i][j][open] != null){
            return dp[i][j][open];
        }

        if(i == m-1 && j == n-1){
            if(open == 0){
                return true;
            }

            return false;
        }

        if(solve(i, j+1, grid, open, dp)){
            return dp[i][j][open] = true;
        }
        
        if(solve(i+1, j, grid, open, dp)){
            return dp[i][j][open] = true;
        }

        return dp[i][j][open] = false;
    }

    public boolean hasValidPath(char[][] grid) {
        this.m = grid.length;
        this.n = grid[0].length;

        Boolean[][][] dp = new Boolean[m][n][m+n];

        return solve(0, 0, grid, 0, dp);
    }
}