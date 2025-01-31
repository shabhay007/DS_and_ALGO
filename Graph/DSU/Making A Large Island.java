// LeetCode Hard - 827


// Approach 1 - DFS - Gives TLE
// T.C. - O(n^4)
// S.C. - O(n^2)
class Solution {
    public void DFS(int row, int col, int[][] grid, boolean[][] visited, int[] cellsVisited, int n){
        if(row >= n || row < 0 || col >= n || col < 0 || visited[row][col] || grid[row][col] == 0){
            return;
        }

        // marking the visited cell
        visited[row][col] = true;

        cellsVisited[0]++;

        DFS(row + 1, col, grid, visited, cellsVisited, n);
        DFS(row - 1, col, grid, visited, cellsVisited, n);
        DFS(row, col + 1, grid, visited, cellsVisited, n);
        DFS(row, col - 1, grid, visited, cellsVisited, n);
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int largestIsland = 0;
        boolean allLand = true;

        for(int row = 0; row < n; row++){ // O(n^2)
            for(int col = 0; col < n; col++){
                
                // we are calling DFS from 0 as we can convert one 0 to 1.
                if(grid[row][col] == 0){
                    allLand = false; // grid is not full of lands

                    // temporarily make the cell 1
                    grid[row][col] = 1;

                    boolean[][] visited = new boolean[n][n];
                    int[] cellsVisited = new int[1];

                    DFS(row, col, grid, visited, cellsVisited, n); // O(n^2)

                    int noOfIslands = cellsVisited[0]; // adding 1 as we called dfs from 0;

                    largestIsland = Math.max(largestIsland, noOfIslands);

                    // restore the changed cell, back to 0
                    grid[row][col] = 0;
                }
            }
        }

        return allLand ? n * n : largestIsland;
    }
}





// Approach 2 - DFS
// T.C. - O(n^2)
// S.C. - O(n^2)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int DFS(int row, int col, int[][] grid, boolean[][] visited, int n, int islandId){
        if(row >= n || row < 0 || col >= n || col < 0 || visited[row][col] || grid[row][col] == 0){
            return 0;
        }

        // assigning the id to the cell
        grid[row][col] = islandId;

        // marking the visited cell
        visited[row][col] = true;

        int size = 1;

        for(int[] dir : directions){
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            size += DFS(newRow, newCol, grid, visited, n, islandId);
        }

        return size;
    }

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int largestIsland = 0;

        boolean[][] visited = new boolean[n][n];

        // map to store id of island -> size of the island
        int islandId = 2; // as there 
        HashMap<Integer, Integer> islandMap = new HashMap<>();

        for(int row = 0; row<n; row++){
            for(int col = 0; col < n; col++){
                if(!visited[row][col] && grid[row][col] == 1){
                    int size = DFS(row, col, grid, visited, n, islandId);
                    largestIsland = Math.max(largestIsland, size);

                    // putting into map
                    islandMap.put(islandId, size);
                    islandId++; // incrementing the island id
                }
            }
        }


        for(int row = 0; row < n; row++){ // O(n^2)
            for(int col = 0; col < n; col++){
                if(grid[row][col] == 0){
                    // 
                    HashSet<Integer> idSet = new HashSet<>();

                    // asking the neighbors about their size
                    for(int[] dir : directions){
                        int newRow = row + dir[0];
                        int newCol = col + dir[1];

                        if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] != 0){
                            idSet.add(grid[newRow][newCol]); // adding the unique id's
                        }
                    }

                    // as one 0 is converted to 1
                    int overallSize = 1;

                    for(int id : idSet){
                        overallSize += islandMap.get(id);
                    }

                    largestIsland = Math.max(largestIsland, overallSize);
                }
            }
        }

        return largestIsland;
    }
}