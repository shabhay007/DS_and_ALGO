// LeetCode - 2290 - Hard


// Approach 1 - Dijkstra's
class Solution {
    public int minimumObstacles(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        //result[i][j] stores the min obstacles count between src to destination
        int[][] result = new int[m][n];
        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        result[0][0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        pq.offer(new int[]{0, 0, 0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int wt = curr[0];
            int x = curr[1];
            int y = curr[2];

            for(int[] dir : directions){
                int new_x = x + dir[0];
                int new_y = y + dir[1];

                if(new_x < 0 || new_x >= m || new_y < 0 || new_y >= n){
                    continue;
                }

                int newWt = (grid[new_x][new_y] == 1) ? 1 : 0;

                if(wt + newWt < result[new_x][new_y]){
                    result[new_x][new_y] = wt + newWt;
                    pq.offer(new int[]{wt + newWt, new_x, new_y});
                }
            }
        }

        return result[m-1][n-1];
    }
}
