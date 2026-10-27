// LeetCode - 3963



// Approach 1
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for(int i = 0; i<m; i++){
            for(int j = 0; j<n; j++){
                if(j == 0){
                    grid[i][j] = '.';
                }
                else if(i == m-1){
                    grid[i][j] = '.';
                }
                else{
                    grid[i][j] = '#';
                }
            }
        }

        String[] result = new String[m];
        int i = 0;

        for(char[] arr : grid){
            result[i] = new String(arr);
            i++;
        }

        return result;
    }
}