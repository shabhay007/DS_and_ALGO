// LeetCode Hard - 407


// Approach 1 - Heap, BFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        int n = heightMap[0].length;

        int[][] directions = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[m][n];

        // min heap -> {height, i, j}
        PriorityQueue<int[]> boundaryCells = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // putting boundaries into heap
        // putting left most and right most columns into heap i.e 0th & (n-1)th column
        for(int row = 0; row < m; row++){
            for(int col : new int[]{0, n-1}){
                boundaryCells.offer(new int[]{heightMap[row][col], row, col});

                // mark visited, the cell which is visited or pushed into the heap
                visited[row][col] = true;
            }
        }

        // putting top most and bottom most row into heap i.e 0th & (m-1)th row
        for(int row : new int[]{0, m-1}){
            for(int col = 0; col < n; col++){
                boundaryCells.offer(new int[]{heightMap[row][col], row, col});
                visited[row][col] = true;
            }
        }

        int volumeOfWater = 0;

        while(!boundaryCells.isEmpty()){
            int[] curr = boundaryCells.poll();
            int currHeight = curr[0];
            int i = curr[1];
            int j = curr[2];

            // find the neighbours
            for(int[] dir : directions){
                int new_i = i + dir[0];
                int new_j = j + dir[1];

                if(new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && !visited[new_i][new_j]){
                    volumeOfWater += Math.max(currHeight - heightMap[new_i][new_j], 0);
                    boundaryCells.offer(new int[]{Math.max(currHeight, heightMap[new_i][new_j]), new_i, new_j});
                    visited[new_i][new_j] = true;
                }
            }
        }

        return volumeOfWater;
    }
}