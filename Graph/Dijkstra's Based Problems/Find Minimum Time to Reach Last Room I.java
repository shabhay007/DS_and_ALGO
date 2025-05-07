// LeetCode - 3341



// Approach 1 - Dijkstra's Algorithm
// T.C. - O(n.m + (n.m)log(n.m))
// S.C. - O(2n.m)
class Solution {
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length;
        int m = moveTime[0].length;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int[][] result = new int[n][m];

        for(int[] row : result){
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // int[] -> {time, i, j}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        result[0][0] = 0; // source
        pq.offer(new int[]{0, 0, 0});

        while(!pq.isEmpty()){
            int[] curr = pq.poll();
            int currTime = curr[0];
            int i = curr[1];
            int j = curr[2];

            if(i == n-1 && j == m-1){
                return currTime;
            }

            for(int[] dir : directions){
                int new_i = i + dir[0];
                int new_j = j + dir[1];

                if(new_i < n && new_i >= 0 && new_j < m && new_j >= 0){
                    int waitTime = Math.max(moveTime[new_i][new_j] - currTime, 0);
                    int reachTime = currTime + waitTime + 1;

                    if(result[new_i][new_j] > reachTime){
                        result[new_i][new_j] = reachTime;
                        pq.offer(new int[]{reachTime, new_i, new_j});
                    }
                }
            }
        }

        return result[n-1][m-1];
    }
}