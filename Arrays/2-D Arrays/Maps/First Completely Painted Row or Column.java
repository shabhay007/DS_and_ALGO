// LeetCode Medium - 2661


// Brute Force
// T.C. - O(k + k * (2k)); k = m * n
// S.C. - O(k)
class Solution {
    public boolean checkForRow(int[][] grid, int m, int n){
        for(int[] row : grid){
            boolean flag = true;

            for(int j = 0; j < n; j++){
                if(row[j] != -1){
                    flag = false;
                    break;
                }
            }

            if(flag){
                return true;
            }
        }

        return false;
    }

    public boolean checkForCol(int[][] grid, int m, int n){
        for(int col = 0; col < n; col++){
            boolean flag = true;

            for(int row = 0; row < m; row++){
                if(grid[row][col] != -1){
                    flag = false;
                    break;
                }
            }

            if(flag){
                return true;
            }
        }

        return false;
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        HashMap<Integer, int[]> indexMap = new HashMap<>();

        // mapping every element with the respective indices in the matrix
        for(int i = 0; i < m; i++){ // O(m * n)
            for(int j = 0; j < n; j++){
                indexMap.put(mat[i][j], new int[]{i, j});
            }
        }

        for(int i = 0; i < arr.length; i++){ // O(m * n)
            int[] position = indexMap.get(arr[i]);
            int x = position[0];
            int y = position[1];

            // painting the cell by marking
            mat[x][y] = -1;

            // checking if any row is completely painted or not
            boolean isRowFilled = checkForRow(mat, m, n); // O(m * n)
            if(isRowFilled){
                return i;
            }

            // checking if any column is completely painted or not
            boolean isColFilled = checkForCol(mat, m, n); // O(m * n)
            if(isColFilled){
                return i;
            }
        }

        return -1;
    }
}





// Better - Using Map and extra two arrays
// T.C. - O(2k); k = m * n
// S.C. - O((m * n) + m + n)
class Solution {
    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int arrLen = arr.length;

        HashMap<Integer, int[]> indexMap = new HashMap<>();

        for(int i = 0; i < m; i++){ // O(m * n)
            for(int j = 0; j < n; j++){
                indexMap.put(mat[i][j], new int[]{i, j});
            }
        }

        int[] row = new int[m];
        int[] col = new int[n];

        for(int i = 0; i < arrLen; i++){ // O(m * n)
            int[] currPosition = indexMap.get(arr[i]);
            int x = currPosition[0];
            int y = currPosition[1];

            // mark the painted cell
            mat[x][y] = -1; // Optional

            // row[x] = no of elements painted in that row
            // if no of elements painted in any row = n, as there are n elements in a row
            // means that row is fully painted
            row[x]++;
            if(row[x] == n){
                return i;
            }

            // col[y] = no of elements painted in that column
            // if no of elements painted in any col = m, as there are m elements in a column
            // means that col is fully painted
            col[y]++;
            if(col[y] == m){
                return i;
            }
        }

        return -1;
    }
}





// Optimal - Using Map
// T.C. - O(3(m * n))
// S.C. - O(m * n)
class Solution {
    public int getRowMinIndex(int[][] mat, int m, int n, HashMap<Integer, Integer> map){
        int rowMinIdx = Integer.MAX_VALUE;
        // checking each row one by one;
        for(int[] row : mat){
            int idxOfLastEleInArr = -1;

            for(int col = 0; col < n; col++){
                int idx = map.get(row[col]);
                idxOfLastEleInArr = Math.max(idxOfLastEleInArr, idx);
            }

            rowMinIdx = Math.min(rowMinIdx, idxOfLastEleInArr);
        }

        return rowMinIdx;
    }

    public int getColMinIndex(int[][] mat, int m, int n, HashMap<Integer, Integer> map){
        int colMinIdx = Integer.MAX_VALUE;
        
        // checking each column one by one
        for(int col = 0; col < n; col++){
            int idxOfLastEleInArr = -1;

            for(int row = 0; row < m; row++){
                int idx = map.get(mat[row][col]);
                idxOfLastEleInArr = Math.max(idxOfLastEleInArr, idx);
            }

            colMinIdx = Math.min(colMinIdx, idxOfLastEleInArr);
        }

        return colMinIdx;
    }

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int len = arr.length;

        // it will store index of elements present in arr
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < len; i++){ // O(m * n)
            map.put(arr[i], i);
        }

        // now checking, in which index in arr, 
        // the last element of the row or column is present
        int rowMinIndex = getRowMinIndex(mat, m, n, map); // O(m * n)
        int colMinIndex = getColMinIndex(mat, m, n, map); // O(m * n)

        // smallest index where row or column get full painted
        int minIndex = Math.min(rowMinIndex, colMinIndex);

        return minIndex;
    }
}