// LeetCode - 1266



// Approach 1 - Maths
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int time = 0;

        for(int i = 1; i<n; i++){
            int x1 = points[i-1][0];
            int y1 = points[i-1][1];

            int x2 = points[i][0];
            int y2 = points[i][1];
            
            // diagonal distances
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            if(dx > dy){
                time += dy + (dx - dy);
            }
            else{
                time += dx + (dy - dx);
            }
        }

        return time;
    }
}






// Approach 2 - Maths (Slight change in approach 1)
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int minTimeToVisitAllPoints(int[][] points) {
        int n = points.length;
        int time = 0;

        for(int i = 1; i<n; i++){
            int x1 = points[i-1][0];
            int y1 = points[i-1][1];

            int x2 = points[i][0];
            int y2 = points[i][1];
            
            // diagonal distances
            int dx = Math.abs(x2 - x1);
            int dy = Math.abs(y2 - y1);

            time += Math.min(dx, dy) + Math.abs(dx - dy);
        }

        return time;
    }
}