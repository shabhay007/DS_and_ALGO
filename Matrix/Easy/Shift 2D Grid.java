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





// Approach 2 - Reverse Trick
// T.C : O(n), n = row*col
// S.C : O(1)
class Solution {
    public void reverse(int i, int j, int[][] grid, int col){
        while(i < j){
            int temp = grid[i/col][i%col];
            grid[i/col][i%col] = grid[j/col][j%col];
            grid[j/col][j%col] = temp;
            i++;
            j--;
        }
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n  = grid[0].length;
        int N = m * n;

        k = k % N;

        if(k != 0){
            reverse(0, N-1, grid, n);
            reverse(0, k-1, grid, n);
            reverse(k, N-1, grid, n); 
        }

        return Arrays.stream(grid).map(row -> Arrays.stream(row).boxed().toList()).toList();
    }
}