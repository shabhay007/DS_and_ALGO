// LeetCode - 2929



// Approach 1 - Maths
// T.C. - O(min(n, limit))
// S.C. - O(1)
class Solution {
    public long distributeCandies(int n, int limit) {
        long ways = 0;

        int minCh1 = Math.max(0, n - 2*limit);
        int maxCh1 = Math.min(n, limit);

        for(int i = minCh1; i <= maxCh1; i++){
            int m = n - i; // for child 2 and 3

            int minCh2 = Math.max(0, m - limit);
            int maxCh2 = Math.min(m, limit);

            ways += maxCh2 - minCh2 + 1;
        }

        return ways;
    }
}