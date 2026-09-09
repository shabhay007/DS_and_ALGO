// LeetCode - 3871



// Approach 1 - Maths Simulation
// T.C. - O(1)
// S.C. - O(1)
class Solution {
    public long countCommas(long n) {
        if(n < 1000){
            return 0;
        }

        // 10^15 -> 1,000,000,000,000,000
        long result = 0L;

        // 1000 - 999,999 -> 1
        long i = 1000L;
        long j = Math.min(999999L, n);
        result = j - i + 1;

        if(j == n){
            return result;
        }

        // 1000000 - 999,999,999 -> 2
        i = 1000000L;
        j = Math.min(999999999L, n);
        result += 2 * (j - i + 1);

        if(j == n){
            return result;
        }

        // 1,000,000,000 - 999,999,999,999 -> 3
        i = 1000000000L;
        j = Math.min(999999999999L, n);
        result += 3 * (j - i + 1);

        if(j == n){
            return result;
        }

        // 1,000,000,000,000 - 999,999,999,999,999 -> 4
        i = 1000000000000L;
        j = Math.min(999999999999999L, n);
        result += 4 * (j - i + 1);

        if(j == n){
            return result;
        }
        
        // 1,000,000,000,000,000 -> 5
        return result + 5;
    }
}