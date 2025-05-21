// LeetCode - 73



// Approach 1 - Brute Force
// T.C. - O(2m.n + k(m+n))
// S.C. - O(m.n + k)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] grid = new int[m][n];

        List<int[]> coordinates = new ArrayList<>();

        for(int i = 0; i<m; i++){ // O(m.n)
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == 0){
                    coordinates.add(new int[]{i, j});
                }
            }
        }

        // now process the grid array
        for(int[] coordinate : coordinates){ // O(k.(m+n)); k = total 0's in matrix
            int x = coordinate[0];
            int y = coordinate[1];

            // filling the row
            for(int cell = 0; cell<n; cell++){
                grid[x][cell] = -1;
            }

            // filling the col
            for(int cell = 0; cell<m; cell++){
                grid[cell][y] = -1;
            }
        }

        // now copying the grid to matrix
        for(int i = 0; i<m; i++){ // O(m.n)
            for(int j = 0; j<n; j++){
                if(grid[i][j] == -1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}




// Approach 2 - Better
// T.C. - O(2m.n)
// S.C. - O(m+n)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] row = new int[n];
        int[] col = new int[m];

        for(int i = 0; i<m; i++){ // O(m.n)
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == 0){
                    row[j] = -1;
                    col[i] = -1;
                }
            }
        }

        // now process the matrix array
        // if any element in row is -1 then matrix[i][j] = 0
        // and vice versa
        for(int i = 0; i<m; i++){ // O(m.n)
            for(int j = 0; j<n; j++){
                if(row[j] == -1 || col[i] == -1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}