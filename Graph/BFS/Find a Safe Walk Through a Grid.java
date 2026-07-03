// LeetCode Medium - 3286



// Approach 1 - DFS + Backtracking
// T.C. - O(4^(m*n))
// S.C. - O(m * n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean solve(int r, int c, List<List<Integer>> grid, int health, boolean[][] visited, int m, int n){
        if(r < 0 || r >= m || c < 0 || c >= n || visited[r][c]){
            return false;
        }

        visited[r][c] = true;
        health -= grid.get(r).get(c);

        // if(grid.get(r).get(c) == 1){
        //     health -= 1;
        // }

        if(r == m-1 && c == n-1 && health >= 1){
            return true;
        }

        if(health <= 0){ // pruning
            visited[r][c] = false;
            return false;
        }

        for(int[] dir : directions){
            int x = dir[0] + r;
            int y = dir[1] + c;

            if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                if(solve(x, y, grid, health, visited, m, n)){
                    return true;
                }
            }
        }

        // backtracking
        visited[r][c] = false;
        health += grid.get(r).get(c);

        // if(grid.get(r).get(c) == 1){ // restoring health due to backtracking
        //     health += 1;
        // }


        return false;
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        boolean[][] visited = new boolean[m][n];

        return solve(0, 0, grid, health, visited, m, n);
    }
}






// Approach 2 -> 0-1 BFS
// T.C. - O(m * n)
// S.C. - O(m * n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        // will store min health to reach grid[i][j]
        int[][] result = new int[m][n];
        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE); // initializing with infinity
        }

        Deque<int[]> dq = new LinkedList<>();
        dq.offerFirst(new int[]{0, 0});
        result[0][0] = grid.get(0).get(0); // health req. from 0, 0 to reach 0, 0

        while(!dq.isEmpty()){
            int[] curr = dq.pollFirst();

            for(int[] dir : directions){
                int nr = curr[0] + dir[0];
                int nc = curr[1] + dir[1];

                if(nr < 0 || nr >= m || nc < 0 || nc >= n){
                    continue;
                }

                if(result[curr[0]][curr[1]] + grid.get(nr).get(nc) < result[nr][nc]){
                    result[nr][nc] = result[curr[0]][curr[1]] + grid.get(nr).get(nc);

                    // it ensures elements in sorted order
                    if(result[nr][nc] == 0){
                        dq.offerFirst(new int[]{nr, nc});
                    }
                    else{
                        dq.offerLast(new int[]{nr, nc});
                    }
                }
            }
        }

        return health - result[m-1][n-1] >= 1;
    }
}






// Approach 3 - Dijkstra's
// T.C. - O((m.n)*log(m.n))
// S.C. - O(m * n)
class Solution {
    int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        int[][] result = new int[m][n];
        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);

        result[0][0] = grid.get(0).get(0); // src (0, 0)
        pq.offer(new int[]{0, 0, result[0][0]});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int r = curr[0];
            int c = curr[1];
            int cost = curr[2];

            if(cost > result[r][c]){
                continue;
            }

            for(int[] dir : directions){
                int nr = r + dir[0];
                int nc = c + dir[1];

                if(nr < 0 || nr >= m || nc < 0 || nc >= n){
                    continue;
                }

                if(cost + grid.get(nr).get(nc) < result[nr][nc]){
                    result[nr][nc] = cost + grid.get(nr).get(nc);
                    pq.offer(new int[]{nr, nc, result[nr][nc]});
                }
            }
        }

        return health - result[m-1][n-1] >= 1;
    }
}