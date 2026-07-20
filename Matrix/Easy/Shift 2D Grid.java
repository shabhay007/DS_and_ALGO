// LeetCode - 1260



// Approach 1 - Matrix Manipulation
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int shift = k % (m * n);
        int[][] result = new int[m][n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                int idx = i * n + j; // linear index
                int newIdx = (idx + shift) % (m * n);
                int newRow = newIdx / n;
                int newCol = newIdx % n;

                result[newRow][newCol] = grid[i][j];
            }
        }

        return Arrays.stream(result).map(r -> Arrays.stream(r).boxed().toList()).toList();
    }
}