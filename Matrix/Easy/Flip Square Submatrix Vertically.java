// LeetCode - 3643



// Approach 1 - Two Pointer + Traversal of k*k sub-matrix
// T.C. - O(k*k)
// S.C. - O(1)
class Solution {
    public int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        int i = 0;
        int j = k - 1;

        while (i < j) {
            for (int col = 0; col < k; col++) {
                int temp = grid[x + i][y + col];
                grid[x + i][y + col] = grid[x + j][y + col];
                grid[x + j][y + col] = temp;
            }

            i++;
            j--;
        }

        return grid;
    }
}