// LeetCode - 3513



// Approach  1 - Bit Manipulation properties
// T.C. - O(log2(n))
// S.C. - O(1)
class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if(n == 1 || n == 2){
            return n;
        }

        // power of 2 >= n will be our solution
        int result = 1; // 2^0
        while(result <= n){
            result *= 2;
        }

        return result;
    }
}