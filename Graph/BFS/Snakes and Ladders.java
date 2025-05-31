// LeetCode Medium - 909



// Approach 1 - BFS
// T.C. - O(n^2 * 6)
// S.C. - O(n^2)
class Solution {
    public int[] getCoordinate(int num, int n){
        /*
            row = num/n; col = num % n;
            This formula is for 0-based numbers 0, 1, 2 ...
            And here numbers are 1 based,
            So, we have to use row = num-1/n; col = num-1 % n;
        */

        int rt = (num - 1)/n; // row from top
        int rb = (n - 1) - (num - 1)/n; // row from bottom

        // col will never be from top or from bottom
        int col = (num - 1) % n;

        // if both odd or both even
        if((n % 2 == 1 && rb % 2 == 1) || (n % 2 == 0 && rb % 2 == 0)){
            col = (n-1) - col;
        }

        return new int[]{rb, col};
    }

    public int bfs(int[][] grid, int n){
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);

        boolean[][] visited = new boolean[n][n];
        visited[n-1][0] = true;

        int steps = 0;

        while(!q.isEmpty()){
            int size = q.size();

            while(size-- > 0){
                int curr = q.poll();

                if(curr == n*n){
                    return steps;
                }

                for(int i = 1; i <= 6; i++){
                    int next = curr + i;

                    if(next > n * n){ // out of board
                        break;
                    }
                    
                    int[] coordinate = getCoordinate(next, n);
                    int row = coordinate[0];
                    int col = coordinate[1];

                    if(visited[row][col]){
                        continue;
                    }

                    visited[row][col] = true;

                    // if the next coordinate has snake/ladder
                    // then push the sqare value where it will reach
                    if(grid[row][col] != -1){
                        q.offer(grid[row][col]);
                    }
                    else{
                        q.offer(next);
                    }
                }
            }

            steps++;
        }

        return -1;
    }

    public int snakesAndLadders(int[][] board) {
        int n = board.length;

        return bfs(board, n);
    }
}