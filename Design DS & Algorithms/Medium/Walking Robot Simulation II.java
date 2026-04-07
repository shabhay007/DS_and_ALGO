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






// Approach 3 - Pre computing all position with directions robot can move + Simulation
// T.C. - O(height *+ width)
// S.C. - O(2 * height * width - 4)
class Robot {
    int idx = 0;
    boolean moved = false; // to handle 1 corner case
    List<int[]> list; // to store all boundary cells with directions

    public Robot(int width, int height) {
        this.list = new ArrayList<>();

        // Bottom row (left to right) -> East(0)
        for(int x = 0; x<width; x++){
            list.add(new int[]{x, 0, 0});
        }

        // Right col (bottom to top) -> North(1)
        for(int y = 1; y<height; y++){
            list.add(new int[]{width-1, y, 1});
        }

        // Top row (right to left) -> West(2)
        for(int x = width - 2; x >= 0; x--){
            list.add(new int[]{x, height - 1, 2});
        }

        // Left col (top to bottom) -> South(3)
        for(int y = height - 2; y > 0; y--){ // we'll handle (0) case separately
            list.add(new int[]{0, y, 3});
        }
        
        // list = {{0, 0, 0}, ....}
        // Making it south for handling corner case when it comes to (0, 0) after moving
        list.get(0)[2] = 3;
    }
    
    public void step(int num) {
        moved = true;
        idx = (idx + num) % list.size();
    }
    
    public int[] getPos() {
        return new int[]{list.get(idx)[0], list.get(idx)[1]};
    }
    
    public String getDir() {
        if(!moved){
            return "East";
        }

        int dir = list.get(idx)[2];

        if(dir == 0) return "East";
        else if(dir == 1) return "North";
        else if(dir == 2) return "West";
        else return "South";
    }
}