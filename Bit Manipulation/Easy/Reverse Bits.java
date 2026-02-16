// LeetCode - 190



// Approach 1 - Bit Manipulation
// T.C. - O(32) ~ O(1)
// S.C. - O(1)
class Solution {
    public int reverseBits(int n) {
        int result = 0;

        for(int i = 1; i <= 32; i++){
            result <<= 1;
            result = (result | (n & 1));
            n >>= 1;
        }

        return result;
    }
}