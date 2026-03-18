// LeetCode - 3070



// Approach 1 - Prefix Sum in Grid
// T.C. - O(m*n)
// S.C. - O(1)
class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        for(int row = 0; row<m; row++){
            for(int col = 0; col<n; col++){
                grid[row][col] = ((row > 0) ? grid[row-1][col] : 0)
                                    + ((col > 0) ? grid[row][col-1] : 0)
                                    + grid[row][col] 
                                    - ((row > 0 && col > 0) ? grid[row-1][col-1] : 0);
            }
        }

        int count = 0;
        for(int row = 0; row<m; row++){
            for(int col = 0; col<n; col++){
                if(grid[row][col] <= k){
                    count++;
                }
            }
        }

        return count;
    }
}






// Approach 2 - Matrix Prefix Sum
// T.C. - O(m*n)
// S.C. - O(1)
class Solution {
    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;

        for(int row = 0; row<m; row++){
            for(int col = 0; col<n; col++){
                if(row > 0){
                    grid[row][col] += grid[row-1][col];
                }

                if(col > 0){
                    grid[row][col] += grid[row][col-1];
                }

                if(row > 0 && col > 0){
                    grid[row][col] -= grid[row-1][col-1];
                }

                if(grid[row][col] <= k){
                    count++;
                }
                else{
                    break; // no need to move forward as sum will be greater
                }
            }
        }

        return count;
    }
}