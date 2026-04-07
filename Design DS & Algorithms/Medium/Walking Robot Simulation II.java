// LeetCode - 2069



// Approach 1 - Simulation (Gives TLE)
// T.C. - O(10^4 * num); num = 10^9
// S.C. - O(1)
class Robot {
    int m, n;
    int x = 0;
    int y = 0;

    // initially east
    int dir = 0; // E N W S -> 0, 1, 2, 3
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public Robot(int width, int height) {
        this.m = height;
        this.n = width;
    }
    
    public void step(int num) {
        for(int i = 0; i < num; i++){
            int nx = x + directions[dir][0];
            int ny = y + directions[dir][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                dir = (dir + 1) % 4;

                nx = x + directions[dir][0];
                ny = y + directions[dir][1];
            }

            x = nx;
            y = ny;
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        if(dir == 0){
            return "East";
        }
        else if(dir == 1){
            return "North";
        }
        else if(dir == 2){
            return "West";
        }
        else{
            return "South";
        }
    }
}







// Approach 2 - Simulation
// T.C. - O(10^4 * num) (but num is reduced to 10^4 by taking mod with cycle length)
// S.C. - O(1)
class Robot {
    int m, n;
    int x = 0;
    int y = 0;

    // initially east
    int dir = 0; // E N W S -> 0, 1, 2, 3
    int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public Robot(int width, int height) {
        this.m = height;
        this.n = width;
    }
    
    public void step(int num) {
        int cycle = 2 * (n + m - 2);
        
        if(num % cycle == 0 && num > 0){
            num = cycle;
        }
        else{
            num %= cycle;
        }

        for(int i = 0; i < num; i++){
            int nx = x + directions[dir][0];
            int ny = y + directions[dir][1];

            if(nx < 0 || nx >= n || ny < 0 || ny >= m){
                dir = (dir + 1) % 4;

                nx = x + directions[dir][0];
                ny = y + directions[dir][1];
            }

            x = nx;
            y = ny;
        }
    }
    
    public int[] getPos() {
        return new int[]{x, y};
    }
    
    public String getDir() {
        if(dir == 0){
            return "East";
        }
        else if(dir == 1){
            return "North";
        }
        else if(dir == 2){
            return "West";
        }
        else{
            return "South";
        }
    }
}