// LeetCode - 1914



// Approach 1 - Matrix Manipulation/Simulation
// T.c. - O(layer * m * n)
// S.C. - O(2 * (m + n)) ~ (m + n)
class Solution {
    public void rotate(List<Integer> list, int k){
        int size = list.size();
        int rotation = k % size;

        for(int r = 0; r<rotation; r++){
            int temp = list.get(size - 1);

            for(int i = size - 1; i > 0; i--){
                list.set(i, list.get(i-1));
            }

            list.set(0, temp);
        }
    }

    public void assign(List<Integer> list, int[][] grid, int top, int left, int bottom, int right){

        int k = 0;

        // left column
        for(int i = top; i <= bottom; i++){
            grid[i][left] = list.get(k++);
        }

        // bottom row
        for(int j = left + 1; j <= right; j++){
            grid[bottom][j] = list.get(k++);
        }

        // right column
        for(int i = bottom - 1; i >= top; i--){
            grid[i][right] = list.get(k++);
        }

        // top row
        for(int j = right - 1; j >= left + 1; j--){
            grid[top][j] = list.get(k++);
        }
    }

    public int[][] rotateGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        int layer = Math.min(m, n) / 2;
        int row = 0; 
        int col = 0;

        while(layer > 0){
            List<Integer> list = new ArrayList<>();
            int top = row;
            int bottom = m - 1 - row;
            int left = col;
            int right = n - 1 - col;

            // left column - top to bottom
            for(int i = top; i<=bottom; i++){
                list.add(grid[i][left]);
            }

            // bottom row - left to right
            for(int j = left+1; j<=right; j++){
                list.add(grid[bottom][j]);
            }

            // right column - bottom to top
            for(int i = bottom-1; i >= top; i--){
                list.add(grid[i][right]);
            }

            // top row - right to left
            for(int j = right - 1; j >= left+1; j--){
                list.add(grid[top][j]);
            }

            // rotating the list and reassigning back into grid
            rotate(list, k);
            assign(list, grid, top, left, bottom, right);

            row++;
            col++;
            layer--;
        }

        return grid;
    }
}