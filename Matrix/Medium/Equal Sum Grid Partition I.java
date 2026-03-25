// LeetCode - 3546



// Approach 1 - Prefix Sum
// T.C. - O(m * n)
// S.C. - O(m + n)
class Solution {
    public boolean canPartitionGrid(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        long total = 0;

        int[] row = new int[m];
        int[] col = new int[n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                total += grid[i][j];

                // row
                row[i] += grid[i][j];

                // col
                col[j] += grid[i][j];
            }
        }

        // sum is odd, will not be partitioned into two sections
        if(total % 2 != 0){
            return false;
        }

        // horizontal cut
        long upper = 0;
        for(int i = 0; i<m; i++){
            upper += row[i];

            if(upper == total - upper){
                return true;
            }
        }

        // vertical cut
        long left = 0;
        for(int i = 0; i<n; i++){
            left += col[i];

            if(left == total - left){
                return true;
            }
        }

        return false;
    }
}