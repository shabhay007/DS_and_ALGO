// LeetCode Hard - 2503


// Approach 1 - DFS (Gives TLE)
// T.C. - O(q.m.n)
// S.C. - O(m.n)
class Solution {
    public void dfs(int target, int i, int j, int[][] grid, int m, int n, int[][] directions, boolean[][] visited, int[] count){
        if(i < 0 || i >= m || j < 0 || j >= n || grid[i][j] >= target || visited[i][j]){
            return;
        }

        visited[i][j] = true;
        count[0]++;

        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            
            dfs(target, x, y, grid, m, n, directions, visited, count);
        }
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int q = queries.length;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[] result = new int[q];


        for(int i = 0; i<q; i++){
            boolean[][] visited = new boolean[m][n];
            int[] count = new int[1];

            dfs(queries[i], 0, 0, grid, m, n, directions, visited, count);
            result[i] = count[0];
        }

        return result;
    }
}




// Approach 2 - BFS (Gives TLE)
// T.C. - O(q.m.n)
// S.C. - O(2.m.n)
class Solution {
    public int bfs(int target, int[][] grid, int m, int n){
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited = new boolean[m][n];

        if(target <= grid[0][0]){
            return 0;
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        visited[0][0] = true;
        
        int count = 0;

        while(!q.isEmpty()){
            int[] curr = q.poll();
            count++;
            
            int i = curr[0];
            int j = curr[1];

            for(int[] dir : directions){
                int x = i + dir[0];
                int y = j + dir[1];

                if(x < m && x >= 0 && y < n && y >= 0 && !visited[x][y] && target > grid[x][y]){
                    visited[x][y] = true;
                    q.add(new int[]{x, y});
                }
            }
        }

        return count;
    }

    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int q = queries.length;

        
        int[] result = new int[q];


        for(int i = 0; i<q; i++){
            result[i] = bfs(queries[i], grid, m, n);
        }

        return result;
    }
}





// Approach 3 (Optimal) - Sorting + Heap
// T.C. - O(q + qlog(q) + q.(m.n)log(m.n))
// S.C. - O(q + m.n + m.n)
class Solution {
    public int[] maxPoints(int[][] grid, int[] queries) {
        int m = grid.length;
        int n = grid[0].length;
        int q = queries.length;

        // step 1 : storing query and respective index
        List<int[]> list = new ArrayList<>();

        for(int i = 0; i<q; i++){ // O(q)
            list.add(new int[]{queries[i], i});
        }

        // step 2 : sorting the list
        Collections.sort(list, (a, b) -> a[0] - b[0]); // O(q.log(q))

        // step 3 : taking a min heap for processing
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{grid[0][0], 0, 0});

        boolean[][] visited = new boolean[m][n];
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int count = 0;
        visited[0][0] = true;

        int[] result = new int[q];

        for(int i = 0; i<q; i++){ // O(q)
            int value = list.get(i)[0];
            int idx = list.get(i)[1];

            while(!pq.isEmpty() && pq.peek()[0] < value){ // O((m.n)log(m.n))
                int[] curr = pq.poll();
                int currRow = curr[1];
                int currCol = curr[2];
                count++;

                for(int[] dir : directions){
                    int x = currRow + dir[0];
                    int y = currCol + dir[1];

                    if(x >= 0 && x < m && y >= 0 && y < n && !visited[x][y]){
                        visited[x][y] = true;
                        pq.offer(new int[]{grid[x][y], x, y});
                    }
                }
            }

            result[idx] = count;
        }

        return result;
    }
}