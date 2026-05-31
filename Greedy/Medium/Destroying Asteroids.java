// LeetCode - 2126



// Approach 1 - Sorting
// T.C. - O(nlog(n))
// S.C. - O(1)
class Solution {
    public boolean asteroidsDestroyed(int mass, int[] asteroids) {
        int n = asteroids.length;
        Arrays.sort(asteroids);

        long Mass = (long) mass;

        for(int m : asteroids){
            if(Mass >= m){
                Mass += m;
            }
            else{
                return false;
            }
        }

        return true;
    }
}