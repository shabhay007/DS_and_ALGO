// LeetCode - 3014



// Approach - Greedy + Simulation
// T.C. - O(26)
// S.C. - O(1)
class Solution {
    public int minimumPushes(String word) {
        int n = word.length();

        if(n <= 8){
            return n;
        }

        if(n <= 16){
            return 8 + 2 * (n - 8);
        }
        else if(n <= 24){
            return 8 + 16 + 3 * (n - 16);
        }
        else{
            return 8 + 16 + 24 + 4 * (n - 24);
        }
    }
}