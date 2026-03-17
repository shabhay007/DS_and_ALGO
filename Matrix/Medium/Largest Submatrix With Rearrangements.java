// LeetCode - 1727



// Approach 1 - Flattening of Rows + Sorting
// T.C. - O(m * nlog(n))
// S.C. - O(m * n)
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int maxArea = 0;

        // flattening the rows
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j] == 1 && i > 0){
                    matrix[i][j] += matrix[i-1][j];
                }
            }

            List<Integer> curr = new ArrayList<>();
            for(int j = 0; j<n; j++){
                curr.add(matrix[i][j]);
            }

            Collections.sort(curr, (a, b) -> b - a);

            // finding max area
            for(int k = 0; k<n; k++){
                int height = curr.get(k);
                int base = k+1;

                maxArea = Math.max(maxArea, height * base);
            }
        }

        return maxArea;
    }
}






// Approach 2 - (Without modifying the given input)
// T.C : (m * nlogn)
// S.C : O(m*n)
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] prevRow = new int[n];
        int result = 0;

        for (int row = 0; row < m; row++) {
            int[] currRow = matrix[row].clone();
            for (int col = 0; col < n; col++) {
                if (currRow[col] != 0) {
                    currRow[col] += prevRow[col];
                }
            }

            int[] sortedRow = currRow.clone();
            Arrays.sort(sortedRow);
            for (int i = 0; i < n; i++) {
                int base   = (n - i);
                int height = sortedRow[i];
                result = Math.max(result, base*height);
            }

            prevRow = currRow;
        }

        return result;
    }
}






// Approach 3 - Without sorting
// T.C : (m * n)
// S.C : O(m*n)
class Solution {
    public int largestSubmatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<int[]> prevHeights = new ArrayList<>();
        int maxArea = 0;

        for (int row = 0; row < m; row++) {
            List<int[]> currHeights = new ArrayList<>();
            boolean[] visited = new boolean[n];

            for(int[] prev : prevHeights){
                int h = prev[0];
                int col = prev[1];

                if(matrix[row][col] == 1){
                    currHeights.add(new int[]{h+1, col});
                    visited[col] = true;
                }
            }

            // now processing rest of the cells
            for(int col = 0; col < n; col++){
                if(!visited[col] && matrix[row][col] == 1){
                    currHeights.add(new int[]{1, col});
                    visited[col] = true;
                }
            }

            // now processing area
            for(int i = 0; i<currHeights.size(); i++){
                int height = currHeights.get(i)[0];
                int base = i+1;
                maxArea = Math.max(maxArea, height * base);
            }

            prevHeights = currHeights;
        }

        return maxArea;
    }
}