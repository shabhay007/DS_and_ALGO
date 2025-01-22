// LeetCode Medium - 542



// Approach 1 - Multi source BFS
// T.C. - O(2(m * n));
// S.C. - O(m * n)
class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;

        int[][] distanceMat = new int[m][n];

        // queue for multi source bfs
        Queue<int[]> multiSrcQ = new LinkedList<>();

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    distanceMat[i][j] = mat[i][j];
                    multiSrcQ.offer(new int[]{i, j}); // bfs with 0
                }
                else{
                    distanceMat[i][j] = -1;
                }
            }
        }

        // now performing bfs
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        while(!multiSrcQ.isEmpty()){
            int currLevelSize = multiSrcQ.size();

            for(int curr = 0; curr < currLevelSize; curr++){
                int[] src = multiSrcQ.poll();
                int i = src[0];
                int j = src[1];

                for(int[] dir : directions){
                    int new_i = i + dir[0];
                    int new_j = j + dir[1];

                    if(new_i >= 0 && new_i < m && new_j >= 0 && new_j < n && distanceMat[new_i][new_j] == -1){
                        distanceMat[new_i][new_j] = distanceMat[i][j] + 1;
                        multiSrcQ.offer(new int[]{new_i, new_j});
                    }
                }
            }
        }

        return distanceMat;
    }
}