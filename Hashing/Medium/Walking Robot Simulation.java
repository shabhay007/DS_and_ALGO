// LeetCode - 874



// Approach 1 - Set + Simulation
// T.C. - O(n)
// S.C. - O(obstacle)
class Solution {
    public int robotSim(int[] commands, int[][] obstacles) {
        Set<String> set = new HashSet<>();
        for(int[] obs : obstacles){
            set.add(obs[0] + "-" + obs[1]);
        }

        int x = 0;
        int y = 0;
        int dir = 0; // N E W S -> 0, 1, 2, 3
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // N E W S
        int result = 0;

        for(int cmd : commands){
            if(cmd == -1){
                dir = (dir + 1) % 4;
                continue;
            }

            if(cmd == -2){
                dir = (dir - 1 + 4) % 4; // in case of -ve
                continue;
            }

            for(int i = 0; i<cmd; i++){
                int nx = x + directions[dir][0];
                int ny = y + directions[dir][1];

                if(set.contains(nx + "-" + ny)){
                    break;
                }
                
                x = nx;
                y = ny;

                result = Math.max(result, nx * nx + ny * ny);
            }
        }

        return result;
    }
}