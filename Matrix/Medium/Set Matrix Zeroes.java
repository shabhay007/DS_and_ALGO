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





// Approach 3 (Optimal)
// T.C. - O(2m.n + 2(m+n))
// S.C. - O(1)
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        boolean firstRowImpacted = false;
        boolean firstColImpacted = false;

        // check if first row is impacted or not
        for(int col = 0; col<n; col++){
            if(matrix[0][col] == 0){
                firstRowImpacted = true;
                break;
            }
        }

        // check if first col is impacted or not
        for(int row = 0; row<m; row++){
            if(matrix[row][0] == 0){
                firstColImpacted = true;
                break;
            }
        }

        // marking the potential cell in 1st row and col with 0
        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(matrix[i][j] == 0){
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }

        // now processing the matrix from (1, 1) to avoid conflicts
        for(int i = 1; i<m; i++){
            for(int j = 1; j<n; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0){
                    matrix[i][j] = 0;
                }
            }
        }

        // now transform 1st row, if it is impacted
        if(firstRowImpacted){
            for(int i = 0; i<n; i++){
                matrix[0][i] = 0;
            }
        }


        // now transform 1st col, if it is impacted
        if(firstColImpacted){
            for(int i = 0; i<m; i++){
                matrix[i][0] = 0;
            }
        }
    }
}