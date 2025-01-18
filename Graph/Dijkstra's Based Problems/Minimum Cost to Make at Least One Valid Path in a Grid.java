// LeetCode Hard - 1368


// Brute Force - (Backtracking DFS to try all paths)
// T.C. - O(4^(m*n)); (i, j) -> (m, n) and 4 directions , all possibilities
// S.C. - O(m*n)
class Solution {
    // Right Left Down Up -> 1 2 3 4
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int m;
    int n;

    public int dfs(int i, int j, int[][] grid, boolean[][] visited, int cost){
        if(i == m-1 && j == n-1){
            return cost;
        }

        // mark visited
        visited[i][j] = true;

        // explore
        int minCost = Integer.MAX_VALUE;

        for(int dir = 0; dir<4; dir++){
            int new_i = i + directions[dir][0];
            int new_j = j + directions[dir][1];

            // check for out of bound
            if(new_i < m && new_i >= 0 && new_j < n && new_j >= 0 && !visited[new_i][new_j]){
                int orgDirection = grid[i][j];
                int dirCost = 0;

                if((orgDirection == 1 && dir != 0) || (orgDirection == 2 && dir != 1) ||
                (orgDirection == 3 && dir != 2) || (orgDirection == 4 && dir != 3)){
                    dirCost = 1;
                }

                int newCost = cost + dirCost;
                minCost = Math.min(minCost, dfs(new_i, new_j, grid, visited, newCost));
            }
        }

        // backtrack by marking the curr cell false
        visited[i][j] = false;

        return minCost;
    }

    public int minCost(int[][] grid) {
        m = grid.length;
        n = grid[0].length;

        boolean[][] visited = new boolean[m][n];
        int minCost = 0;

        // explore all paths by backtracking
        return dfs(0, 0, grid, visited, minCost);
    }
}





// Optimal - Graph, Dijkstra's
// T.C. - O(m*n + log(m*n))
// S.C. - O(m*n)
class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // Direction : Right Left Down Up -> 1 2 3 4
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        int[][] result = new int[m][n];
        for(int[] row : result){ // O(m * n)
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Min-heap priority queue: {cost, i, j}
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] p1, int[] p2){
                return p1[0] - p2[0];
            }
        });

        // cost is 0 to move from i = 0; to j = 0;
        pq.offer(new int[]{0, 0, 0});
        result[0][0] = 0; 

        // O(m × n × log(m × n))
        // Each cell can be added to the priority queue once, and heap operations take
        // O(log(m * n))
        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            
            int currCost = curr[0];
            int i = curr[1];
            int j = curr[2];

            for(int dir = 0; dir<4; dir++){
                int new_i = i + directions[dir][0];
                int new_j = j + directions[dir][1];

                if(new_i < m && new_i >= 0 && new_j < n && new_j >= 0){
                    int orgDirection = grid[i][j];
                    
                    // finding the direction cost
                    int dirCost = (orgDirection - 1 != dir) ? 1 : 0;
                    int newCost = currCost + dirCost;

                    // if found cheap path
                    if(newCost < result[new_i][new_j]){
                        result[new_i][new_j] = newCost;
                        pq.offer(new int[]{newCost, new_i, new_j});
                    }
                }
            }
        }

        // min cost to reach m-1, n-1
        return result[m-1][n-1];
    }
}