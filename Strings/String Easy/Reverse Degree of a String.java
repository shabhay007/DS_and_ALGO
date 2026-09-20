// LeetCode - 3498



// Approach 1 - Simulation
// T.C. - O(n)
// S.C. - O(1)
class Solution {
    public int reverseDegree(String s) {
        int n = s.length();
        int degree = 0;

        for(int i = 1; i<=n; i++){
            char ch = s.charAt(i-1);

            degree += i * (26 - (ch - 'a'));
        }

        return degree;
    }
}